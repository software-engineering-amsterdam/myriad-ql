module QLS.TypeChecker.UnplacedQuestion exposing (check)

import QL.AST exposing (Form, Location)
import QLS.AST exposing (StyleSheet, Question)
import QLS.TypeChecker.Messages exposing (Message(UnplacedQuestion))
import QL.AST.Collectors as QLCollectors
import QLS.AST.Collectors as QLSCollectors
import Dict exposing (Dict)


check : Form -> StyleSheet -> List Message
check form styleSheet =
    let
        declaredQuestionNames =
            QLCollectors.collectDeclaredIds form
                |> List.map Tuple.first

        questionReferences =
            QLSCollectors.collectQuestionReferencesAsDict styleSheet
    in
        declaredQuestionNames
            |> List.filter (\name -> not (Dict.member name questionReferences))
            |> List.map UnplacedQuestion
