module Parser.ExpressionTests exposing (all)

import Parser.Expression exposing (expression)
import Test exposing (Test, test, concat, describe)
import AST exposing (..)
import ParserTestUtil exposing (testWithParser)


all : Test
all =
    describe "ParserTests"
        [ atomTests
        , arithmeticTests
        , relationalTests
        , comparisonTests
        , logicalTests
        ]


atomTests : Test
atomTests =
    testWithParser expression
        "atomTests"
        [ ( "Should parse varName", "someVarName", Just (Var "someVarName") )
        , ( "Should parse int literal", "2", Just (Integer 2) )
        , ( "Should parse parens int literal", "(2)", Just (ParensExpression (Integer 2)) )
        ]


arithmeticTests : Test
arithmeticTests =
    testWithParser expression
        "arithmeticTests"
        [ ( "Should parse simple add", "2+3", Just (ArithmeticExpression Plus (Integer 2) (Integer 3)) )
        , ( "Should parse bigger add", "2+3+4", Just (ArithmeticExpression Plus (ArithmeticExpression Plus (Integer 2) (Integer 3)) (Integer 4)) )
        , ( "Should parse plus and multiplication", "2+3*4", Just (ArithmeticExpression Plus (Integer 2) (ArithmeticExpression Multiply (Integer 3) (Integer 4))) )
        , ( "Should parse minus and division", "2-3/4", Just (ArithmeticExpression Minus (Integer 2) (ArithmeticExpression Divide (Integer 3) (Integer 4))) )
        , ( "Should parse variables", "x+y", Just (ArithmeticExpression Plus (Var "x") (Var "y")) )
        ]


relationalTests : Test
relationalTests =
    testWithParser expression
        "relationalTests"
        [ ( "Should parse less than relation", "x < y", Just (LessThanExpression (Var "x") (Var "y")) )
        , ( "Should parse greater than relation", "x > y", Just (GreaterThanExpression (Var "x") (Var "y")) )
        , ( "Should parse less than equal relation", "x <= y", Just (LessThanOrEqualExpression (Var "x") (Var "y")) )
        , ( "Should parse greater than equal relation", "x >= y", Just (GreaterThanOrEqualExpression (Var "x") (Var "y")) )
        , ( "Should parse relation with arithmetic"
          , "x+y < z * a"
          , Just
                (LessThanExpression
                    (ArithmeticExpression Plus (Var "x") (Var "y"))
                    (ArithmeticExpression Multiply (Var "z") (Var "a"))
                )
          )
        ]


comparisonTests : Test
comparisonTests =
    testWithParser expression
        "comparisonTests"
        [ ( "Should parse equal comparison", "x == y", Just (EqualToExpression (Var "x") (Var "y")) )
        , ( "Should parse not equal comparison", "x != y", Just (NotEqualToExpression (Var "x") (Var "y")) )
        , ( "Should parse comparison with correct precedence"
          , "x + y == y < z"
          , Just
                (EqualToExpression
                    (ArithmeticExpression Plus (Var "x") (Var "y"))
                    (LessThanExpression (Var "y") (Var "z"))
                )
          )
        ]


logicalTests : Test
logicalTests =
    testWithParser expression
        "logicalTests"
        [ ( "Should parse AND", "x&&y", Just (AndExpression (Var "x") (Var "y")) )
        , ( "Should parse OR", "x||y", Just (OrExpression (Var "x") (Var "y")) )
        , ( "AND should preced OR", "x && y || z", Just (OrExpression (AndExpression (Var "x") (Var "y")) (Var "z")) )
        , ( "OR should be preceded by AND", "x || y && z", Just (OrExpression (Var "x") (AndExpression (Var "y") (Var "z"))) )
        ]
