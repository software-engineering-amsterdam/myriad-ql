module TypeChecker.CheckerUtil exposing (..)

import AST exposing (..)
import List.Extra


type alias QuestionTypeRelations =
    List ( String, ValueType )


removeListFrom : List a -> List a -> List a
removeListFrom source target =
    List.foldl List.Extra.remove target source


intersectLists : List a -> List a -> List a
intersectLists a b =
    removeListFrom a <| removeListFrom a b


questionIds : QuestionTypeRelations -> List String
questionIds =
    List.map Tuple.first


questionTypeRelationsFromBlock : Block -> QuestionTypeRelations
questionTypeRelationsFromBlock =
    List.map questionTypeRelationsFromItem >> List.concat


questionTypeRelationsFromItem : FormItem -> QuestionTypeRelations
questionTypeRelationsFromItem item =
    case item of
        Field _ id valueType ->
            [ ( id, valueType ) ]

        ComputedField _ id valueType _ ->
            [ ( id, valueType ) ]

        IfThen _ _ ->
            []

        IfThenElse _ thenBranch elseBranch ->
            intersectLists
                (questionTypeRelationsFromBlock thenBranch)
                (questionTypeRelationsFromBlock elseBranch)
