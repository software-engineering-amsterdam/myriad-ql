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
        , test "VariableName" <|
            \() ->
                Expect.equal (parseToMaybe variableName "varname") (Just "varname")
        , test "QuestionLabel" <|
            \() ->
                Expect.equal (parseToMaybe questionLabel "\"Is this a question?\"") (Just "Is this a question?")
        , test "ValueType integer" <|
            \() ->
                Expect.equal (parseToMaybe valueType "integer") (Just Integer)
        , test "ValueType string " <|
            \() ->
                Expect.equal (parseToMaybe valueType "string") (Just String)
        , test "ValueType boolean" <|
            \() ->
                Expect.equal (parseToMaybe valueType "boolean") (Just Boolean)
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
