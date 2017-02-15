module Combine.Extra exposing (whitespace1, trimmed, stringAs)

import Combine exposing (Parser, fail, succeed, string, whitespace, (>>=), (<*), (*>), ($>))


whitespace1 : Parser s String
whitespace1 =
    whitespace >>= nonEmpty


nonEmpty : String -> Parser s String
nonEmpty s =
    if String.isEmpty s then
        fail "Expected non empty parse"
    else
        succeed s


trimmed : Parser s res -> Parser s res
trimmed parser =
    whitespace *> parser <* whitespace


stringAs : String -> res -> Parser s res
stringAs input constant =
    string input $> constant
