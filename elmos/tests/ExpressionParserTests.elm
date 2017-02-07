module ExpressionParserTests exposing (all)

import ExpressionParser
import Test exposing (Test, test, concat, describe)
import AST exposing (..)
import ParserTestUtil exposing (testWithParser)


all : Test
all =
    describe "ParserTests"
        [ arithmeticTests
        , comparisonTests
        , atomTests
        ]


arithmeticTests : Test
arithmeticTests =
    testWithParser ExpressionParser.expression
        "arithmeticTests"
        [ ( "Should parse simple add", "2+3", Just (PlusExpression (Integer 2) (Integer 3)) )
        , ( "Should parse bigger add", "2+3+4", Just (PlusExpression (PlusExpression (Integer 2) (Integer 3)) (Integer 4)) )
        , ( "Should parse plus and multiplication", "2+3*4", Just (PlusExpression (Integer 2) (MultiplyExpression (Integer 3) (Integer 4))) )
        , ( "Should parse minus and division", "2-3/4", Just (MinusExpression (Integer 2) (DivideExpression (Integer 3) (Integer 4))) )
        , ( "Should parse variables", "x+y", Just (PlusExpression (Var "x") (Var "z")) )
        ]


comparisonTests : Test
comparisonTests =
    testWithParser ExpressionParser.expression
        "comparisonTests"
        [ ( "Should parse less than comparison", "x < y", Just (LessThanExpression (Var "x") (Var "y")) )
        , ( "Should parse greater than comparison", "x > y", Just (GreaterThanExpression (Var "x") (Var "y")) )
        , ( "Should parse less than equal comparison", "x <= y", Just (GreaterThanOrEqualExpression (Var "x") (Var "y")) )
        , ( "Should parse greater than equal comparison", "x >= y", Just (LessThanOrEqualExpression (Var "x") (Var "y")) )
        , ( "Should parse comparison with arithmetic"
          , "x+y < z * a"
          , Just
                (LessThanExpression
                    (PlusExpression (Var "x") (Var "y"))
                    (MultiplyExpression (Var "z") (Var "a"))
                )
          )
        ]


atomTests : Test
atomTests =
    testWithParser ExpressionParser.expression
        "atomTests"
        [ ( "Should parse varName", "someVarName", Just (Var "someVarName") )
        , ( "Should parse int literal", "2", Just (ParensExpression (Integer 2)) )
        ]
