module Parser exposing (..)

import ExpressionParser exposing (..)
import Combine exposing (..)
import AST exposing (..)
import Combine.Extra exposing (whitespace1)


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
        <*> fieldLabel
        <*> (whitespace1 *> variableName)
        <*> (whitespace *> string ":" *> whitespace *> valueType)
        <*> maybe (whitespace *> string "=" *> whitespace *> expression)


ifBlock : Parser s IfBlock
ifBlock =
    lazy <|
        \() ->
            succeed IfBlock
                <*> (string "if" *> whitespace *> parens expression <* whitespace)
                <*> block
                <*> (maybe (whitespace *> string "else" *> whitespace *> block))


block : Parser s (List FormItem)
block =
    lazy <| \() -> braces (whitespace *> formItems <* whitespace)


formItems : Parser s (List FormItem)
formItems =
    lazy <| \() -> sepBy1 whitespace1 formItem


expression : Parser s Expression
expression =
    lazy <|
        \() ->
            choice
                [ Var <$> variableName
                , ParensExpression <$> parensExpression
                ]


parensExpression : Parser s Expression
parensExpression =
    parens (expression)


fieldLabel : Parser s String
fieldLabel =
    string "\"" *> regex "[^\\\"]+" <* string "\""


valueType : Parser s ValueType
valueType =
    choice
        [ string "string" $> StringType
        , string "boolean" $> BooleanType
        , string "integer" $> IntegerType
        ]
