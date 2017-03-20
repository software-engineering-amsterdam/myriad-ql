module QL.TypeChecker.Messages exposing (..)

import QL.AST exposing (..)


type Message
    = Error ErrorMessage
    | Warning WarningMessage


type ErrorMessage
    = ArithmeticExpressionTypeMismatch ArithmeticOperator Location ValueType ValueType
    | LogicExpressionTypeMismatch LogicOperator Location ValueType ValueType
    | ComparisonExpressionTypeMismatch ComparisonOperator Location ValueType ValueType
    | RelationExpressionTypeMismatch RelationOperator Location ValueType ValueType
    | InvalidConditionType Location ValueType
    | InvalidComputedFieldType Id ValueType ValueType
    | DuplicateQuestionDefinition String (List Location)
    | ReferenceToUndefinedQuestion Id
    | DependencyCycle (List String)


type WarningMessage
    = DuplicateLabels String (List Id)
