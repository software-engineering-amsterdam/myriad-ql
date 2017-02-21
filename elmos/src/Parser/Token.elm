module Parser.Token exposing (variableName, quotedString)

import Combine exposing (Parser, string, regex, (*>), (<*))


variableName : Parser s String
variableName =
    regex "[a-z][a-zA-Z0-9_]*"


quotedString : Parser s String
quotedString =
    string "\"" *> regex "[^\\\"]+" <* string "\""
