module QL.TypeChecker.DuplicateQuestionsTests exposing (all)

import QL.AST exposing (..)
import QL.ASTTestUtil exposing (emptyLoc, loc)
import Expect
import Test exposing (Test, describe, test)
import QL.TypeChecker.DuplicateQuestions exposing (check)
import QL.TypeChecker.Messages exposing (..)


all : Test
all =
    describe "DuplicateQuestions"
        [ test "no duplicate question defintion for shared definition in ifthenelse block" <|
            \() ->
                check
                    (Form
                        ( "", emptyLoc )
                        [ IfThenElse (Boolean emptyLoc True)
                            [ Question "label" ( "x", loc 3 3 ) StringType ]
                            [ Question "label" ( "x", loc 4 4 ) StringType ]
                        ]
                    )
                    |> Expect.equal []
        , test "find duplicate ignoring type" <|
            \() ->
                check
                    (Form
                        ( "", emptyLoc )
                        [ Question "StringQuestion" ( "x", loc 3 3 ) StringType
                        , Question "MoneyQuestion" ( "x", loc 4 4 ) IntegerType
                        ]
                    )
                    |> Expect.equal [ Error (DuplicateQuestionDefinition "x" [ loc 3 3, loc 4 4 ]) ]
        , test "find duplicate in if block" <|
            \() ->
                check
                    (Form
                        ( "", emptyLoc )
                        [ Question "QuestionA" ( "x", loc 3 3 ) StringType
                        , IfThen (Boolean emptyLoc True) [ Question "QuestionB" ( "x", loc 4 4 ) StringType ]
                        ]
                    )
                    |> Expect.equal [ Error (DuplicateQuestionDefinition "x" [ loc 3 3, loc 4 4 ]) ]
        , test "find duplicate in ifThenElse block and merge into a single message" <|
            \() ->
                check
                    (Form
                        ( "", emptyLoc )
                        [ IfThenElse (Boolean emptyLoc True)
                            [ Question "label" ( "x", loc 3 3 ) StringType ]
                            [ Question "label" ( "x", loc 4 4 ) StringType ]
                        , Question "QuestionA" ( "x", loc 7 7 ) StringType
                        ]
                    )
                    |> Expect.equal [ Error (DuplicateQuestionDefinition "x" [ loc 3 3, loc 4 4, loc 7 7 ]) ]
        ]
