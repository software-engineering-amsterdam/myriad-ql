module QLS.TypeChecker.QuestionWidgetType exposing (check)

import QL.AST exposing (Form, Location, Id, ValueType(..))
import QLS.AST exposing (StyleSheet, Question, Configuration(..), Widget(..))
import QLS.AST.Widget as Widget
import QLS.TypeChecker.Messages exposing (Message(WidgetConfigMismatch))
import QLS.AST.Collectors as QLSCollectors
import Dict exposing (Dict)
import QL.AST.Collectors as QLCollectors exposing (TypeEnvironment)
import Maybe.Extra as Maybe
import QLS.TypeChecker.WidgetCompatibility as WidgetCompatibility


check : Form -> StyleSheet -> List Message
check form styleSheet =
    let
        typeEnv =
            QLCollectors.collectTypeEnv form
    in
        QLSCollectors.collectConfiguredQuestions styleSheet
            |> List.filterMap (invalidWidgetConfiguration typeEnv)


invalidWidgetConfiguration : TypeEnvironment -> ( Id, Configuration ) -> Maybe Message
invalidWidgetConfiguration typeEnv ( ( name, loc ), conf ) =
    Maybe.map2 (,) (Dict.get name typeEnv) (Widget.widgetFromConfiguration conf)
        |> Maybe.filter (not << WidgetCompatibility.allowedValueTypeWidgetPair)
        |> Maybe.map (\( valueType, widget ) -> WidgetConfigMismatch name loc valueType widget)
