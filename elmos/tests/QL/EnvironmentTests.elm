module QL.EnvironmentTests exposing (all)

import Expect
import Test exposing (Test, describe, test, fuzz2)
import QL.Values as Values
import QL.Environment as Env exposing (Environment)
import Fuzz exposing (string, bool, int)


startingEnvironment : Environment
startingEnvironment =
    Env.empty
        |> Env.withFormValue "foo" (Values.int 1)
        |> Env.withFormValue "bar" (Values.string "Hello")
        |> Env.withFormValue "baz" (Values.bool True)


all : Test
all =
    describe "Environment"
        [ test "removeFields" <|
            \() ->
                Env.removeKeys [ "foo", "baz" ] startingEnvironment
                    |> Expect.equal (Env.empty |> Env.withFormValue "bar" (Values.string "Hello"))
        , test "getBoolean for existing value" <|
            \() ->
                startingEnvironment
                    |> Env.getFormValue "baz"
                    |> Maybe.andThen Values.asBool
                    |> Expect.equal (Just True)
        , test "getBoolean for missing value" <|
            \() ->
                startingEnvironment
                    |> Env.getFormValue "missing"
                    |> Maybe.andThen Values.asBool
                    |> Expect.equal Nothing
        , test "getBoolean for wrong type" <|
            \() ->
                startingEnvironment
                    |> Env.getFormValue "foo"
                    |> Maybe.andThen Values.asBool
                    |> Expect.equal Nothing
        , fuzz2 string bool "withString should always allow to lookup the value with a getString" <|
            \k v ->
                startingEnvironment
                    |> Env.withFormValue k (Values.bool v)
                    |> Env.getFormValue k
                    |> Maybe.andThen Values.asBool
                    |> Expect.equal (Just v)
        , test "getString for existing value" <|
            \() ->
                startingEnvironment
                    |> Env.getFormValue "bar"
                    |> Maybe.andThen Values.asString
                    |> Expect.equal (Just "Hello")
        , test "getString for missing value" <|
            \() ->
                startingEnvironment
                    |> Env.getFormValue "missing"
                    |> Maybe.andThen Values.asString
                    |> Expect.equal Nothing
        , test "getString for wrong type" <|
            \() ->
                startingEnvironment
                    |> Env.getFormValue "foo"
                    |> Maybe.andThen Values.asString
                    |> Expect.equal Nothing
        , fuzz2 string string "withString should always allow to lookup the value with a getString" <|
            \k v ->
                startingEnvironment
                    |> Env.withFormValue k (Values.string v)
                    |> Env.getFormValue k
                    |> Maybe.andThen Values.asString
                    |> Expect.equal (Just v)
        , test "getInteger for existing value" <|
            \() ->
                startingEnvironment
                    |> Env.getFormValue "foo"
                    |> Maybe.andThen Values.asInt
                    |> Expect.equal (Just 1)
        , test "getInteger for missing value" <|
            \() ->
                startingEnvironment
                    |> Env.getFormValue "missing"
                    |> Maybe.andThen Values.asInt
                    |> Expect.equal Nothing
        , test "getInteger for wrong type" <|
            \() ->
                startingEnvironment
                    |> Env.getFormValue "baz"
                    |> Maybe.andThen Values.asInt
                    |> Expect.equal Nothing
        , fuzz2 string int "withInteger should always allow to lookup the value with a getInteger" <|
            \k v ->
                startingEnvironment
                    |> Env.withFormValue k (Values.int v)
                    |> Env.getFormValue k
                    |> Maybe.andThen Values.asInt
                    |> Expect.equal (Just v)
        ]
