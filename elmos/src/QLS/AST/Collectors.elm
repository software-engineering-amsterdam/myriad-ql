module QLS.AST.Collectors exposing (collectDefaultValueConfigs, collectQuestionReferencesAsDict, collectQuestionReferences, collectConfiguredQuestions)

import QL.AST exposing (Form, Location, Id, ValueType)
import QLS.AST exposing (StyleSheet, Question(Question, ConfiguredQuestion), Configuration)
import QLS.StyleSheetVisitor as StyleSheetVisitor exposing (defaultConfig)
import Dict exposing (Dict)


collectDefaultValueConfigs : StyleSheet -> List ( Location, ValueType, Configuration )
collectDefaultValueConfigs styleSheet =
    StyleSheetVisitor.inspect
        { defaultConfig | onDefaultValueConfig = StyleSheetVisitor.on (::) }
        styleSheet
        []


collectQuestionReferencesAsDict : StyleSheet -> Dict String Location
collectQuestionReferencesAsDict =
    Dict.fromList << collectQuestionReferences


collectQuestionReferences : StyleSheet -> List Id
collectQuestionReferences styleSheet =
    let
        onQuestion : Question -> List Id -> List Id
        onQuestion question context =
            case question of
                Question ref ->
                    ref :: context

                ConfiguredQuestion ref _ ->
                    ref :: context
    in
        StyleSheetVisitor.inspect
            { defaultConfig | onQuestion = StyleSheetVisitor.on onQuestion }
            styleSheet
            []


collectConfiguredQuestions : StyleSheet -> List ( Id, Configuration )
collectConfiguredQuestions styleSheet =
    StyleSheetVisitor.inspect
        { defaultConfig
            | onQuestion =
                StyleSheetVisitor.on <|
                    \question context ->
                        case question of
                            Question _ ->
                                context

                            ConfiguredQuestion ref configuration ->
                                ( ref, configuration ) :: context
        }
        styleSheet
        []
