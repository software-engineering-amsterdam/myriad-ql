module UI.FormDataTests exposing (all)

import Dict
import Expect
import Test exposing (Test, describe, test, fuzz2)
import UI.FormData as FormData exposing (..)
import Fuzz exposing (string, bool, int)


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
        , fuzz2 string bool "withString should always allow to lookup the value with a getString" <|
            \k v ->
                startingFormData
                    |> FormData.withBoolean k v
                    |> FormData.getBoolean k
                    |> Expect.equal (Just v)
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
        , test "getInteger for existing value" <|
            \() ->
                FormData.getInteger "foo" startingFormData |> Expect.equal (Just 1)
        , test "getInteger for missing value" <|
            \() ->
                FormData.getInteger "missing" startingFormData |> Expect.equal Nothing
        , test "getInteger for wrong type" <|
            \() ->
                FormData.getInteger "baz" startingFormData |> Expect.equal Nothing
        , fuzz2 string int "withInteger should always allow to lookup the value with a getInteger" <|
            \k v ->
                startingFormData
                    |> FormData.withInteger k v
                    |> FormData.getInteger k
                    |> Expect.equal (Just v)
        ]
