module Evaluator exposing (evaluate)

import AST
    exposing
        ( Expression(..)
        , Operator(Plus, Minus, Divide, Multiply)
        , Relation(LessThan, LessThanOrEqual, GreaterThan, GreaterThanOrEqual)
        , Logic(And, Or)
        , Comparison(Equal, NotEqual)
        )
import Dict exposing (Dict)
import Environment exposing (Environment)
import Values exposing (Value(Undefined))


evaluate : Environment -> Expression -> Value
evaluate env expression =
    case expression of
        Var x ->
            Dict.get x env |> Maybe.withDefault Values.undefined

        AST.Str str ->
            Values.string str

        AST.Integer integer ->
            Values.int integer

        AST.Boolean boolean ->
            Values.bool boolean

        ParensExpression inner ->
            evaluate env inner

        ArithmeticExpression op left right ->
            let
                leftValue =
                    evaluate env left

                rightValue =
                    evaluate env right
            in
                case ( leftValue, rightValue ) of
                    ( Values.Integer l, Values.Integer r ) ->
                        Values.int (applicativeForOperator op l r)

                    _ ->
                        Values.undefined

        RelationExpression op left right ->
            let
                leftValue =
                    evaluate env left

                rightValue =
                    evaluate env right
            in
                case ( leftValue, rightValue ) of
                    ( Values.Integer l, Values.Integer r ) ->
                        Values.bool (applicativeForRelation op l r)

                    _ ->
                        Values.undefined

        LogicExpression op left right ->
            let
                leftValue =
                    evaluate env left

                rightValue =
                    evaluate env right
            in
                case ( leftValue, rightValue ) of
                    ( Values.Boolean l, Values.Boolean r ) ->
                        Values.bool (applicativeForLogic op l r)

                    _ ->
                        Values.undefined

        ComparisonExpression op left right ->
            let
                leftValue =
                    evaluate env left

                rightValue =
                    evaluate env right
            in
                case ( leftValue, rightValue ) of
                    ( Values.Undefined, _ ) ->
                        Values.undefined

                    ( _, Values.Undefined ) ->
                        Values.undefined

                    ( a, b ) ->
                        Values.bool (applicativeForComparison op a b)


applicativeForOperator : Operator -> Int -> Int -> Int
applicativeForOperator op =
    case op of
        Plus ->
            (+)

        Minus ->
            (-)

        Divide ->
            (//)

        Multiply ->
            (*)


applicativeForRelation : Relation -> Int -> Int -> Bool
applicativeForRelation relation =
    case relation of
        LessThan ->
            (<)

        LessThanOrEqual ->
            (<=)

        GreaterThan ->
            (>)

        GreaterThanOrEqual ->
            (>=)


applicativeForLogic : Logic -> Bool -> Bool -> Bool
applicativeForLogic logic =
    case logic of
        And ->
            (&&)

        Or ->
            (||)


applicativeForComparison : Comparison -> a -> a -> Bool
applicativeForComparison comparison =
    case comparison of
        NotEqual ->
            (/=)

        Equal ->
            (==)
