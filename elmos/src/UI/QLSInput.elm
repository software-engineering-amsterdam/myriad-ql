module UI.QLSInput exposing (Model, Msg, init, asStyleSheet, setForm, update, view)

import Html exposing (Html, textarea, div, pre, text)
import Html.Keyed exposing (node)
import Html.Attributes exposing (class, defaultValue, rows, cols, class, style, id)
import Html.Events exposing (onInput)
import QLS.AST exposing (StyleSheet)
import QL.AST exposing (Form)
import QLS.Parser as Parser
import QLS.TypeChecker as TypeChecker
import QLS.TypeChecker.Messages exposing (Message(UndefinedQuestionReference, UnplacedQuestion, DuplicatePlacedQuestion))
import UI.Messages


type alias Model =
    { input : String
    , parsedStyleSheet : Maybe StyleSheet
    , parsedForm : Maybe Form
    , messages : List Message
    }


type Msg
    = OnInput String


init : Maybe Form -> Model
init form =
    { input = "", parsedStyleSheet = Nothing, parsedForm = form, messages = [] }
        |> update (OnInput exampleDsl)


asStyleSheet : Model -> Maybe StyleSheet
asStyleSheet model =
    if List.isEmpty model.messages then
        model.parsedStyleSheet
    else
        Nothing


setForm : Maybe Form -> Model -> Model
setForm form model =
    { model | parsedForm = form }
        |> updateMessages


setInput : String -> Model -> Model
setInput input model =
    { model | input = input, parsedStyleSheet = Parser.parse input }
        |> updateMessages


updateMessages : Model -> Model
updateMessages model =
    { model
        | messages =
            Maybe.map2 TypeChecker.check model.parsedForm model.parsedStyleSheet
                |> Maybe.withDefault []
    }


exampleDsl : String
exampleDsl =
    """stylesheet taxOfficeExample
  page Housing {
    section "Buying"
      question hasBoughtHouse
        widget checkbox
    section "Loaning"
      question hasMaintLoan
  }

  page Selling {
    section "Selling" {
      question hasSoldHouse
        widget radio("Yes", "No")
      section "You sold a house" {
        question sellingPrice
          widget spinbox
        question privateDebt
          widget spinbox
        question valueResidue
        default money {
          width: 400
          font: "Arial"
          fontsize: 14
          color: #999999
          widget spinbox
        }
      }
    }
    default boolean widget radio("Yes", "No")
  }

"""


update : Msg -> Model -> Model
update msg model =
    case msg of
        OnInput newInput ->
            setInput newInput model


view : Model -> Html Msg
view { input, parsedStyleSheet, parsedForm, messages } =
    node "div"
        []
        [ ( "qlsInput"
          , div []
                [ div [ class "row" ]
                    [ div [ class "col-md-6" ]
                        [ Html.form [ class "form" ]
                            [ textarea
                                [ defaultValue input
                                , rows 20
                                , cols 45
                                , class "form-control"
                                , style [ ( "width", "100%" ), ( "resize", "none" ), ( "font-family", "courier" ) ]
                                , onInput OnInput
                                ]
                                []
                            ]
                        ]
                    , div [ class "col-md-6" ]
                        [ div [] <|
                            if parsedForm == Nothing then
                                [ UI.Messages.error
                                    [ text "No valid Form" ]
                                ]
                            else if parsedStyleSheet == Nothing then
                                [ UI.Messages.error
                                    [ text "Could not parse the stylesheet" ]
                                ]
                            else
                                List.map (UI.Messages.error << renderMessage) messages
                        ]
                    ]
                ]
          )
        ]


renderMessage : Message -> List (Html msg)
renderMessage message =
    case message of
        UndefinedQuestionReference name loc ->
            [ text <| "Reference to undefined question "
            , UI.Messages.renderVarName name
            , text " at "
            , UI.Messages.renderLocation loc
            ]

        UnplacedQuestion name ->
            [ text <| "Question "
            , UI.Messages.renderVarName name
            , text " is not placed in the QLS program"
            ]

        DuplicatePlacedQuestion name locations ->
            [ text "Question placed multiple times for variable "
            , UI.Messages.renderVarName name
            , text " at the following locations: [ "
            , UI.Messages.renderLocations locations
            , text "]"
            ]
