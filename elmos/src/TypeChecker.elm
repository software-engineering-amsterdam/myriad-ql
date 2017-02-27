module TypeChecker exposing (check)

import AST exposing (Form)
import TypeChecker.BadReferences exposing (badReferences)
import TypeChecker.DuplicateQuestions exposing (duplicateQuestions)


-- import TypeChecker.Expressions exposing (typeCheckerErrors)

import TypeChecker.Messages exposing (Message)


check : Form -> List Message
check form =
    List.concat
        [ badReferences form
        , duplicateQuestions form
          -- , typeCheckerErrors form
        ]
