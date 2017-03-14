module QL.TypeChecker.Expressions.ConditionTypes exposing (conditionTypeErrors)

import QL.AST exposing (Form, FormItem(..), Expression(..), Id, ValueType(BooleanType), Location)
import QL.AST.Collectors as Collectors exposing (QuestionTypes)
import QL.TypeChecker.Expressions.ExpressionType exposing (getType)
import QL.TypeChecker.Messages exposing (Message(Error), ErrorMessage(InvalidConditionType))


conditionTypeErrors : Form -> QuestionTypes -> List Message
conditionTypeErrors form questionTypes =
    Collectors.collectConditions form
        |> List.filterMap (conditionWithType questionTypes)
        |> List.filter (Tuple.second >> badConditional)
        |> List.map (\( condition, conditionType ) -> Error <| InvalidConditionType (locationOf condition) conditionType)


conditionWithType : QuestionTypes -> Expression -> Maybe ( Expression, ValueType )
conditionWithType questionTypes condition =
    case getType questionTypes condition of
        Ok valueType ->
            Just ( condition, valueType )

        Err _ ->
            Nothing


badConditional : ValueType -> Bool
badConditional =
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

        ArithmeticExpression _ location _ _ ->
            location

        RelationExpression _ location _ _ ->
            location

        LogicExpression _ location _ _ ->
            location

        ComparisonExpression _ location _ _ ->
            location
