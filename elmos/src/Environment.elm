module Environment exposing (..)

import Dict exposing (Dict)
import Values exposing (Value)


type alias Environment =
    Dict String Value


removeKeys : List String -> Environment -> Environment
removeKeys keys formData =
    List.foldl Dict.remove formData keys


empty : Environment
empty =
    Dict.empty


withBoolean : String -> Bool -> Environment -> Environment
withBoolean k v =
    Dict.insert k (Values.bool v)


getBoolean : String -> Environment -> Maybe Bool
getBoolean key data =
    Dict.get key data
        |> Maybe.andThen Values.asBool


withFormValue : String -> Value -> Environment -> Environment
withFormValue =
    Dict.insert


withString : String -> String -> Environment -> Environment
withString k v =
    Dict.insert k (Values.string v)


getString : String -> Environment -> Maybe String
getString key data =
    Dict.get key data
        |> Maybe.andThen Values.asString


withInteger : String -> Int -> Environment -> Environment
withInteger k v =
    Dict.insert k (Values.int v)


getInteger : String -> Environment -> Maybe Int
getInteger key data =
    Dict.get key data
        |> Maybe.andThen Values.asInt


getFloat : String -> Environment -> Maybe Float
getFloat key data =
    Dict.get key data
        |> Maybe.andThen Values.asFloat
