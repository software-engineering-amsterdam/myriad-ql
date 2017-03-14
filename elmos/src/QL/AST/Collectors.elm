module QL.AST.Collectors
    exposing
        ( QuestionTypes
        , collectConditions
        , collectComputedFields
        , collectDeclaredIds
        , collectTopLevelExpressions
        , collectQuestionLabels
        , collectQuestionReferences
        , collectQuestionTypes
        )

import QL.AST exposing (..)
import QL.FormVisitor as FormVisitor exposing (defaultConfig)
import Dict exposing (Dict)


type alias QuestionTypes =
    Dict String ValueType


collectConditions : Form -> List Expression
collectConditions form =
    FormVisitor.inspect
        { defaultConfig
            | onIfThen = FormVisitor.on (\( condition, _ ) result -> condition :: result)
            , onIfThenElse = FormVisitor.on (\( condition, _, _ ) result -> condition :: result)
        }
        form
        []


collectQuestionLabels : Form -> List ( Id, String )
collectQuestionLabels form =
    FormVisitor.inspect
        { defaultConfig
            | onField = FormVisitor.on (\( label, id, _ ) result -> ( id, label ) :: result)
            , onComputedField = FormVisitor.on (\( label, id, _, _ ) result -> ( id, label ) :: result)
        }
        form
        []


collectComputedFields : Form -> List ( Id, Expression )
collectComputedFields form =
    FormVisitor.inspect
        { defaultConfig
            | onComputedField = FormVisitor.on (\( _, id, _, computation ) result -> ( id, computation ) :: result)
        }
        form
        []


collectDeclaredIds : Form -> List Id
collectDeclaredIds form =
    FormVisitor.inspect
        { defaultConfig
            | onField = FormVisitor.on (\( _, id, _ ) result -> id :: result)
            , onComputedField = FormVisitor.on (\( _, id, _, _ ) result -> id :: result)
        }
        form
        []


collectTopLevelExpressions : Form -> List Expression
collectTopLevelExpressions form =
    FormVisitor.inspect
        { defaultConfig | onExpression = FormVisitor.on (::) }
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
            | onField = FormVisitor.on (\( _, ( name, _ ), valueType ) result -> Dict.insert name valueType result)
            , onComputedField = FormVisitor.on (\( _, ( name, _ ), valueType, _ ) result -> Dict.insert name valueType result)
        }
        form
        Dict.empty
