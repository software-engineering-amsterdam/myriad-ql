module QL.TypeChecker.Expressions.OperandTypes exposing (operandTypeErrors)

import QL.TypeChecker.Expressions.ExpressionType exposing (getType)
import QL.AST exposing (Form, FormItem(..), Expression(..), Id, ValueType, Location)
import QL.AST.Collectors as Collectors exposing (QuestionTypes)
import QL.TypeChecker.Messages exposing (Message, ErrorMessage)


operandTypeErrors : Form -> QuestionTypes -> List Message
operandTypeErrors form questionTypes =
    Collectors.collectTopLevelExpressions form
        |> List.concatMap (checkExpression questionTypes)


checkExpression : QuestionTypes -> Expression -> List Message
checkExpression questionTypes expression =
    case getType questionTypes expression of
        Ok _ ->
            []

        Err messages ->
            messages
