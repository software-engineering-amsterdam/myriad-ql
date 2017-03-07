module UI.QLSFormRenderer exposing (Model, Msg, init, update, view)

import Html exposing (Html, div, text, h3, pre, b)
import Html.Attributes exposing (class)
import List.Extra as List
import UI.Widget.Boolean as BooleanWidget
import UI.Widget.Integer as IntegerWidget
import UI.Widget.String as StringWidget
import UI.Widget.Float as FloatWidget
import UI.Widget.Base as BaseWidget exposing (WidgetContext)
import QL.Environment as Env exposing (Environment)
import QL.Values exposing (Value)
import QL.AST exposing (Id, Label, Expression, ValueType(StringType, IntegerType, BooleanType, MoneyType), Form, FormItem)
import QLS.AST exposing (..)
import UI.FormUpdater as FormUpdater
import UI.Field as Field exposing (Field(Editable, Computed))
import UI.QLS.Pagination as Pagination exposing (Pagination)


type alias Model =
    { form : Form
    , styleSheet : StyleSheet
    , pagination : Maybe Pagination
    , env : Environment
    }


type Msg
    = OnFieldChange String Value


init : Form -> StyleSheet -> Model
init form styleSheet =
    { form = form
    , styleSheet = styleSheet
    , pagination = Pagination.init (styleSheet.pages)
    , env = Env.empty
    }


update : Msg -> Model -> Model
update msg model =
    case msg of
        OnFieldChange fieldId newValue ->
            { model | env = FormUpdater.updateValue fieldId newValue model.env model.form }


view : Model -> Html Msg
view { form, styleSheet, pagination, env } =
    let
        currentPage =
            Maybe.map (Pagination.current) pagination

        visibleFields =
            Field.activeFields env form
    in
        case currentPage of
            Just page ->
                div [ class "row" ]
                    [ div [ class "col-md-6" ]
                        [ h3 [] [ text "Form: ", text (Tuple.first form.id) ]
                        , Html.form []
                            [ (renderPage env visibleFields page) ]
                        ]
                    , div [ class "col-md-6" ]
                        [ h3 [] [ text "Result" ]
                        , pre [] [ text <| toString env ]
                        ]
                    ]

            Nothing ->
                div [] [ text "noCurrentPage TODO" ]


renderPage : Environment -> List Field -> Page -> Html Msg
renderPage env visibleFields (Page title sections defaultValueConfig) =
    div []
        [ h3 [] [ text title ]
        , div [] (List.map (renderSection env visibleFields defaultValueConfig) sections)
        ]


renderSection : Environment -> List Field -> List DefaultValueConfig -> Section -> Html Msg
renderSection env visibleFields defaultValueConfigs section =
    case section of
        SingleChildSection title sectionChild ->
            div []
                [ h3 [] [ text title ]
                , renderSectionChild env visibleFields defaultValueConfigs sectionChild
                ]

        MultiChildSection title sectionChilds ->
            div []
                [ h3 [] [ text title ]
                , div [] (List.map (renderSectionChild env visibleFields defaultValueConfigs) sectionChilds)
                ]


renderSectionChild : Environment -> List Field -> List DefaultValueConfig -> SectionChild -> Html Msg
renderSectionChild env visibleFields defaultValueConfigs sectionChild =
    case sectionChild of
        SubSection subSection ->
            renderSection env visibleFields defaultValueConfigs subSection

        Field (Question ( name, _ )) ->
            renderField env visibleFields defaultValueConfigs name

        Field (ConfiguredQuestion ( name, _ ) fieldConfig) ->
            -- TODO use the fieldConfig for rendering
            renderField env visibleFields defaultValueConfigs name

        Config _ ->
            h3 [] [ text "TODO fix config" ]


renderField : Environment -> List Field -> List DefaultValueConfig -> String -> Html Msg
renderField env visibleFields defaultValueConfigs name =
    case List.find (\field -> name == Field.name field) visibleFields of
        Nothing ->
            div [] [ text "field not visible" ]

        Just field ->
            viewField env field


viewField : Environment -> Field -> Html Msg
viewField env field =
    BaseWidget.container (visibleFieldWidgetConfig env field) <|
        case Field.fieldValueType field of
            StringType ->
                StringWidget.view

            BooleanType ->
                BooleanWidget.view

            IntegerType ->
                IntegerWidget.view

            MoneyType ->
                FloatWidget.view


visibleFieldWidgetConfig : Environment -> Field -> WidgetContext Msg
visibleFieldWidgetConfig env field =
    case field of
        Editable label identifier _ ->
            { identifier = identifier
            , label = label
            , env = env
            , onChange = OnFieldChange identifier
            , editable = True
            }

        Computed label identifier _ _ ->
            { identifier = identifier
            , label = label
            , env = env
            , onChange = OnFieldChange identifier
            , editable = False
            }
