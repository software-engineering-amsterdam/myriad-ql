module QL.TypeChecker.Expressions.ConditionTypesTests exposing (all)

import QL.AST exposing (..)
import QL.ASTTestUtil exposing (emptyLoc)
import Dict
import Expect
import Test exposing (Test, describe, test)
import QL.TypeChecker.Messages exposing (..)
import QL.TypeChecker.Expressions.ConditionTypes exposing (conditionTypeErrors)


all : Test
all =
    describe "ConditionTypes.conditionTypeErrors"
        [ test "should detect condition with non-boolean type" <|
            \() ->
                conditionTypeErrors
                    (Form ( "form", emptyLoc )
                        [ IfThen (Str emptyLoc "test") [ Field "Label" ( "t", Location 1 1 ) BooleanType ] ]
                    )
                    (Dict.empty)
                    |> Expect.equal [ Error (InvalidConditionType (Location 0 0) StringType) ]
        , test "should detect condition with var value with non-boolean type" <|
            \() ->
                conditionTypeErrors
                    (Form ( "form", emptyLoc )
                        [ IfThen (Var ( "y", Location 0 0 )) [ Field "Label" ( "x", Location 1 1 ) BooleanType ] ]
                    )
                    (Dict.singleton "y" IntegerType)
                    |> Expect.equal [ Error (InvalidConditionType (Location 0 0) IntegerType) ]
        ]
