module UI.QLApp exposing (Model, Msg, init, update, view)

import AST exposing (ValueType(StringType, IntegerType, BooleanType), Form, FormItem)
import Html exposing (Html, div, text, h3, form, textarea, pre, hr)
import Html.Attributes exposing (class, style, defaultValue, rows, cols)
import Html.Events exposing (onInput)
import Parser.Parser as Parser
import UI.Widget.Boolean as BooleanWidget
import UI.Widget.Integer as IntegerWidget
import UI.Widget.String as StringWidget
import UI.Widget.Base as BaseWidget
import UI.FormData as FormData exposing (FormData)
import Values exposing (Value)
import Dict


type alias Model =
    { dslInput : String
    , parsedForm : Maybe Form
    , formData : FormData
    }


type Msg
    = OnDslInput String
    | OnFieldChange String Value


init : Model
init =
    { dslInput = ""
    , parsedForm = Nothing
    , formData = FormData.empty
    }
        |> update (OnDslInput """form taxOfficeExample {
  "Name?"
    name : string

  "Name 2?"
    name : string

  "Age?"
    age : integer
  "Age2?"
    age : integer

  "Wallet"
    wallet : money

  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean

  if (hasSoldHouse) {
    "What was the selling price?"
      sellingPrice: money
    "Private debts for the sold house:"
      privateDebt: money
    "Value residue:"
      valueResidue: money =
        (sellingPrice - privateDebt)
  }

}""")


update : Msg -> Model -> Model
update msg model =
    case msg of
        OnDslInput newDslInput ->
            let
                parsedForm =
                    Parser.parse newDslInput
            in
                { model
                    | dslInput = newDslInput
                    , parsedForm = parsedForm
                }

        OnFieldChange fieldId newValue ->
            { model | formData = FormData.withFormValue fieldId newValue model.formData }


view : Model -> Html Msg
view model =
    div [ class "container" ]
        [ h3 [] [ text "DSL Input" ]
        , Html.form [ class "form" ]
            [ textarea
                [ defaultValue model.dslInput
                , rows 24
                , cols 80
                , class "form-control"
                , style [ ( "width", "100%" ), ( "resize", "none" ), ( "font-family", "courier" ) ]
                , onInput OnDslInput
                ]
                []
            ]
        , hr [] []
        , pre [] [ text <| toString model.parsedForm ]
        , pre [] [ text <| String.join "\n" <| List.map toString <| Dict.toList model.formData ]
        , model.parsedForm
            |> Maybe.map (viewForm model)
            |> Maybe.withDefault (div [] [])
        ]


viewForm : Model -> Form -> Html Msg
viewForm model formDsl =
    form []
        (List.map (viewField model) (getFields formDsl))


viewField : Model -> AST.Field -> Html Msg
viewField model field =
    BaseWidget.container
        { field = field
        , formData = model.formData
        , onChange = OnFieldChange field.id
        }
    <|
        case field.valueType of
            StringType ->
                StringWidget.view

            BooleanType ->
                BooleanWidget.view

            IntegerType ->
                IntegerWidget.view


getFields : Form -> List AST.Field
getFields =
    .items >> getFieldsForItems


getFieldsForItem : FormItem -> List AST.Field
getFieldsForItem item =
    case item of
        AST.FieldItem field ->
            [ field ]

        AST.IfItem { thenBranch, elseBranch } ->
            List.concat
                [ getFieldsForItems thenBranch
                , getFieldsForItems elseBranch
                ]


getFieldsForItems : List FormItem -> List AST.Field
getFieldsForItems =
    List.concatMap getFieldsForItem
