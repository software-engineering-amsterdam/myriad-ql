module QL.TypeChecker.Expressions.ExpressionType exposing (getType)

import Dict exposing (Dict)
import QL.AST exposing (..)
import QL.AST.Collectors exposing (QuestionTypes)
import QL.TypeChecker.Messages exposing (..)


getType : QuestionTypes -> Expression -> Result (List Message) ValueType
getType variableTypes expression =
    case expression of
        Var ( name, _ ) ->
            Result.fromMaybe
                []
                (Dict.get name variableTypes)

        Str _ _ ->
            Ok StringType

        Integer _ _ ->
            Ok IntegerType

        Decimal _ _ ->
            Ok MoneyType

        Boolean _ _ ->
            Ok BooleanType

        ParensExpression _ inner ->
            getType variableTypes inner

        BinaryExpression op loc left right ->
            let
                leftType =
                    getType variableTypes left

                rightType =
                    getType variableTypes right
            in
                checkExpressionOnValidValueTypes leftType rightType (getTypeForBinaryExpression op loc)


getTypeForBinaryExpression : Operator -> Location -> ( ValueType, ValueType ) -> Result (List Message) ValueType
getTypeForBinaryExpression op loc ( leftType, rightType ) =
    case op of
        Arithmetic arithmetic ->
            case ( leftType, rightType ) of
                ( IntegerType, IntegerType ) ->
                    Ok IntegerType

                ( MoneyType, IntegerType ) ->
                    Ok MoneyType

                ( IntegerType, MoneyType ) ->
                    Ok MoneyType

                ( MoneyType, MoneyType ) ->
                    Ok MoneyType

                ( l, r ) ->
                    Err [ Error <| ArithmeticExpressionTypeMismatch arithmetic loc l r ]

        Relation relation ->
            case ( leftType, rightType ) of
                ( IntegerType, IntegerType ) ->
                    Ok BooleanType

                ( l, r ) ->
                    Err [ Error <| RelationExpressionTypeMismatch relation loc l r ]

        Logic logic ->
            case ( leftType, rightType ) of
                ( BooleanType, BooleanType ) ->
                    Ok BooleanType

                ( l, r ) ->
                    Err [ Error <| LogicExpressionTypeMismatch logic loc l r ]

        Comparison comparison ->
            if leftType == rightType then
                Ok BooleanType
            else
                Err [ Error <| ComparisonExpressionTypeMismatch comparison loc leftType rightType ]


checkExpressionOnValidValueTypes :
    Result (List Message) ValueType
    -> Result (List Message) ValueType
    -> (( ValueType, ValueType ) -> Result (List Message) ValueType)
    -> Result (List Message) ValueType
checkExpressionOnValidValueTypes left right succ =
    case ( left, right ) of
        ( Err l, Err r ) ->
            Err (l ++ r)

        _ ->
            Result.andThen succ (Result.map2 (,) left right)
