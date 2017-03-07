module ParserTestUtil
    exposing
        ( TestParserInput
        , testWithParser
        , testWithParserAndMap
        , parseToMaybe
        )

import Combine exposing (Parser, end, (<*))
import Test exposing (Test, describe, test)
import Expect


type alias TestParserInput b =
    ( String, String, Maybe b )


testWithParser : Parser () b -> String -> List ( String, String, Maybe b ) -> Test
testWithParser p =
    testWithParserAndMap p identity


testWithParserAndMap : Parser () b -> (b -> c) -> String -> List ( String, String, Maybe c ) -> Test
testWithParserAndMap p f name tests =
    describe name
        (List.map (testParser p f) tests)


testParser : Parser () b -> (b -> c) -> TestParserInput c -> Test
testParser p f ( name, input, output ) =
    test name <|
        \() -> parseToMaybe p input |> Maybe.map f |> Expect.equal output


parseToMaybe : Parser () res -> String -> Maybe res
parseToMaybe p s =
    case Combine.parse (p <* end) s of
        Err _ ->
            Nothing

        Ok ( _, _, res ) ->
            Just res
