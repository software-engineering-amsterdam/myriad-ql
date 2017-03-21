module QL.TypeChecker.Expressions.ExpressionTypesTests exposing (all)

import QL.AST exposing (..)
import QL.ASTTestUtil exposing (emptyLoc)
import Dict
import Expect
import Test exposing (Test, describe, test)
import QL.TypeChecker.Messages exposing (..)
import QL.TypeChecker.Expressions.ExpressionType as ExpressionType


all : Test
all =
    describe "ExpressionType.getType"
        [ test "type for undefined variable" <|
            \() ->
                ExpressionType.getType Dict.empty (Var ( "a", emptyLoc ))
                    |> Expect.equal (Err [])
        , test "type for string type" <|
            \() ->
                ExpressionType.getType Dict.empty (Str emptyLoc "foo")
                    |> Expect.equal (Ok StringType)
        , test "type for int" <|
            \() ->
                ExpressionType.getType Dict.empty (Integer emptyLoc 1)
                    |> Expect.equal (Ok IntegerType)
        , test "type for bool" <|
            \() ->
                ExpressionType.getType Dict.empty (Boolean emptyLoc True)
                    |> Expect.equal (Ok BooleanType)
        , test "type of arithmetic expression" <|
            \() ->
                ExpressionType.getType Dict.empty (BinaryExpression (Arithmetic Plus) emptyLoc (Integer emptyLoc 2) (Integer emptyLoc 1))
                    |> Expect.equal (Ok IntegerType)
        , test "type mismatch for arithmetic expression on lhs" <|
            \() ->
                ExpressionType.getType Dict.empty (BinaryExpression (Arithmetic Plus) emptyLoc (Boolean emptyLoc True) (Integer emptyLoc 1))
                    |> Expect.equal (Err [ Error (ArithmeticExpressionTypeMismatch Plus emptyLoc BooleanType IntegerType) ])
        , test "type mismatch for arithmetic expression on rhs" <|
            \() ->
                ExpressionType.getType Dict.empty (BinaryExpression (Arithmetic Plus) emptyLoc (Integer emptyLoc 1) (Boolean emptyLoc True))
                    |> Expect.equal (Err [ Error (ArithmeticExpressionTypeMismatch Plus emptyLoc IntegerType BooleanType) ])
        , test "type mismatch for arithmetic expression when both sides do error " <|
            \() ->
                ExpressionType.getType Dict.empty (BinaryExpression (Arithmetic Plus) emptyLoc (Var ( "a", emptyLoc )) (Var ( "b", emptyLoc )))
                    |> Expect.equal
                        (Err [])
        , test "type of relation expression" <|
            \() ->
                ExpressionType.getType Dict.empty (BinaryExpression (Relation LessThan) emptyLoc (Integer emptyLoc 2) (Integer emptyLoc 1))
                    |> Expect.equal (Ok BooleanType)
        , test "type mismatch for relation expression on lhs" <|
            \() ->
                ExpressionType.getType Dict.empty (BinaryExpression (Relation LessThan) emptyLoc (Boolean emptyLoc True) (Integer emptyLoc 1))
                    |> Expect.equal (Err [ Error (RelationExpressionTypeMismatch LessThan emptyLoc BooleanType IntegerType) ])
        , test "type mismatch for relation expression on rhs" <|
            \() ->
                ExpressionType.getType Dict.empty (BinaryExpression (Relation GreaterThan) emptyLoc (Integer emptyLoc 1) (Boolean emptyLoc True))
                    |> Expect.equal (Err [ Error (RelationExpressionTypeMismatch GreaterThan emptyLoc IntegerType BooleanType) ])
        , test "type mismatch for relation expression when both sides do error" <|
            \() ->
                ExpressionType.getType Dict.empty (BinaryExpression (Relation GreaterThan) emptyLoc (Var ( "a", emptyLoc )) (Var ( "b", emptyLoc )))
                    |> Expect.equal
                        (Err [])
        , test "type of logic expression" <|
            \() ->
                ExpressionType.getType Dict.empty (BinaryExpression (Logic And) emptyLoc (Boolean emptyLoc True) (Boolean emptyLoc False))
                    |> Expect.equal (Ok BooleanType)
        , test "type mismatch for logic expression on lhs" <|
            \() ->
                ExpressionType.getType Dict.empty (BinaryExpression (Logic Or) emptyLoc (Boolean emptyLoc True) (Integer emptyLoc 1))
                    |> Expect.equal (Err [ Error (LogicExpressionTypeMismatch Or emptyLoc BooleanType IntegerType) ])
        , test "type mismatch for logic expression on rhs" <|
            \() ->
                ExpressionType.getType Dict.empty (BinaryExpression (Logic And) emptyLoc (Integer emptyLoc 1) (Boolean emptyLoc True))
                    |> Expect.equal (Err [ Error (LogicExpressionTypeMismatch And emptyLoc IntegerType BooleanType) ])
        , test "type mismatch for logic expression when both sides do error" <|
            \() ->
                ExpressionType.getType Dict.empty (BinaryExpression (Logic Or) emptyLoc (Var ( "a", emptyLoc )) (Var ( "b", emptyLoc )))
                    |> Expect.equal
                        (Err [])
        , test "type of comparison expression with both boolean" <|
            \() ->
                ExpressionType.getType Dict.empty (BinaryExpression (Comparison Equal) emptyLoc (Boolean emptyLoc True) (Boolean emptyLoc False))
                    |> Expect.equal (Ok BooleanType)
        , test "type of comparison expression with both string" <|
            \() ->
                ExpressionType.getType Dict.empty (BinaryExpression (Comparison NotEqual) emptyLoc (Str emptyLoc "foo") (Str emptyLoc "bar"))
                    |> Expect.equal (Ok BooleanType)
        , test "type of comparison expression with both integer" <|
            \() ->
                ExpressionType.getType Dict.empty (BinaryExpression (Comparison NotEqual) emptyLoc (Integer emptyLoc 1) (Integer emptyLoc 2))
                    |> Expect.equal (Ok BooleanType)
        , test "type mismatch for comparison expression" <|
            \() ->
                ExpressionType.getType Dict.empty (BinaryExpression (Comparison NotEqual) emptyLoc (Boolean emptyLoc True) (Integer emptyLoc 1))
                    |> Expect.equal (Err [ Error (ComparisonExpressionTypeMismatch NotEqual emptyLoc BooleanType IntegerType) ])
        , test "type mismatch for logic expression when both sides do error" <|
            \() ->
                ExpressionType.getType Dict.empty (BinaryExpression (Comparison Equal) emptyLoc (Var ( "a", emptyLoc )) (Var ( "b", emptyLoc )))
                    |> Expect.equal
                        (Err [])
        ]
