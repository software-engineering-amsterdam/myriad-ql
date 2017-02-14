module TypeChecker.Expressions exposing (..)

import AST exposing (Expression(..), ValueType(IntegerType, BooleanType, StringType))
import Dict exposing (Dict)
import TypeChecker.Messages as Messages exposing (Message)


type alias VariableTypes =
    Dict String ValueType


getType : VariableTypes -> Expression -> Result (List Message) ValueType
getType variableTypes expression =
    case expression of
        Var x ->
            Result.fromMaybe
                [ Messages.undefinedExpressionVariable x () ]
                (Dict.get x variableTypes)

        AST.Str str ->
            Ok StringType

        AST.Integer integer ->
            Ok IntegerType

        AST.Boolean boolean ->
            Ok BooleanType

        ParensExpression inner ->
            getType variableTypes inner

        ArithmeticExpression op left right ->
            let
                handleSideTypes ( leftType, rightType ) =
                    case ( leftType, rightType ) of
                        ( IntegerType, IntegerType ) ->
                            Ok IntegerType

                        ( l, r ) ->
                            Err [ Messages.arithmeticExpressionTypeMismatch op l r ]
            in
                combineResult (++) handleSideTypes (getType variableTypes left) (getType variableTypes right)

        RelationExpression op left right ->
            let
                handleSideTypes ( leftType, rightType ) =
                    case ( leftType, rightType ) of
                        ( IntegerType, IntegerType ) ->
                            Ok BooleanType

                        ( l, r ) ->
                            Err [ Messages.relationExpressionTypeMismatch op l r ]
            in
                combineResult (++) handleSideTypes (getType variableTypes left) (getType variableTypes right)

        LogicExpression op left right ->
            let
                handleSideTypes ( leftType, rightType ) =
                    case ( leftType, rightType ) of
                        ( BooleanType, BooleanType ) ->
                            Ok BooleanType

                        ( l, r ) ->
                            Err [ Messages.logicExpressionTypeMismatch op l r ]
            in
                combineResult (++) handleSideTypes (getType variableTypes left) (getType variableTypes right)

        ComparisonExpression op left right ->
            let
                handleSideTypes ( leftType, rightType ) =
                    if leftType == rightType then
                        Ok BooleanType
                    else
                        Err [ Messages.comparisonExpressionTypeMismatch op leftType rightType ]
            in
                combineResult (++) handleSideTypes (getType variableTypes left) (getType variableTypes right)


combineResult : (e -> e -> e) -> (( a, a ) -> Result e a) -> Result e a -> Result e a -> Result e a
combineResult err succ left right =
    case ( left, right ) of
        ( Err l, Err r ) ->
            Err (err l r)

        _ ->
            Result.andThen succ (Result.map2 (,) left right)
