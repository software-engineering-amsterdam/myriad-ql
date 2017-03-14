module UI.QLInput exposing (Model, Msg, init, asForm, update, view)

import Html exposing (Html, b, div, form, pre, text, textarea)
import Html.Attributes exposing (class, cols, defaultValue, rows, style, id)
import Html.Events exposing (onInput)
import Html.Keyed exposing (node)
import QL.AST exposing (Form, Location, ValueType)
import QL.Parser as Parser
import QL.TypeChecker as TypeChecker
import QL.TypeChecker.Messages exposing (Message(Error, Warning), ErrorMessage(..), WarningMessage(..))
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
  "What is your gender?"
    gender: string
  "What was your age on new year's eve in 2016"
    age: integer
  if (age > 0) {
    "You were born in "
    birthYear: integer = 2016 - age

    if (age > 50){
      "Damn you're old!"
      uselessStatement: string = " "
    }
  }
  "Are you married?"
    marritalStatus: string
  "How do you rate the above questions?"
    rating : integer

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
asForm model =
    if List.isEmpty model.messages then
        model.parsedForm
    else
        Nothing


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
    node "div"
        []
        [ ( "qlInput"
          , div [ class "row" ]
                [ div [ class "col-md-6" ]
                    [ form [ class "form" ]
                        [ textarea
                            [ id "qlInput"
                            , defaultValue rawInput
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
                    (if parsedForm == Nothing then
                        [ UI.Messages.error
                            [ text "Invalid Form" ]
                        ]
                     else if List.isEmpty messages then
                        [ UI.Messages.success
                            [ text "Everything seems OK!" ]
                        ]
                     else
                        List.map renderMessage messages
                    )
                ]
          )
        , ( "preview", pre [] [ text <| toString parsedForm ] )
        ]


renderMessage : Message -> Html msg
renderMessage message =
    case message of
        Error errorMessage ->
            UI.Messages.error (renderErrorMessage errorMessage)

        Warning warningMessage ->
            UI.Messages.warning (renderWarningMessage warningMessage)


renderWarningMessage : WarningMessage -> List (Html msg)
renderWarningMessage message =
    case message of
        DuplicateLabels label ids ->
            [ text "label \""
            , b [] [ text label ]
            , text "\" is used for multiple questions : "
            , UI.Messages.renderIds ids
            ]


renderErrorMessage : ErrorMessage -> List (Html msg)
renderErrorMessage message =
    case message of
        DuplicateQuestionDefinition name locations ->
            [ text "Duplicate question definitions for variable "
            , UI.Messages.renderVarName name
            , text " at the following locations: [ "
            , UI.Messages.renderLocations locations
            , text "]"
            ]

        ReferenceToUndefinedQuestion ( name, loc ) ->
            [ text <| "Reference to undefined variable "
            , UI.Messages.renderVarName name
            , text " at "
            , UI.Messages.renderLocation loc
            ]

        DependencyCycle cycle ->
            [ text "Found dependency cycle : "
            , text (String.join " -> " cycle)
            ]

        ArithmeticExpressionTypeMismatch operator loc leftType rightType ->
            operatorMismatchMessage operator loc leftType rightType

        LogicExpressionTypeMismatch operator loc leftType rightType ->
            operatorMismatchMessage operator loc leftType rightType

        ComparisonExpressionTypeMismatch operator loc leftType rightType ->
            operatorMismatchMessage operator loc leftType rightType

        RelationExpressionTypeMismatch operator loc leftType rightType ->
            operatorMismatchMessage operator loc leftType rightType

        InvalidConditionType loc conditionType ->
            [ text "Condition at "
            , UI.Messages.renderLocation loc
            , text " has invalid type: "
            , UI.Messages.renderType conditionType
            ]

        InvalidComputedFieldType identifier computedType fieldType ->
            [ text "Question "
            , UI.Messages.renderId identifier
            , text " is defined as "
            , UI.Messages.renderType computedType
            , text " but it's computation is of type "
            , UI.Messages.renderType fieldType
            ]


operatorMismatchMessage : a -> Location -> ValueType -> ValueType -> List (Html msg)
operatorMismatchMessage operator loc leftType rightType =
    [ b [] [ text <| toString operator ]
    , text <| " is not supported for " ++ toString leftType ++ " and " ++ toString rightType ++ " at "
    , UI.Messages.renderLocation loc
    ]
