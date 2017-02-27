module QL.TypeChecker.BadReferences exposing (badReferences)

import QL.AST exposing (..)
import Dict
import Set exposing (Set)
import QL.TypeChecker.CheckerUtil as CheckerUtil
import QL.TypeChecker.Messages exposing (Message, referenceToUndefinedQuestion)


badReferences : Form -> List Message
badReferences form =
    let
        ids =
            CheckerUtil.questionIndexFromForm form |> Dict.keys |> Set.fromList

        expressions =
            CheckerUtil.collectExpressions form
    in
        List.concatMap (badReferencesInExpression ids) expressions


badReferencesInExpression : Set String -> Expression -> List Message
badReferencesInExpression availableIdentifiers expression =
    questionReferences expression
        |> List.filter (flip isBadReference availableIdentifiers)
        |> List.map referenceToUndefinedQuestion


isBadReference : Id -> Set String -> Bool
isBadReference ( id, _ ) =
    Set.member id >> not


questionReferences : Expression -> List Id
questionReferences expression =
    case expression of
        Var id ->
            [ id ]

        Integer _ _ ->
            []

        Decimal _ _ ->
            []

        Boolean _ _ ->
            []

        Str _ _ ->
            []

        ParensExpression _ expr ->
            questionReferences expr

        ArithmeticExpression _ _ exprLeft exprRight ->
            questionReferences exprLeft ++ questionReferences exprRight

        RelationExpression _ _ exprLeft exprRight ->
            questionReferences exprLeft ++ questionReferences exprRight

        LogicExpression _ _ exprLeft exprRight ->
            questionReferences exprLeft ++ questionReferences exprRight

        ComparisonExpression _ _ exprLeft exprRight ->
            questionReferences exprLeft ++ questionReferences exprRight
