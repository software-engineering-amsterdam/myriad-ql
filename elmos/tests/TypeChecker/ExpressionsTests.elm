module TypeChecker.ExpressionsTests exposing (all)

import Expect
import Test exposing (Test, describe, test)
import TypeChecker.Expressions as Expressions
import TypeChecker.Messages exposing (..)
import Dict
import AST exposing (..)


all : Test
all =
    describe
        "TypeChecker.Expressions"
        [ test "type for undefined variable" <|
            \() ->
                Expressions.getType Dict.empty (Var "a")
                    |> Expect.equal (Err [ (Error (UndefinedExpressionVariable "a" ())) ])
        , test "type for string type" <|
            \() ->
                Expressions.getType Dict.empty (Str "foo")
                    |> Expect.equal (Ok StringType)
        , test "type for int" <|
            \() ->
                Expressions.getType Dict.empty (Integer 1)
                    |> Expect.equal (Ok IntegerType)
        , test "type for bool" <|
            \() ->
                Expressions.getType Dict.empty (Boolean True)
                    |> Expect.equal (Ok BooleanType)
        , test "type of arithmetic expression" <|
            \() ->
                Expressions.getType Dict.empty (ArithmeticExpression Plus (Integer 2) (Integer 1))
                    |> Expect.equal (Ok IntegerType)
        , test "type mismatch for arithmetic expression on lhs" <|
            \() ->
                Expressions.getType Dict.empty (ArithmeticExpression Plus (Boolean True) (Integer 1))
                    |> Expect.equal (Err [ Error (ArithmeticExpressionTypeMismatch Plus BooleanType IntegerType) ])
        , test "type mismatch for arithmetic expression on rhs" <|
            \() ->
                Expressions.getType Dict.empty (ArithmeticExpression Plus (Integer 1) (Boolean True))
                    |> Expect.equal (Err [ Error (ArithmeticExpressionTypeMismatch Plus IntegerType BooleanType) ])
        , test "type mismatch for arithmetic expression when both sides do error " <|
            \() ->
                Expressions.getType Dict.empty (ArithmeticExpression Plus (Var "a") (Var "b"))
                    |> Expect.equal
                        (Err
                            [ (Error (UndefinedExpressionVariable "a" ()))
                            , (Error (UndefinedExpressionVariable "b" ()))
                            ]
                        )
        , test "type of relation expression" <|
            \() ->
                Expressions.getType Dict.empty (RelationExpression LessThan (Integer 2) (Integer 1))
                    |> Expect.equal (Ok BooleanType)
        , test "type mismatch for relation expression on lhs" <|
            \() ->
                Expressions.getType Dict.empty (RelationExpression LessThan (Boolean True) (Integer 1))
                    |> Expect.equal (Err [ Error (RelationExpressionTypeMismatch LessThan BooleanType IntegerType) ])
        , test "type mismatch for relation expression on rhs" <|
            \() ->
                Expressions.getType Dict.empty (RelationExpression GreaterThan (Integer 1) (Boolean True))
                    |> Expect.equal (Err [ Error (RelationExpressionTypeMismatch GreaterThan IntegerType BooleanType) ])
        , test "type mismatch for relation expression when both sides do error" <|
            \() ->
                Expressions.getType Dict.empty (RelationExpression GreaterThan (Var "a") (Var "b"))
                    |> Expect.equal
                        (Err
                            [ (Error (UndefinedExpressionVariable "a" ()))
                            , (Error (UndefinedExpressionVariable "b" ()))
                            ]
                        )
        , test "type of logic expression" <|
            \() ->
                Expressions.getType Dict.empty (LogicExpression And (Boolean True) (Boolean False))
                    |> Expect.equal (Ok BooleanType)
        , test "type mismatch for logic expression on lhs" <|
            \() ->
                Expressions.getType Dict.empty (LogicExpression Or (Boolean True) (Integer 1))
                    |> Expect.equal (Err [ Error (LogicExpressionTypeMismatch Or BooleanType IntegerType) ])
        , test "type mismatch for logic expression on rhs" <|
            \() ->
                Expressions.getType Dict.empty (LogicExpression And (Integer 1) (Boolean True))
                    |> Expect.equal (Err [ Error (LogicExpressionTypeMismatch And IntegerType BooleanType) ])
        , test "type mismatch for logic expression when both sides do error" <|
            \() ->
                Expressions.getType Dict.empty (LogicExpression Or (Var "a") (Var "b"))
                    |> Expect.equal
                        (Err
                            [ (Error (UndefinedExpressionVariable "a" ()))
                            , (Error (UndefinedExpressionVariable "b" ()))
                            ]
                        )
        , test "type of comparison expression with both boolean" <|
            \() ->
                Expressions.getType Dict.empty (ComparisonExpression Equal (Boolean True) (Boolean False))
                    |> Expect.equal (Ok BooleanType)
        , test "type of comparison expression with both string" <|
            \() ->
                Expressions.getType Dict.empty (ComparisonExpression NotEqual (Str "foo") (Str "bar"))
                    |> Expect.equal (Ok BooleanType)
        , test "type of comparison expression with both integer" <|
            \() ->
                Expressions.getType Dict.empty (ComparisonExpression NotEqual (Integer 1) (Integer 2))
                    |> Expect.equal (Ok BooleanType)
        , test "type mismatch for comparison expression" <|
            \() ->
                Expressions.getType Dict.empty (ComparisonExpression NotEqual (Boolean True) (Integer 1))
                    |> Expect.equal (Err [ Error (ComparisonExpressionTypeMismatch NotEqual BooleanType IntegerType) ])
        , test "type mismatch for logic expression when both sides do error" <|
            \() ->
                Expressions.getType Dict.empty (ComparisonExpression Equal (Var "a") (Var "b"))
                    |> Expect.equal
                        (Err
                            [ (Error (UndefinedExpressionVariable "a" ()))
                            , (Error (UndefinedExpressionVariable "b" ()))
                            ]
                        )
        ]
