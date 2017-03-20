module QL.Evaluator exposing (evaluate)

import QL.AST as AST exposing (..)
import QL.Environment as Environment exposing (Environment)
import QL.Values as Values exposing (Value)
import Maybe.Extra as Maybe


evaluate : Environment -> Expression -> Value
evaluate env expression =
    case expression of
        Var ( x, _ ) ->
            Environment.getFormValue x env
                |> Maybe.withDefault Values.undefined

        AST.Str _ str ->
            Values.string str

        AST.Integer _ integer ->
            Values.int integer

        AST.Decimal _ float ->
            Values.float float

        AST.Boolean _ boolean ->
            Values.bool boolean

        ParensExpression _ inner ->
            evaluate env inner

        BinaryExpression op _ left right ->
            evaluateBinaryExpression op (evaluate env left) (evaluate env right)


evaluateBinaryExpression : Operator -> Value -> Value -> Value
evaluateBinaryExpression op leftValue rightValue =
    case op of
        Arithmetic arithmetic ->
            let
                maybeInteger =
                    Maybe.map2 (,) (Values.asInt leftValue) (Values.asInt rightValue)
                        |> Maybe.map (\( l, r ) -> binaryForIntArithmitic arithmetic l r)
                        |> Maybe.filter Values.isValidInt
                        |> Maybe.map Values.int

                maybeFloat =
                    Maybe.map2 (,) (Values.asFloat leftValue) (Values.asFloat rightValue)
                        |> Maybe.map (\( l, r ) -> binaryForFloatArithmitic arithmetic l r)
                        |> Maybe.filter Values.isValidFloat
                        |> Maybe.map Values.float
            in
                Maybe.or maybeInteger maybeFloat
                    |> Maybe.withDefault Values.undefined

        Relation rel ->
            Maybe.map2 (,) (Values.asFloat leftValue) (Values.asFloat rightValue)
                |> Maybe.map (\( l, r ) -> Values.bool (applicativeForRelation rel l r))
                |> Maybe.withDefault Values.undefined

        Logic logic ->
            Maybe.map2 (,) (Values.asBool leftValue) (Values.asBool rightValue)
                |> Maybe.map (\( l, r ) -> Values.bool (applicativeForLogic logic l r))
                |> Maybe.withDefault Values.undefined

        Comparison comparison ->
            if Values.isUndefined leftValue || Values.isUndefined rightValue then
                Values.undefined
            else
                Values.bool (applicativeForComparison comparison leftValue rightValue)


binaryForIntArithmitic : ArithmeticOperator -> Int -> Int -> Int
binaryForIntArithmitic op =
    case op of
        Plus ->
            (+)

        Minus ->
            (-)

        Divide ->
            (//)

        Multiply ->
            (*)


binaryForFloatArithmitic : ArithmeticOperator -> Float -> Float -> Float
binaryForFloatArithmitic op =
    case op of
        Plus ->
            (+)

        Minus ->
            (-)

        Divide ->
            (/)

        Multiply ->
            (*)


applicativeForRelation : RelationOperator -> comparable -> comparable -> Bool
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


applicativeForLogic : LogicOperator -> Bool -> Bool -> Bool
applicativeForLogic logic =
    case logic of
        And ->
            (&&)

        Or ->
            (||)


applicativeForComparison : ComparisonOperator -> a -> a -> Bool
applicativeForComparison comparison =
    case comparison of
        NotEqual ->
            (/=)

        Equal ->
            (==)
