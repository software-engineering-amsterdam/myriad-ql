module FormUtilTests exposing (all)

import Test exposing (Test, describe, test)
import AST exposing (..)
import FormUtil
import Environment as Env
import Expect


exampleForm : Form
exampleForm =
    { id = "exampleForm"
    , items =
        [ Field "Name" "name" StringType
        , ComputedField "Name with prefix" "nameWithPrefix" StringType (Var "name")
        , Field "Has house" "hasHouse" BooleanType
        , IfThen (Var "hasHouse")
            [ Field "Price" "price" IntegerType ]
        , IfThenElse (ComparisonExpression Equal (Var "name") (Str "John"))
            [ Field "Is your name john?" "isJohn" BooleanType ]
            [ Field "Are you sure your name is not john?" "sure" BooleanType ]
        ]
    }


all : Test
all =
    describe "FormUtil"
        [ test "should return all fields and else branches on empty env" <|
            \() ->
                let
                    env =
                        Env.empty
                in
                    FormUtil.activeFields env exampleForm
                        |> Expect.equal
                            [ (,,) "Name" "name" StringType
                            , (,,) "Name with prefix" "nameWithPrefix" StringType
                            , (,,) "Has house" "hasHouse" BooleanType
                            , (,,) "Are you sure your name is not john?" "sure" BooleanType
                            ]
        , test "should not return the else branch when the expression branch evaluates to true" <|
            \() ->
                let
                    env =
                        Env.empty
                            |> Env.withString "name" "John"
                in
                    FormUtil.activeFields env exampleForm
                        |> Expect.equal
                            [ (,,) "Name" "name" StringType
                            , (,,) "Name with prefix" "nameWithPrefix" StringType
                            , (,,) "Has house" "hasHouse" BooleanType
                            , (,,) "Is your name john?" "isJohn" BooleanType
                            ]
        , test "should give all the if branch values if all expressions evaluate to true" <|
            \() ->
                let
                    env =
                        Env.empty
                            |> Env.withString "name" "John"
                            |> Env.withBoolean "hasHouse" True
                in
                    FormUtil.activeFields env exampleForm
                        |> Expect.equal
                            [ (,,) "Name" "name" StringType
                            , (,,) "Name with prefix" "nameWithPrefix" StringType
                            , (,,) "Has house" "hasHouse" BooleanType
                            , (,,) "Price" "price" IntegerType
                            , (,,) "Is your name john?" "isJohn" BooleanType
                            ]
        ]
