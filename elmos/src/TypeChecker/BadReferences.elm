module TypeChecker.BadReferences exposing (badReferences)

import AST exposing (..)
import DictList
import Set exposing (Set)
import TypeChecker.CheckerUtil exposing (..)


badReferences : Form -> Set String
badReferences form =
    badReferencesInBlock Set.empty form.items


badReferencesInBlock : Set String -> Block -> Set String
badReferencesInBlock questionIdsFromParent block =
    let
        availableIdentifiers =
            questionIdsInBlock block |> Set.union questionIdsFromParent

        questionReferences =
            questionReferencesFromBlock block

        badReferences =
            Set.diff availableIdentifiers availableIdentifiers
    in
        List.foldl (\item -> badReferencesInFormItem availableIdentifiers item |> Set.union) badReferences block


badReferencesInFormItem : Set String -> FormItem -> Set String
badReferencesInFormItem availableIdentifiers formItem =
    case formItem of
        Field _ _ _ ->
            Set.empty

        ComputedField _ _ _ _ ->
            Set.empty

        IfThen _ thenBranch ->
            badReferencesInBlock availableIdentifiers thenBranch

        IfThenElse _ thenBranch elseBranch ->
            Set.union
                (badReferencesInBlock availableIdentifiers thenBranch)
                (badReferencesInBlock availableIdentifiers elseBranch)


questionIdsInBlock : Block -> Set String
questionIdsInBlock =
    questionIndexFromBlock >> DictList.keys >> Set.fromList


questionReferencesFromBlock : Block -> Set String
questionReferencesFromBlock block =
    List.foldl (\item -> questionReferencesFromItem item |> Set.union) Set.empty block


questionReferencesFromItem : FormItem -> Set String
questionReferencesFromItem item =
    expressionFromItem item
        |> Maybe.map questionReferences
        |> Maybe.withDefault Set.empty


expressionFromItem : FormItem -> Maybe Expression
expressionFromItem item =
    case item of
        Field _ _ _ ->
            Nothing

        ComputedField _ _ _ expression ->
            Just expression

        IfThen expression _ ->
            Just expression

        IfThenElse expression _ _ ->
            Just expression


questionReferences : Expression -> Set String
questionReferences expression =
    case expression of
        Var ( s, _ ) ->
            Set.singleton s

        Integer _ _ ->
            Set.empty

        Decimal _ _ ->
            Set.empty

        Boolean _ _ ->
            Set.empty

        AST.Str _ _ ->
            Set.empty

        ParensExpression _ expr ->
            questionReferences expr

        ArithmeticExpression _ _ exprLeft exprRight ->
            Set.union (questionReferences exprLeft) (questionReferences exprRight)

        RelationExpression _ _ exprLeft exprRight ->
            Set.union (questionReferences exprLeft) (questionReferences exprRight)

        LogicExpression _ _ exprLeft exprRight ->
            Set.union (questionReferences exprLeft) (questionReferences exprRight)

        ComparisonExpression _ _ exprLeft exprRight ->
            Set.union (questionReferences exprLeft) (questionReferences exprRight)
