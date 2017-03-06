module QL.AST.Collectors
    exposing
        ( QuestionTypes
        , collectComputedFields
        , collectDeclaredIds
        , collectExpressions
        , collectQuestionLabels
        , collectQuestionReferences
        , collectQuestionTypes
        )

import QL.AST exposing (..)
import QL.FormVisitor as FormVisitor exposing (defaultConfig)
import Dict exposing (Dict)


type alias QuestionTypes =
    Dict String ValueType


collectQuestionLabels : Form -> List ( Id, String )
collectQuestionLabels form =
    FormVisitor.inspect
        { defaultConfig
            | onField = FormVisitor.post (\( label, id, _ ) result -> ( id, label ) :: result)
            , onComputedField = FormVisitor.post (\( label, id, _, _ ) result -> ( id, label ) :: result)
        }
        form
        []


collectComputedFields : Form -> List ( String, Expression )
collectComputedFields form =
    FormVisitor.inspect
        { defaultConfig
            | onComputedField = FormVisitor.post (\( _, ( name, _ ), _, computation ) result -> ( name, computation ) :: result)
        }
        form
        []


collectDeclaredIds : Form -> List Id
collectDeclaredIds form =
    FormVisitor.inspect
        { defaultConfig
            | onField = FormVisitor.post (\( _, id, _ ) result -> id :: result)
            , onComputedField = FormVisitor.post (\( _, id, _, _ ) result -> id :: result)
        }
        form
        []


collectExpressions : Form -> List Expression
collectExpressions form =
    FormVisitor.inspect
        { defaultConfig | onExpression = FormVisitor.post (::) }
        form
        []


collectQuestionReferences : Expression -> List Id
collectQuestionReferences expression =
    case expression of
        Var id ->
            [ id ]

        Integer _ _ ->
            []

        Decimal _ _ ->
            []

        Boolean _ _ ->
            []

        Str _ _ ->
            []

        ParensExpression _ expr ->
            collectQuestionReferences expr

        ArithmeticExpression _ _ exprLeft exprRight ->
            collectQuestionReferences exprLeft ++ collectQuestionReferences exprRight

        RelationExpression _ _ exprLeft exprRight ->
            collectQuestionReferences exprLeft ++ collectQuestionReferences exprRight

        LogicExpression _ _ exprLeft exprRight ->
            collectQuestionReferences exprLeft ++ collectQuestionReferences exprRight

        ComparisonExpression _ _ exprLeft exprRight ->
            collectQuestionReferences exprLeft ++ collectQuestionReferences exprRight


collectQuestionTypes : Form -> QuestionTypes
collectQuestionTypes form =
    FormVisitor.inspect
        { defaultConfig
            | onField = FormVisitor.post (\( _, ( name, _ ), valueType ) result -> Dict.insert name valueType result)
            , onComputedField = FormVisitor.post (\( _, ( name, _ ), valueType, _ ) result -> Dict.insert name valueType result)
        }
        form
        Dict.empty
