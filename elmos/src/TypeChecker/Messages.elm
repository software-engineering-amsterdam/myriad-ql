module TypeChecker.Messages exposing (..)

import AST exposing (ValueType, Operator, Comparison, Logic, Relation, Location)


type Message
    = Error ErrorMessage


type ErrorMessage
    = UndefinedExpressionVariable String Location
    | ArithmeticExpressionTypeMismatch Operator Location ValueType ValueType
    | LogicExpressionTypeMismatch Logic Location ValueType ValueType
    | ComparisonExpressionTypeMismatch Comparison Location ValueType ValueType
    | RelationExpressionTypeMismatch Relation Location ValueType ValueType


undefinedExpressionVariable : String -> Location -> Message
undefinedExpressionVariable name loc =
    Error (UndefinedExpressionVariable name loc)


arithmeticExpressionTypeMismatch : Operator -> Location -> ValueType -> ValueType -> Message
arithmeticExpressionTypeMismatch op loc lhs rhs =
    Error (ArithmeticExpressionTypeMismatch op loc lhs rhs)


logicExpressionTypeMismatch : Logic -> Location -> ValueType -> ValueType -> Message
logicExpressionTypeMismatch logic loc lhs rhs =
    Error (LogicExpressionTypeMismatch logic loc lhs rhs)


comparisonExpressionTypeMismatch : Comparison -> Location -> ValueType -> ValueType -> Message
comparisonExpressionTypeMismatch comparison loc lhs rhs =
    Error (ComparisonExpressionTypeMismatch comparison loc lhs rhs)


relationExpressionTypeMismatch : Relation -> Location -> ValueType -> ValueType -> Message
relationExpressionTypeMismatch relation loc lhs rhs =
    Error (RelationExpressionTypeMismatch relation loc lhs rhs)
