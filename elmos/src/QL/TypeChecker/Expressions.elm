module QL.TypeChecker.Expressions exposing (..)

import Dict exposing (Dict)
import QL.AST exposing (Form, FormItem(..), Expression(..), ValueType(IntegerType, BooleanType, StringType, MoneyType))
import QL.TypeChecker.Messages as Messages exposing (Message)


type alias VariableTypes =
    Dict String ValueType


typeCheckerErrors : Form -> List Message
typeCheckerErrors form =
    expressionFromBlock form.items
        |> List.concatMap typeCheckerErrorsFromExpression


{-| Do not remove the argument. There is a bug in the elm-compiler that will result in a runtime error. Possibly due to the currying
-}
expressionFromBlock : List FormItem -> List Expression
expressionFromBlock x =
    List.concatMap expressionsFromItem x


expressionsFromItem : FormItem -> List Expression
expressionsFromItem formItem =
    case formItem of
        Field _ _ _ ->
            []

        ComputedField _ _ _ computation ->
            [ computation ]

        IfThen condition thenBlock ->
            condition
                :: expressionFromBlock thenBlock

        IfThenElse condition thenBlock elseBlock ->
            condition
                :: expressionFromBlock thenBlock
                ++ expressionFromBlock elseBlock


typeCheckerErrorsFromExpression : Expression -> List Message
typeCheckerErrorsFromExpression expression =
    case (getType Dict.empty expression) of
        Ok _ ->
            []

        Err messages ->
            messages


getType : VariableTypes -> Expression -> Result (List Message) ValueType
getType variableTypes expression =
    case expression of
        Var ( name, loc ) ->
            Result.fromMaybe
                [ Messages.undefinedExpressionVariable name loc ]
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

                        ( l, r ) ->
                            Err [ Messages.arithmeticExpressionTypeMismatch op loc l r ]
            in
                combineResult (++) handleSideTypes (getType variableTypes left) (getType variableTypes right)

        RelationExpression op loc left right ->
            let
                handleSideTypes ( leftType, rightType ) =
                    case ( leftType, rightType ) of
                        ( IntegerType, IntegerType ) ->
                            Ok BooleanType

                        ( l, r ) ->
                            Err [ Messages.relationExpressionTypeMismatch op loc l r ]
            in
                combineResult (++) handleSideTypes (getType variableTypes left) (getType variableTypes right)

        LogicExpression op loc left right ->
            let
                handleSideTypes ( leftType, rightType ) =
                    case ( leftType, rightType ) of
                        ( BooleanType, BooleanType ) ->
                            Ok BooleanType

                        ( l, r ) ->
                            Err [ Messages.logicExpressionTypeMismatch op loc l r ]
            in
                combineResult (++) handleSideTypes (getType variableTypes left) (getType variableTypes right)

        ComparisonExpression op loc left right ->
            let
                handleSideTypes ( leftType, rightType ) =
                    if leftType == rightType then
                        Ok BooleanType
                    else
                        Err [ Messages.comparisonExpressionTypeMismatch op loc leftType rightType ]
            in
                combineResult (++) handleSideTypes (getType variableTypes left) (getType variableTypes right)


combineResult : (e -> e -> e) -> (( a, a ) -> Result e a) -> Result e a -> Result e a -> Result e a
combineResult err succ left right =
    case ( left, right ) of
        ( Err l, Err r ) ->
            Err (err l r)

        _ ->
            Result.andThen succ (Result.map2 (,) left right)
