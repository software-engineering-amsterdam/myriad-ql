module QL.Parser.TokenTests exposing (all)

import QL.AST exposing (Location(Location))
import QL.ASTTestUtil exposing (removeLocactionFromId)
import QL.Parser.Token as Token
import ParserTestUtil exposing (testWithParserAndMap)
import Test exposing (Test, describe)


all : Test
all =
    describe "ParserTests"
        [ identifierTests
        ]


identifierTests : Test
identifierTests =
    testWithParserAndMap Token.identifier
        removeLocactionFromId
        "identifierTests"
        [ ( "should not parse an empty string", "", Nothing )
        , ( "should not parse a number", "1", Nothing )
        , ( "should parse a single lower case character", "a", Just ( "a", Location 0 0 ) )
        , ( "should not parse a single upper case character", "B", Nothing )
        , ( "should support camel case variable names", "fooBarBaz", Just ( "fooBarBaz", Location 0 0 ) )
        , ( "should support underscores", "a_b", Just ( "a_b", Location 0 0 ) )
        , ( "should not support question mark", "a?b", Nothing )
        ]
