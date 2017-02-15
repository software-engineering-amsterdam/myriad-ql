module UI.QLApp exposing (Model, Msg, init, update, view)

import AST exposing (Id, Label, Expression, ValueType(StringType, IntegerType, BooleanType), Form, FormItem)
import Html exposing (Html, div, text, h3, form, pre)
import Html.Attributes exposing (class)
import UI.Widget.Boolean as BooleanWidget
import UI.Widget.Integer as IntegerWidget
import UI.Widget.String as StringWidget
import UI.Widget.Base as BaseWidget
import UI.FormDslInput as FormDslInput
import Environment as Env exposing (Environment)
import Values exposing (Value)
import Dict
import FormUtil


type alias Model =
    { formDslInput : FormDslInput.Model
    , env : Environment
    }


type Msg
    = FormDslInputMsg FormDslInput.Msg
    | OnFieldChange String Value


init : Model
init =
    { formDslInput = FormDslInput.init
    , env = Env.empty
    }


update : Msg -> Model -> Model
update msg model =
    case msg of
        FormDslInputMsg subMsg ->
            { model | formDslInput = FormDslInput.update subMsg model.formDslInput }

        OnFieldChange fieldId newValue ->
            { model | env = Env.withFormValue fieldId newValue model.env }


view : Model -> Html Msg
view model =
    div [ class "container" ]
        [ h3 [] [ text "DSL Input" ]
        , FormDslInput.view model.formDslInput |> Html.map FormDslInputMsg
        , pre [] [ text <| String.join "\n" <| List.map toString <| Dict.toList model.env ]
        , FormDslInput.asForm model.formDslInput
            |> Maybe.map (viewForm model)
            |> Maybe.withDefault (div [] [])
        ]


viewForm : Model -> Form -> Html Msg
viewForm model formDsl =
    let
        visibleFields =
            FormUtil.activeFields model.env formDsl
    in
        form [] (List.map (viewField model) visibleFields)


viewField : Model -> ( Label, String, ValueType ) -> Html Msg
viewField model ( label, identifier, valueType ) =
    BaseWidget.container
        { identifier = identifier
        , label = label
        , env = model.env
        , onChange = OnFieldChange (identifier)
        }
    <|
        case valueType of
            StringType ->
                StringWidget.view

            BooleanType ->
                BooleanWidget.view

            IntegerType ->
                IntegerWidget.view
