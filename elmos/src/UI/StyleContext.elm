module UI.StyleContext exposing (..)

import QLS.AST exposing (..)
import QL.AST exposing (ValueType(..))
import Maybe.Extra as Maybe


type alias ValueTypeStyle =
    ( Maybe Widget, List Style )


type alias StyleContext =
    { integerType : ValueTypeStyle
    , moneyType : ValueTypeStyle
    , stringType : ValueTypeStyle
    , booleanType : ValueTypeStyle
    }


init : List DefaultValueConfig -> StyleContext
init x =
    addDefaultConfigs x
        { integerType = ( Nothing, [] )
        , stringType = ( Nothing, [] )
        , moneyType = ( Nothing, [] )
        , booleanType = ( Nothing, [] )
        }


addDefaultConfigs : List DefaultValueConfig -> StyleContext -> StyleContext
addDefaultConfigs =
    flip (List.foldl addDefaultConfig)


addDefaultConfig : DefaultValueConfig -> StyleContext -> StyleContext
addDefaultConfig (DefaultValueConfig _ vt conf) =
    addValueTypeConfig vt conf


addValueTypeConfig : ValueType -> Configuration -> StyleContext -> StyleContext
addValueTypeConfig vt conf context =
    case vt of
        MoneyType ->
            { context | moneyType = mergeValueTypeStyle conf context.moneyType }

        StringType ->
            { context | stringType = mergeValueTypeStyle conf context.stringType }

        BooleanType ->
            { context | booleanType = mergeValueTypeStyle conf context.booleanType }

        IntegerType ->
            { context | integerType = mergeValueTypeStyle conf context.integerType }


mergeValueTypeStyle : Configuration -> ValueTypeStyle -> ValueTypeStyle
mergeValueTypeStyle config ( widget, styles ) =
    case config of
        SingleConfig w ->
            ( Just w
            , styles
            )

        MultiConfig newStyles maybeWidget ->
            ( Maybe.or maybeWidget widget
            , styles ++ newStyles
            )


getForValueType : ValueType -> StyleContext -> ( Maybe Widget, List Style )
getForValueType vt context =
    case vt of
        MoneyType ->
            context.moneyType

        StringType ->
            context.stringType

        BooleanType ->
            context.booleanType

        IntegerType ->
            context.integerType
