module TypeChecker.DuplicateQuestions exposing (..)

import AST exposing (..)
import TypeChecker.CheckerUtil exposing (..)
import List.Extra


duplicateQuestions : Form -> List String
duplicateQuestions form =
    questionTypeRelationsFromBlock form.items
        |> Debug.log "test"
        |> questionIds
        |> (\varList -> removeListFrom varList <| List.Extra.unique varList)
