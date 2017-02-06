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
        , fieldTests
        , valueTypeTests
        , expressionTests
        ]


formTokenTests : Test
formTokenTests =
    testWithParser Parser.formToken
        "formToken"
        [ ( "should parse a form token", "form", Just "form" )
        ]


varNameTests : Test
varNameTests =
    testWithParser Parser.variableName
        "variableName"
        [ ( "should not parse an empty string", "", Nothing )
        , ( "should parse a single lower case character", "a", Just "a" )
        , ( "should not parse a single upper case character", "B", Nothing )
        , ( "should support camel case variable names", "fooBarBaz", Just "fooBarBaz" )
        , ( "should support underscores", "a_b", Just "a_b" )
        , ( "should not support question mark", "a?b", Nothing )
        ]


fieldTests : Test
fieldTests =
    testWithParser Parser.field
        "field"
        [ ( "should parse a simple field", "\"label\" id: integer", Just { label = "label", id = "id", valueType = Integer } )
        , ( "expects whitespace after the label", "\"label\"id: integer", Nothing )
        , ( "allows no whitespace after the colon", "\"label\" id:integer", Just { label = "label", id = "id", valueType = Integer } )
        , ( "id should be a varName", "\"label\" Other: integer", Nothing )
        , ( "should only support valid types", "\"label\" id: invalid", Nothing )
        ]


valueTypeTests : Test
valueTypeTests =
    testWithParser Parser.valueType
        "valueType"
        [ ( "should parse string", "string", Just String )
        , ( "should parse boolean", "boolean", Just Boolean )
        , ( "should parse integer", "integer", Just Integer )
        ]


expressionTests : Test
expressionTests =
    testWithParser Parser.expression
        "expression"
        [ ( "Should parse varName", "someVarName", Just (Var "someVarName") )
        , ( "Should parse expression between parentheses", "(someVarName)", Just (ParensExpression (Var "someVarName")) )
        ]
