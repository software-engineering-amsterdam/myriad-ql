module UI.QLSFormRenderer exposing (Model, Msg, init, update, view)

import Html exposing (Html, div, text, h3, pre, button, hr)
import Html.Attributes exposing (class, disabled)
import Html.Events exposing (onClick)
import UI.Widget.BooleanRadio as BooleanRadioWidget
import UI.Widget.BooleanDropdown as BooleanDropdownWidget
import UI.Widget.StringDropdown as StringDropdownWidget
import UI.Widget.StringRadio as StringRadioWidget
import UI.Widget.Boolean as BooleanWidget
import UI.Widget.Integer as IntegerWidget
import UI.Widget.String as StringWidget
import UI.Widget.Float as FloatWidget
import UI.Widget.Slider as SliderWidget
import UI.Widget.Spinbox as SpinboxWidget
import UI.Widget.Base as BaseWidget exposing (WidgetContext)
import QL.Environment as Env exposing (Environment)
import QL.Values exposing (Value)
import QL.AST exposing (Id, Label, Expression, ValueType(StringType, IntegerType, BooleanType, MoneyType), Form, FormItem)
import QLS.AST exposing (..)
import UI.FormUpdater as FormUpdater
import UI.Field as Field exposing (Field(Editable, Computed))
import UI.QLS.Pagination as Pagination exposing (Pagination)
import UI.StyleContext as StyleContext exposing (StyleContext)
import UI.Headings as Headings exposing (Heading)


type alias Model =
    { form : Form
    , styleSheet : StyleSheet
    , pagination : Maybe Pagination
    , env : Environment
    }


type Msg
    = OnFieldChange String Value
    | NextPage
    | PreviousPage


init : Form -> StyleSheet -> Model
init form styleSheet =
    { form = form
    , styleSheet = styleSheet
    , pagination = Pagination.init styleSheet.pages
    , env = Env.empty
    }


update : Msg -> Model -> Model
update msg model =
    case msg of
        OnFieldChange fieldId newValue ->
            { model | env = FormUpdater.updateValue fieldId newValue model.env model.form }

        NextPage ->
            { model | pagination = Maybe.map Pagination.next model.pagination }

        PreviousPage ->
            { model | pagination = Maybe.map Pagination.previous model.pagination }


view : Model -> Html Msg
view ({ form, env } as model) =
    let
        visibleFields =
            Field.activeFields env form
    in
        case model.pagination of
            Just pagination ->
                let
                    currentPage =
                        Pagination.current pagination
                in
                    div []
                        [ div [ class "row" ]
                            [ div [ class "col-md-6" ]
                                [ h3 [] [ text "Form: ", text (Tuple.first form.id) ]
                                , hr [] []
                                , Html.form []
                                    [ renderPage env visibleFields currentPage ]
                                , hr [] []
                                , renderPagination pagination
                                ]
                            , div [ class "col-md-6" ]
                                [ h3 [] [ text "Result" ]
                                , pre [] [ text <| toString env ]
                                ]
                            ]
                        ]

            Nothing ->
                div [] [ text "noCurrentPage TODO" ]


renderPage : Environment -> List Field -> Page -> Html Msg
renderPage env visibleFields (Page _ sections defaultValueConfigs) =
    div [] (List.filterMap (renderSection env Headings.init visibleFields (StyleContext.init defaultValueConfigs)) sections)


renderPagination : Pagination -> Html Msg
renderPagination pagination =
    div [ class "btn-group btn-group-justified" ]
        [ div [ class "btn-group" ]
            [ button [ class "btn btn-primary", onClick PreviousPage, disabled (Pagination.hasPrevious pagination) ] [ text "<< Previous" ] ]
        , div [ class "btn-group" ]
            [ button [ class "btn btn-primary", onClick NextPage, disabled (Pagination.hasNext pagination) ] [ text "Next >>" ] ]
        ]


renderSection : Environment -> Heading -> List Field -> StyleContext -> Section -> Maybe (Html Msg)
renderSection env heading visibleFields styleContext section =
    case section of
        SingleChildSection title sectionChild ->
            renderSectionChild env heading visibleFields styleContext sectionChild
                |> Maybe.map (List.singleton >> sectionFromLabelAndChildren heading title)

        MultiChildSection title sectionChilds configs ->
            List.filterMap (renderSectionChild env heading visibleFields (StyleContext.addDefaultConfigs configs styleContext)) sectionChilds
                |> nonEmptyList
                |> Maybe.map (sectionFromLabelAndChildren heading title)


nonEmptyList : List a -> Maybe (List a)
nonEmptyList x =
    case x of
        [] ->
            Nothing

        _ ->
            Just x


