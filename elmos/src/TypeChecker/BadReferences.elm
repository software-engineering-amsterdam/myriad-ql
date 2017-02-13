module TypeChecker.BadReferences exposing (checkUndefinedVarReferences)

import TypeChecker.CheckerUtil exposing (..)
import AST exposing (..)
import DictSet exposing (..)
import Set


checkUndefinedVarReferences : Form -> Set.Set String
checkUndefinedVarReferences form =
    Set.diff
        (usedVarsFromList form.items |> Set.fromList)
        (declaredVarFromList form.items |> DictSet.values |> List.map Tuple.first |> Set.fromList)


usedVarsFromList : List FormItem -> List String
usedVarsFromList formItems =
    List.map usedVarsFromItem formItems
        |> List.concat


usedVarsFromItem : FormItem -> List String
usedVarsFromItem item =
    expressionFromItem item
        |> Maybe.map usedVars
        |> Maybe.withDefault []


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

        -- Kunnen deze niet samen? Bijvoorbeeld door middel van "BinaryExpression"
        ArithmeticExpression _ exprLeft exprRight ->
            usedVars exprLeft ++ usedVars exprRight

        RelationExpression _ exprLeft exprRight ->
            usedVars exprLeft ++ usedVars exprRight

        LogicExpression _ exprLeft exprRight ->
            usedVars exprLeft ++ usedVars exprRight

        ComparisonExpression _ exprLeft exprRight ->
            usedVars exprLeft ++ usedVars exprRight
