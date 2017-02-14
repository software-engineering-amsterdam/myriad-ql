module FormUtil exposing (activeFields)

import AST exposing (Form, Label, Id, ValueType, Expression, FormItem(Field, ComputedField, IfThen, IfThenElse))
import Environment exposing (Environment)


activeFields : Environment -> Form -> List ( Label, Id, ValueType )
activeFields env { items } =
    []


activeFieldsForItems : Environment -> List FormItem -> List ( Label, Id, ValueType )
activeFieldsForItems env items =
    []


activeFieldsForItem : Environment -> FormItem -> List ( Label, Id, ValueType )
activeFieldsForItem env item =
    []


isTrueValue : Environment -> Expression -> Bool
isTrueValue env expression =
    False
