module UI.FormData exposing (..)

import Dict exposing (Dict)


type alias FormData =
    Dict String FormValue


type FormValue
    = Str String
    | Boolean Bool
    | Integer Int


removeKeys : List String -> FormData -> FormData
removeKeys keys formData =
    List.foldl Dict.remove formData keys
