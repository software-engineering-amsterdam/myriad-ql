module QL.TypeChecker.QuestionIndex exposing (QuestionIndex, questionIndexFromForm)

import QL.AST exposing (Form, Block, Location, FormItem(Question, ComputedQuestion, IfThen, IfThenElse))
import Dict exposing (Dict)


type alias QuestionIndex =
    Dict String (List Location)


questionIndexFromForm : Form -> QuestionIndex
questionIndexFromForm =
    .items >> questionIndexFromBlock


questionIndexFromBlock : Block -> QuestionIndex
questionIndexFromBlock =
    List.foldr (questionIndexFromItem >> Dict.union) Dict.empty


questionIndexFromItem : FormItem -> QuestionIndex
questionIndexFromItem item =
    case item of
        Question _ ( id, loc ) _ ->
            Dict.singleton id [ loc ]

        ComputedQuestion _ ( id, loc ) _ _ ->
            Dict.singleton id [ loc ]

        IfThen _ thenBranch ->
            questionIndexFromBlock thenBranch

        IfThenElse _ thenBranch elseBranch ->
            mergeSharedQuestionDefinitions
                (questionIndexFromBlock thenBranch)
                (questionIndexFromBlock elseBranch)


mergeSharedQuestionDefinitions : QuestionIndex -> QuestionIndex -> QuestionIndex
mergeSharedQuestionDefinitions indexA indexB =
    Dict.merge
        Dict.insert
        (\questionId locationsInA locationsInB result -> Dict.insert questionId (locationsInA ++ locationsInB) result)
        Dict.insert
        indexA
        indexB
        Dict.empty
