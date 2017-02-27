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

                maybeIntegerPair =
                    Maybe.map2 (,) (Values.asInt leftValue) (Values.asInt rightValue)

                maybeFloatPair =
                    Maybe.map2 (,) (Values.asFloat leftValue) (Values.asFloat rightValue)

                ( intOperator, floatOperator ) =
                    applicativeForOperator op
            in
                case ( maybeIntegerPair, maybeFloatPair ) of
                    ( Just ( l, r ), _ ) ->
                        Values.int (intOperator l r)

                    ( _, Just ( l, r ) ) ->
                        Values.float (floatOperator l r)

                    _ ->
                        Values.undefined

        RelationExpression op _ left right ->
            let
                leftValue =
                    evaluate env left

                rightValue =
                    evaluate env right
            in
                case Maybe.map2 (,) (Values.asInt leftValue) (Values.asInt rightValue) of
                    Just ( l, r ) ->
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


applicativeForOperator : Operator -> ( Int -> Int -> Int, Float -> Float -> Float )
applicativeForOperator op =
    case op of
        Plus ->
            ( (+), (+) )

        Minus ->
            ( (-), (-) )

        Divide ->
            ( (//), (/) )

        Multiply ->
            ( (*), (*) )


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
