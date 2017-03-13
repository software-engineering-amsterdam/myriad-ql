module QLS.TypeChecker.QuestionWidgetType exposing (check)

import QL.AST exposing (Form, Location, Id, ValueType(..))
import QLS.AST exposing (StyleSheet, Question, Configuration(..), Widget(..))
import QLS.TypeChecker.Messages exposing (Message(WidgetConfigMismatch))
import QLS.AST.Collectors as QLSCollectors
import Dict exposing (Dict)
import Dict.Extra as Dict
import QL.AST.Collectors as QLCollectors exposing (QuestionTypes)
import Maybe.Extra as Maybe
import QLS.TypeChecker.WidgetCompatibility as WidgetCompatibility


check : Form -> StyleSheet -> List Message
check form styleSheet =
    let
        questionTypes =
            QLCollectors.collectQuestionTypes form
    in
        QLSCollectors.collectConfiguredQuestions styleSheet
            |> List.filterMap (invalidWidgetConfiguration questionTypes)


invalidWidgetConfiguration : QuestionTypes -> ( Id, Configuration ) -> Maybe Message
invalidWidgetConfiguration questionTypes ( ( name, loc ), conf ) =
    Maybe.map2 (,) (Dict.get name questionTypes) (configuredWidget conf)
        |> Maybe.filter (not << WidgetCompatibility.allowedValueTypeWidgetPair)
        |> Maybe.map (\( vt, widget ) -> WidgetConfigMismatch name loc vt widget)


configuredWidget : Configuration -> Maybe Widget
configuredWidget c =
    case c of
        SingleConfig widget ->
            Just widget

        MultiConfig _ widgetMaybe ->
            widgetMaybe
