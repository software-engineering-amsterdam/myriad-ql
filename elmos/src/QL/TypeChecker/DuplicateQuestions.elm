module QL.TypeChecker.DuplicateQuestions exposing (duplicateQuestions)

import QL.AST exposing (..)
import Dict exposing (Dict)
import List.Extra as List
import QL.AST.Collectors as Collectors
import QL.TypeChecker.QuestionIndex as QuestionIndex exposing (QuestionIndex)
import QL.TypeChecker.Messages exposing (Message(Error), ErrorMessage(DuplicateQuestionDefinition))


type alias Duplicate =
    ( String, List Location )


type alias DuplicateIndex =
    Dict String (List Location)


duplicateQuestions : Form -> List Message
duplicateQuestions form =
    let
        questionIds =
            QuestionIndex.questionIndexFromForm form

        itemIds =
            Collectors.collectDeclaredIds form
    in
        List.filterMap (duplicateQuestionDeclarations questionIds) itemIds
            |> mergeOverlappingDuplicates
            |> Dict.map (\k v -> DuplicateQuestionDefinition k v |> Error)
            |> Dict.values


mergeOverlappingDuplicates : List Duplicate -> DuplicateIndex
mergeOverlappingDuplicates duplicates =
    List.foldl updateDuplicateIndex Dict.empty duplicates


updateDuplicateIndex : Duplicate -> DuplicateIndex -> DuplicateIndex
updateDuplicateIndex ( id, locations ) duplicateIndex =
    Dict.update id (Maybe.withDefault [] >> mergeLocations locations >> Just) duplicateIndex


mergeLocations : List Location -> List Location -> List Location
mergeLocations newLocations existingLocations =
    (existingLocations ++ newLocations)
        |> List.uniqueBy (\(Location line col) -> ( line, col ))


duplicateQuestionDeclarations : QuestionIndex -> Id -> Maybe Duplicate
duplicateQuestionDeclarations declaredQuestions (( value, _ ) as question) =
    Dict.get value declaredQuestions
        |> Maybe.andThen (asDuplicate question)


asDuplicate : Id -> List Location -> Maybe Duplicate
asDuplicate ( value, questionLocation ) declaredQuestionLocations =
    if List.member questionLocation declaredQuestionLocations then
        Nothing
    else
        Just ( value, declaredQuestionLocations ++ [ questionLocation ] )
