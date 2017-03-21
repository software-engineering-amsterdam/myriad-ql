module QL.TypeChecker.Expressions.ConditionTypes exposing (conditionTypeErrors)

import QL.AST exposing (Form, FormItem(..), Expression(..), Id, ValueType(BooleanType), Location)
import QL.AST.Collectors as Collectors exposing (TypeEnvironment)
import QL.TypeChecker.Expressions.ExpressionType exposing (getType)
import QL.TypeChecker.Messages exposing (Message(Error), ErrorMessage(InvalidConditionType))


conditionTypeErrors : Form -> TypeEnvironment -> List Message
conditionTypeErrors form typeEnv =
    Collectors.collectConditions form
        |> List.filterMap (conditionWithType typeEnv)
        |> List.filter (\( _, conditionType ) -> isBadConditional conditionType)
        |> List.map (\( condition, conditionType ) -> Error (InvalidConditionType (locationOf condition) conditionType))


conditionWithType : TypeEnvironment -> Expression -> Maybe ( Expression, ValueType )
conditionWithType typeEnv condition =
    case getType typeEnv condition of
        Ok valueType ->
            Just ( condition, valueType )

        Err _ ->
            Nothing


isBadConditional : ValueType -> Bool
isBadConditional =
    (/=) BooleanType


locationOf : Expression -> Location
locationOf expression =
    case expression of
        Var ( _, location ) ->
            location

        Str location _ ->
            location

        Decimal location _ ->
            location

        Integer location _ ->
            location

        Boolean location _ ->
            location

        ParensExpression location _ ->
            location

        BinaryExpression _ location _ _ ->
            location
