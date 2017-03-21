module QL.Evaluator exposing (evaluate)

import QL.AST as AST exposing (..)
import QL.Environment as Environment exposing (Environment)
import QL.Values as Values exposing (Value)
import Maybe.Extra as Maybe
import QL.Numbers as Numbers


evaluate : Environment -> Expression -> Value
evaluate env expression =
    case expression of
        Var ( x, _ ) ->
            Environment.getFormValue x env
                |> Maybe.withDefault Values.Undefined

        AST.Str _ str ->
            Values.Str str

        AST.Integer _ integer ->
            Values.Integer integer

        AST.Decimal _ decimal ->
            Values.Decimal decimal

        AST.Boolean _ boolean ->
            Values.Boolean boolean

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
                        |> Maybe.filter Numbers.isValidInt
                        |> Maybe.map Values.Integer

                maybeDecimal =
                    Maybe.map2 (,) (Values.asDecimal leftValue) (Values.asDecimal rightValue)
                        |> Maybe.map (\( l, r ) -> binaryForDecimalArithmitic arithmetic l r)
                        |> Maybe.filter Numbers.isValidFloat
                        |> Maybe.map Values.Decimal
            in
                Maybe.or maybeInteger maybeDecimal
                    |> Maybe.withDefault Values.Undefined

        Relation rel ->
            Maybe.map2 (,) (Values.asDecimal leftValue) (Values.asDecimal rightValue)
                |> Maybe.map (\( l, r ) -> Values.Boolean (applicativeForRelation rel l r))
                |> Maybe.withDefault Values.Undefined

        Logic logic ->
            Maybe.map2 (,) (Values.asBool leftValue) (Values.asBool rightValue)
                |> Maybe.map (\( l, r ) -> Values.Boolean (applicativeForLogic logic l r))
                |> Maybe.withDefault Values.Undefined

        Comparison comparison ->
            if Values.isUndefined leftValue || Values.isUndefined rightValue then
                Values.Undefined
            else
                Values.Boolean (applicativeForComparison comparison leftValue rightValue)


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


binaryForDecimalArithmitic : ArithmeticOperator -> Float -> Float -> Float
binaryForDecimalArithmitic op =
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
