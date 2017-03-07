module QLS.AST.Collectors exposing (collectQuestionReferencesAsDict, collectQuestionReferences)

import QL.AST exposing (Form, Location)
import QLS.AST exposing (StyleSheet, Question(Question, ConfiguredQuestion))
import QLS.StyleSheetVisitor as StyleSheetVisitor exposing (defaultConfig)
import Dict exposing (Dict)


collectQuestionReferencesAsDict : StyleSheet -> Dict String Location
collectQuestionReferencesAsDict =
    Dict.fromList << collectQuestionReferences


collectQuestionReferences : StyleSheet -> List ( String, Location )
collectQuestionReferences styleSheet =
    let
        onQuestion : Question -> List ( String, Location ) -> List ( String, Location )
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
