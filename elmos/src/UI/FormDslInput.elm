module UI.FormDslInput exposing (Model, Msg, init, asForm, update, view)

import Html exposing (Html, form, textarea, div, hr, pre, text)
import Html.Attributes exposing (class, defaultValue, rows, cols, class, style)
import Html.Events exposing (onInput)
import AST exposing (Form)
import Parser.Parser as Parser


type Model
    = Model String (Maybe Form)


type Msg
    = OnDslInput String


init : Model
init =
    Model "" Nothing
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
asForm (Model _ maybeForm) =
    maybeForm


update : Msg -> Model -> Model
update msg _ =
    case msg of
        OnDslInput newDslInput ->
            Model newDslInput (Parser.parse newDslInput)


view : Model -> Html Msg
view (Model rawText parsedForm) =
    div []
        [ form [ class "form" ]
            [ textarea
                [ defaultValue rawText
                , rows 24
                , cols 80
                , class "form-control"
                , style [ ( "width", "100%" ), ( "resize", "none" ), ( "font-family", "courier" ) ]
                , onInput OnDslInput
                ]
                []
            ]
        , hr [] []
        , pre [] [ text <| toString parsedForm ]
        ]
