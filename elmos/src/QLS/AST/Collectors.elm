module QLS.AST.Collectors exposing (collectQuestionReferencesAsDict, collectQuestionReferences, collectConfiguredQuestions)

import QL.AST exposing (Form, Location, Id)
import QLS.AST exposing (StyleSheet, Question(Question, ConfiguredQuestion), Configuration)
import QLS.StyleSheetVisitor as StyleSheetVisitor exposing (defaultConfig)
import Dict exposing (Dict)


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
            { defaultConfig | onQuestion = StyleSheetVisitor.post onQuestion }
            styleSheet
            []


collectConfiguredQuestions : StyleSheet -> List ( Id, Configuration )
collectConfiguredQuestions styleSheet =
    StyleSheetVisitor.inspect
        { defaultConfig
            | onQuestion =
                StyleSheetVisitor.post <|
                    \question context ->
                        case question of
                            Question ref ->
                                context

                            ConfiguredQuestion ref configuration ->
                                ( ref, configuration ) :: context
        }
        styleSheet
        []
