module QLS.TypeChecker.Messages exposing (..)

import QL.AST exposing (ValueType, Operator, Comparison, Logic, Relation, Location, Id)


type Message
    = Error ErrorMessage


type ErrorMessage
    = UndefinedQuestionReference String Location


undefinedQuestionReference : String -> Location -> Message
undefinedQuestionReference name location =
    Error (UndefinedQuestionReference name location)
