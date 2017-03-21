module QL.TypeChecker.Expressions exposing (typeCheckerErrors)

import QL.AST exposing (Form)
import QL.AST.Collectors as Collectors
import QL.TypeChecker.Messages exposing (Message)
import QL.TypeChecker.Expressions.ComputedQuestionTypes exposing (computedQuestionTypeErrors)
import QL.TypeChecker.Expressions.ConditionTypes exposing (conditionTypeErrors)
import QL.TypeChecker.Expressions.OperandTypes exposing (operandTypeErrors)


typeCheckerErrors : Form -> List Message
typeCheckerErrors form =
    let
        typeEnv =
            Collectors.collectTypeEnv form
    in
        List.concat
            [ operandTypeErrors form typeEnv
            , conditionTypeErrors form typeEnv
            , computedQuestionTypeErrors form typeEnv
            ]
