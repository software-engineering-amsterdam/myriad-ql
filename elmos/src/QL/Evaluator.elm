module QL.Evaluator exposing (evaluate)

import QL.AST as AST
    exposing
        ( Expression(..)
        , Operator(Plus, Minus, Divide, Multiply)
        , Relation(LessThan, LessThanOrEqual, GreaterThan, GreaterThanOrEqual)
        , Logic(And, Or)
        , Comparison(Equal, NotEqual)
        )
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

        ArithmeticExpression op _ left right ->
            let
                leftValue =
                    evaluate env left

                rightValue =
                    evaluate env right

                maybeInteger =
                    Maybe.map2 (,) (Values.asInt leftValue) (Values.asInt rightValue)
                        |> Maybe.map (\( l, r ) -> Values.int (binaryForIntArithmitic op l r))

                maybeFloat =
                    Maybe.map2 (,) (Values.asFloat leftValue) (Values.asFloat rightValue)
                        |> Maybe.map (\( l, r ) -> Values.float (binaryForFloatArithmitic op l r))
            in
                Maybe.or maybeInteger maybeFloat
                    |> Maybe.withDefault Values.undefined

        RelationExpression op _ left right ->
            let
                leftValue =
                    evaluate env left

                rightValue =
                    evaluate env right
            in
                Maybe.map2 (,) (Values.asFloat leftValue) (Values.asFloat rightValue)
                    |> Maybe.map (\( l, r ) -> Values.bool (applicativeForRelation op l r))
                    |> Maybe.withDefault Values.undefined

        LogicExpression op _ left right ->
            let
                leftValue =
                    evaluate env left

                rightValue =
                    evaluate env right
            in
                Maybe.map2 (,) (Values.asBool leftValue) (Values.asBool rightValue)
                    |> Maybe.map (\( l, r ) -> Values.bool (applicativeForLogic op l r))
                    |> Maybe.withDefault Values.undefined

        ComparisonExpression op _ left right ->
            let
                leftValue =
                    evaluate env left

                rightValue =
                    evaluate env right
            in
                if Values.isUndefined leftValue || Values.isUndefined rightValue then
                    Values.undefined
                else
                    Values.bool (applicativeForComparison op leftValue rightValue)


binaryForIntArithmitic : Operator -> Int -> Int -> Int
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


binaryForFloatArithmitic : Operator -> Float -> Float -> Float
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


applicativeForRelation : Relation -> comparable -> comparable -> Bool
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
