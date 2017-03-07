module UI.Main exposing (main)

import CssFrameworks
import CssFrameworks.Bootstrap exposing (bootstrap)
import Html exposing (Html, beginnerProgram, div)
import Html.Attributes exposing (style)
import UI.QLSApp as App exposing (Model, Msg)


main : Program Never Model Msg
main =
    beginnerProgram
        { model = App.init
        , view = view
        , update = App.update
        }


view : Model -> Html Msg
view model =
    div [ style [ ( "padding", "20px" ) ] ]
        [ CssFrameworks.toStyleNode bootstrap
        , App.view model
        ]
