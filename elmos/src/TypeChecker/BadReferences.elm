module TypeChecker.BadReferences exposing (badReferences)

import TypeChecker.CheckerUtil exposing (..)
import AST exposing (..)
import Set


badReferences : Form -> Set.Set String
badReferences form =
    Set.diff
        (usedVarsFromBlock form.items |> Set.fromList)
        (questionTypeRelationsFromBlock form.items |> questionIds |> Set.fromList)


usedVarsFromBlock : Block -> List String
usedVarsFromBlock formItems =
    List.map usedVarsFromItem formItems
        |> List.concat


usedVarsFromItem : FormItem -> List String
usedVarsFromItem item =
    expressionFromItem item
        |> Maybe.map usedVars
        |> Maybe.withDefault []


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


usedVars : Expression -> List String
usedVars expression =
    case expression of
        Var s ->
            [ s ]

        Integer _ ->
            []

        Boolean _ ->
            []

        AST.Str _ ->
            []

        ParensExpression expr ->
            usedVars expr

        ArithmeticExpression _ exprLeft exprRight ->
            usedVars exprLeft ++ usedVars exprRight

        RelationExpression _ exprLeft exprRight ->
            usedVars exprLeft ++ usedVars exprRight

        LogicExpression _ exprLeft exprRight ->
            usedVars exprLeft ++ usedVars exprRight

        ComparisonExpression _ exprLeft exprRight ->
            usedVars exprLeft ++ usedVars exprRight
