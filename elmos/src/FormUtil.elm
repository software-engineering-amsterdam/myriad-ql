module FormUtil exposing (activeFields)

import AST exposing (Form, Label, ValueType, Expression, FormItem(Field, ComputedField, IfThen, IfThenElse))
import Environment exposing (Environment)
import Values
import Evaluator


activeFields : Environment -> Form -> List ( Label, String, ValueType )
activeFields env { items } =
    activeFieldsForItems env items


activeFieldsForItems : Environment -> List FormItem -> List ( Label, String, ValueType )
activeFieldsForItems env =
    List.concatMap (activeFieldsForItem env)


activeFieldsForItem : Environment -> FormItem -> List ( Label, String, ValueType )
activeFieldsForItem env item =
    case item of
        Field label ( identifier, _ ) valueType ->
            [ ( label, identifier, valueType ) ]

        ComputedField label ( identifier, _ ) valueType _ ->
            [ ( label, identifier, valueType ) ]

        IfThen expression thenBranch ->
            if isTrueValue env expression then
                activeFieldsForItems env thenBranch
            else
                []

        IfThenElse expression thenBranch elseBranch ->
            if isTrueValue env expression then
                activeFieldsForItems env thenBranch
            else
                activeFieldsForItems env elseBranch


isTrueValue : Environment -> Expression -> Bool
isTrueValue env expression =
    Evaluator.evaluate env expression
        |> Values.asBool
        |> Maybe.withDefault False
