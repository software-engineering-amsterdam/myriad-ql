module TypeChecker.DuplicateQuestions exposing (..)

import AST exposing (..)
import DictList exposing (DictList)
import Maybe.Extra
import List.Extra
import TypeChecker.Messages as Messages exposing (Message)


-- TODO: Use DictList instead of DictList?


type alias QuestionIndex =
    DictList String (List Location)


type alias Duplicate =
    ( String, List Location )


duplicateQuestionIdentifiers : Form -> List Message
duplicateQuestionIdentifiers form =
    duplicateQuestionsInBlock (questionIndexFromBlock form.items) form.items
        |> mergeOverlappingDuplicates
        |> DictList.map Messages.duplicateQuestionDefinition
        |> DictList.values


mergeOverlappingDuplicates : List Duplicate -> DictList String (List Location)
mergeOverlappingDuplicates duplicates =
    let
        updateDuplicateIndex ( id, locations ) duplicateIndex =
            DictList.update id (updateLocations locations) duplicateIndex

        updateLocations : List Location -> Maybe (List Location) -> Maybe (List Location)
        updateLocations newLocations existingLocations =
            Maybe.withDefault [] existingLocations
                |> (++) newLocations
                |> List.Extra.uniqueBy (\(Location line col) -> ( line, col ))
                |> Just
    in
        List.foldl (updateDuplicateIndex) DictList.empty duplicates


duplicateQuestionsInBlock : QuestionIndex -> Block -> List Duplicate
duplicateQuestionsInBlock parentScope block =
    let
        currentScope =
            DictList.union parentScope (questionIndexFromBlock block)
    in
        List.concatMap (duplicateQuestionsInItem currentScope) block


duplicateQuestionsInItem : QuestionIndex -> FormItem -> List Duplicate
duplicateQuestionsInItem declaredQuestions formItem =
    case formItem of
        Field _ questionId _ ->
            duplicateQuestion declaredQuestions questionId
                |> Maybe.Extra.maybeToList

        ComputedField _ questionId _ _ ->
            duplicateQuestion declaredQuestions questionId
                |> Maybe.Extra.maybeToList

        IfThen _ thenBranch ->
            duplicateQuestionsInBlock declaredQuestions thenBranch

        IfThenElse _ thenBranch elseBranch ->
            (++)
                (duplicateQuestionsInBlock declaredQuestions thenBranch)
                (duplicateQuestionsInBlock declaredQuestions elseBranch)


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

        ComputedField _ ( id, loc ) _ _ ->
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



-- TODO: These names are shit, they both check if it is a duplicate but they do it on a different level and only one of them creates the actuale duplciate


duplicateQuestion : QuestionIndex -> Id -> Maybe Duplicate
duplicateQuestion declaredQuestions question =
    DictList.get (Tuple.first question) declaredQuestions
        |> Maybe.andThen (duplicateQuestionDefinitions question)


duplicateQuestionDefinitions : Id -> List Location -> Maybe Duplicate
duplicateQuestionDefinitions ( id, location ) declaredQuestionLocations =
    if List.member location declaredQuestionLocations then
        Nothing
    else
        Just ( id, (location :: declaredQuestionLocations) )
