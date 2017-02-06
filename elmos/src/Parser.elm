module Parser exposing (..)

import Combine exposing (..)


formToken : Parser s String
formToken =
    string "form"


{-| TODO: fix tests
-}
variableName : Parser s String
variableName =
    regex "\\w+"


form : Parser s Form
form =
    map Form (formToken *> whitespace *> variableName <* maybe whitespace <* string "{" <* string "}")


question : Parser s Question
question =
    succeed Question
        <*> questionLabel
        <*> (whitespace *> variableName)
        <*> (maybe whitespace *> string ":" *> maybe whitespace *> valueType)


questionLabel : Parser s String
questionLabel =
    string "\"" *> regex "[^\\\"]+" <* string "\""


valueType : Parser s ValueType
valueType =
    choice [ string "integer" $> Integer ]


type alias Form =
    { name : String
    }


type alias Question =
    { label : String
    , id : String
    , valueType : ValueType
    }


type ValueType
    = String
    | Boolean
    | Integer
