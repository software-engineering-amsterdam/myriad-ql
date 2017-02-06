module Parser exposing (..)

import Combine exposing (..)
import AST exposing (..)
import Combine.Extra exposing (whitespace1)


form : Parser s Form
form =
    succeed Form
        <*> (formToken *> whitespace *> variableName <* maybe whitespace)
        <*> between (string "{") (string "}") (many formItem)


formToken : Parser s String
formToken =
    string "form"


{-| TODO: fix tests
-}
variableName : Parser s String
variableName =
    regex "[a-z0-9][a-zA-Z0-9_]*"


formItem : Parser s FormItem
formItem =
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


ifBlock : Parser s IfBlock
ifBlock =
    lazy <|
        \() ->
            succeed IfBlock
                <*> (string "(" *> expression <* string ")")
                <*> (string "{" *> many formItem <* string "}")
                <*> (maybe (whitespace *> string "else" *> whitespace *> string "{" *> whitespace *> many formItem <* whitespace <* string "}"))


{-| TODO add more
-}
expression : Parser s Expression
expression =
    choice [ Var <$> variableName ]


fieldLabel : Parser s String
fieldLabel =
    string "\"" *> regex "[^\\\"]+" <* string "\""


valueType : Parser s ValueType
valueType =
    choice
        [ string "string" $> String
        , string "boolean" $> Boolean
        , string "integer" $> Integer
        ]
