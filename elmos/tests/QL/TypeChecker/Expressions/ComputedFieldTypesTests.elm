module QL.TypeChecker.Expressions.ComputedFieldTypesTests exposing (all)

import QL.AST exposing (..)
import QL.ASTTestUtil exposing (emptyLoc)
import Dict
import Expect
import Test exposing (Test, describe, test)
import QL.TypeChecker.Messages exposing (..)
import QL.TypeChecker.Expressions.ComputedFieldTypes exposing (computedFieldTypeErrors)


all : Test
all =
    describe "ComputedFieldTypes.computedFieldTypeErrors"
        [ test "should detect incompatible type" <|
            \() ->
                computedFieldTypeErrors
                    (Form ( "form", emptyLoc )
                        [ ComputedField "Label" ( "x", Location 1 1 ) BooleanType (Str emptyLoc "test")
                        ]
                    )
                    (Dict.singleton "x" BooleanType)
                    |> Expect.equal [ Error (InvalidComputedFieldType ( "x", Location 1 1 ) StringType BooleanType) ]
        , test "should not report an error on a Money field with an integerValue" <|
            \() ->
                computedFieldTypeErrors
                    (Form ( "form", emptyLoc )
                        [ ComputedField "Label" ( "x", Location 1 1 ) MoneyType (Integer emptyLoc 5)
                        ]
                    )
                    (Dict.singleton "x" MoneyType)
                    |> Expect.equal []
        ]
