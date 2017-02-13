module UI.FormDataTests exposing (all)

import Dict
import Expect
import Test exposing (Test, describe, test, fuzz2)
import UI.FormData as FormData exposing (..)
import Fuzz exposing (string)


startingFormData : FormData
startingFormData =
    Dict.fromList
        [ ( "foo", Integer 1 )
        , ( "bar", Str "Hello" )
        , ( "baz", Boolean True )
        ]


all : Test
all =
    describe "UI.FormData"
        [ test "removeFields" <|
            \() ->
                FormData.removeKeys [ "foo", "baz" ] startingFormData
                    |> Expect.equal (Dict.fromList [ ( "bar", Str "Hello" ) ])
        , test "getBoolean for existing value" <|
            \() ->
                FormData.getBoolean "baz" startingFormData |> Expect.equal (Just True)
        , test "getBoolean for missing value" <|
            \() ->
                FormData.getBoolean "missing" startingFormData |> Expect.equal Nothing
        , test "getBoolean for wrong type" <|
            \() ->
                FormData.getBoolean "foo" startingFormData |> Expect.equal Nothing
        , test "getString for existing value" <|
            \() ->
                FormData.getString "bar" startingFormData |> Expect.equal (Just "Hello")
        , test "getString for missing value" <|
            \() ->
                FormData.getString "missing" startingFormData |> Expect.equal Nothing
        , test "getString for wrong type" <|
            \() ->
                FormData.getString "foo" startingFormData |> Expect.equal Nothing
        , fuzz2 string string "withString should always allow to lookup the value with a getString" <|
            \k v ->
                startingFormData
                    |> FormData.withString k v
                    |> FormData.getString k
                    |> Expect.equal (Just v)
        ]
