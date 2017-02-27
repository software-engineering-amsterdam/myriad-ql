module QLS.TypeChecker.InvalidQuestionReferences exposing (check)

import QL.AST exposing (Form, Location)
import QLS.AST exposing (StyleSheet, Question(Question, ConfiguredQuestion))
import QL.FormVisitor as FormVisitor exposing (defaultConfig)
import QLS.StyleSheetVisitor as StyleSheetVisitor
import QLS.TypeChecker.Messages exposing (Message, undefinedQuestionReference)
import Dict exposing (Dict)


check : Form -> StyleSheet -> List Message
check form styleSheet =
    let
        declaredQuestions =
            declaredQuestionsInForm form
    in
        usedQuestionForStyleSheet styleSheet
            |> Dict.filter (\k _ -> not (List.member k declaredQuestions))
            |> Dict.toList
            |> List.map (uncurry undefinedQuestionReference)


declaredQuestionsInForm : Form -> List String
declaredQuestionsInForm form =
    FormVisitor.inspect
        { defaultConfig
            | onField = FormVisitor.Post (\( _, ( id, _ ), _ ) context -> id :: context)
            , onComputedField = FormVisitor.Post (\( _, ( id, _ ), _, _ ) context -> id :: context)
        }
        form
        []


usedQuestionForStyleSheet : StyleSheet -> Dict String Location
usedQuestionForStyleSheet styleSheet =
    StyleSheetVisitor.inspect
        { defaultConfigStyleSheetVisitor | onQuestion = StyleSheetVisitor.Post onQuestion }
        styleSheet
        Dict.empty


defaultConfigStyleSheetVisitor : StyleSheetVisitor.Config x
defaultConfigStyleSheetVisitor =
    StyleSheetVisitor.defaultConfig


onQuestion : Question -> Dict String Location -> Dict String Location
onQuestion question context =
    case question of
        Question ( k, v ) ->
            Dict.insert k v context

        ConfiguredQuestion ( k, v ) _ ->
            Dict.insert k v context
