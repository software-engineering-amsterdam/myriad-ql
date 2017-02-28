module UI.FormUtil exposing (Field(Editable, Computed), updateValue, activeFields, fieldValueType)

import QL.AST exposing (Form, Label, ValueType, Expression, FormItem(Field, ComputedField, IfThen, IfThenElse))
import QL.Environment as Env exposing (Environment)
import QL.Values as Values exposing (Value)
import QL.Evaluator as Evaluator


type Field
    = Editable Label String ValueType
    | Computed Label String ValueType Expression


fieldValueType : Field -> ValueType
fieldValueType field =
    case field of
        Editable _ _ valueType ->
            valueType

        Computed _ _ valueType _ ->
            valueType


updateValue : String -> Value -> Environment -> Form -> Environment
updateValue fieldId value env form =
    Env.withFormValue fieldId value env
        |> updateComputedFields form


updateComputedFields : Form -> Environment -> Environment
updateComputedFields form env =
    let
        newEnv =
            activeComputedFields env form
                |> List.map (Tuple.mapSecond (Evaluator.evaluate env))
                |> List.foldr (\( identifier, value ) -> Env.withFormValue identifier value) env
    in
        if newEnv == env then
            env
        else
            updateComputedFields form newEnv


activeComputedFields : Environment -> Form -> List ( String, Expression )
activeComputedFields env form =
    activeFields env form
        |> List.filterMap
            (\field ->
                case field of
                    Computed _ identifier _ expression ->
                        Just ( identifier, expression )

                    _ ->
                        Nothing
            )


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
