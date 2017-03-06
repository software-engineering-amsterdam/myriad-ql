module UI.FormUpdater exposing (updateValue)

import QL.AST exposing (Form, Label, ValueType, Expression, FormItem(Field, ComputedField, IfThen, IfThenElse))
import QL.Environment as Env exposing (Environment)
import QL.Values as Values exposing (Value)
import QL.Evaluator as Evaluator
import UI.Field as Field exposing (Field(Computed, Editable))


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
    Field.activeFields env form
        |> List.filterMap
            (\field ->
                case field of
                    Computed _ identifier _ expression ->
                        Just ( identifier, expression )

                    _ ->
                        Nothing
            )
