module QL.TypeChecker.DuplicateLabels exposing (duplicateLabels)

import Dict exposing (Dict)
import Dict.Extra as Dict
import QL.AST exposing (Form, Id)
import QL.AST.Collectors as Collectors
import QL.TypeChecker.Messages exposing (Message(Warning), WarningMessage(DuplicateLabels))


duplicateLabels : Form -> List Message
duplicateLabels form =
    Collectors.collectQuestionLabels form
        |> groupByLabel
        |> Dict.filter hasMultipleDeclarations
        |> Dict.map toDuplicateLabelMessage
        |> Dict.values


groupByLabel : List ( Id, String ) -> Dict String (List ( Id, String ))
groupByLabel =
    Dict.groupBy Tuple.second


hasMultipleDeclarations : b -> List a -> Bool
hasMultipleDeclarations _ occurrences =
    List.length occurrences > 1


toDuplicateLabelMessage : String -> List ( Id, String ) -> Message
toDuplicateLabelMessage label duplicateLabelDefinitions =
    List.map Tuple.first duplicateLabelDefinitions
        |> DuplicateLabels label
        |> Warning
