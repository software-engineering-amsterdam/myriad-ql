module UI.FormData exposing (..)

import Dict exposing (Dict)
import Values exposing (Value)


type alias FormData =
    Dict String Value


removeKeys : List String -> FormData -> FormData
removeKeys keys formData =
    List.foldl Dict.remove formData keys


empty : FormData
empty =
    Dict.empty


withBoolean : String -> Bool -> FormData -> FormData
withBoolean k v =
    Dict.insert k (Values.bool v)


getBoolean : String -> FormData -> Maybe Bool
getBoolean key data =
    Dict.get key data
        |> Maybe.andThen Values.asBool


withFormValue : String -> Value -> FormData -> FormData
withFormValue =
    Dict.insert


withString : String -> String -> FormData -> FormData
withString k v =
    Dict.insert k (Values.string v)


getString : String -> FormData -> Maybe String
getString key data =
    Dict.get key data
        |> Maybe.andThen Values.asString


withInteger : String -> Int -> FormData -> FormData
withInteger k v =
    Dict.insert k (Values.int v)


getInteger : String -> FormData -> Maybe Int
getInteger key data =
    Dict.get key data
        |> Maybe.andThen Values.asInt
