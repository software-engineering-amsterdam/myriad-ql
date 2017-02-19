module TypeChecker.CheckerUtil exposing (..)

import AST exposing (..)
import List.Extra
import Set exposing (Set)


type alias QuestionTypeRelations =
    List ( String, ValueType )


removeListFrom : List a -> List a -> List a
removeListFrom toBeRemoved target =
    List.foldl List.Extra.remove target toBeRemoved


intersectLists : List a -> List a -> List a
intersectLists a b =
    removeListFrom (removeListFrom b a) a


duplicates : List comparable -> List comparable
duplicates list =
    removeListFrom (List.Extra.unique list) list


questionIds : QuestionTypeRelations -> List String
questionIds =
    List.map Tuple.first


availableIdentifiersOnScope : Set String -> Block -> Set String
availableIdentifiersOnScope parentIdentifiers block =
    questionTypeRelationsFromBlock block
        |> questionIds
        |> Set.fromList
        |> Set.union parentIdentifiers


questionTypeRelationsFromBlock : Block -> QuestionTypeRelations
questionTypeRelationsFromBlock =
    List.map questionTypeRelationsFromItem >> List.concat


questionTypeRelationsFromItem : FormItem -> QuestionTypeRelations
questionTypeRelationsFromItem item =
    case item of
        Field _ ( id, loc ) valueType ->
            [ ( id, valueType ) ]

        ComputedField _ ( id, loc ) valueType _ ->
            [ ( id, valueType ) ]

        IfThen _ _ ->
            []

        IfThenElse _ thenBranch elseBranch ->
            intersectLists
                (questionTypeRelationsFromBlock thenBranch)
                (questionTypeRelationsFromBlock elseBranch)
