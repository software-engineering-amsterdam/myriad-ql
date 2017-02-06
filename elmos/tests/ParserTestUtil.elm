module ParserTestUtil exposing (TestParserInput, testWithParser)

import Combine exposing (Parser)
import Test exposing (Test, describe, test)
import Expect


type alias TestParserInput b =
    ( String, String, Maybe b )


testWithParser : Parser () b -> String -> List ( String, String, Maybe b ) -> Test
testWithParser p name tests =
    describe name
        (List.map (testParser p) tests)


testParser : Parser () b -> TestParserInput b -> Test
testParser p ( name, input, output ) =
    test name <|
        \() -> parseToMaybe p input |> Expect.equal output


parseToMaybe : Parser () res -> String -> Maybe res
parseToMaybe p s =
    case Combine.parse p s of
        Err e ->
            Nothing

        Ok ( _, _, res ) ->
            Just res
