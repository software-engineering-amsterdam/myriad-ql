module Parser.Form exposing (..)

import AST exposing (..)
import Combine exposing (..)
import Combine.Extra exposing (whitespace1)
import Parser.Expression as Expression
import Parser.Token exposing (quotedString, variableName)


form : Parser s Form
form =
    (succeed Form
        <*> (formToken *> whitespace *> variableName <* whitespace)
        <*> block
    )
        <* whitespace


formToken : Parser s String
formToken =
    string "form"


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
        <*> (whitespace *> string ":" *> whitespace *> valueType)
        <*> maybe (whitespace *> string "=" *> whitespace *> Expression.expression)


ifBlock : Parser s IfBlock
ifBlock =
    lazy <|
        \() ->
            succeed IfBlock
                <*> (string "if" *> whitespace *> parens Expression.expression <* whitespace)
                <*> block
                <*> maybe (whitespace *> string "else" *> whitespace *> block)


block : Parser s (List FormItem)
block =
    lazy <| \() -> braces (whitespace *> formItems <* whitespace)


valueType : Parser s ValueType
valueType =
    choice
        [ string "string" $> StringType
        , string "boolean" $> BooleanType
        , string "integer" $> IntegerType
        , string "money" $> IntegerType
        ]
