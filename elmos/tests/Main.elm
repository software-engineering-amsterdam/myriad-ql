port module Main exposing (main)

import Tests
import Test.Runner.Node exposing (run, TestProgram)
import Json.Encode exposing (Value)


main : TestProgram
main =
    run emit Tests.all


port emit : ( String, Value ) -> Cmd msg
