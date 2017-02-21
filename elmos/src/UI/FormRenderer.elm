module UI.FormRenderer exposing (..)

import Html exposing (Html, form, div, text, pre, hr)
import Dict
import UI.Widget.Boolean as BooleanWidget
import UI.Widget.Integer as IntegerWidget
import UI.Widget.String as StringWidget
import UI.Widget.Float as FloatWidget
import UI.Widget.Base as BaseWidget exposing (WidgetContext)
import Environment as Env exposing (Environment)
import Values exposing (Value)
import AST exposing (Id, Label, Expression, ValueType(StringType, IntegerType, BooleanType, MoneyType), Form, FormItem)
import FormUtil exposing (VisibleField(Editable, Computed))


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
            { model
                | env =
                    FormUtil.updateValue fieldId newValue model.env model.form
            }


view : Model -> Html Msg
view model =
    let
        visibleFields =
            FormUtil.activeFields model.env model.form
    in
        div
            []
            [ pre [] [ text <| String.join "\n" <| List.map toString <| Dict.toList model.env ]
            , hr [] []
            , form []
                (List.map (viewField model) visibleFields)
            ]


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
