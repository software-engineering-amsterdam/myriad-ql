module Tests exposing (..)

import Test exposing (..)
import Combine exposing (..)
import Expect
import Parser exposing (..)


all : Test
all =
    describe "QL Parser"
        [ test "FormToken" <|
            \() ->
                Expect.equal (parseToMaybe formToken "form") (Just "form")
        , test "FormQuestion" <|
            \() ->
                Expect.equal (parseToMaybe question "\"label\" id: integer")
                    (Just
                        { label = "label"
                        , id = "id"
                        , valueType = Integer
                        }
                    )
        , test "FormQuestion" <|
            \() ->
                Expect.equal (parseToMaybe question "\"label\" id: integer")
                    (Just
                        { label = "label"
                        , id = "id"
                        , valueType = Integer
                        }
                    )
        ]


parseToMaybe : Parser () res -> String -> Maybe res
parseToMaybe p s =
    case Combine.parse p s of
        Err e ->
            Nothing

        Ok ( _, _, res ) ->
            Just res
