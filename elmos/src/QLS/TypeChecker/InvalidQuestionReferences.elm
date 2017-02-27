module QLS.TypeChecker.InvalidQuestionReferences exposing (check)

import QL.AST exposing (Form, Location)
import QLS.AST exposing (StyleSheet, Question(Question, ConfiguredQuestion))
import QLS.StyleSheetVisitor as StyleSheetVisitor exposing (defaultConfig)
import QLS.TypeChecker.Messages exposing (Message, undefinedQuestionReference)
import QL.TypeChecker.CheckerUtil as CheckerUtil
import VisitorUtil exposing (Order(Post))
import Dict exposing (Dict)


check : Form -> StyleSheet -> List Message
check form styleSheet =
    let
        declaredQuestions =
            CheckerUtil.collectDeclaredIds form
                |> List.map Tuple.first
    in
        collectQuestionReferences styleSheet
            |> Dict.filter (\k _ -> not (List.member k declaredQuestions))
            |> Dict.toList
            |> List.map (uncurry undefinedQuestionReference)


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
