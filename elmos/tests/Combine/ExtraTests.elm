module Combine.ExtraTests exposing (all)

import Combine.Extra exposing (whitespace1)
import ParserTestUtil exposing (parseToMaybe)
import Test exposing (Test, test, fuzz, describe)
import Expect
import Fuzz exposing (Fuzzer)


anyWhitespace : Fuzzer String
anyWhitespace =
    [ " ", "\t", "\n", "\x0D\n" ]
        |> List.map (Fuzz.constant >> (,) 1)
        |> Fuzz.frequencyOrCrash


all : Test
all =
    describe "Combine.ExtraTests"
        [ describe "whitespace1"
            [ test "should not match empty string" <|
                \() ->
                    parseToMaybe whitespace1 ""
                        |> Expect.equal Nothing
            , fuzz anyWhitespace "should match different whitespace" <|
                \input ->
                    parseToMaybe whitespace1 input
                        |> Expect.equal (Just input)
            ]
        ]
