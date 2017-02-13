module UI.FormData exposing (..)

import Dict exposing (Dict)


type alias FormData =
    Dict String FormValue


type FormValue
    = Str String
    | Boolean Bool
    | Integer Int
    | Undefined


removeKeys : List String -> FormData -> FormData
removeKeys keys formData =
    List.foldl Dict.remove formData keys


empty : FormData
empty =
    Dict.empty


withBoolean : String -> Bool -> FormData -> FormData
withBoolean k v =
    Dict.insert k (Boolean v)


getBoolean : String -> FormData -> Maybe Bool
getBoolean key data =
    Dict.get key data
        |> Maybe.andThen onBoolean


onBoolean : FormValue -> Maybe Bool
onBoolean formValue =
    case formValue of
        Boolean b ->
            Just b

        _ ->
            Nothing


withFormValue : String -> FormValue -> FormData -> FormData
withFormValue =
    Dict.insert


withString : String -> String -> FormData -> FormData
withString k v =
    Dict.insert k (Str v)


getString : String -> FormData -> Maybe String
getString key data =
    Dict.get key data
        |> Maybe.andThen onString


onString : FormValue -> Maybe String
onString formValue =
    case formValue of
        Str b ->
            Just b

        _ ->
            Nothing


withInteger : String -> Int -> FormData -> FormData
withInteger k v =
    Dict.insert k (Integer v)


getInteger : String -> FormData -> Maybe Int
getInteger key data =
    Dict.get key data
        |> Maybe.andThen onInteger


onInteger : FormValue -> Maybe Int
onInteger formValue =
    case formValue of
        Integer b ->
            Just b

        _ ->
            Nothing
