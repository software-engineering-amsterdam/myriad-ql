module QL.Parser.Form exposing (..)

import Combine exposing (..)
import Combine.Extra exposing (whitespace1, trimmed, stringAs)
import QL.AST exposing (..)
import QL.Parser.Expression as Expression
import QL.Parser.Token exposing (quotedString, identifier)


form : Parser s Form
form =
    trimmed <|
        succeed Form
            <*> (string "form" *> whitespace1 *> identifier)
            <*> (whitespace *> block)


block : Parser s (List FormItem)
block =
    lazy <| \() -> braces (trimmed formItems)


formItems : Parser s (List FormItem)
formItems =
    lazy <| \() -> sepBy1 whitespace1 formItem


formItem : Parser s FormItem
formItem =
    lazy <|
        \() ->
            choice
                [ ifThenElse
                , ifThen
                , computedField
                , field
                ]


field : Parser s FormItem
field =
    succeed Field
        <*> quotedString
        <*> (whitespace1 *> identifier)
        <*> (trimmed (string ":") *> valueType)


computedField : Parser s FormItem
computedField =
    succeed ComputedField
        <*> quotedString
        <*> (whitespace1 *> identifier)
        <*> (trimmed (string ":") *> valueType)
        <*> (trimmed (string "=") *> Expression.expression)


ifThen : Parser s FormItem
ifThen =
    lazy <|
        \() ->
            succeed IfThen
                <*> (string "if" *> trimmed (parens (trimmed Expression.expression)))
                <*> block


ifThenElse : Parser s FormItem
ifThenElse =
    lazy <|
        \() ->
            succeed IfThenElse
                <*> (string "if" *> trimmed (parens (trimmed Expression.expression)))
                <*> block
                <*> (trimmed (string "else") *> block)


valueType : Parser s ValueType
valueType =
    choice
        [ stringAs "string" StringType
        , stringAs "boolean" BooleanType
        , stringAs "integer" IntegerType
        , stringAs "money" MoneyType
        ]
