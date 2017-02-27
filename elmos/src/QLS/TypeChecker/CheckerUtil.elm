module QLS.TypeChecker.CheckerUtil exposing (collectQuestionReferences)

import QL.AST exposing (Form, Location)
import QLS.AST exposing (StyleSheet, Question(Question, ConfiguredQuestion))
import QLS.StyleSheetVisitor as StyleSheetVisitor exposing (defaultConfig)
import VisitorUtil exposing (Order(Post))
import Dict exposing (Dict)


collectQuestionReferences : StyleSheet -> Dict String Location
collectQuestionReferences styleSheet =
    StyleSheetVisitor.inspect
        { defaultConfig | onQuestion = Post onQuestion }
        styleSheet
        Dict.empty


onQuestion : Question -> Dict String Location -> Dict String Location
onQuestion question context =
    case question of
        Question ( name, location ) ->
            Dict.insert name location context

        ConfiguredQuestion ( name, location ) _ ->
            Dict.insert name location context
