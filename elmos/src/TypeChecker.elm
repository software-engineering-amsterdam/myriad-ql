module TypeChecker exposing (messages)

import AST exposing (Form)
import TypeChecker.BadReferences exposing (badReferences)
import TypeChecker.DuplicateQuestions exposing (duplicateQuestions)
import TypeChecker.Expressions exposing (typeCheckerErrors)
import TypeChecker.Messages exposing (Message)


messages : Form -> List Message
messages form =
    List.concat
        [ badReferences form
        , duplicateQuestions form
        , typeCheckerErrors form
        ]
