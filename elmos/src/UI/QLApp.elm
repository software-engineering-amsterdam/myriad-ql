module UI.QLApp exposing (Model, Msg, init, update, view)

import AST exposing (Id, Label, Expression, ValueType(StringType, IntegerType, BooleanType, MoneyType), Form, FormItem)
import Html exposing (Html, div, text, h3, form, pre, ul, li, a)
import Html.Events exposing (onClick)
import Html.Attributes exposing (class, attribute)
import UI.Widget.Boolean as BooleanWidget
import UI.Widget.Integer as IntegerWidget
import UI.Widget.String as StringWidget
import UI.Widget.Float as FloatWidget
import UI.Widget.Base as BaseWidget exposing (WidgetContext)
import UI.FormDslInput as FormDslInput
import Environment as Env exposing (Environment)
import Values exposing (Value)
import Dict
import FormUtil exposing (VisibleField(Editable, Computed))


type Tab
    = DslInput
    | Preview


type alias Model =
    { formDslInput : FormDslInput.Model
    , env : Environment
    , activeTab : Tab
    }


type Msg
    = FormDslInputMsg FormDslInput.Msg
    | OnFieldChange String Value
    | ChangeTab Tab


init : Model
init =
    { formDslInput = FormDslInput.init
    , env = Env.empty
    , activeTab = DslInput
    }


update : Msg -> Model -> Model
update msg model =
    case msg of
        ChangeTab newTab ->
            { model | activeTab = newTab }

        FormDslInputMsg subMsg ->
            { model | formDslInput = FormDslInput.update subMsg model.formDslInput }

        OnFieldChange fieldId newValue ->
            { model
                | env =
                    FormDslInput.asForm model.formDslInput
                        |> Maybe.map (FormUtil.updateValue fieldId newValue model.env)
                        |> Maybe.withDefault model.env
            }


view : Model -> Html Msg
view model =
    div [ class "container" ]
        [ tabMenu model.activeTab
        , case model.activeTab of
            DslInput ->
                div []
                    [ h3 [] [ text "DSL Input" ]
                    , FormDslInput.view model.formDslInput |> Html.map FormDslInputMsg
                    ]

            Preview ->
                div []
                    [ pre [] [ text <| String.join "\n" <| List.map toString <| Dict.toList model.env ]
                    , FormDslInput.asForm model.formDslInput
                        |> Maybe.map (viewForm model)
                        |> Maybe.withDefault (div [] [])
                    ]
        ]


tabMenu : Tab -> Html Msg
tabMenu currentlyActive =
    ul
        [ class "nav nav-tabs" ]
        (List.map
            (\( tab, name ) ->
                tabItem (currentlyActive == tab) tab name
            )
            availableTabItems
        )


availableTabItems =
    [ ( DslInput, "Dsl Input" )
    , ( Preview, "Preview" )
    ]


tabItem : Bool -> Tab -> String -> Html Msg
tabItem isActive goTo name =
    li
        [ attribute "role" "presentation"
        , class
            (if isActive then
                "active"
             else
                ""
            )
        ]
        [ a
            [ onClick (ChangeTab goTo) ]
            [ text name ]
        ]


viewForm : Model -> Form -> Html Msg
viewForm model formDsl =
    let
        visibleFields =
            FormUtil.activeFields model.env formDsl
    in
        form [] (List.map (viewField model) visibleFields)


viewField : Model -> VisibleField -> Html Msg
viewField model field =
    BaseWidget.container (visibleFieldWidgetConfig model.env field) <|
        case FormUtil.fieldValueType field of
            StringType ->
                StringWidget.view

            BooleanType ->
                BooleanWidget.view

            IntegerType ->
                IntegerWidget.view

            MoneyType ->
                FloatWidget.view


visibleFieldWidgetConfig : Environment -> VisibleField -> WidgetContext Msg
visibleFieldWidgetConfig env field =
    case field of
        Editable label identifier _ ->
            { identifier = identifier
            , label = label
            , env = env
            , onChange = OnFieldChange identifier
            , editable = True
            }

        Computed label identifier valuedType _ ->
            { identifier = identifier
            , label = label
            , env = env
            , onChange = OnFieldChange identifier
            , editable = False
            }
