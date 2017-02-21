module QLS.StyleSheetVisitor exposing (..)

import QLS.AST exposing (..)


type Order context node
    = Continue
    | Pre (node -> context -> context)
    | Post (node -> context -> context)


type alias Config context =
    { onQuestion : Order context Question
    }


defaultConfig : Config x
defaultConfig =
    { onQuestion = Continue
    }


actionLambda : Order context node -> (context -> context) -> node -> context -> context
actionLambda action =
    case action of
        Continue ->
            (\f _ context -> f context)

        Pre g ->
            (\f node context -> g node context |> f)

        Post g ->
            (\f node context -> f context |> g node)


inspect : Config a -> StyleSheet -> a -> a
inspect config styleSheet context =
    List.foldl (inspectPage config) context styleSheet.pages


inspectPage : Config a -> Page -> a -> a
inspectPage config page context =
    List.foldl (inspectSection config) context page.sections


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
