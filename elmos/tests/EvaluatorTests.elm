module EvaluatorTests exposing (all)

import Dict
import Evaluator
import AST exposing (Expression)
import Parser.Expression exposing (expression)
import ParserTestUtil exposing (parseToMaybe)
import Values exposing (Value)
import Test exposing (Test, describe, test)
import Expect


asExpression : String -> Maybe Expression
asExpression =
    parseToMaybe expression


sampleData : Dict.Dict String Value
sampleData =
    Dict.fromList
        [ ( "a", Values.string "A" )
        , ( "b", Values.string "B" )
        , ( "x", Values.int 1 )
        , ( "y", Values.int 2 )
        , ( "z", Values.int 3 )
        ]


all : Test
all =
    describe "EvaluatorTests"
        [ test "string" <|
            \() ->
                asExpression "\"hello\""
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.string "hello"))
        , test "int" <|
            \() ->
                asExpression "1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.int 1))
        , test "bool" <|
            \() ->
                asExpression "true"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool True))
        , test "parens" <|
            \() ->
                asExpression "(1)"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.int 1))
        , test "variable" <|
            \() ->
                asExpression "a"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.string "A"))
        , test "addition" <|
            \() ->
                asExpression "1 + 2"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.int 3))
        , test "subtract" <|
            \() ->
                asExpression "3 - 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.int 2))
        , test "multiply" <|
            \() ->
                asExpression "3 * 2"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.int 6))
        , test "divide" <|
            \() ->
                asExpression "12 / 3"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.int 4))
        , test "divide" <|
            \() ->
                asExpression "12 / 3"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.int 4))
        , test "arithmetic with non-int value on lhs" <|
            \() ->
                asExpression "1 + true"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just Values.undefined)
        , test "arithmetic with non-int value on rhs" <|
            \() ->
                asExpression "\"hello\" + 2"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just Values.undefined)
        , test "and that results in false" <|
            \() ->
                asExpression "true && false"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool False))
        , test "and that results in true" <|
            \() ->
                asExpression "true && true"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool True))
        , test "or that results in false" <|
            \() ->
                asExpression "false || false"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool False))
        , test "or that results in true" <|
            \() ->
                asExpression "false || true"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool True))
        , test "logic with non-bool lhs" <|
            \() ->
                asExpression "1 && true"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just Values.undefined)
        , test "logic with non-bool rhs" <|
            \() ->
                asExpression "true && \"hello\""
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just Values.undefined)
        , test "less than or equal with equal values" <|
            \() ->
                asExpression "1 <= 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool True))
        , test "less than or equal with ascending values" <|
            \() ->
                asExpression "1 <= 2"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool True))
        , test "less than or equal with descending values" <|
            \() ->
                asExpression "2 <= 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool False))
        , test "less than with equal values" <|
            \() ->
                asExpression "1 < 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool False))
        , test "less than with ascending values" <|
            \() ->
                asExpression "1 < 2"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool True))
        , test "less than with descending values" <|
            \() ->
                asExpression "2 < 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool False))
        , test "greater than or equal with equal values" <|
            \() ->
                asExpression "1 >= 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool True))
        , test "greater than or equal with ascending values" <|
            \() ->
                asExpression "1 >= 2"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool False))
        , test "greater than or equal with descending values" <|
            \() ->
                asExpression "2 >= 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool True))
        , test "greater than with equal values" <|
            \() ->
                asExpression "1 > 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool False))
        , test "greater than with ascending values" <|
            \() ->
                asExpression "1 > 2"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool False))
        , test "greater than with descending values" <|
            \() ->
                asExpression "2 > 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool True))
        , test "relation with non-integer lhs" <|
            \() ->
                asExpression "true > 1"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just Values.undefined)
        , test "relation with non-integer rhs" <|
            \() ->
                asExpression "1 > false"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just Values.undefined)
        , test "complex expression" <|
            \() ->
                asExpression "(a == b ||  x < 6) && (y >= 4 || true)"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just (Values.bool True))
        , test "equal undefined variables" <|
            \() ->
                asExpression "unknown == unknown"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just Values.undefined)
        , test "not equal undefined variables" <|
            \() ->
                asExpression "unknown != unknown"
                    |> Maybe.map (Evaluator.evaluate sampleData)
                    |> Expect.equal (Just Values.undefined)
        ]
