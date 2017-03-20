module QL.TypeChecker.Expressions.ComputedFieldTypes exposing (computedFieldTypeErrors)

import Dict exposing (Dict)
import QL.TypeChecker.Expressions.ExpressionType exposing (getType)
import QL.AST exposing (Form, FormItem(..), Expression(..), Id, ValueType(MoneyType, IntegerType), Location)
import QL.AST.Collectors as Collectors exposing (TypeEnvironment)
import QL.TypeChecker.Messages exposing (Message(Error), ErrorMessage(InvalidComputedFieldType))


computedFieldTypeErrors : Form -> TypeEnvironment -> List Message
computedFieldTypeErrors form typeEnv =
    Collectors.collectComputedFields form
        |> List.filterMap (computationToType typeEnv)
        |> List.filterMap (withExpectedType typeEnv)
        |> List.filter badComputedField
        |> List.map (\( id, actualType, expectedType ) -> Error (InvalidComputedFieldType id actualType expectedType))


computationToType : TypeEnvironment -> ( Id, Expression ) -> Maybe ( Id, ValueType )
computationToType typeEnv ( name, computation ) =
    case getType typeEnv computation of
        Ok valueType ->
            Just ( name, valueType )

        Err _ ->
            Nothing


withExpectedType : TypeEnvironment -> ( Id, ValueType ) -> Maybe ( Id, ValueType, ValueType )
withExpectedType typeEnv ( ( name, _ ) as id, actualType ) =
    Dict.get name typeEnv
        |> Maybe.map (\expectedType -> ( id, actualType, expectedType ))


badComputedField : ( Id, ValueType, ValueType ) -> Bool
badComputedField ( _, computationType, fieldType ) =
    case ( computationType, fieldType ) of
        ( IntegerType, MoneyType ) ->
            False

        _ ->
            computationType /= fieldType
