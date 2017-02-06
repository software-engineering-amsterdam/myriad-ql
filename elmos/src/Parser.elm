module Parser exposing (..)

import Combine exposing (..)
import AST exposing (..)


form : Parser s Form
form =
    succeed Form
        <*> (formToken *> whitespace *> variableName <* maybe whitespace <* string "{")
        <*> (many formItem <* string "}")


formItem : Parser s FormItem
formItem =
    choice
        [ FieldItem <$> field ]


formToken : Parser s String
formToken =
    string "form"


{-| TODO: fix tests
-}
variableName : Parser s String
variableName =
    regex "\\w+"


field : Parser s Field
field =
    succeed Field
        <*> questionLabel
        <*> (whitespace *> variableName)
        <*> (maybe whitespace *> string ":" *> maybe whitespace *> valueType)


questionLabel : Parser s String
questionLabel =
    string "\"" *> regex "[^\\\"]+" <* string "\""


valueType : Parser s ValueType
valueType =
    choice
        [ string "string" $> String
        , string "boolean" $> Boolean
        , string "integer" $> Integer
        ]
