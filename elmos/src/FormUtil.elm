module FormUtil exposing (activeFields)

import AST exposing (Form, Label, Id, ValueType, Expression, FormItem(Field, ComputedField, IfThen, IfThenElse))
import Environment exposing (Environment)
import Values
import Evaluator


activeFields : Environment -> Form -> List ( Label, Id, ValueType )
activeFields env { items } =
    activeFieldsForItems env items


activeFieldsForItems : Environment -> List FormItem -> List ( Label, Id, ValueType )
activeFieldsForItems env =
    List.concatMap (activeFieldsForItem env)


activeFieldsForItem : Environment -> FormItem -> List ( Label, Id, ValueType )
activeFieldsForItem env item =
    case item of
        Field label identifier valueType ->
            [ ( label, identifier, valueType ) ]

        ComputedField label identifier valueType _ ->
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
