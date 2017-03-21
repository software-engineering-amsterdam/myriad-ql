module QL.TypeChecker exposing (check)

import QL.AST exposing (Form)
import QL.TypeChecker.BadReferences as BadReferences
import QL.TypeChecker.DuplicateQuestions as DuplicateQuestions
import QL.TypeChecker.Expressions as Expressions
import QL.TypeChecker.CyclicDependencies as CyclicDependencies
import QL.TypeChecker.DuplicateLabels as DuplicateLabels
import QL.TypeChecker.Messages exposing (Message)


check : Form -> List Message
check form =
    List.concat
        [ BadReferences.check form
        , DuplicateQuestions.check form
        , Expressions.check form
        , CyclicDependencies.check form
        , DuplicateLabels.check form
        ]
