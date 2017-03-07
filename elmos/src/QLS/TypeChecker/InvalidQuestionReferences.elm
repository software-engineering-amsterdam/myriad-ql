module QLS.TypeChecker.InvalidQuestionReferences exposing (check)

import QL.AST exposing (Form, Location)
import QLS.AST exposing (StyleSheet, Question)
import QLS.TypeChecker.Messages exposing (Message, undefinedQuestionReference)
import QL.AST.Collectors as QLCollectors
import QLS.AST.Collectors as QLSCollectors
import Dict exposing (Dict)


check : Form -> StyleSheet -> List Message
check form styleSheet =
    let
        declaredQuestions =
            QLCollectors.collectDeclaredIds form
                |> List.map Tuple.first
    in
        QLSCollectors.collectQuestionReferencesAsDict styleSheet
            |> Dict.filter (\k _ -> not (List.member k declaredQuestions))
            |> Dict.toList
            |> List.map (uncurry undefinedQuestionReference)
