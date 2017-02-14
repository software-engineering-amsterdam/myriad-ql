module UI.QLApp exposing (Model, Msg, init, update, view)

import AST exposing (Id, Label, Expression, ValueType(StringType, IntegerType, BooleanType), Form, FormItem(Field, ComputedField, IfThen, IfThenElse))
import Html exposing (Html, div, text, h3, form, textarea, pre, hr, ul, li, p)
import Html.Attributes exposing (class, style, defaultValue, rows, cols)
import Html.Events exposing (onInput)
import Parser.Parser as Parser
import UI.Widget.Boolean as BooleanWidget
import UI.Widget.Integer as IntegerWidget
import UI.Widget.String as StringWidget
import UI.Widget.Base as BaseWidget
import Environment exposing (FormData)
import Values exposing (Value)
import Evaluator
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
        , viewExpressions model
        ]


viewExpressions : Model -> Html msg
viewExpressions model =
    let
        exprs =
            model.parsedForm
                |> Maybe.map (.items >> List.concatMap getExpressions)
                |> Maybe.withDefault []
    in
        div []
            [ ul []
                (List.map
                    (\item ->
                        li []
                            [ p [] [ text <| toString item ]
                            , p [] [ text <| toString <| Evaluator.evaluate model.formData item ]
                            ]
                    )
                    exprs
                )
            ]


viewForm : Model -> Form -> Html Msg
viewForm model formDsl =
    form []
        (List.map (viewField model) (getFields formDsl))


viewField : Model -> ( Label, Id, ValueType ) -> Html Msg
viewField model ( label, identifier, valueType ) =
    BaseWidget.container
        { identifier = identifier
        , label = label
        , formData = model.formData
        , onChange = OnFieldChange identifier
        }
    <|
        case valueType of
            StringType ->
                StringWidget.view

            BooleanType ->
                BooleanWidget.view

            IntegerType ->
                IntegerWidget.view


getFields : Form -> List ( Label, Id, ValueType )
getFields =
    .items >> getFieldsForItems


getFieldsForItem : FormItem -> List ( Label, Id, ValueType )
getFieldsForItem item =
    case item of
        Field label identifier valueType ->
            [ ( label, identifier, valueType ) ]

        ComputedField label identifier valueType _ ->
            [ ( label, identifier, valueType ) ]

        IfThen _ thenBranch ->
            getFieldsForItems thenBranch

        IfThenElse _ thenBranch elseBranch ->
            List.concat
                [ getFieldsForItems thenBranch
                , getFieldsForItems elseBranch
                ]


getFieldsForItems : List FormItem -> List ( Label, Id, ValueType )
getFieldsForItems =
    List.concatMap getFieldsForItem


getExpressions : FormItem -> List Expression
getExpressions item =
    case item of
        Field _ _ _ ->
            []

        ComputedField _ _ _ valueExpression ->
            [ valueExpression ]

        IfThen expression thenBranch ->
            List.concat
                [ [ expression ]
                , List.concatMap getExpressions thenBranch
                ]

        IfThenElse expression thenBranch elseBranch ->
            List.concat
                [ [ expression ]
                , List.concatMap getExpressions thenBranch
                , List.concatMap getExpressions elseBranch
                ]
