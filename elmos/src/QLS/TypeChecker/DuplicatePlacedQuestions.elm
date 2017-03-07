module QLS.TypeChecker.DuplicatePlacedQuestions exposing (check)

import QL.AST exposing (Form, Location)
import QLS.AST exposing (StyleSheet, Question)
import QLS.TypeChecker.Messages exposing (Message(DuplicatePlacedQuestion), undefinedQuestionReference)
import QLS.AST.Collectors as QLSCollectors
import Dict exposing (Dict)
import Dict.Extra as Dict


check : Form -> StyleSheet -> List Message
check form styleSheet =
    QLSCollectors.collectQuestionReferences styleSheet
        |> groupByLabel
        |> Dict.filter (\k v -> List.length v > 1)
        |> Dict.toList
        |> List.map (uncurry DuplicatePlacedQuestion)


groupByLabel : List ( String, Location ) -> Dict String (List Location)
groupByLabel x =
    x
        |> Dict.groupBy Tuple.first
        |> Dict.map (\k v -> List.map Tuple.second v)
