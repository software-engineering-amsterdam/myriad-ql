module UI.QLInput exposing (Model, Msg, init, asForm, update, view)

import AST exposing (Form, Location)
import Html exposing (Html, div, form, h3, hr, pre, text, textarea)
import Html.Attributes exposing (class, cols, defaultValue, rows, style)
import Html.Events exposing (onInput)
import Parser.Parser as Parser
import TypeChecker exposing (messages)
import TypeChecker.Messages exposing (Message(Error), ErrorMessage(..))


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
                    , messages = Maybe.withDefault [] <| Maybe.map messages <| parseResult
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
                , pre []
                    [ text <| String.join "\n" <| List.map toString <| messages
                    ]
                ]
            ]
        , pre [] [ text <| toString parsedForm ]
        ]


renderMessage : Message -> Html.Html Msg
renderMessage message =
    case message of
        Error (DuplicateQuestionDefinition _ _) ->
            text "TODO"

        Error (ReferenceToUndefinedQuestion ( name, AST.Location line col )) ->
            text <| "Reference to undefined variable <" ++ name ++ "> at line " ++ toString line ++ "(" ++ toString col ++ ")"

        Error (UndefinedExpressionVariable _ _) ->
            text "TODO"

        Error (ArithmeticExpressionTypeMismatch _ _ _ _) ->
            text "TODO"

        Error (LogicExpressionTypeMismatch _ _ _ _) ->
            text "TODO"

        Error (ComparisonExpressionTypeMismatch _ _ _ _) ->
            text "TODO"

        Error (RelationExpressionTypeMismatch _ _ _ _) ->
            text "TODO"