sectionFromLabelAndChildren : Heading -> String -> List (Html Msg) -> Html Msg
sectionFromLabelAndChildren heading title children =
    div []
        [ Headings.header heading [] [ text title ]
        , div [] children
        ]


renderSectionChild : Environment -> Heading -> List Field -> StyleContext -> SectionChild -> Maybe (Html Msg)
renderSectionChild env heading visibleFields styleContext sectionChild =
    case sectionChild of
        SubSection subSection ->
            renderSection env (Headings.deeper heading) visibleFields styleContext subSection

        Field (Question ( name, _ )) ->
            Field.visibleFieldForName name visibleFields
                |> Maybe.map (renderField env styleContext)

        Field (ConfiguredQuestion ( name, _ ) fieldConfig) ->
            Field.visibleFieldForName name visibleFields
                |> Maybe.map
                    (\field ->
                        renderField env (StyleContext.addValueTypeConfig (Field.fieldValueType field) fieldConfig styleContext) field
                    )


renderField : Environment -> StyleContext -> Field -> Html Msg
renderField env styleContext field =
    let
        valueType =
            Field.fieldValueType field

        pair =
            StyleContext.getForValueType valueType styleContext
    in
        viewField valueType env field pair


viewField : ValueType -> Environment -> Field -> ( Maybe Widget, List Style ) -> Html Msg
viewField valueType env field ( maybeWidget, styles ) =
    BaseWidget.container (visibleFieldWidgetConfig env styles field) <|
        case maybeWidget of
            Just w ->
                asRenderable w valueType

            Nothing ->
                case valueType of
                    StringType ->
                        StringWidget.view

                    BooleanType ->
                        BooleanWidget.view

                    IntegerType ->
                        IntegerWidget.view

                    MoneyType ->
                        FloatWidget.view


asRenderable : Widget -> ValueType -> (WidgetContext Msg -> Html Msg)
asRenderable widget valueType =
    case widget of
        Spinbox ->
            SpinboxWidget.view

        Radio labels ->
            radioWidgetRendererForValueType valueType labels

        Checkbox ->
            BooleanWidget.view

        Text ->
            textWidgetRendererForValueType valueType
                |> Maybe.withDefault StringWidget.view

        Slider args ->
            SliderWidget.view (sliderArgsAsProperties args)

        QLS.AST.Dropdown values ->
            dropdownWidgetRendererForValueType valueType values


sliderArgsAsProperties : SliderArgs -> SliderWidget.SliderProperties
sliderArgsAsProperties sliderArgs =
    case sliderArgs of
        SliderMax x ->
            { min = 0, max = x }

        SliderMinMax x y ->
            { min = x, max = y }


radioWidgetRendererForValueType : ValueType -> List String -> WidgetContext Msg -> Html Msg
radioWidgetRendererForValueType valueType labels =
    case valueType of
        StringType ->
            flip StringRadioWidget.view labels

        BooleanType ->
            flip BooleanRadioWidget.view labels

        _ ->
            Debug.crash "It is not possible to render a radio widget"


dropdownWidgetRendererForValueType : ValueType -> List String -> WidgetContext Msg -> Html Msg
dropdownWidgetRendererForValueType valueType labels =
    case valueType of
        StringType ->
            flip StringDropdownWidget.view labels

        BooleanType ->
            flip BooleanDropdownWidget.view labels

        _ ->
            Debug.crash "It is not possible to render a radio widget"


textWidgetRendererForValueType : ValueType -> Maybe (WidgetContext Msg -> Html Msg)
textWidgetRendererForValueType valueType =
    case valueType of
        StringType ->
            Just StringWidget.view

        BooleanType ->
            Nothing

        IntegerType ->
            Just IntegerWidget.view

        MoneyType ->
            Just FloatWidget.view


visibleFieldWidgetConfig : Environment -> List Style -> Field -> WidgetContext Msg
visibleFieldWidgetConfig env styles field =
    case field of
        Editable label identifier _ ->
            { identifier = identifier
            , label = label
            , env = env
            , onChange = OnFieldChange identifier
            , editable = True
            , style = List.map styleAsPair styles
            }

        Computed label identifier _ _ ->
            { identifier = identifier
            , label = label
            , env = env
            , onChange = OnFieldChange identifier
            , editable = False
            , style = List.map styleAsPair styles
            }


styleAsPair : Style -> ( String, String )
styleAsPair x =
    case x of
        Width n ->
            ( "width", toString n ++ "px" )

        Font font ->
            ( "font-family", font )

        FontSize fontSize ->
            ( "font-size", toString fontSize ++ "px" )

        Color c ->
            ( "color", c )
