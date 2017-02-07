module UI.QLApp exposing (..)

import Html exposing (..)
import Html.Attributes exposing (..)
import Html.Events exposing (onInput)
import AST exposing (Form)
import Parser.Parser as Parser


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
                [ value model.dslInput
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
        ]
