module QL.TypeChecker exposing (check)

import QL.AST exposing (Form)
import QL.TypeChecker.BadReferences exposing (badReferences)
import QL.TypeChecker.DuplicateQuestions exposing (duplicateQuestions)
import QL.TypeChecker.Expressions exposing (typeCheckerErrors)
import QL.TypeChecker.CyclicDependencies exposing (cyclicDependencies)
import QL.TypeChecker.DuplicateLabels exposing (duplicateLabels)
import QL.TypeChecker.Messages exposing (Message)


check : Form -> List Message
check form =
    List.concat
        [ badReferences form
        , duplicateQuestions form
        , typeCheckerErrors form
        , cyclicDependencies form
        , duplicateLabels form
        ]
