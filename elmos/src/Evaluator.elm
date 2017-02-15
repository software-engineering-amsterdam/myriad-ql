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
import Values exposing (Value)


evaluate : Environment -> Expression -> Value
evaluate env expression =
    case expression of
        Var ( x, _ ) ->
            Dict.get x env |> Maybe.withDefault Values.undefined

        AST.Str _ str ->
            Values.string str

        AST.Integer _ integer ->
            Values.int integer

        AST.Boolean _ boolean ->
            Values.bool boolean

        ParensExpression _ inner ->
            evaluate env inner

        ArithmeticExpression op _ left right ->
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

        RelationExpression op _ left right ->
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

        LogicExpression op _ left right ->
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

        ComparisonExpression op _ left right ->
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
