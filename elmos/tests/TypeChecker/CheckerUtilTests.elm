module TypeChecker.CheckerUtilTests exposing (all)

import Expect
import AST exposing (..)
import Test exposing (Test, describe, test)
import TypeChecker.CheckerUtil exposing (..)
import ASTTestUtil exposing (emptyLoc, loc)
import DictList


all : Test
all =
    describe "CheckerUtil"
        [ questionIndexFromBlockTest ]


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
                    |> Expect.equal (DictList.singleton "x" [ loc 3 3, loc 4 4 ])
        , test "definitions on rootscope for ifthenelse block with no shared definitions " <|
            \() ->
                questionIndexFromBlock
                    [ IfThenElse (Boolean emptyLoc True)
                        [ Field "label" ( "x", loc 3 3 ) StringType ]
                        [ Field "label" ( "y", loc 4 4 ) StringType ]
                    ]
                    |> Expect.equal (DictList.fromList [ ( "x", [ loc 3 3 ] ), ( "y", [ loc 4 4 ] ) ])
        , test "definition on rootscope for single ifThen block" <|
            \() ->
                questionIndexFromBlock
                    [ IfThen (Boolean emptyLoc True)
                        [ Field "label" ( "x", loc 3 3 ) StringType ]
                    ]
                    |> Expect.equal (DictList.singleton "x" [ loc 3 3 ])
        , test "no duplicate definitions in questionIndex for double ifthenelse blocks" <|
            \() ->
                questionIndexFromBlock
                    [ Field "label" ( "x", loc 3 3 ) StringType
                    , Field "label" ( "x", loc 4 4 ) StringType
                    ]
                    |> Expect.equal (DictList.singleton "x" [ loc 3 3 ])
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
                    |> Expect.equal (DictList.singleton "x" [ loc 3 3, loc 4 4 ])
        , test "put the first occurrence of a declaration in the questionIndex" <|
            \() ->
                questionIndexFromBlock
                    [ Field "QuestionA" ( "x", loc 3 3 ) StringType
                    , IfThen (Boolean emptyLoc True) [ Field "QuestionB" ( "x", loc 4 4 ) StringType ]
                    ]
                    |> Expect.equal (DictList.singleton "x" [ loc 3 3 ])
        ]


testWithQuestionIndex : String -> Form -> QuestionIndex -> Test
testWithQuestionIndex message form expectedIndex =
    test message <|
        \() -> questionIndexFromBlock form.items |> Expect.equal expectedIndex
