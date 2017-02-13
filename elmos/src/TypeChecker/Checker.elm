module TypeChecker.Checker exposing (..)

import AST exposing (..)
import DictSet exposing (..)
import Set


type alias Index a =
    DictSet String a


checkUndefinedVarReferences : Form -> Set.Set String
checkUndefinedVarReferences form =
    Set.diff
        (usedVarsFromList form.items |> Set.fromList)
        (declaredVarFromList form.items |> DictSet.values |> List.map Tuple.first |> Set.fromList)


emptyIndex : Index a
emptyIndex =
    DictSet.empty toString


mergeIndex : List (Index a) -> Index a
mergeIndex =
    List.foldr DictSet.union emptyIndex


declaredVarFromList : List FormItem -> Index ( String, ValueType )
declaredVarFromList =
    List.map declaredVars >> mergeIndex


declaredVars : FormItem -> Index ( String, ValueType )
declaredVars item =
    case item of
        FieldItem { id, valueType } ->
            DictSet.fromList toString [ ( id, valueType ) ]

        IfItem { thenBranch, elseBranch } ->
            DictSet.intersect
                (declaredVarFromList thenBranch)
                (declaredVarFromList elseBranch)


usedVarsFromList : List FormItem -> List String
usedVarsFromList formItems =
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
        FieldItem { valueExpression } ->
            valueExpression

        IfItem { expression } ->
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
