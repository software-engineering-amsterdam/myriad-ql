module QL.TypeChecker.BadReferences exposing (check)

import QL.AST exposing (Form, Id, Expression)
import Dict
import Set exposing (Set)
import QL.AST.Collectors as Collectors
import QL.TypeChecker.Messages exposing (Message(Error), ErrorMessage(ReferenceToUndefinedQuestion))
import QL.TypeChecker.QuestionIndex as QuestionIndex


check : Form -> List Message
check form =
    let
        ids =
            QuestionIndex.questionIndexFromForm form
                |> Dict.keys
                |> Set.fromList

        expressions =
            Collectors.collectTopLevelExpressions form
    in
        List.concatMap (badReferencesInExpression ids) expressions


badReferencesInExpression : Set String -> Expression -> List Message
badReferencesInExpression availableIdentifiers expression =
    Collectors.collectQuestionReferences expression
        |> List.filter (\id -> isBadReference id availableIdentifiers)
        |> List.map (Error << ReferenceToUndefinedQuestion)


isBadReference : Id -> Set String -> Bool
isBadReference ( id, _ ) =
    Set.member id >> not
