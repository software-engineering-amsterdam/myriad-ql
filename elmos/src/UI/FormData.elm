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


empty : FormData
empty =
    Dict.empty


withBoolean : String -> Bool -> FormData -> FormData
withBoolean k v =
    Dict.insert k (Boolean v)


withString : String -> String -> FormData -> FormData
withString k v =
    Dict.insert k (Str v)


getBoolean : String -> FormData -> Maybe Bool
getBoolean key data =
    Dict.get key data
        |> Maybe.andThen onBoolean


getString : String -> FormData -> Maybe String
getString key data =
    Dict.get key data
        |> Maybe.andThen onString


onBoolean : FormValue -> Maybe Bool
onBoolean formValue =
    case formValue of
        Boolean b ->
            Just b

        _ ->
            Nothing


onString : FormValue -> Maybe String
onString formValue =
    case formValue of
        Str b ->
            Just b

        _ ->
            Nothing
