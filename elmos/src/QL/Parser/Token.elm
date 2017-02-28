module QL.Parser.Token exposing (identifier, quotedString, parseLocation, withLocation)

import QL.AST exposing (Location(Location), Id)
import Combine exposing (Parser, string, regex, succeed, (>>=), (*>), (<*), (<$>))


parseLocation : Parser s Location
parseLocation =
    Combine.withLocation (asLocation >> succeed)


withLocation : Parser state (Location -> res) -> Parser state res
withLocation p =
    Combine.withLocation (asLocation >> \loc -> (|>) loc <$> p)


asLocation : Combine.ParseLocation -> Location
asLocation { line, column } =
    Location line column


identifier : Parser s Id
identifier =
    withLocation ((,) <$> regex "[a-z][a-zA-Z0-9_]*")


quotedString : Parser s String
quotedString =
    string "\"" *> regex "[^\\\"]+" <* string "\""
