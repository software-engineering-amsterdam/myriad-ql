module QLS.TypeChecker.Messages exposing (..)

import QL.AST exposing (ValueType, Operator, Comparison, Logic, Relation, Location, Id)
import QLS.AST exposing (Widget)


type Message
    = UndefinedQuestionReference String Location
    | UnplacedQuestion String
    | DuplicatePlacedQuestion String (List Location)
    | WidgetConfigMismatch String Location ValueType Widget
    | WidgetDefaultConfigMismatch Location ValueType Widget
