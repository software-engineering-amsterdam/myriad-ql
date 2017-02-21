module QLS.TypeChecker.InvalidQuestionReferences exposing (check)

import AST exposing (Form, Location)
import QLS.AST exposing (StyleSheet, Question(Question, ConfiguredQuestion))
import QLS.FormVisitor as FormVisitor exposing (defaultConfig)
import QLS.StyleSheetVisitor as StyleSheetVisitor
import Dict exposing (Dict)


check : Form -> StyleSheet -> List String
check form styleSheet =
    let
        declaredQuestions =
            declaredQuestionsInForm form

        usedQuestion =
            usedQuestionForStyleSheet styleSheet

        invalidReferences =
            usedQuestion
                |> Dict.filter (\k _ -> not (List.member k declaredQuestions))
                |> Dict.toList
    in
        []


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

        QLS.AST.ConfiguredQuestion ( k, v ) _ ->
            Dict.insert k v context
