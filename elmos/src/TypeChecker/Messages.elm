module TypeChecker.Messages exposing (..)

import AST exposing (ValueType, Operator, Comparison, Logic, Relation)


type Message
    = Error ErrorMessage


type ErrorMessage
    = UndefinedExpressionVariable String ()
    | ArithmeticExpressionTypeMismatch Operator ValueType ValueType
    | LogicExpressionTypeMismatch Logic ValueType ValueType
    | ComparisonExpressionTypeMismatch Comparison ValueType ValueType
    | RelationExpressionTypeMismatch Relation ValueType ValueType


undefinedExpressionVariable : String -> () -> Message
undefinedExpressionVariable name range =
    Error (UndefinedExpressionVariable name range)


arithmeticExpressionTypeMismatch : Operator -> ValueType -> ValueType -> Message
arithmeticExpressionTypeMismatch op lhs rhs =
    Error (ArithmeticExpressionTypeMismatch op lhs rhs)


logicExpressionTypeMismatch : Logic -> ValueType -> ValueType -> Message
logicExpressionTypeMismatch logic lhs rhs =
    Error (LogicExpressionTypeMismatch logic lhs rhs)


comparisonExpressionTypeMismatch : Comparison -> ValueType -> ValueType -> Message
comparisonExpressionTypeMismatch comparison lhs rhs =
    Error (ComparisonExpressionTypeMismatch comparison lhs rhs)


relationExpressionTypeMismatch : Relation -> ValueType -> ValueType -> Message
relationExpressionTypeMismatch relation lhs rhs =
    Error (RelationExpressionTypeMismatch relation lhs rhs)
