module QL.FormVisitor exposing (Config, Order, defaultConfig, actionLambda, inspect, continue, on)

import QL.AST exposing (..)


type alias Config context =
    { onIfThen : Order context ( Expression, Block )
    , onIfThenElse : Order context ( Expression, Block, Block )
    , onField : Order context ( Label, Id, ValueType )
    , onComputedField : Order context ( Label, Id, ValueType, Expression )
    , onExpression : Order context Expression
    }


type Order context node
    = Continue
    | On (node -> context -> context)


continue : Order context node
continue =
    Continue


on : (node -> context -> context) -> Order context node
on =
    On


actionLambda : Order context node -> (context -> context) -> node -> context -> context
actionLambda action =
    case action of
        Continue ->
            (\f _ context -> f context)

        On g ->
            (\f node context -> f context |> g node)


defaultConfig : Config x
defaultConfig =
    { onIfThen = Continue
    , onIfThenElse = Continue
    , onField = Continue
    , onComputedField = Continue
    , onExpression = Continue
    }


inspect : Config a -> Form -> a -> a
inspect config { items } =
    inspectBlock config items


inspectBlock : Config a -> Block -> a -> a
inspectBlock config block context =
    List.foldl (inspectFormItem config) context block


inspectExpression : Config a -> Expression -> a -> a
inspectExpression config expression context =
    actionLambda config.onExpression
        identity
        expression
        context


inspectFormItem : Config a -> FormItem -> a -> a
inspectFormItem config formItem context =
    case formItem of
        Field label id valueType ->
            actionLambda config.onField
                identity
                ( label, id, valueType )
                context

        ComputedField label id valueType computation ->
            actionLambda config.onComputedField
                (inspectExpression config computation)
                ( label, id, valueType, computation )
                context

        IfThen condition thenBlock ->
            actionLambda config.onIfThen
                (inspectExpression config condition >> inspectBlock config thenBlock)
                ( condition, thenBlock )
                context

        IfThenElse condition thenBlock elseBlock ->
            actionLambda config.onIfThenElse
                (inspectExpression config condition >> inspectBlock config thenBlock >> inspectBlock config elseBlock)
                ( condition, thenBlock, elseBlock )
                context
