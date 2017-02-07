module Tests exposing (..)

import Combine exposing (..)
import Combine.ExtraTests
import Parser.ExpressionTests as ExpressionTests
import Parser.FormTests as FormTests
import Parser.TokenParserTests as TokenParserTests
import Test exposing (..)


all : Test
all =
    describe "QL Parser"
        [ FormTests.all
        , Combine.ExtraTests.all
        , ExpressionTests.all
        , TokenParserTests.all
        ]


parseToMaybe : Parser () res -> String -> Maybe res
parseToMaybe p s =
    case Combine.parse p s of
        Err e ->
            Nothing

        Ok ( _, _, res ) ->
            Just res
