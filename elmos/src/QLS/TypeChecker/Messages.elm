module QLS.TypeChecker.Messages exposing (..)

import QL.AST exposing (ValueType, Operator, Comparison, Logic, Relation, Location, Id)


type Message
    = UndefinedQuestionReference String Location
    | UnplacedQuestion String
    | DuplicatePlacedQuestion String (List Location)


undefinedQuestionReference : String -> Location -> Message
undefinedQuestionReference name location =
    (UndefinedQuestionReference name location)


unplacedQuestion : String -> Message
unplacedQuestion name =
    (UnplacedQuestion name)
