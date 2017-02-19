module TypeChecker.BadReferences exposing (badReferences)

import TypeChecker.CheckerUtil exposing (..)
import AST exposing (..)
import Set exposing (Set)


badReferences : Form -> Set String
badReferences form =
    badReferencesInBlock Set.empty form.items


badReferencesInBlock : Set String -> Block -> Set String
badReferencesInBlock parentIdentifiers block =
    let
        availableIdentifiers =
            availableIdentifiersOnScope parentIdentifiers block

        usedIdentifiers =
            usedIdentifiersFromBlock block

        badReferences =
            Set.diff usedIdentifiers availableIdentifiers
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


usedIdentifiersFromBlock : Block -> Set String
usedIdentifiersFromBlock block =
    List.foldl (\item -> usedIdentifiersFromItem item |> Set.union) Set.empty block


usedIdentifiersFromItem : FormItem -> Set String
usedIdentifiersFromItem item =
    expressionFromItem item
        |> Maybe.map usedIdentifiers
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


usedIdentifiers : Expression -> Set String
usedIdentifiers expression =
    case expression of
        Var ( s, _ ) ->
            Set.singleton s

        Integer _ _ ->
            Set.empty

        Boolean _ _ ->
            Set.empty

        AST.Str _ _ ->
            Set.empty

        ParensExpression _ expr ->
            usedIdentifiers expr

        ArithmeticExpression _ _ exprLeft exprRight ->
            Set.union (usedIdentifiers exprLeft) (usedIdentifiers exprRight)

        RelationExpression _ _ exprLeft exprRight ->
            Set.union (usedIdentifiers exprLeft) (usedIdentifiers exprRight)

        LogicExpression _ _ exprLeft exprRight ->
            Set.union (usedIdentifiers exprLeft) (usedIdentifiers exprRight)

        ComparisonExpression _ _ exprLeft exprRight ->
            Set.union (usedIdentifiers exprLeft) (usedIdentifiers exprRight)
