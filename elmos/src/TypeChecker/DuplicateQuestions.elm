module TypeChecker.DuplicateQuestions exposing (..)

import AST exposing (..)
import DictList exposing (DictList)
import TypeChecker.Messages as Messages exposing (Message)


-- TODO: Use DictList instead of DictList?


type alias QuestionIndex =
    DictList String (List Location)


duplicateQuestionIdentifiers : Form -> List Message
duplicateQuestionIdentifiers form =
    duplicateQuestionsInBlock (questionIndexFromBlock form.items) form.items


duplicateQuestionsInBlock : QuestionIndex -> Block -> List Messages.Message
duplicateQuestionsInBlock parentScope block =
    let
        currentScope =
            DictList.union parentScope (questionIndexFromBlock block)
    in
        List.concatMap (duplicateQuestionsInItem currentScope) block


duplicateQuestionsInItem : QuestionIndex -> FormItem -> List Message
duplicateQuestionsInItem scope formItem =
    case formItem of
        Field _ questionId _ ->
            duplicateQuestion scope questionId
                |> Maybe.map List.singleton
                |> Maybe.withDefault []

        ComputedField _ questionId _ _ ->
            duplicateQuestion scope questionId
                |> Maybe.map List.singleton
                |> Maybe.withDefault []

        IfThen _ thenBranch ->
            duplicateQuestionsInBlock scope thenBranch

        IfThenElse _ thenBranch elseBranch ->
            (++)
                (duplicateQuestionsInBlock scope thenBranch)
                (duplicateQuestionsInBlock scope elseBranch)


{-| TODO only take the first definition .... Uses DictList.concat, if a block contains a duplicate, one of them is ignored
-}
questionIndexFromBlock : Block -> QuestionIndex
questionIndexFromBlock =
    List.map questionIndexFromItem >> DictList.concat



-- List.foldl (\item -> questionIndexFromItem item |> DictList.union) DictList.empty


questionIndexFromItem : FormItem -> QuestionIndex
questionIndexFromItem item =
    case item of
        Field _ ( id, loc ) _ ->
            DictList.singleton id [ loc ]

        ComputedField _ ( id, loc ) valueType _ ->
            DictList.singleton id [ loc ]

        IfThen _ _ ->
            DictList.empty

        IfThenElse _ thenBranch elseBranch ->
            mergeSharedQuestionDefinitions
                (questionIndexFromBlock thenBranch)
                (questionIndexFromBlock elseBranch)


mergeSharedQuestionDefinitions : QuestionIndex -> QuestionIndex -> QuestionIndex
mergeSharedQuestionDefinitions indexA indexB =
    DictList.merge
        (\_ _ result -> result)
        (\key itemA itemB result -> DictList.insert key (itemA ++ itemB) result)
        (\_ _ result -> result)
        indexA
        indexB
        DictList.empty


duplicateQuestion : QuestionIndex -> Id -> Maybe Message
duplicateQuestion index questionId =
    case DictList.get (Tuple.first questionId) index of
        Nothing ->
            Nothing

        Just locations ->
            duplicateQuestionDefinitions locations questionId


duplicateQuestionDefinitions : List Location -> Id -> Maybe Message
duplicateQuestionDefinitions definedLocations ( id, locationToCheck ) =
    case List.member locationToCheck definedLocations of
        True ->
            Nothing

        False ->
            Just <| Messages.duplicateQuestionDefinition id (locationToCheck :: definedLocations)
