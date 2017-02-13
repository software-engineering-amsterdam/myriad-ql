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


getBoolean : String -> FormData -> Bool
getBoolean key data =
    Dict.get key data
        |> Maybe.andThen onBoolean
        |> Maybe.withDefault False


onBoolean : FormValue -> Maybe Bool
onBoolean formValue =
    case formValue of
        Boolean b ->
            Just b

        _ ->
            Nothing
