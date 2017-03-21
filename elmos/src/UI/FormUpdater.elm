module UI.FormUpdater exposing (updateValue, updateComputedQuestions)

import QL.AST exposing (Form, Label, ValueType, Expression, FormItem)
import QL.Environment as Env exposing (Environment)
import QL.Values exposing (Value)
import QL.Evaluator as Evaluator
import UI.Field as Field exposing (Field(Computed))


updateValue : String -> Value -> Environment -> Form -> Environment
updateValue fieldId value env form =
    Env.withFormValue fieldId value env
        |> updateComputedQuestions form


updateComputedQuestions : Form -> Environment -> Environment
updateComputedQuestions form env =
    let
        newEnv =
            activeComputedQuestions env form
                |> List.map (Tuple.mapSecond (Evaluator.evaluate env))
                |> List.foldr (\( identifier, value ) -> Env.withFormValue identifier value) env
    in
        if newEnv == env then
            env
        else
            updateComputedQuestions form newEnv


activeComputedQuestions : Environment -> Form -> List ( String, Expression )
activeComputedQuestions env form =
    Field.activeFields env form
        |> List.filterMap
            (\field ->
                case field of
                    Computed _ identifier _ expression ->
                        Just ( identifier, expression )

                    _ ->
                        Nothing
            )
