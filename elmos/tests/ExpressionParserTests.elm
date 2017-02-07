module ExpressionParserTests exposing (all)

import ExpressionParser
import Test exposing (Test, test, concat, describe)
import AST exposing (..)
import ParserTestUtil exposing (testWithParser)


all : Test
all =
    describe "ParserTests"
        [ expressionTests
        , arithmeticTests
        ]


arithmeticTests : Test
arithmeticTests =
    testWithParser ExpressionParser.expression
        "expression"
        [ ( "Should parse simple add", "2+3", Just (PlusExpression (Integer 2) (Integer 3)) )
        , ( "Should parse bigger add", "2+3+4", Just (PlusExpression (PlusExpression (Integer 2) (Integer 3)) (Integer 4)) )
        , ( "Should parse plus and multiplication", "2+3*4", Just (PlusExpression (Integer 2) (MultiplyExpression (Integer 3) (Integer 4))) )
        , ( "Should parse minus and division", "2-3/4", Just (MinusExpression (Integer 2) (DivideExpression (Integer 3) (Integer 4))) )
        , ( "Should parse variables", "x+y", Just (PlusExpression (Var "x") (Var "z")) )
        ]


expressionTests : Test
expressionTests =
    testWithParser ExpressionParser.expression
        "expression"
        [ ( "Should parse varName", "someVarName", Just (Var "someVarName") )
        , ( "Should parse expression between parentheses", "(someVarName)", Just (ParensExpression (Var "someVarName")) )
        ]
