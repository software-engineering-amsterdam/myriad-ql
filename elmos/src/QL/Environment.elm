module QL.Environment exposing (Environment, removeKeys, empty, withFormValue, getFormValue)

import Dict exposing (Dict)
import QL.Values exposing (Value)


type Environment
    = Environment (Dict String Value)


removeKeys : List String -> Environment -> Environment
removeKeys keys (Environment env) =
    Environment (List.foldl Dict.remove env keys)


empty : Environment
empty =
    Environment Dict.empty


withFormValue : String -> Value -> Environment -> Environment
withFormValue key val (Environment env) =
    Environment (Dict.insert key val env)


getFormValue : String -> Environment -> Maybe Value
getFormValue key (Environment env) =
    Dict.get key env
