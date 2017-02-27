module QL.EnvironmentTests exposing (all)

import Dict
import Expect
import Test exposing (Test, describe, test, fuzz2)
import QL.Environment as Env exposing (Environment)
import QL.Values as Values
import Fuzz exposing (string, bool, int)


startingEnvironment : Environment
startingEnvironment =
    Dict.fromList
        [ ( "foo", Values.int 1 )
        , ( "bar", Values.string "Hello" )
        , ( "baz", Values.bool True )
        ]


all : Test
all =
    describe "Environment"
        [ test "removeFields" <|
            \() ->
                Env.removeKeys [ "foo", "baz" ] startingEnvironment
                    |> Expect.equal (Dict.fromList [ ( "bar", Values.string "Hello" ) ])
        , test "getBoolean for existing value" <|
            \() ->
                Env.getBoolean "baz" startingEnvironment |> Expect.equal (Just True)
        , test "getBoolean for missing value" <|
            \() ->
                Env.getBoolean "missing" startingEnvironment |> Expect.equal Nothing
        , test "getBoolean for wrong type" <|
            \() ->
                Env.getBoolean "foo" startingEnvironment |> Expect.equal Nothing
        , fuzz2 string bool "withString should always allow to lookup the value with a getString" <|
            \k v ->
                startingEnvironment
                    |> Env.withBoolean k v
                    |> Env.getBoolean k
                    |> Expect.equal (Just v)
        , test "getString for existing value" <|
            \() ->
                Env.getString "bar" startingEnvironment |> Expect.equal (Just "Hello")
        , test "getString for missing value" <|
            \() ->
                Env.getString "missing" startingEnvironment |> Expect.equal Nothing
        , test "getString for wrong type" <|
            \() ->
                Env.getString "foo" startingEnvironment |> Expect.equal Nothing
        , fuzz2 string string "withString should always allow to lookup the value with a getString" <|
            \k v ->
                startingEnvironment
                    |> Env.withString k v
                    |> Env.getString k
                    |> Expect.equal (Just v)
        , test "getInteger for existing value" <|
            \() ->
                Env.getInteger "foo" startingEnvironment |> Expect.equal (Just 1)
        , test "getInteger for missing value" <|
            \() ->
                Env.getInteger "missing" startingEnvironment |> Expect.equal Nothing
        , test "getInteger for wrong type" <|
            \() ->
                Env.getInteger "baz" startingEnvironment |> Expect.equal Nothing
        , fuzz2 string int "withInteger should always allow to lookup the value with a getInteger" <|
            \k v ->
                startingEnvironment
                    |> Env.withInteger k v
                    |> Env.getInteger k
                    |> Expect.equal (Just v)
        ]
