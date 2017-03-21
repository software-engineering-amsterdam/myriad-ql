module QL.TypeChecker.Expressions.OperandTypes exposing (check)

import QL.TypeChecker.Expressions.ExpressionType exposing (getType)
import QL.AST exposing (Form, Expression, ValueType, Location)
import QL.AST.Collectors as Collectors exposing (TypeEnvironment)
import QL.TypeChecker.Messages exposing (Message, ErrorMessage)


check : Form -> TypeEnvironment -> List Message
check form typeEnv =
    Collectors.collectTopLevelExpressions form
        |> List.concatMap (checkExpression typeEnv)


checkExpression : TypeEnvironment -> Expression -> List Message
checkExpression typeEnv expression =
    case getType typeEnv expression of
        Ok _ ->
            []

        Err messages ->
            messages
