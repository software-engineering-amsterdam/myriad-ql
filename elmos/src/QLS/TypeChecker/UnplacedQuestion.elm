module QLS.TypeChecker.UnplacedQuestion exposing (check)

import QL.AST exposing (Form, Location)
import QLS.AST exposing (StyleSheet, Question)
import QLS.TypeChecker.Messages exposing (Message, unplacedQuestion)
import QL.AST.Collectors as QLCollectors
import QLS.AST.Collectors as QLSCollectors
import Dict exposing (Dict)


check : Form -> StyleSheet -> List Message
check form styleSheet =
    let
        declaredQuestions =
            QLCollectors.collectDeclaredIds form

        questionReferences =
            QLSCollectors.collectQuestionReferencesAsDict styleSheet
    in
        declaredQuestions
            |> List.filter (\( name, _ ) -> not (Dict.member name questionReferences))
            |> List.map (Tuple.first >> unplacedQuestion)
