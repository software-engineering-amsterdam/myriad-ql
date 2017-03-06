module QLS.TypeChecker.UnplacedQuestion exposing (check)

import QL.AST exposing (Form, Location)
import QLS.AST exposing (StyleSheet, Question(Question, ConfiguredQuestion))
import QLS.TypeChecker.Messages exposing (Message, unplacedQuestion)
import QL.TypeChecker.CheckerUtil as QLCheckerUtil
import QLS.TypeChecker.CheckerUtil as QLSCheckerUtil
import Dict exposing (Dict)


check : Form -> StyleSheet -> List Message
check form styleSheet =
    let
        declaredQuestions =
            QLCheckerUtil.collectDeclaredIds form

        questionReferences =
            QLSCheckerUtil.collectQuestionReferences styleSheet
    in
        declaredQuestions
            |> List.filter (\( name, _ ) -> not (Dict.member name questionReferences))
            |> List.map (Tuple.first >> unplacedQuestion)
