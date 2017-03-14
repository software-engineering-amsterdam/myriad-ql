module QL.TypeChecker.Expressions.ComputedFieldTypes exposing (computedFieldTypeErrors)

import Dict exposing (Dict)
import QL.TypeChecker.Expressions.ExpressionType exposing (getType)
import QL.AST exposing (Form, FormItem(..), Expression(..), Id, ValueType(MoneyType, IntegerType), Location)
import QL.AST.Collectors as Collectors exposing (QuestionTypes)
import QL.TypeChecker.Messages exposing (Message(Error), ErrorMessage(InvalidComputedFieldType))


computedFieldTypeErrors : Form -> QuestionTypes -> List Message
computedFieldTypeErrors form questionTypes =
    Collectors.collectComputedFields form
        |> List.filterMap (computationToType questionTypes)
        |> List.filterMap (withExpectedType questionTypes)
        |> List.filter badComputedField
        |> List.map (\( id, actualType, expectedType ) -> Error <| InvalidComputedFieldType id actualType expectedType)


computationToType : QuestionTypes -> ( Id, Expression ) -> Maybe ( Id, ValueType )
computationToType questionTypes ( name, computation ) =
    case getType questionTypes computation of
        Ok valueType ->
            Just ( name, valueType )

        Err _ ->
            Nothing


withExpectedType : QuestionTypes -> ( Id, ValueType ) -> Maybe ( Id, ValueType, ValueType )
withExpectedType questionTypes ( ( name, _ ) as id, actualType ) =
    Dict.get name questionTypes
        |> Maybe.map (\expectedType -> ( id, actualType, expectedType ))


badComputedField : ( Id, ValueType, ValueType ) -> Bool
badComputedField ( _, computationType, fieldType ) =
    case ( computationType, fieldType ) of
        ( IntegerType, MoneyType ) ->
            False

        _ ->
            computationType /= fieldType
