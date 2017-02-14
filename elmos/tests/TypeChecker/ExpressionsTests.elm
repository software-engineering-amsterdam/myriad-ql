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
                Expressions.getType Dict.empty (Var ( "a", Location 0 0 ))
                    |> Expect.equal (Err [ (Error (UndefinedExpressionVariable "a" (Location 0 0))) ])
        , test "type for string type" <|
            \() ->
                Expressions.getType Dict.empty (Str (Location 0 0) "foo")
                    |> Expect.equal (Ok StringType)
        , test "type for int" <|
            \() ->
                Expressions.getType Dict.empty (Integer (Location 0 0) 1)
                    |> Expect.equal (Ok IntegerType)
        , test "type for bool" <|
            \() ->
                Expressions.getType Dict.empty (Boolean (Location 0 0) True)
                    |> Expect.equal (Ok BooleanType)
        , test "type of arithmetic expression" <|
            \() ->
                Expressions.getType Dict.empty (ArithmeticExpression Plus (Location 0 0) (Integer (Location 0 0) 2) (Integer (Location 0 0) 1))
                    |> Expect.equal (Ok IntegerType)
        , test "type mismatch for arithmetic expression on lhs" <|
            \() ->
                Expressions.getType Dict.empty (ArithmeticExpression Plus (Location 0 0) (Boolean (Location 0 0) True) (Integer (Location 0 0) 1))
                    |> Expect.equal (Err [ Error (ArithmeticExpressionTypeMismatch Plus (Location 0 0) BooleanType IntegerType) ])
        , test "type mismatch for arithmetic expression on rhs" <|
            \() ->
                Expressions.getType Dict.empty (ArithmeticExpression Plus (Location 0 0) (Integer (Location 0 0) 1) (Boolean (Location 0 0) True))
                    |> Expect.equal (Err [ Error (ArithmeticExpressionTypeMismatch Plus (Location 0 0) IntegerType BooleanType) ])
        , test "type mismatch for arithmetic expression when both sides do error " <|
            \() ->
                Expressions.getType Dict.empty (ArithmeticExpression Plus (Location 0 0) (Var ( "a", (Location 0 0) )) (Var ( "b", Location 0 0 )))
                    |> Expect.equal
                        (Err
                            [ (Error (UndefinedExpressionVariable "a" (Location 0 0)))
                            , (Error (UndefinedExpressionVariable "b" (Location 0 0)))
                            ]
                        )
        , test "type of relation expression" <|
            \() ->
                Expressions.getType Dict.empty (RelationExpression LessThan (Location 0 0) (Integer (Location 0 0) 2) (Integer (Location 0 0) 1))
                    |> Expect.equal (Ok BooleanType)
        , test "type mismatch for relation expression on lhs" <|
            \() ->
                Expressions.getType Dict.empty (RelationExpression LessThan (Location 0 0) (Boolean (Location 0 0) True) (Integer (Location 0 0) 1))
                    |> Expect.equal (Err [ Error (RelationExpressionTypeMismatch LessThan (Location 0 0) BooleanType IntegerType) ])
        , test "type mismatch for relation expression on rhs" <|
            \() ->
                Expressions.getType Dict.empty (RelationExpression GreaterThan (Location 0 0) (Integer (Location 0 0) 1) (Boolean (Location 0 0) True))
                    |> Expect.equal (Err [ Error (RelationExpressionTypeMismatch GreaterThan (Location 0 0) IntegerType BooleanType) ])
        , test "type mismatch for relation expression when both sides do error" <|
            \() ->
                Expressions.getType Dict.empty (RelationExpression GreaterThan (Location 0 0) (Var ( "a", Location 0 0 )) (Var ( "b", (Location 0 0) )))
                    |> Expect.equal
                        (Err
                            [ (Error (UndefinedExpressionVariable "a" (Location 0 0)))
                            , (Error (UndefinedExpressionVariable "b" (Location 0 0)))
                            ]
                        )
        , test "type of logic expression" <|
            \() ->
                Expressions.getType Dict.empty (LogicExpression And (Location 0 0) (Boolean (Location 0 0) True) (Boolean (Location 0 0) False))
                    |> Expect.equal (Ok BooleanType)
        , test "type mismatch for logic expression on lhs" <|
            \() ->
                Expressions.getType Dict.empty (LogicExpression Or (Location 0 0) (Boolean (Location 0 0) True) (Integer (Location 0 0) 1))
                    |> Expect.equal (Err [ Error (LogicExpressionTypeMismatch Or (Location 0 0) BooleanType IntegerType) ])
        , test "type mismatch for logic expression on rhs" <|
            \() ->
                Expressions.getType Dict.empty (LogicExpression And (Location 0 0) (Integer (Location 0 0) 1) (Boolean (Location 0 0) True))
                    |> Expect.equal (Err [ Error (LogicExpressionTypeMismatch And (Location 0 0) IntegerType BooleanType) ])
        , test "type mismatch for logic expression when both sides do error" <|
            \() ->
                Expressions.getType Dict.empty (LogicExpression Or (Location 0 0) (Var ( "a", Location 0 0 )) (Var ( "b", (Location 0 0) )))
                    |> Expect.equal
                        (Err
                            [ (Error (UndefinedExpressionVariable "a" (Location 0 0)))
                            , (Error (UndefinedExpressionVariable "b" (Location 0 0)))
                            ]
                        )
        , test "type of comparison expression with both boolean" <|
            \() ->
                Expressions.getType Dict.empty (ComparisonExpression Equal (Location 0 0) (Boolean (Location 0 0) True) (Boolean (Location 0 0) False))
                    |> Expect.equal (Ok BooleanType)
        , test "type of comparison expression with both string" <|
            \() ->
                Expressions.getType Dict.empty (ComparisonExpression NotEqual (Location 0 0) (Str (Location 0 0) "foo") (Str (Location 0 0) "bar"))
                    |> Expect.equal (Ok BooleanType)
        , test "type of comparison expression with both integer" <|
            \() ->
                Expressions.getType Dict.empty (ComparisonExpression NotEqual (Location 0 0) (Integer (Location 0 0) 1) (Integer (Location 0 0) 2))
                    |> Expect.equal (Ok BooleanType)
        , test "type mismatch for comparison expression" <|
            \() ->
                Expressions.getType Dict.empty (ComparisonExpression NotEqual (Location 0 0) (Boolean (Location 0 0) True) (Integer (Location 0 0) 1))
                    |> Expect.equal (Err [ Error (ComparisonExpressionTypeMismatch NotEqual (Location 0 0) BooleanType IntegerType) ])
        , test "type mismatch for logic expression when both sides do error" <|
            \() ->
                Expressions.getType Dict.empty (ComparisonExpression Equal (Location 0 0) (Var ( "a", (Location 0 0) )) (Var ( "b", (Location 0 0) )))
                    |> Expect.equal
                        (Err
                            [ (Error (UndefinedExpressionVariable "a" (Location 0 0)))
                            , (Error (UndefinedExpressionVariable "b" (Location 0 0)))
                            ]
                        )
        ]
