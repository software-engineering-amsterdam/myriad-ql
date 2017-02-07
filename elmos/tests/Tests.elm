module Tests exposing (..)

import Test exposing (..)
import Combine exposing (..)
import ParserTests
import ExpressionParserTests
import Combine.ExtraTests


all : Test
all =
    describe "QL Parser"
        [ Combine.ExtraTests.all
        , ParserTests.all
        , ExpressionParserTests.all
        ]


parseToMaybe : Parser () res -> String -> Maybe res
parseToMaybe p s =
    case Combine.parse p s of
        Err e ->
            Nothing

        Ok ( _, _, res ) ->
            Just res
