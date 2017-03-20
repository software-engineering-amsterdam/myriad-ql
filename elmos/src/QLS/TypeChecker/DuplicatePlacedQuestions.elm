module QLS.TypeChecker.DuplicatePlacedQuestions exposing (check)

import QL.AST exposing (Form, Location)
import QLS.AST exposing (StyleSheet, Question)
import QLS.TypeChecker.Messages exposing (Message(DuplicatePlacedQuestion))
import QLS.AST.Collectors as QLSCollectors
import Dict exposing (Dict)
import Dict.Extra as Dict


check : Form -> StyleSheet -> List Message
check _ styleSheet =
    QLSCollectors.collectQuestionReferences styleSheet
        |> groupByLabel
        |> Dict.filter (\_ v -> List.length v > 1)
        |> Dict.toList
        |> List.map (uncurry DuplicatePlacedQuestion)


groupByLabel : List ( String, Location ) -> Dict String (List Location)
groupByLabel x =
    x
        |> Dict.groupBy Tuple.first
        |> Dict.map (\_ v -> List.map Tuple.second v)
