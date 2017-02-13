module Parser.ExpressionTests exposing (all)

import Parser.Expression exposing (expression)
import Test exposing (Test, describe)
import AST
    exposing
        ( Expression(Var, Integer, ParensExpression, ArithmeticExpression, ComparisonExpression, LogicExpression, RelationExpression)
        , Operator(Plus, Minus, Divide, Multiply)
        , Relation(LessThan, GreaterThan, GreaterThanOrEqual, LessThanOrEqual)
        , Comparison(Equal, NotEqual)
        , Logic(And, Or)
        )
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
        [ ( "Should parse less than relation", "x < y", Just (RelationExpression LessThan (Var "x") (Var "y")) )
        , ( "Should parse greater than relation", "x > y", Just (RelationExpression GreaterThan (Var "x") (Var "y")) )
        , ( "Should parse less than equal relation", "x <= y", Just (RelationExpression LessThanOrEqual (Var "x") (Var "y")) )
        , ( "Should parse greater than equal relation", "x >= y", Just (RelationExpression GreaterThanOrEqual (Var "x") (Var "y")) )
        , ( "Should parse relation with arithmetic"
          , "x+y < z * a"
          , Just
                (RelationExpression
                    LessThan
                    (ArithmeticExpression Plus (Var "x") (Var "y"))
                    (ArithmeticExpression Multiply (Var "z") (Var "a"))
                )
          )
        ]


comparisonTests : Test
comparisonTests =
    testWithParser expression
        "comparisonTests"
        [ ( "Should parse equal comparison", "x == y", Just (ComparisonExpression Equal (Var "x") (Var "y")) )
        , ( "Should parse not equal comparison", "x != y", Just (ComparisonExpression NotEqual (Var "x") (Var "y")) )
        , ( "Should parse comparison with correct precedence"
          , "x + y == y < z"
          , Just
                (ComparisonExpression Equal
                    (ArithmeticExpression Plus (Var "x") (Var "y"))
                    (RelationExpression LessThan (Var "y") (Var "z"))
                )
          )
        ]


logicalTests : Test
logicalTests =
    testWithParser expression
        "logicalTests"
        [ ( "Should parse AND", "x&&y", Just (LogicExpression And (Var "x") (Var "y")) )
        , ( "Should parse OR", "x||y", Just (LogicExpression Or (Var "x") (Var "y")) )
        , ( "AND should preced OR", "x && y || z", Just (LogicExpression Or (LogicExpression And (Var "x") (Var "y")) (Var "z")) )
        , ( "OR should be preceded by AND", "x || y && z", Just (LogicExpression Or (Var "x") (LogicExpression And (Var "y") (Var "z"))) )
        ]
