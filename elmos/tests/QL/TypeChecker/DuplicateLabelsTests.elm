module QL.TypeChecker.DuplicateLabelsTests exposing (all)

import QL.AST exposing (..)
import QL.ASTTestUtil exposing (emptyLoc, loc)
import Expect
import Test exposing (Test, describe, test)
import QL.TypeChecker.DuplicateLabels exposing (duplicateLabels)
import QL.TypeChecker.Messages exposing (Message(Warning), WarningMessage(DuplicateLabels))


all : Test
all =
    describe "DuplicateLabels"
        [ test "should detect duplicate labels" <|
            \() ->
                duplicateLabels
                    (Form
                        ( "", emptyLoc )
                        [ IfThenElse (Boolean emptyLoc True)
                            [ Question "label" ( "x", loc 3 3 ) StringType ]
                            [ Question "label" ( "y", loc 4 4 ) StringType ]
                        ]
                    )
                    |> Expect.equal [ Warning (DuplicateLabels "label" [ ( "y", loc 4 4 ), ( "x", loc 3 3 ) ]) ]
        , test "should detect duplicate labels with three occurrences" <|
            \() ->
                duplicateLabels
                    (Form
                        ( "", emptyLoc )
                        [ Question "someLabel?" ( "x", loc 3 3 ) StringType
                        , Question "someLabel?" ( "y", loc 4 4 ) IntegerType
                        , Question "someLabel?" ( "z", loc 5 5 ) MoneyType
                        ]
                    )
                    |> Expect.equal [ Warning (DuplicateLabels "someLabel?" [ ( "z", loc 5 5 ), ( "y", loc 4 4 ), ( "x", loc 3 3 ) ]) ]
        , test "should only detect labels that are duplicated" <|
            \() ->
                duplicateLabels
                    (Form
                        ( "", emptyLoc )
                        [ Question "someLabel?" ( "x", loc 3 3 ) StringType
                        , Question "OtherLabel!" ( "z", loc 5 5 ) MoneyType
                        ]
                    )
                    |> Expect.equal []
        , test "should detect multiple types of duplicate labels" <|
            \() ->
                duplicateLabels
                    (Form
                        ( "", emptyLoc )
                        [ Question "OtherLabel!" ( "x", loc 3 3 ) StringType
                        , Question "someLabel?" ( "y", loc 4 4 ) IntegerType
                        , Question "OtherLabel!" ( "z", loc 5 5 ) MoneyType
                        , Question "someLabel?" ( "a", loc 6 6 ) MoneyType
                        ]
                    )
                    |> Expect.equal
                        [ Warning (DuplicateLabels "OtherLabel!" [ ( "z", loc 5 5 ), ( "x", loc 3 3 ) ])
                        , Warning (DuplicateLabels "someLabel?" [ ( "a", loc 6 6 ), ( "y", loc 4 4 ) ])
                        ]
        ]
