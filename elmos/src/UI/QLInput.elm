module UI.QLInput exposing (Model, Msg, init, asForm, update, view)

import Html exposing (Html, b, div, form, h3, pre, text, textarea)
import Html.Attributes exposing (class, cols, defaultValue, rows, style)
import Html.Events exposing (onInput)
import QL.AST exposing (Form, Location(Location), ValueType)
import QL.Parser as Parser
import QL.TypeChecker as TypeChecker
import QL.TypeChecker.Messages exposing (Message(Error), ErrorMessage(..))
import UI.Messages


type alias Model =
    { rawInput : String
    , parsedForm : Maybe Form
    , messages : List Message
    }


type Msg
    = OnDslInput String


init : Model
init =
    { rawInput = ""
    , parsedForm = Nothing
    , messages = []
    }
        |> update (OnDslInput exampleDsl)


exampleDsl : String
exampleDsl =
    """form taxOfficeExample {
  "Name?"
  name : string

  "Age?"
  age : integer

  "Age?"
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

}"""


asForm : Model -> Maybe Form
asForm { parsedForm } =
    parsedForm


update : Msg -> Model -> Model
update msg model =
    case msg of
        OnDslInput newDslInput ->
            let
                parseResult =
                    Parser.parse newDslInput
            in
                { model
                    | rawInput = newDslInput
                    , parsedForm = parseResult
                    , messages = Maybe.withDefault [] <| Maybe.map TypeChecker.check <| parseResult
                }


view : Model -> Html Msg
view { rawInput, parsedForm, messages } =
    div []
        [ div [ class "row" ]
            [ div [ class "col-md-6" ]
                [ form [ class "form" ]
                    [ textarea
                        [ defaultValue rawInput
                        , rows 20
                        , cols 45
                        , class "form-control"
                        , style [ ( "width", "100%" ), ( "resize", "none" ), ( "font-family", "courier" ) ]
                        , onInput OnDslInput
                        ]
                        []
                    ]
                ]
            , div [ class "col-md-6" ]
                [ h3 [] [ text "TypeChecker" ]
                , div []
                    (List.map renderMessage messages)
                ]
            ]
        , pre [] [ text <| toString parsedForm ]
        ]


renderMessage : Message -> Html.Html Msg
renderMessage message =
    case message of
        Error (DuplicateQuestionDefinition name locations) ->
            UI.Messages.error
                ([ text "Duplicate question definitions for variable "
                 , UI.Messages.varName name
                 , text " at the following locations: [ "
                 , UI.Messages.locations locations
                 , text "]"
                 ]
                )

        Error (ReferenceToUndefinedQuestion ( name, loc )) ->
            UI.Messages.error
                [ text <| "Reference to undefined variable "
                , UI.Messages.varName name
                , text " at "
                , UI.Messages.location loc
                ]

        (Error (ArithmeticExpressionTypeMismatch operator loc leftType rightType)) as error ->
            operatorMismatchMessage operator loc leftType rightType

        (Error (LogicExpressionTypeMismatch operator loc leftType rightType)) as error ->
            operatorMismatchMessage operator loc leftType rightType

        (Error (ComparisonExpressionTypeMismatch operator loc leftType rightType)) as error ->
            operatorMismatchMessage operator loc leftType rightType

        (Error (RelationExpressionTypeMismatch operator loc leftType rightType)) as error ->
            operatorMismatchMessage operator loc leftType rightType


operatorMismatchMessage : a -> Location -> ValueType -> ValueType -> Html msg
operatorMismatchMessage operator loc leftType rightType =
    UI.Messages.error
        [ b [] [ text <| toString operator ]
        , text <| " is not supported for " ++ toString leftType ++ " and " ++ toString rightType ++ " at "
        , UI.Messages.location loc
        ]
