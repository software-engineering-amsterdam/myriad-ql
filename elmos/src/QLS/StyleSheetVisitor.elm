module QLS.StyleSheetVisitor exposing (Config, defaultConfig, pre, post, inspect)

import QLS.AST exposing (..)
import QL.FormVisitor as FormVisitor exposing (actionLambda)


pre : (node -> context -> context) -> FormVisitor.Order context node
pre =
    FormVisitor.pre


post : (node -> context -> context) -> FormVisitor.Order context node
post =
    FormVisitor.post


type alias Config context =
    { onQuestion : FormVisitor.Order context Question
    }


defaultConfig : Config x
defaultConfig =
    { onQuestion = FormVisitor.continue
    }


inspect : Config a -> StyleSheet -> a -> a
inspect config styleSheet context =
    List.foldl (inspectPage config) context styleSheet.pages


inspectPage : Config a -> Page -> a -> a
inspectPage config (Page _ sections _) context =
    List.foldl (inspectSection config) context sections


inspectSection : Config a -> Section -> a -> a
inspectSection config section context =
    case section of
        SingleChildSection _ child ->
            (inspectSectionChild config child context)

        MultiChildSection _ children ->
            List.foldl (inspectSectionChild config) context children


inspectSectionChild : Config a -> SectionChild -> a -> a
inspectSectionChild config sectionChild context =
    case sectionChild of
        SubSection section ->
            inspectSection config section context

        Field question ->
            inspectQuestion config question context

        QLS.AST.Config _ ->
            context


inspectQuestion : Config a -> Question -> a -> a
inspectQuestion config question context =
    actionLambda config.onQuestion
        identity
        question
        context
