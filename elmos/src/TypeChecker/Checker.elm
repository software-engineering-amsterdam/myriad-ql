module TypeChecker.Checker exposing (..)

import AST exposing (..)
import DictSet exposing (..)


type alias Index a =
    DictSet String a


checkUndefinedQuestions : Form -> List String
checkUndefinedQuestions a =
    []


emptyIndex : Index a
emptyIndex =
    DictSet.empty toString


mergeIndex : List (Index a) -> Index a
mergeIndex =
    List.foldr DictSet.union emptyIndex


declaredVarFromList : List FormItem -> DictSet String ( String, ValueType )
declaredVarFromList =
    List.map declaredVars >> mergeIndex


declaredVars : FormItem -> DictSet String ( String, ValueType )
declaredVars item =
    case item of
        FieldItem { id, valueType } ->
            DictSet.fromList toString [ ( id, valueType ) ]

        IfItem { thenBranch, elseBranch } ->
            DictSet.intersect
                (declaredVarFromList thenBranch)
                (declaredVarFromList elseBranch)


getQuestionId : FormItem -> Maybe String
getQuestionId question =
    case question of
        FieldItem { id } ->
            Just id

        IfItem _ ->
            Nothing
