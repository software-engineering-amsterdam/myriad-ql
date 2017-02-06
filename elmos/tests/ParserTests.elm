module ParserTests exposing (all)

import Parser
import Test exposing (Test, test, concat, describe)
import AST exposing (..)
import ParserTestUtil exposing (testWithParser)


all : Test
all =
    describe "ParserTests"
        [ formTokenTests
        , varNameTests
        , valueTypeTests
        ]


formTokenTests : Test
formTokenTests =
    testWithParser Parser.formToken
        "formToken"
        [ ( "should parse a form token", "form", Just "form" )
        ]


{-| TODO more tests with special characters?
-}
varNameTests : Test
varNameTests =
    testWithParser Parser.variableName
        "variableName"
        [ ( "should not parse an empty string", "", Nothing )
        , ( "should parse a single lower case character", "a", Just "a" )
        , ( "should not parse a single upper case character", "B", Nothing )
        , ( "should support camel case variable names", "fooBarBaz", Just "fooBarBaz" )
        , ( "should not support special characters", "a_b", Nothing )
        ]


valueTypeTests : Test
valueTypeTests =
    testWithParser Parser.valueType
        "valueType"
        [ ( "should parse string", "string", Just String )
        , ( "should parse boolean", "bolean", Just Boolean )
        , ( "should parse integer", "integer", Just Integer )
        ]
