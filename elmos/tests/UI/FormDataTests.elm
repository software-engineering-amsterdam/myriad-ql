module UI.FormDataTests exposing (all)

import Dict
import Expect
import Test exposing (Test, describe, test)
import UI.FormData as FormData exposing (..)


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
        ]
