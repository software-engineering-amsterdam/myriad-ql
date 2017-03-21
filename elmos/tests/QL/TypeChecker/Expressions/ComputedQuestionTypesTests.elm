module QL.TypeChecker.Expressions.ComputedQuestionTypesTests exposing (all)

import QL.AST exposing (..)
import QL.ASTTestUtil exposing (emptyLoc)
import Dict
import Expect
import Test exposing (Test, describe, test)
import QL.TypeChecker.Messages exposing (..)
import QL.TypeChecker.Expressions.ComputedQuestionTypes exposing (computedQuestionTypeErrors)


all : Test
all =
    describe "ComputedQuestionTypes.computedQuestionTypeErrors"
        [ test "should detect incompatible type" <|
            \() ->
                computedQuestionTypeErrors
                    (Form ( "form", emptyLoc )
                        [ ComputedQuestion "Label" ( "x", Location 1 1 ) BooleanType (Str emptyLoc "test")
                        ]
                    )
                    (Dict.singleton "x" BooleanType)
                    |> Expect.equal [ Error (InvalidComputedQuestionType ( "x", Location 1 1 ) StringType BooleanType) ]
        , test "should not report an error on a Money field with an integerValue" <|
            \() ->
                computedQuestionTypeErrors
                    (Form ( "form", emptyLoc )
                        [ ComputedQuestion "Label" ( "x", Location 1 1 ) MoneyType (Integer emptyLoc 5)
                        ]
                    )
                    (Dict.singleton "x" MoneyType)
                    |> Expect.equal []
        ]
