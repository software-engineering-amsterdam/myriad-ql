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
