module Parser exposing (..)

import ExpressionParser exposing (..)
import Combine exposing (..)
import AST exposing (..)
import Combine.Extra exposing (whitespace1)


form : Parser s Form
form =
    succeed Form
        <*> (formToken *> whitespace *> variableName <* maybe whitespace)
        <*> block


formToken : Parser s String
formToken =
    string "form"


formItem : Parser s FormItem
formItem =
    lazy <|
        \() ->
            choice
                [ FieldItem <$> field
                , IfItem <$> ifBlock
                ]


field : Parser s Field
field =
    succeed Field
        <*> fieldLabel
        <*> (whitespace1 *> variableName)
        <*> (maybe whitespace *> string ":" *> maybe whitespace *> valueType)
        <*> (maybe (whitespace *> string "=" *> expression))


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
    lazy <| \() -> braces (whitespace *> many formItem <* whitespace)


{-| TODO add more
-}
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
