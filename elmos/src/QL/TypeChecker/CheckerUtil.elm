module QL.TypeChecker.CheckerUtil exposing (QuestionIndex, QuestionTypes, collectDeclaredIds, collectExpressions, questionIndexFromForm, questionIndexFromBlock, questionTypes)

import QL.AST exposing (..)
import QL.FormVisitor exposing (Order(Post), defaultConfig)
import Dict exposing (Dict)


type alias QuestionIndex =
    Dict String (List Location)


type alias QuestionTypes =
    Dict String ValueType


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



--TODO TESTS


questionTypes : Form -> QuestionTypes
questionTypes form =
    QL.FormVisitor.inspect
        { defaultConfig
            | onField = Post (\( _, ( name, _ ), valueType ) result -> Dict.insert name valueType result)
            , onComputedField = Post (\( _, ( name, _ ), valueType, _ ) result -> Dict.insert name valueType result)
        }
        form
        Dict.empty


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
