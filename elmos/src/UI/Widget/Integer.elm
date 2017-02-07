module UI.Widget.Integer exposing (..)

import Html exposing (Html, input)
import Html.Attributes exposing (type_, class)
import AST exposing (Field)


view : Field -> Html msg
view field =
    input [ type_ "text", class "form-control" ] []
