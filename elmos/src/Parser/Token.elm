module Parser.Token exposing (..)

import Combine exposing (..)


variableName : Parser s String
variableName =
    regex "[a-z0-9][a-zA-Z0-9_]*"


quotedString : Parser s String
quotedString =
    string "\"" *> regex "[^\\\"]+" <* string "\""
