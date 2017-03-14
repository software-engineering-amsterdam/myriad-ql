module QL.Parser.ExpressionTests exposing (all)

import QL.Parser.Expression exposing (expression)
import Test exposing (Test, describe)
import QL.AST
    exposing
        ( Expression
            ( Var
            , Integer
            , Decimal
            , Str
            , Boolean
            , ParensExpression
            , ArithmeticExpression
            , ComparisonExpression
            , LogicExpression
            , RelationExpression
            )
        , Operator(Plus, Minus, Divide, Multiply)
        , Relation(LessThan, GreaterThan, GreaterThanOrEqual, LessThanOrEqual)
        , Comparison(Equal, NotEqual)
        , Logic(And, Or)
        , Location(Location)
        )
import QL.ASTTestUtil exposing (removeLocactionFromExpression)
import ParserTestUtil exposing (testWithParserAndMap)


all : Test
all =
    describe "ParserTests"
        [ atomTests
        , arithmeticTests
        , relationalTests
        , comparisonTests
        , logicalTests
        , whitespaceTests
        ]


whitespaceTests : Test
whitespaceTests =
    testWithParserAndMap expression
        removeLocactionFromExpression
        "whitespaceTests"
        [ ( "Should not parse surrounding whitespace", " 1 ", Nothing )
        , ( "Should not parse whitespace before", " 1", Nothing )
        , ( "Should not parse whitespace after", "1 ", Nothing )
        , ( "Should not parse whitespace within parens", "( 1 )", Just (ParensExpression (Location 0 0) (Integer (Location 0 0) 1)) )
        , ( "Should parse whitespace within operators"
          , "1 + 2"
          , Just (ArithmeticExpression Plus (Location 0 0) (Integer (Location 0 0) 1) (Integer (Location 0 0) 2))
          )
        ]


atomTests : Test
atomTests =
    testWithParserAndMap expression
        removeLocactionFromExpression
        "atomTests"
        [ ( "Should parse varName", "someVarName", Just (Var ( "someVarName", Location 0 0 )) )
        , ( "Should parse int literal", "2", Just (Integer (Location 0 0) 2) )
        , ( "Should parse a float", "1.0", Just (Decimal (Location 0 0) 1.0) )
        , ( "Should parse parens int literal", "(2)", Just (ParensExpression (Location 0 0) (Integer (Location 0 0) 2)) )
        , ( "Should parse parens boolean literal", "true", Just (Boolean (Location 0 0) True) )
        , ( "Should parse string expression", "\"Hello\"", Just (Str (Location 0 0) "Hello") )
        ]


arithmeticTests : Test
arithmeticTests =
    testWithParserAndMap expression
        removeLocactionFromExpression
        "arithmeticTests"
        [ ( "Should parse simple add", "2+3", Just (ArithmeticExpression Plus (Location 0 0) (Integer (Location 0 0) 2) (Integer (Location 0 0) 3)) )
        , ( "Should parse bigger add"
          , "2+3+4"
          , Just
                (ArithmeticExpression Plus
                    (Location 0 0)
                    (ArithmeticExpression Plus (Location 0 0) (Integer (Location 0 0) 2) (Integer (Location 0 0) 3))
                    (Integer (Location 0 0) 4)
                )
          )
        , ( "Should parse plus and multiplication"
          , "2+3*4"
          , Just
                (ArithmeticExpression Plus
                    (Location 0 0)
                    (Integer (Location 0 0) 2)
                    (ArithmeticExpression Multiply (Location 0 0) (Integer (Location 0 0) 3) (Integer (Location 0 0) 4))
                )
          )
        , ( "Should parse minus and division"
          , "2-3/4"
          , Just
                (ArithmeticExpression Minus
                    (Location 0 0)
                    (Integer (Location 0 0) 2)
                    (ArithmeticExpression Divide (Location 0 0) (Integer (Location 0 0) 3) (Integer (Location 0 0) 4))
                )
          )
        , ( "Should parse variables", "x+y", Just (ArithmeticExpression Plus (Location 0 0) (Var ( "x", Location 0 0 )) (Var ( "y", Location 0 0 ))) )
        ]


