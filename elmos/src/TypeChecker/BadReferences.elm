module TypeChecker.BadReferences exposing (badReferences)

import AST exposing (..)
import DictList
import Set exposing (Set)
import TypeChecker.CheckerUtil exposing (..)
import TypeChecker.Messages as Message exposing (Message, referenceToUndefinedQuestion)


badReferences : Form -> List Message
badReferences form =
    badReferencesInBlock Set.empty form.items


badReferencesInBlock : Set String -> Block -> List Message
badReferencesInBlock questionIdsFromParent block =
    let
        availableQuestionIds =
            questionIdsInBlock block
                |> Set.union questionIdsFromParent
    in
        List.concatMap (badReferencesInFormItem availableQuestionIds) block


questionIdsInBlock : Block -> Set String
questionIdsInBlock =
    questionIndexFromBlock >> DictList.keys >> Set.fromList


badReferencesInFormItem : Set String -> FormItem -> List Message
badReferencesInFormItem availableIdentifiers formItem =
    case formItem of
        Field _ _ _ ->
            []

        ComputedField _ _ _ computation ->
            badReferencesInExpression availableIdentifiers computation

        IfThen condition thenBranch ->
            List.concat
                [ badReferencesInExpression availableIdentifiers condition
                , badReferencesInBlock availableIdentifiers thenBranch
                ]

        IfThenElse condition thenBranch elseBranch ->
            List.concat
                [ badReferencesInExpression availableIdentifiers condition
                , badReferencesInBlock availableIdentifiers thenBranch
                , badReferencesInBlock availableIdentifiers elseBranch
                ]


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

        AST.Str _ _ ->
            []

        ParensExpression _ expr ->
            questionReferences expr

        ArithmeticExpression _ _ exprLeft exprRight ->
            (questionReferences exprLeft) ++ (questionReferences exprRight)

        RelationExpression _ _ exprLeft exprRight ->
            (questionReferences exprLeft) ++ (questionReferences exprRight)

        LogicExpression _ _ exprLeft exprRight ->
            (questionReferences exprLeft) ++ (questionReferences exprRight)

        ComparisonExpression _ _ exprLeft exprRight ->
            (questionReferences exprLeft) ++ (questionReferences exprRight)
