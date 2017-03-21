module QL.AST.Collectors
    exposing
        ( TypeEnvironment
        , collectConditions
        , collectComputedQuestions
        , collectDeclaredIds
        , collectTopLevelExpressions
        , collectQuestionLabels
        , collectQuestionReferences
        , collectTypeEnv
        )

import QL.AST exposing (..)
import QL.FormVisitor as FormVisitor exposing (defaultConfig)
import Dict exposing (Dict)


type alias TypeEnvironment =
    Dict String ValueType


collectTypeEnv : Form -> TypeEnvironment
collectTypeEnv form =
    FormVisitor.inspect
        { defaultConfig
            | onQuestion = FormVisitor.on (\( _, ( name, _ ), valueType ) result -> Dict.insert name valueType result)
            , onComputedQuestion = FormVisitor.on (\( _, ( name, _ ), valueType, _ ) result -> Dict.insert name valueType result)
        }
        form
        Dict.empty


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
            | onQuestion = FormVisitor.on (\( label, id, _ ) result -> ( id, label ) :: result)
            , onComputedQuestion = FormVisitor.on (\( label, id, _, _ ) result -> ( id, label ) :: result)
        }
        form
        []


collectComputedQuestions : Form -> List ( Id, Expression )
collectComputedQuestions form =
    FormVisitor.inspect
        { defaultConfig
            | onComputedQuestion = FormVisitor.on (\( _, id, _, computation ) result -> ( id, computation ) :: result)
        }
        form
        []


collectDeclaredIds : Form -> List Id
collectDeclaredIds form =
    FormVisitor.inspect
        { defaultConfig
            | onQuestion = FormVisitor.on (\( _, id, _ ) result -> id :: result)
            , onComputedQuestion = FormVisitor.on (\( _, id, _, _ ) result -> id :: result)
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

        BinaryExpression _ _ exprLeft exprRight ->
            collectQuestionReferences exprLeft ++ collectQuestionReferences exprRight
