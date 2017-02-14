module Parser.TokenTests exposing (all)

import Parser.Token as Token
import ParserTestUtil exposing (testWithParser)
import Test exposing (Test, describe)


all : Test
all =
    describe "ParserTests"
        [ identifierTests
        ]


identifierTests : Test
identifierTests =
    testWithParser Token.identifier
        "identifierTests"
        [ ( "should not parse an empty string", "", Nothing )
        , ( "should not parse a number", "1", Nothing )
        , ( "should parse a single lower case character", "a", Just "a" )
        , ( "should not parse a single upper case character", "B", Nothing )
        , ( "should support camel case variable names", "fooBarBaz", Just "fooBarBaz" )
        , ( "should support underscores", "a_b", Just "a_b" )
        , ( "should not support question mark", "a?b", Nothing )
        ]
