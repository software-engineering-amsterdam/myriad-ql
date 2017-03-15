module QL.TypeChecker.Expressions exposing (typeCheckerErrors)

import QL.AST exposing (Form)
import QL.AST.Collectors as Collectors
import QL.TypeChecker.Messages exposing (Message)
import QL.TypeChecker.Expressions.ComputedFieldTypes exposing (computedFieldTypeErrors)
import QL.TypeChecker.Expressions.ConditionTypes exposing (conditionTypeErrors)
import QL.TypeChecker.Expressions.OperandTypes exposing (operandTypeErrors)


typeCheckerErrors : Form -> List Message
typeCheckerErrors form =
    let
        questionTypes =
            Collectors.collectQuestionTypes form
    in
        operandTypeErrors form questionTypes
            ++ conditionTypeErrors form questionTypes
            ++ computedFieldTypeErrors form questionTypes
