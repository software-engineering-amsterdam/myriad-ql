module UI.Field exposing (Field(Editable, Computed), fieldValueType, activeFields, visibleFieldForName)

import QL.AST exposing (Form, Label, ValueType, Expression, FormItem(Field, ComputedField, IfThen, IfThenElse))
import QL.Environment exposing (Environment)
import QL.Values as Values exposing (Value)
import QL.Evaluator as Evaluator
import List.Extra as List


type Field
    = Editable Label String ValueType
    | Computed Label String ValueType Expression


fieldName : Field -> String
fieldName field =
    case field of
        Editable _ name _ ->
            name

        Computed _ name _ _ ->
            name



-- TODO: rename to valueType


fieldValueType : Field -> ValueType
fieldValueType field =
    case field of
        Editable _ _ valueType ->
            valueType

        Computed _ _ valueType _ ->
            valueType


visibleFieldForName : String -> List Field -> Maybe Field
visibleFieldForName name visibleFields =
    List.find (\field -> name == fieldName field) visibleFields


activeFields : Environment -> Form -> List Field
activeFields env { items } =
    activeFieldsForItems env items


activeFieldsForItems : Environment -> List FormItem -> List Field
activeFieldsForItems env =
    List.concatMap (activeFieldsForItem env)


activeFieldsForItem : Environment -> FormItem -> List Field
activeFieldsForItem env item =
    case item of
        Field label ( identifier, _ ) valueType ->
            [ Editable label identifier valueType ]

        ComputedField label ( identifier, _ ) valueType e ->
            [ Computed label identifier valueType e ]

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
