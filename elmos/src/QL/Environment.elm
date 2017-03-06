module QL.Environment exposing (Environment, removeKeys, empty, withBoolean, getBoolean, withFormValue, getFormValue, withString, getString, withInteger, getInteger, getFloat)

import Dict exposing (Dict)
import QL.Values as Values exposing (Value)


type Environment
    = Environment (Dict String Value)


removeKeys : List String -> Environment -> Environment
removeKeys keys (Environment env) =
    Environment (List.foldl Dict.remove env keys)


empty : Environment
empty =
    Environment Dict.empty


withBoolean : String -> Bool -> Environment -> Environment
withBoolean key val (Environment env) =
    Environment (Dict.insert key (Values.bool val) env)


getBoolean : String -> Environment -> Maybe Bool
getBoolean key (Environment env) =
    Dict.get key env
        |> Maybe.andThen Values.asBool


withFormValue : String -> Value -> Environment -> Environment
withFormValue key val (Environment env) =
    Environment (Dict.insert key val env)


getFormValue : String -> Environment -> Maybe Value
getFormValue key (Environment env) =
    Dict.get key env


withString : String -> String -> Environment -> Environment
withString key val (Environment env) =
    Environment (Dict.insert key (Values.string val) env)


getString : String -> Environment -> Maybe String
getString key (Environment env) =
    Dict.get key env
        |> Maybe.andThen Values.asString


withInteger : String -> Int -> Environment -> Environment
withInteger key val (Environment env) =
    Environment (Dict.insert key (Values.int val) env)


getInteger : String -> Environment -> Maybe Int
getInteger key (Environment env) =
    Dict.get key env
        |> Maybe.andThen Values.asInt


getFloat : String -> Environment -> Maybe Float
getFloat key (Environment env) =
    Dict.get key env
        |> Maybe.andThen Values.asFloat
