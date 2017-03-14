module UI.QLFormRenderer exposing (Model, Msg, init, update, view)

import Html exposing (Html, div, text, h3, pre)
import Html.Attributes exposing (class)
import UI.Widget.Boolean as BooleanWidget
import UI.Widget.Integer as IntegerWidget
import UI.Widget.String as StringWidget
import UI.Widget.Float as FloatWidget
import UI.Widget.Base as BaseWidget exposing (WidgetContext)
import QL.Environment as Env exposing (Environment)
import QL.Values exposing (Value)
import QL.AST exposing (Id, Label, Expression, ValueType(StringType, IntegerType, BooleanType, MoneyType), Form, FormItem)
import UI.FormUpdater as FormUpdater
import UI.Field as Field exposing (Field(Editable, Computed))


type alias Model =
    { form : Form
    , env : Environment
    }


type Msg
    = OnFieldChange String Value


init : Form -> Model
init form =
    { form = form
    , env = Env.empty
    }


update : Msg -> Model -> Model
update msg model =
    case msg of
        OnFieldChange fieldId newValue ->
            { model | env = FormUpdater.updateValue fieldId newValue model.env model.form }


view : Model -> Html Msg
view { env, form } =
    let
        visibleFields =
            Field.activeFields env form
    in
        div
            [ class "row" ]
            [ div [ class "col-md-6" ]
                [ h3 [] [ text "Form: ", text (Tuple.first form.id) ]
                , Html.form []
                    (List.map (viewField env) visibleFields)
                ]
            , div [ class "col-md-6" ]
                [ h3 [] [ text "Result" ]
                , pre [] [ text <| toString env ]
                ]
            ]


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
