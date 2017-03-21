module QL.TypeChecker.DuplicateQuestions exposing (check)

import QL.AST exposing (..)
import Dict exposing (Dict)
import QL.AST.Collectors as Collectors
import QL.TypeChecker.QuestionIndex as QuestionIndex exposing (QuestionIndex)
import QL.TypeChecker.Messages exposing (Message(Error), ErrorMessage(DuplicateQuestionDefinition))


type alias Duplicate =
    ( String, List Location )


type alias DuplicateIndex =
    Dict String (List Location)


check : Form -> List Message
check form =
    let
        {- questionIndex contains the unique values and ignores duplicates -}
        questionIds =
            QuestionIndex.questionIndexFromForm form

        itemIds =
            Collectors.collectDeclaredIds form
    in
        List.filterMap (duplicateQuestionDeclarations questionIds) itemIds
            |> mergeOverlappingDuplicates
            |> Dict.map (\id locations -> Error (DuplicateQuestionDefinition id locations))
            |> Dict.values


mergeOverlappingDuplicates : List Duplicate -> DuplicateIndex
mergeOverlappingDuplicates =
    List.foldl updateDuplicateIndex Dict.empty


updateDuplicateIndex : Duplicate -> DuplicateIndex -> DuplicateIndex
updateDuplicateIndex ( id, locations ) =
    Dict.update id (Maybe.withDefault [] >> mergeLocations locations >> Just)


mergeLocations : List Location -> List Location -> List Location
mergeLocations newLocations existingLocations =
    existingLocations
        ++ List.filter (\x -> not (List.member x existingLocations)) newLocations


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
