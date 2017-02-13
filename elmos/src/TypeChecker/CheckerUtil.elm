module TypeChecker.CheckerUtil exposing (..)

import AST exposing (..)
import DictSet exposing (..)


type alias Index a =
    DictSet String a


emptyIndex : Index a
emptyIndex =
    DictSet.empty toString


mergeIndex : List (Index a) -> Index a
mergeIndex =
    (List.foldr DictSet.union emptyIndex)


declaredVarsFromList : List FormItem -> Index ( String, ValueType )
declaredVarsFromList =
    List.map declaredVars >> mergeIndex


declaredVars : FormItem -> Index ( String, ValueType )
declaredVars item =
    case item of
        FieldItem { id, valueType } ->
            DictSet.fromList toString [ ( id, valueType ) ]

        IfItem { thenBranch, elseBranch } ->
            DictSet.intersect
                (declaredVarsFromList thenBranch)
                (declaredVarsFromList elseBranch)


expressionFromItem : FormItem -> Maybe Expression
expressionFromItem item =
    case item of
        FieldItem { valueExpression } ->
            valueExpression

        IfItem { expression } ->
            Just expression
