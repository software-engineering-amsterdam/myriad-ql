module TypeChecker.DuplicateQuestions exposing (..)

import AST exposing (..)
import TypeChecker.CheckerUtil exposing (..)
import List.Extra


duplicateQuestions : Form -> List String
duplicateQuestions form =
    questionTypeRelationsFromBlock form.items
        |> questionIds
        |> (\varList -> removeListFrom (List.Extra.unique varList) varList)
