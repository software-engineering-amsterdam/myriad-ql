module Tests exposing (..)

import Test exposing (..)
import Combine exposing (..)
import Expect
import Parser exposing (..)
import ParserTests
import Combine.ExtraTests


all : Test
all =
    describe "QL Parser"
        [ ParserTests.all
        , Combine.ExtraTests.all
        , test "QuestionLabel" <|
            \() ->
                Expect.equal (parseToMaybe fieldLabel "\"Is this a question?\"") (Just "Is this a question?")
        ]


parseToMaybe : Parser () res -> String -> Maybe res
parseToMaybe p s =
    case Combine.parse p s of
        Err e ->
            Nothing

        Ok ( _, _, res ) ->
            Just res
