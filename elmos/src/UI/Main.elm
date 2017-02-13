module UI.Main exposing (main)

import CssFrameworks
import CssFrameworks.Bootstrap exposing (bootstrap)
import Html exposing (Html, beginnerProgram, div)
import UI.QLApp as App exposing (Model, Msg)


main : Program Never Model Msg
main =
    beginnerProgram
        { model = App.init
        , view = view
        , update = App.update
        }


view : Model -> Html Msg
view model =
    div []
        [ CssFrameworks.toStyleNode bootstrap
        , App.view model
        ]
