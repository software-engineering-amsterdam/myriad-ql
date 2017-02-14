module Parser.Form exposing (..)

import AST exposing (..)
import Combine exposing (..)
import Combine.Extra exposing (whitespace1, trimmed, stringAs)
import Parser.Expression as Expression
import Parser.Token exposing (quotedString, variableName)


form : Parser s Form
form =
    trimmed <|
        succeed Form
            <*> (string "form" *> whitespace1 *> variableName)
            <*> (whitespace *> block)


formItems : Parser s (List FormItem)
formItems =
    lazy <| \() -> sepBy1 whitespace1 formItem


formItem : Parser s FormItem
formItem =
    lazy <|
        \() ->
            choice
                [ IfItem <$> ifBlock
                , FieldItem <$> field
                ]


field : Parser s Field
field =
    succeed Field
        <*> quotedString
        <*> (whitespace1 *> variableName)
        <*> (trimmed (string ":") *> valueType)
        <*> maybe (trimmed (string "=") *> Expression.expression)


ifBlock : Parser s IfBlock
ifBlock =
    lazy <|
        \() ->
            succeed IfBlock
                <*> (string "if" *> trimmed (parens Expression.expression))
                <*> block
                <*> (elseBranch <|> succeed [])


elseBranch : Parser s (List FormItem)
elseBranch =
    trimmed (string "else") *> block


block : Parser s (List FormItem)
block =
    lazy <| \() -> braces (trimmed formItems)


valueType : Parser s ValueType
valueType =
    choice
        [ stringAs "string" StringType
        , stringAs "boolean" BooleanType
        , stringAs "integer" IntegerType
        , stringAs "money" IntegerType
        ]
