module QLS.TypeChecker.Messages exposing (..)

import QL.AST exposing (ValueType, Operator, Comparison, Logic, Relation, Location, Id)


type Message
    = Error ErrorMessage


type ErrorMessage
    = UndefinedQuestionReference String Location
    | UnplacedQuestion String


undefinedQuestionReference : String -> Location -> Message
undefinedQuestionReference name location =
    Error (UndefinedQuestionReference name location)


unplacedQuestion : String -> Message
unplacedQuestion name =
    Error (UnplacedQuestion name)
