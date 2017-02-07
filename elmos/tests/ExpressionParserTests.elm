module ExpressionParserTests exposing (all)

import ExpressionParser
import Test exposing (Test, test, concat, describe)
import AST exposing (..)
import ParserTestUtil exposing (testWithParser)


all : Test
all =
    describe "ParserTests"
        [ expressionTests
        , arithmaticTests
        ]


arithmaticTests : Test
arithmaticTests =
    testWithParser ExpressionParser.expression
        "expression"
        [ ( "Should parse add", "2+3", Just (PlusExpression (Integer 2) (Integer 3)) )
        ]


expressionTests : Test
expressionTests =
    testWithParser ExpressionParser.expression
        "expression"
        [ ( "Should parse varName", "someVarName", Just (Var "someVarName") )
        , ( "Should parse expression between parentheses", "(someVarName)", Just (ParensExpression (Var "someVarName")) )
        ]
