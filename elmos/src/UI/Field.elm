module UI.Field exposing (Field(Editable, Computed), fieldValueType, activeFields, fieldForName)

import QL.AST exposing (Form, Label, ValueType, Expression, FormItem(Question, ComputedQuestion, IfThen, IfThenElse))
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


fieldValueType : Field -> ValueType
fieldValueType field =
    case field of
        Editable _ _ valueType ->
            valueType

        Computed _ _ valueType _ ->
            valueType


fieldForName : String -> List Field -> Maybe Field
fieldForName name =
    List.find (\field -> name == fieldName field)


activeFields : Environment -> Form -> List Field
activeFields env { items } =
    activeFieldsForItems env items


activeFieldsForItems : Environment -> List FormItem -> List Field
activeFieldsForItems env =
    List.concatMap (activeFieldsForItem env)


activeFieldsForItem : Environment -> FormItem -> List Field
activeFieldsForItem env item =
    case item of
        Question label ( identifier, _ ) valueType ->
            [ Editable label identifier valueType ]

        ComputedQuestion label ( identifier, _ ) valueType e ->
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
