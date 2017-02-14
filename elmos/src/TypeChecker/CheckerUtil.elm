module TypeChecker.CheckerUtil exposing (..)

import AST exposing (..)
import List.Extra


type alias QuestionTypeRelations =
    List ( String, ValueType )


removeListFrom : List a -> List a -> List a
removeListFrom toBeRemoved target =
    List.foldl List.Extra.remove target toBeRemoved


intersectLists : List a -> List a -> List a
intersectLists a b =
    removeListFrom (removeListFrom b a) a


questionIds : QuestionTypeRelations -> List String
questionIds =
    List.map Tuple.first


questionTypeRelationsFromBlock : Block -> QuestionTypeRelations
questionTypeRelationsFromBlock =
    List.map questionTypeRelationsFromItem >> List.concat


questionTypeRelationsFromItem : FormItem -> QuestionTypeRelations
questionTypeRelationsFromItem item =
    case item of
        Field _ ( id, _ ) valueType ->
            [ ( id, valueType ) ]

        ComputedField _ ( id, _ ) valueType _ ->
            [ ( id, valueType ) ]

        IfThen _ _ ->
            []

        IfThenElse _ thenBranch elseBranch ->
            intersectLists
                (questionTypeRelationsFromBlock thenBranch)
                (questionTypeRelationsFromBlock elseBranch)