relationalTests : Test
relationalTests =
    testWithParserAndMap expression
        removeLocactionFromExpression
        "relationalTests"
        [ ( "Should parse less than relation"
          , "x < y"
          , Just
                (RelationExpression LessThan
                    (Location 0 0)
                    (Var ( "x", Location 0 0 ))
                    (Var ( "y", Location 0 0 ))
                )
          )
        , ( "Should parse greater than relation"
          , "x > y"
          , Just
                (RelationExpression GreaterThan
                    (Location 0 0)
                    (Var ( "x", Location 0 0 ))
                    (Var ( "y", Location 0 0 ))
                )
          )
        , ( "Should parse less than equal relation"
          , "x <= y"
          , Just
                (RelationExpression LessThanOrEqual
                    (Location 0 0)
                    (Var ( "x", Location 0 0 ))
                    (Var ( "y", Location 0 0 ))
                )
          )
        , ( "Should parse greater than equal relation"
          , "x >= y"
          , Just
                (RelationExpression GreaterThanOrEqual
                    (Location 0 0)
                    (Var ( "x", Location 0 0 ))
                    (Var ( "y", Location 0 0 ))
                )
          )
        , ( "Should parse relation with arithmetic"
          , "x+y < z * a"
          , Just
                (RelationExpression
                    LessThan
                    (Location 0 0)
                    (ArithmeticExpression Plus (Location 0 0) (Var ( "x", Location 0 0 )) (Var ( "y", Location 0 0 )))
                    (ArithmeticExpression Multiply (Location 0 0) (Var ( "z", Location 0 0 )) (Var ( "a", Location 0 0 )))
                )
          )
        ]


comparisonTests : Test
comparisonTests =
    testWithParserAndMap expression
        removeLocactionFromExpression
        "comparisonTests"
        [ ( "Should parse equal comparison"
          , "x == y"
          , Just
                (ComparisonExpression Equal
                    (Location 0 0)
                    (Var ( "x", Location 0 0 ))
                    (Var ( "y", Location 0 0 ))
                )
          )
        , ( "Should parse not equal comparison"
          , "x != y"
          , Just
                (ComparisonExpression NotEqual
                    (Location 0 0)
                    (Var ( "x", Location 0 0 ))
                    (Var ( "y", Location 0 0 ))
                )
          )
        , ( "Should parse comparison with correct precedence"
          , "x + y == y < z"
          , Just
                (ComparisonExpression Equal
                    (Location 0 0)
                    (ArithmeticExpression Plus (Location 0 0) (Var ( "x", Location 0 0 )) (Var ( "y", Location 0 0 )))
                    (RelationExpression LessThan (Location 0 0) (Var ( "y", Location 0 0 )) (Var ( "z", Location 0 0 )))
                )
          )
        ]


logicalTests : Test
logicalTests =
    testWithParserAndMap expression
        removeLocactionFromExpression
        "logicalTests"
        [ ( "Should parse AND", "x&&y", Just (LogicExpression And (Location 0 0) (Var ( "x", Location 0 0 )) (Var ( "y", Location 0 0 ))) )
        , ( "Should parse OR", "x||y", Just (LogicExpression Or (Location 0 0) (Var ( "x", Location 0 0 )) (Var ( "y", Location 0 0 ))) )
        , ( "AND should preced OR"
          , "x && y || z"
          , Just
                (LogicExpression Or
                    (Location 0 0)
                    (LogicExpression And (Location 0 0) (Var ( "x", Location 0 0 )) (Var ( "y", Location 0 0 )))
                    (Var ( "z", Location 0 0 ))
                )
          )
        , ( "OR should be preceded by AND"
          , "x || y && z"
          , Just
                (LogicExpression Or
                    (Location 0 0)
                    (Var ( "x", Location 0 0 ))
                    (LogicExpression And (Location 0 0) (Var ( "y", Location 0 0 )) (Var ( "z", Location 0 0 )))
                )
          )
        ]
