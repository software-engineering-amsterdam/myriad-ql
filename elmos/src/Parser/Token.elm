module Parser.Token exposing (identifier, quotedString, parseLoc, withLoc)

import AST exposing (Location(Location), Id)
import Combine exposing (Parser, string, regex, withLocation, succeed, (>>=), (*>), (<*), (<$>))


parseLoc : Parser s Location
parseLoc =
    withLocation (asLocation >> succeed)


withLoc : Parser state (Location -> res) -> Parser state res
withLoc p =
    withLocation (asLocation >> \loc -> (|>) loc <$> p)


asLocation : Combine.ParseLocation -> Location
asLocation { line, column } =
    Location line column


identifier : Parser s Id
identifier =
    withLoc ((,) <$> regex "[a-z][a-zA-Z0-9_]*")


quotedString : Parser s String
quotedString =
    string "\"" *> regex "[^\\\"]+" <* string "\""
