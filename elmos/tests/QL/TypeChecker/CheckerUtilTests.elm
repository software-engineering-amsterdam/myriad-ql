module QL.TypeChecker.CheckerUtilTests exposing (all)

import Expect
import QL.AST exposing (..)
import Test exposing (Test, describe, test)
import QL.TypeChecker.CheckerUtil exposing (..)
import QL.ASTTestUtil exposing (emptyLoc, loc)
import Dict


all : Test
all =
    describe "CheckerUtil"
        [ questionTypesTest
        , questionIndexFromBlockTest
        ]


questionTypesTest : Test
questionTypesTest =
    describe "questionTypes"
        [ test "collect question types for form" <|
            \() ->
                questionTypes
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


questionIndexFromBlockTest : Test
questionIndexFromBlockTest =
    describe "questionIndexFromBlockTest"
        [ test "shared definition for question defined by if and else branch" <|
            \() ->
                questionIndexFromBlock
                    [ IfThenElse (Boolean emptyLoc True)
                        [ Field "label" ( "x", loc 3 3 ) StringType ]
                        [ Field "label" ( "x", loc 4 4 ) StringType ]
                    ]
                    |> Expect.equal (Dict.singleton "x" [ loc 3 3, loc 4 4 ])
        , test "definitions on rootscope for ifthenelse block with no shared definitions " <|
            \() ->
                questionIndexFromBlock
                    [ IfThenElse (Boolean emptyLoc True)
                        [ Field "label" ( "x", loc 3 3 ) StringType ]
                        [ Field "label" ( "y", loc 4 4 ) StringType ]
                    ]
                    |> Expect.equal (Dict.fromList [ ( "x", [ loc 3 3 ] ), ( "y", [ loc 4 4 ] ) ])
        , test "definition on rootscope for single ifThen block" <|
            \() ->
                questionIndexFromBlock
                    [ IfThen (Boolean emptyLoc True)
                        [ Field "label" ( "x", loc 3 3 ) StringType ]
                    ]
                    |> Expect.equal (Dict.singleton "x" [ loc 3 3 ])
        , test "no duplicate definitions in questionIndex for double ifthenelse blocks" <|
            \() ->
                questionIndexFromBlock
                    [ Field "label" ( "x", loc 3 3 ) StringType
                    , Field "label" ( "x", loc 4 4 ) StringType
                    ]
                    |> Expect.equal (Dict.singleton "x" [ loc 3 3 ])
        , test "no duplicate definitions in questionIndex for double ifthenelse blocks" <|
            \() ->
                questionIndexFromBlock
                    [ IfThenElse (Boolean emptyLoc True)
                        [ Field "label" ( "x", loc 3 3 ) StringType ]
                        [ Field "label" ( "x", loc 4 4 ) StringType ]
                    , IfThenElse (Boolean emptyLoc True)
                        [ Field "label" ( "x", loc 5 5 ) StringType ]
                        [ Field "label" ( "x", loc 7 7 ) StringType ]
                    ]
                    |> Expect.equal (Dict.singleton "x" [ loc 3 3, loc 4 4 ])
        , test "put the first occurrence of a declaration in the questionIndex" <|
            \() ->
                questionIndexFromBlock
                    [ Field "QuestionA" ( "x", loc 3 3 ) StringType
                    , IfThen (Boolean emptyLoc True) [ Field "QuestionB" ( "x", loc 4 4 ) StringType ]
                    ]
                    |> Expect.equal (Dict.singleton "x" [ loc 3 3 ])
        ]


testWithQuestionIndex : String -> Form -> QuestionIndex -> Test
testWithQuestionIndex message form expectedIndex =
    test message <|
        \() -> questionIndexFromBlock form.items |> Expect.equal expectedIndex
