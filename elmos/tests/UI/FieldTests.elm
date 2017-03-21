module UI.FieldTests exposing (all)

import Test exposing (Test, describe, test)
import QL.AST exposing (..)
import UI.Field as Field exposing (Field(Editable, Computed))
import QL.Environment as Env
import QL.Values as Values
import Expect


exampleForm : Form
exampleForm =
    { id = ( "exampleForm", Location 0 0 )
    , items =
        [ Question "Name" ( "name", Location 0 0 ) StringType
        , ComputedQuestion "Name with prefix" ( "nameWithPrefix", Location 0 0 ) StringType (Var ( "name", Location 0 0 ))
        , Question "Has house" ( "hasHouse", Location 0 0 ) BooleanType
        , IfThen (Var ( "hasHouse", Location 0 0 ))
            [ Question "Price" ( "price", Location 0 0 ) IntegerType ]
        , IfThenElse (BinaryExpression (Comparison Equal) (Location 0 0) (Var ( "name", Location 0 0 )) (Str (Location 0 0) "John"))
            [ Question "Is your name john?" ( "isJohn", Location 0 0 ) BooleanType ]
            [ Question "Are you sure your name is not john?" ( "sure", Location 0 0 ) BooleanType ]
        ]
    }


all : Test
all =
    describe "Field"
        [ test "should return all fields and else branches on empty env" <|
            \() ->
                let
                    env =
                        Env.empty
                in
                    Field.activeFields env exampleForm
                        |> Expect.equal
                            [ Editable "Name" "name" StringType
                            , Computed "Name with prefix" "nameWithPrefix" StringType (Var ( "name", Location 0 0 ))
                            , Editable "Has house" "hasHouse" BooleanType
                            , Editable "Are you sure your name is not john?" "sure" BooleanType
                            ]
        , test "should not return the else branch when the expression branch evaluates to true" <|
            \() ->
                let
                    env =
                        Env.empty
                            |> Env.withFormValue "name" (Values.Str "John")
                in
                    Field.activeFields env exampleForm
                        |> Expect.equal
                            [ Editable "Name" "name" StringType
                            , Computed "Name with prefix" "nameWithPrefix" StringType (Var ( "name", Location 0 0 ))
                            , Editable "Has house" "hasHouse" BooleanType
                            , Editable "Is your name john?" "isJohn" BooleanType
                            ]
        , test "should give all the if branch values if all expressions evaluate to true" <|
            \() ->
                let
                    env =
                        Env.empty
                            |> Env.withFormValue "name" (Values.Str "John")
                            |> Env.withFormValue "hasHouse" (Values.Boolean True)
                in
                    Field.activeFields env exampleForm
                        |> Expect.equal
                            [ Editable "Name" "name" StringType
                            , Computed "Name with prefix" "nameWithPrefix" StringType (Var ( "name", Location 0 0 ))
                            , Editable "Has house" "hasHouse" BooleanType
                            , Editable "Price" "price" IntegerType
                            , Editable "Is your name john?" "isJohn" BooleanType
                            ]
        ]
