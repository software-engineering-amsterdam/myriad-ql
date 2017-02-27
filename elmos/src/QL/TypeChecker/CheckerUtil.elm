module QL.TypeChecker.CheckerUtil exposing (..)

import QL.AST exposing (..)
import DictList exposing (DictList)


type alias QuestionIndex =
    DictList String (List Location)


questionIndexFromBlock : Block -> QuestionIndex
questionIndexFromBlock =
    -- We want the first occurence of a question in the index, concat gives preference to the second which is why the list is reversed
    List.map questionIndexFromItem >> List.reverse >> DictList.concat


questionIndexFromItem : FormItem -> QuestionIndex
questionIndexFromItem item =
    case item of
        Field _ ( id, loc ) _ ->
            DictList.singleton id [ loc ]

        ComputedField _ ( id, loc ) _ _ ->
            DictList.singleton id [ loc ]

        IfThen _ thenBranch ->
            questionIndexFromBlock thenBranch

        IfThenElse _ thenBranch elseBranch ->
            mergeSharedQuestionDefinitions
                (questionIndexFromBlock thenBranch)
                (questionIndexFromBlock elseBranch)


mergeSharedQuestionDefinitions : QuestionIndex -> QuestionIndex -> QuestionIndex
mergeSharedQuestionDefinitions indexA indexB =
    DictList.merge
        DictList.insert
        (\questionId locationsInA locationsInB result -> DictList.insert questionId (locationsInA ++ locationsInB) result)
        DictList.insert
        indexA
        indexB
        DictList.empty
