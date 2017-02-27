module QL.TypeChecker.ExpressionsTests exposing (all)

import QL.AST exposing (..)
import QL.ASTTestUtil exposing (emptyLoc)
import Dict
import Expect
import Test exposing (Test, describe, test)
import QL.TypeChecker.Expressions as Expressions
import QL.TypeChecker.Messages exposing (..)


all : Test
all =
    describe
        "TypeChecker.Expressions"
        [ test "type for undefined variable" <|
            \() ->
                Expressions.getType Dict.empty (Var ( "a", emptyLoc ))
                    |> Expect.equal (Err [ (Error (UndefinedExpressionVariable "a" emptyLoc)) ])
        , test "type for string type" <|
            \() ->
                Expressions.getType Dict.empty (Str emptyLoc "foo")
                    |> Expect.equal (Ok StringType)
        , test "type for int" <|
            \() ->
                Expressions.getType Dict.empty (Integer emptyLoc 1)
                    |> Expect.equal (Ok IntegerType)
        , test "type for bool" <|
            \() ->
                Expressions.getType Dict.empty (Boolean emptyLoc True)
                    |> Expect.equal (Ok BooleanType)
        , test "type of arithmetic expression" <|
            \() ->
                Expressions.getType Dict.empty (ArithmeticExpression Plus emptyLoc (Integer emptyLoc 2) (Integer emptyLoc 1))
                    |> Expect.equal (Ok IntegerType)
        , test "type mismatch for arithmetic expression on lhs" <|
            \() ->
                Expressions.getType Dict.empty (ArithmeticExpression Plus emptyLoc (Boolean emptyLoc True) (Integer emptyLoc 1))
                    |> Expect.equal (Err [ Error (ArithmeticExpressionTypeMismatch Plus emptyLoc BooleanType IntegerType) ])
        , test "type mismatch for arithmetic expression on rhs" <|
            \() ->
                Expressions.getType Dict.empty (ArithmeticExpression Plus emptyLoc (Integer emptyLoc 1) (Boolean emptyLoc True))
                    |> Expect.equal (Err [ Error (ArithmeticExpressionTypeMismatch Plus emptyLoc IntegerType BooleanType) ])
        , test "type mismatch for arithmetic expression when both sides do error " <|
            \() ->
                Expressions.getType Dict.empty (ArithmeticExpression Plus emptyLoc (Var ( "a", emptyLoc )) (Var ( "b", emptyLoc )))
                    |> Expect.equal
                        (Err
                            [ (Error (UndefinedExpressionVariable "a" emptyLoc))
                            , (Error (UndefinedExpressionVariable "b" emptyLoc))
                            ]
                        )
        , test "type of relation expression" <|
            \() ->
                Expressions.getType Dict.empty (RelationExpression LessThan emptyLoc (Integer emptyLoc 2) (Integer emptyLoc 1))
                    |> Expect.equal (Ok BooleanType)
        , test "type mismatch for relation expression on lhs" <|
            \() ->
                Expressions.getType Dict.empty (RelationExpression LessThan emptyLoc (Boolean emptyLoc True) (Integer emptyLoc 1))
                    |> Expect.equal (Err [ Error (RelationExpressionTypeMismatch LessThan emptyLoc BooleanType IntegerType) ])
        , test "type mismatch for relation expression on rhs" <|
            \() ->
                Expressions.getType Dict.empty (RelationExpression GreaterThan emptyLoc (Integer emptyLoc 1) (Boolean emptyLoc True))
                    |> Expect.equal (Err [ Error (RelationExpressionTypeMismatch GreaterThan emptyLoc IntegerType BooleanType) ])
        , test "type mismatch for relation expression when both sides do error" <|
            \() ->
                Expressions.getType Dict.empty (RelationExpression GreaterThan emptyLoc (Var ( "a", emptyLoc )) (Var ( "b", emptyLoc )))
                    |> Expect.equal
                        (Err
                            [ (Error (UndefinedExpressionVariable "a" emptyLoc))
                            , (Error (UndefinedExpressionVariable "b" emptyLoc))
                            ]
                        )
        , test "type of logic expression" <|
            \() ->
                Expressions.getType Dict.empty (LogicExpression And emptyLoc (Boolean emptyLoc True) (Boolean emptyLoc False))
                    |> Expect.equal (Ok BooleanType)
        , test "type mismatch for logic expression on lhs" <|
            \() ->
                Expressions.getType Dict.empty (LogicExpression Or emptyLoc (Boolean emptyLoc True) (Integer emptyLoc 1))
                    |> Expect.equal (Err [ Error (LogicExpressionTypeMismatch Or emptyLoc BooleanType IntegerType) ])
        , test "type mismatch for logic expression on rhs" <|
            \() ->
                Expressions.getType Dict.empty (LogicExpression And emptyLoc (Integer emptyLoc 1) (Boolean emptyLoc True))
                    |> Expect.equal (Err [ Error (LogicExpressionTypeMismatch And emptyLoc IntegerType BooleanType) ])
        , test "type mismatch for logic expression when both sides do error" <|
            \() ->
                Expressions.getType Dict.empty (LogicExpression Or emptyLoc (Var ( "a", emptyLoc )) (Var ( "b", emptyLoc )))
                    |> Expect.equal
                        (Err
                            [ (Error (UndefinedExpressionVariable "a" emptyLoc))
                            , (Error (UndefinedExpressionVariable "b" emptyLoc))
                            ]
                        )
        , test "type of comparison expression with both boolean" <|
            \() ->
                Expressions.getType Dict.empty (ComparisonExpression Equal emptyLoc (Boolean emptyLoc True) (Boolean emptyLoc False))
                    |> Expect.equal (Ok BooleanType)
        , test "type of comparison expression with both string" <|
            \() ->
                Expressions.getType Dict.empty (ComparisonExpression NotEqual emptyLoc (Str emptyLoc "foo") (Str emptyLoc "bar"))
                    |> Expect.equal (Ok BooleanType)
        , test "type of comparison expression with both integer" <|
            \() ->
                Expressions.getType Dict.empty (ComparisonExpression NotEqual emptyLoc (Integer emptyLoc 1) (Integer emptyLoc 2))
                    |> Expect.equal (Ok BooleanType)
        , test "type mismatch for comparison expression" <|
            \() ->
                Expressions.getType Dict.empty (ComparisonExpression NotEqual emptyLoc (Boolean emptyLoc True) (Integer emptyLoc 1))
                    |> Expect.equal (Err [ Error (ComparisonExpressionTypeMismatch NotEqual emptyLoc BooleanType IntegerType) ])
        , test "type mismatch for logic expression when both sides do error" <|
            \() ->
                Expressions.getType Dict.empty (ComparisonExpression Equal emptyLoc (Var ( "a", emptyLoc )) (Var ( "b", emptyLoc )))
                    |> Expect.equal
                        (Err
                            [ (Error (UndefinedExpressionVariable "a" emptyLoc))
                            , (Error (UndefinedExpressionVariable "b" emptyLoc))
                            ]
                        )
        ]
