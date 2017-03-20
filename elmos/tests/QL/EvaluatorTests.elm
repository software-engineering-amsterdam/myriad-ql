module QL.EvaluatorTests exposing (all)

import QL.Evaluator as Evaluator
import QL.Environment as Env exposing (Environment)
import QL.AST exposing (Expression)
import QL.Parser.Expression exposing (expression)
import ParserTestUtil exposing (parseToMaybe)
import QL.Values as Values exposing (Value)
import Test exposing (Test, describe, test)
import Expect


asExpression : String -> Maybe Expression
asExpression =
    parseToMaybe expression


sampleData : Environment
sampleData =
    Env.empty
        |> Env.withFormValue "a" (Values.Str "A")
        |> Env.withFormValue "b" (Values.Str "B")
        |> Env.withFormValue "x" (Values.Integer 1)
        |> Env.withFormValue "y" (Values.Integer 2)
        |> Env.withFormValue "z" (Values.Integer 3)


all : Test
all =
    describe "Evaluator"
        [ test "string" <|
            \() ->
                asExpression "\"hello\""
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Str "hello"))
        , test "int" <|
            \() ->
                asExpression "1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Integer 1))
        , test "bool" <|
            \() ->
                asExpression "true"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean True))
        , test "parens" <|
            \() ->
                asExpression "(1)"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Integer 1))
        , test "variable" <|
            \() ->
                asExpression "a"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Str "A"))
        , test "addition" <|
            \() ->
                asExpression "1 + 2"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Integer 3))
        , test "subtract" <|
            \() ->
                asExpression "3 - 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Integer 2))
        , test "multiply" <|
            \() ->
                asExpression "3 * 2"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Integer 6))
        , test "divide" <|
            \() ->
                asExpression "12 / 3"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Integer 4))
        , test "divide" <|
            \() ->
                asExpression "12 / 3"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Integer 4))
        , test "arithmetic with non-int value on lhs" <|
            \() ->
                asExpression "1 + true"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just Values.Undefined)
        , test "arithmetic with non-int value on rhs" <|
            \() ->
                asExpression "\"hello\" + 2"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just Values.Undefined)
        , test "and that results in false" <|
            \() ->
                asExpression "true && false"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean False))
        , test "and that results in true" <|
            \() ->
                asExpression "true && true"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean True))
        , test "or that results in false" <|
            \() ->
                asExpression "false || false"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean False))
        , test "or that results in true" <|
            \() ->
                asExpression "false || true"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean True))
        , test "logic with non-bool lhs" <|
            \() ->
                asExpression "1 && true"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just Values.Undefined)
        , test "logic with non-bool rhs" <|
            \() ->
                asExpression "true && \"hello\""
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just Values.Undefined)
        , test "less than or equal with equal values" <|
            \() ->
                asExpression "1 <= 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean True))
        , test "less than or equal with ascending values" <|
            \() ->
                asExpression "1 <= 2"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean True))
        , test "less than or equal with descending values" <|
            \() ->
                asExpression "2 <= 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean False))
        , test "less than with equal values" <|
            \() ->
                asExpression "1 < 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean False))
        , test "less than with ascending values" <|
            \() ->
                asExpression "1 < 2"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean True))
        , test "less than with ascending values for which the rhs is a float" <|
            \() ->
                asExpression "1 < 2.0"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean True))
        , test "less than with ascending values for which the lhs is a float" <|
            \() ->
                asExpression "1.0 < 2"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean True))
        , test "less than with descending values" <|
            \() ->
                asExpression "2 < 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean False))
        , test "greater than or equal with equal values" <|
            \() ->
                asExpression "1 >= 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean True))
        , test "greater than or equal with ascending values" <|
            \() ->
                asExpression "1 >= 2"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean False))
        , test "greater than or equal with descending values" <|
            \() ->
                asExpression "2 >= 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean True))
        , test "greater than with equal values" <|
            \() ->
                asExpression "1 > 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean False))
        , test "greater than with ascending values" <|
            \() ->
                asExpression "1 > 2"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean False))
        , test "greater than with descending values" <|
            \() ->
                asExpression "2 > 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean True))
        , test "relation with non-integer lhs" <|
            \() ->
                asExpression "true > 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just Values.Undefined)
        , test "relation with non-integer rhs" <|
            \() ->
                asExpression "1 > false"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just Values.Undefined)
        , test "complex expression" <|
            \() ->
                asExpression "(a == b ||  x < 6) && (y >= 4 || true)"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.Boolean True))
        , test "equal undefined variables" <|
            \() ->
                asExpression "unknown == unknown"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just Values.Undefined)
        , test "not equal undefined variables" <|
            \() ->
                asExpression "unknown != unknown"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just Values.Undefined)
        ]
