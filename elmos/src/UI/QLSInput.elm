module UI.QLSInput exposing (Model, Msg, init, asStylesheet, update, view)

import Html exposing (Html, form, textarea, div, hr, pre, text)
import Html.Attributes exposing (class, defaultValue, rows, cols, class, style)
import Html.Events exposing (onInput)
import QLS.AST exposing (StyleSheet)
import QLS.Parser as Parser


type Model
    = Model String (Maybe StyleSheet)


type Msg
    = OnInput String


init : Model
init =
    Model "" Nothing
        |> update (OnInput exampleDsl)


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


asStylesheet : Model -> Maybe StyleSheet
asStylesheet (Model _ maybeStylesheet) =
    maybeStylesheet


update : Msg -> Model -> Model
update msg _ =
    case msg of
        OnInput newInput ->
            Model newInput (Parser.parse newInput)


view : Model -> Html Msg
view (Model rawText parsedForm) =
    div []
        [ div [ class "row" ]
            [ div [ class "col-md-6" ]
                [ form [ class "form" ]
                    [ textarea
                        [ defaultValue rawText
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
                [ text "TypeChecker" ]
            ]
        , pre [] [ text <| toString parsedForm ]
        ]
