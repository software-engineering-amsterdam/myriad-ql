module QL.TypeChecker.CheckerUtil exposing (QuestionIndex, collectDeclaredIds, collectExpressions, questionIndexFromForm, questionIndexFromBlock)

import QL.AST exposing (..)
import QL.FormVisitor exposing (Order(Post), defaultConfig)
import Dict exposing (Dict)


type alias QuestionIndex =
    Dict String (List Location)


type alias QuestionValueDict =
    Dict String (List Location)


collectDeclaredIds : Form -> List Id
collectDeclaredIds form =
    QL.FormVisitor.inspect
        { defaultConfig
            | onField = Post (\( _, id, _ ) result -> id :: result)
            , onComputedField = Post (\( _, id, _, _ ) result -> id :: result)
        }
        form
        []


collectExpressions : Form -> List Expression
collectExpressions form =
    QL.FormVisitor.inspect
        { defaultConfig | onExpression = Post (::) }
        form
        []


questionIndexFromForm : Form -> QuestionIndex
questionIndexFromForm =
    .items >> questionIndexFromBlock


questionIndexFromBlock : Block -> QuestionIndex
questionIndexFromBlock =
    -- We want the first occurence of a question in the index, concat gives preference to the second which is why the list is reversed
    List.foldr (questionIndexFromItem >> Dict.union) Dict.empty


questionIndexFromItem : FormItem -> QuestionIndex
questionIndexFromItem item =
    case item of
        Field _ ( id, loc ) _ ->
            Dict.singleton id [ loc ]

        ComputedField _ ( id, loc ) _ _ ->
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
