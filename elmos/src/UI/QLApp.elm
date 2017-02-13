module UI.QLApp exposing (Model, Msg, init, update, view)

import AST exposing (ValueType(StringType, IntegerType, BooleanType), Form, FormItem(FieldItem))
import Html exposing (Html, div, form, text, h3, textarea, pre, hr)
import Html.Attributes exposing (class, style, defaultValue, rows, cols)
import Html.Events exposing (onInput)
import Parser.Parser as Parser
import UI.Widget.Boolean as BooleanWidget
import UI.Widget.Integer as IntegerWidget
import UI.Widget.String as StringWidget
import UI.Widget.Base as BaseWidget
import UI.FormData as FormData exposing (FormData)


type alias Model =
    { dslInput : String
    , parsedForm : Maybe Form
    }


type Msg
    = OnDslInput String


init : Model
init =
    { dslInput = ""
    , parsedForm = Nothing
    }
        |> update (OnDslInput """form taxOfficeExample {
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


baseFormData : FormData
baseFormData =
    FormData.empty
        |> FormData.withBoolean "hasSoldHouse" True


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
        , model.parsedForm
            |> Maybe.map viewForm
            |> Maybe.withDefault (div [] [])
        ]


viewForm : Form -> Html Msg
viewForm formDsl =
    Html.form []
        (List.map viewField (getFields formDsl))


viewField : AST.Field -> Html Msg
viewField field =
    BaseWidget.container { field = field, formData = baseFormData } <|
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
