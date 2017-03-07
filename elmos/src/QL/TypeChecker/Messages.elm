module QL.TypeChecker.Messages exposing (..)

import QL.AST exposing (ValueType, Operator, Comparison, Logic, Relation, Location, Id)


type Message
    = Error ErrorMessage
    | Warning WarningMessage


type ErrorMessage
    = ArithmeticExpressionTypeMismatch Operator Location ValueType ValueType
    | LogicExpressionTypeMismatch Logic Location ValueType ValueType
    | ComparisonExpressionTypeMismatch Comparison Location ValueType ValueType
    | RelationExpressionTypeMismatch Relation Location ValueType ValueType
    | InvalidConditionType Location ValueType
    | InvalidComputedFieldType Id ValueType ValueType
    | DuplicateQuestionDefinition String (List Location)
    | ReferenceToUndefinedQuestion Id
    | DependencyCycle (List String)


type WarningMessage
    = DuplicateLabels String (List Id)


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


invalidConditionType : Location -> ValueType -> Message
invalidConditionType location valueType =
    Error (InvalidConditionType location valueType)


invalidComputedFieldType : Id -> ValueType -> ValueType -> Message
invalidComputedFieldType id computedType fieldType =
    Error (InvalidComputedFieldType id computedType fieldType)


duplicateQuestionDefinition : String -> List Location -> Message
duplicateQuestionDefinition questionId locations =
    Error (DuplicateQuestionDefinition questionId locations)


referenceToUndefinedQuestion : Id -> Message
referenceToUndefinedQuestion var =
    Error (ReferenceToUndefinedQuestion var)


dependencyCycle : List String -> Message
dependencyCycle names =
    Error (DependencyCycle names)


duplicateLabels : String -> List Id -> Message
duplicateLabels label ids =
    Warning (DuplicateLabels label ids)
