module TypeChecker.CheckerTests exposing (all, input)

import Combine exposing (..)
import Expect exposing (..)
import Parser.Form exposing (form)
import Samples.Form exposing (..)
import Test exposing (..)
import Tests exposing (parseToMaybe)
import TypeChecker.Checker exposing (..)


input _ =
    Maybe.map getAllFieldsFromForm (parseToMaybe form goodExample5) |> Debug.log "myform"


all =
    describe "CheckerTests"
        []
