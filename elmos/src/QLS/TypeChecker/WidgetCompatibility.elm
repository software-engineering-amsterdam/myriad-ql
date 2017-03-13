module QLS.TypeChecker.WidgetCompatibility exposing (allowedValueTypeWidgetPair)

import QL.AST exposing (Form, Location, Id, ValueType(..))
import QLS.AST exposing (StyleSheet, Question, Configuration(..), Widget(..))


allowedValueTypeWidgetPair : ( ValueType, Widget ) -> Bool
allowedValueTypeWidgetPair ( valueType, widget ) =
    case valueType of
        BooleanType ->
            validWidgetForBooleanType widget

        StringType ->
            validWidgetForStringType widget

        IntegerType ->
            validWidgetForIntegerType widget

        MoneyType ->
            validWidgetForMoneyType widget


validWidgetForBooleanType : Widget -> Bool
validWidgetForBooleanType w =
    case w of
        Radio [ x, y ] ->
            True

        Radio _ ->
            False

        Spinbox ->
            False

        Checkbox ->
            True

        Text ->
            False

        Slider _ ->
            False


validWidgetForStringType : Widget -> Bool
validWidgetForStringType w =
    case w of
        Radio _ ->
            True

        Spinbox ->
            False

        Checkbox ->
            False

        Text ->
            True

        Slider _ ->
            False


validWidgetForIntegerType : Widget -> Bool
validWidgetForIntegerType w =
    case w of
        Radio _ ->
            False

        Spinbox ->
            True

        Checkbox ->
            False

        Text ->
            True

        Slider _ ->
            True


validWidgetForMoneyType : Widget -> Bool
validWidgetForMoneyType w =
    case w of
        Radio _ ->
            False

        Spinbox ->
            True

        Checkbox ->
            False

        Text ->
            True

        Slider _ ->
            False
