module Parser.Token exposing (identifier, quotedString)

import AST exposing (Id)
import Combine exposing (Parser, string, regex, (*>), (<*))


identifier : Parser s Id
identifier =
    regex "[a-z][a-zA-Z0-9_]*"


quotedString : Parser s String
quotedString =
    string "\"" *> regex "[^\\\"]+" <* string "\""
