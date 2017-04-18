module QL.TypeChecker.Expressions.ExpressionType exposing (getType)

import Dict exposing (Dict)
import QL.AST exposing (..)
import QL.AST.Collectors exposing (TypeEnvironment)
import QL.TypeChecker.Messages exposing (..)


getType : TypeEnvironment -> Expression -> Result (List Message) ValueType
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
                case ( leftType, rightType ) of
                    ( Err l, Err r ) ->
                        Err (l ++ r)

                    ( Err _, _ ) ->
                        leftType

                    ( _, Err _ ) ->
                        rightType

                    ( Ok l, Ok r ) ->
                        getTypeForBinaryExpression op loc l r


getTypeForBinaryExpression : Operator -> Location -> ValueType -> ValueType -> Result (List Message) ValueType
getTypeForBinaryExpression op loc leftType rightType =
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

                _ ->
                    Err [ Error (ArithmeticExpressionTypeMismatch arithmetic loc leftType rightType) ]

        Relation relation ->
            case ( leftType, rightType ) of
                ( IntegerType, IntegerType ) ->
                    Ok BooleanType

                ( MoneyType, IntegerType ) ->
                    Ok BooleanType

                ( IntegerType, MoneyType ) ->
                    Ok BooleanType

                ( MoneyType, MoneyType ) ->
                    Ok BooleanType

                _ ->
                    Err [ Error (RelationExpressionTypeMismatch relation loc leftType rightType) ]

        Logic logic ->
            case ( leftType, rightType ) of
                ( BooleanType, BooleanType ) ->
                    Ok BooleanType

                _ ->
                    Err [ Error (LogicExpressionTypeMismatch logic loc leftType rightType) ]

        Comparison comparison ->
            if leftType == rightType then
                Ok BooleanType
            else
                Err [ Error (ComparisonExpressionTypeMismatch comparison loc leftType rightType) ]
