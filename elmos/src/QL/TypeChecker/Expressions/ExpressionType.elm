module QL.TypeChecker.Expressions.ExpressionType exposing (getType)

import Dict exposing (Dict)
import QL.AST exposing (Form, FormItem(..), Expression(..), Id, ValueType(IntegerType, BooleanType, StringType, MoneyType), Location)
import QL.AST.Collectors exposing (QuestionTypes)
import QL.TypeChecker.Messages exposing (Message(Error), ErrorMessage(ArithmeticExpressionTypeMismatch, RelationExpressionTypeMismatch, LogicExpressionTypeMismatch, ComparisonExpressionTypeMismatch))


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

        ArithmeticExpression op loc left right ->
            let
                handleSideTypes ( leftType, rightType ) =
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
                            Err [ Error <| ArithmeticExpressionTypeMismatch op loc l r ]
            in
                combineResult (++) handleSideTypes (getType variableTypes left) (getType variableTypes right)

        RelationExpression op loc left right ->
            let
                handleSideTypes ( leftType, rightType ) =
                    case ( leftType, rightType ) of
                        ( IntegerType, IntegerType ) ->
                            Ok BooleanType

                        ( l, r ) ->
                            Err [ Error <| RelationExpressionTypeMismatch op loc l r ]
            in
                combineResult (++) handleSideTypes (getType variableTypes left) (getType variableTypes right)

        LogicExpression op loc left right ->
            let
                handleSideTypes ( leftType, rightType ) =
                    case ( leftType, rightType ) of
                        ( BooleanType, BooleanType ) ->
                            Ok BooleanType

                        ( l, r ) ->
                            Err [ Error <| LogicExpressionTypeMismatch op loc l r ]
            in
                combineResult (++) handleSideTypes (getType variableTypes left) (getType variableTypes right)

        ComparisonExpression op loc left right ->
            let
                handleSideTypes ( leftType, rightType ) =
                    if leftType == rightType then
                        Ok BooleanType
                    else
                        Err [ Error <| ComparisonExpressionTypeMismatch op loc leftType rightType ]
            in
                combineResult (++) handleSideTypes (getType variableTypes left) (getType variableTypes right)


combineResult : (e -> e -> e) -> (( a, a ) -> Result e a) -> Result e a -> Result e a -> Result e a
combineResult err succ left right =
    case ( left, right ) of
        ( Err l, Err r ) ->
            Err (err l r)

        _ ->
            Result.andThen succ (Result.map2 (,) left right)
