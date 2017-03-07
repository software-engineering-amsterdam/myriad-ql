module QL.AST.CollectorsTests exposing (all)

import Expect
import QL.AST exposing (..)
import Test exposing (Test, describe, test)
import QL.AST.Collectors exposing (collectQuestionTypes)
import QL.ASTTestUtil exposing (emptyLoc, loc)
import Dict


all : Test
all =
    describe "Collectors"
        [ describe "collectQuestionTypes"
            [ test "collect question types for form" <|
                \() ->
                    collectQuestionTypes
                        { id = ( "my form", emptyLoc )
                        , items =
                            [ IfThenElse (Boolean emptyLoc True)
                                [ Field "label" ( "x", loc 3 3 ) StringType ]
                                [ Field "label" ( "y", loc 4 4 ) MoneyType ]
                            , Field "label" ( "z", loc 4 4 ) BooleanType
                            ]
                        }
                        |> Expect.equal (Dict.fromList [ ( "x", StringType ), ( "y", MoneyType ), ( "z", BooleanType ) ])
            ]
        ]
