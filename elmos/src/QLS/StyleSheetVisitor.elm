module QLS.StyleSheetVisitor exposing (Config, defaultConfig, on, inspect)

import QL.AST exposing (ValueType, Location)
import QLS.AST exposing (..)
import QL.FormVisitor as FormVisitor exposing (actionLambda)


on : (node -> context -> context) -> FormVisitor.Order context node
on =
    FormVisitor.on


type alias Config context =
    { onQuestion : FormVisitor.Order context Question
    , onDefaultValueConfig : FormVisitor.Order context ( Location, ValueType, Configuration )
    }


defaultConfig : Config x
defaultConfig =
    { onQuestion = FormVisitor.continue
    , onDefaultValueConfig = FormVisitor.continue
    }


inspect : Config a -> StyleSheet -> a -> a
inspect config styleSheet context =
    List.foldl (inspectPage config) context styleSheet.pages


inspectPage : Config a -> Page -> a -> a
inspectPage config (Page _ sections defaultValueConfigs) context =
    context
        |> inspectSections config sections
        |> inspectDefaultValueConfigs config defaultValueConfigs


inspectDefaultValueConfigs : Config a -> List DefaultValueConfig -> a -> a
inspectDefaultValueConfigs config defaultValueConfigs context =
    List.foldl
        (inspectDefaultValueConfig config)
        context
        defaultValueConfigs


inspectDefaultValueConfig : Config a -> DefaultValueConfig -> a -> a
inspectDefaultValueConfig config (DefaultValueConfig location valueType configuration) context =
    actionLambda config.onDefaultValueConfig
        identity
        ( location, valueType, configuration )
        context


inspectSections : Config a -> List Section -> a -> a
inspectSections config sections context =
    List.foldl (inspectSection config) context sections


inspectSection : Config a -> Section -> a -> a
inspectSection config section context =
    case section of
        SingleChildSection _ child ->
            (inspectSectionChild config child context)

        MultiChildSection _ children configs ->
            List.foldl (inspectSectionChild config) context children
                |> inspectDefaultValueConfigs config configs


inspectSectionChild : Config a -> SectionChild -> a -> a
inspectSectionChild config sectionChild context =
    case sectionChild of
        SubSection section ->
            inspectSection config section context

        Field question ->
            inspectQuestion config question context


inspectQuestion : Config a -> Question -> a -> a
inspectQuestion config question context =
    actionLambda config.onQuestion
        identity
        question
        context
