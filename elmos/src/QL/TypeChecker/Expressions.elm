module QL.TypeChecker.Expressions exposing (typeCheckerErrors, computedFieldTypeErrors, conditionTypeErrors, getType)

import Dict exposing (Dict)
import QL.AST exposing (Form, FormItem(..), Expression(..), Id, ValueType(IntegerType, BooleanType, StringType, MoneyType), Location)
import QL.AST.Collectors as Collectors exposing (QuestionTypes)
import QL.TypeChecker.Messages as Messages exposing (Message)


typeCheckerErrors : Form -> List Message
typeCheckerErrors form =
    let
        questionTypes =
            Collectors.collectQuestionTypes form
    in
        operandTypeErrors form questionTypes
            ++ conditionTypeErrors form questionTypes
            ++ computedFieldTypeErrors form questionTypes


computedFieldTypeErrors : Form -> QuestionTypes -> List Message
computedFieldTypeErrors form questionTypes =
    Collectors.collectComputedFields form
        |> List.filterMap (computationToType questionTypes)
        |> List.filterMap (withExpectedType questionTypes)
        |> List.filter badComputedField
        |> List.map (\( id, actualType, expectedType ) -> Messages.invalidComputedFieldType id actualType expectedType)


badComputedField : ( Id, ValueType, ValueType ) -> Bool
badComputedField ( _, computedType, fieldType ) =
    computedType /= fieldType


withExpectedType : QuestionTypes -> ( Id, ValueType ) -> Maybe ( Id, ValueType, ValueType )
withExpectedType questionTypes ( ( name, _ ) as id, actualType ) =
    Dict.get name questionTypes
        |> Maybe.map (\expectedType -> ( id, actualType, expectedType ))


conditionTypeErrors : Form -> QuestionTypes -> List Message
conditionTypeErrors form questionTypes =
    Collectors.collectConditions form
        |> List.filterMap (conditionWithType questionTypes)
        |> List.filter (Tuple.second >> badConditional)
        |> List.map (\( condition, conditionType ) -> (Messages.invalidConditionType (locationOf condition) conditionType))


computationToType : QuestionTypes -> ( Id, Expression ) -> Maybe ( Id, ValueType )
computationToType questionTypes ( name, computation ) =
    case getType questionTypes computation of
        Ok valueType ->
            Just ( name, valueType )

        Err _ ->
            Nothing


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


operandTypeErrors : Form -> QuestionTypes -> List Message
operandTypeErrors form questionTypes =
    Collectors.collectExpressions form
        |> List.concatMap (checkExpression questionTypes)


checkExpression : QuestionTypes -> Expression -> List Message
checkExpression questionTypes expression =
    case getType questionTypes expression of
        Ok _ ->
            []

        Err messages ->
            messages


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
