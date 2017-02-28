module QLS.TypeChecker.InvalidQuestionReferences exposing (check)

import QL.AST exposing (Form, Location)
import QLS.AST exposing (StyleSheet, Question)
import QLS.TypeChecker.Messages exposing (Message, undefinedQuestionReference)
import QL.TypeChecker.CheckerUtil as QLCheckerUtil
import QLS.TypeChecker.CheckerUtil as QLSCheckerUtil
import Dict exposing (Dict)


check : Form -> StyleSheet -> List Message
check form styleSheet =
    let
        declaredQuestions =
            QLCheckerUtil.collectDeclaredIds form
                |> List.map Tuple.first
    in
        QLSCheckerUtil.collectQuestionReferences styleSheet
            |> Dict.filter (\k _ -> not (List.member k declaredQuestions))
            |> Dict.toList
            |> List.map (uncurry undefinedQuestionReference)
