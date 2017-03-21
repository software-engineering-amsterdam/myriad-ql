module QL.TypeChecker.Expressions exposing (check)

import QL.AST exposing (Form)
import QL.AST.Collectors as Collectors
import QL.TypeChecker.Messages exposing (Message)
import QL.TypeChecker.Expressions.ComputedQuestionTypes as ComputedQuestionTypes
import QL.TypeChecker.Expressions.ConditionTypes as ConditionTypes
import QL.TypeChecker.Expressions.OperandTypes as OperandTypes


check : Form -> List Message
check form =
    let
        typeEnv =
            Collectors.collectTypeEnv form
    in
        List.concat
            [ OperandTypes.check form typeEnv
            , ConditionTypes.check form typeEnv
            , ComputedQuestionTypes.check form typeEnv
            ]
