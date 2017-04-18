module QL.AST.CollectorsTests exposing (all)

import Expect
import QL.AST exposing (..)
import Test exposing (Test, describe, test)
import QL.AST.Collectors exposing (collectTypeEnv)
import QL.ASTTestUtil exposing (emptyLoc, loc)
import Dict


all : Test
all =
    describe "Collectors"
        [ describe "collectTypeEnv"
            [ test "collect question types for form" <|
                \() ->
                    collectTypeEnv
                        { id = ( "my form", emptyLoc )
                        , items =
                            [ IfThenElse (Boolean emptyLoc True)
                                [ Question "label" ( "x", loc 3 3 ) StringType ]
                                [ Question "label" ( "y", loc 4 4 ) MoneyType ]
                            , Question "label" ( "z", loc 4 4 ) BooleanType
                            ]
                        }
                        |> Expect.equal (Dict.fromList [ ( "x", StringType ), ( "y", MoneyType ), ( "z", BooleanType ) ])
            ]
        ]
