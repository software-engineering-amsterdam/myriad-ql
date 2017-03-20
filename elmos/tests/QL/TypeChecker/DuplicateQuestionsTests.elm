module QL.TypeChecker.DuplicateQuestionsTests exposing (all)

import QL.AST exposing (..)
import QL.ASTTestUtil exposing (emptyLoc, loc)
import Expect
import Test exposing (Test, describe, test)
import QL.TypeChecker.DuplicateQuestions exposing (duplicateQuestions)
import QL.TypeChecker.Messages exposing (..)


all : Test
all =
    describe "DuplicateQuestions"
        [ test "no duplicate question defintion for shared definition in ifthenelse block" <|
            \() ->
                duplicateQuestions
                    (Form
                        ( "", emptyLoc )
                        [ IfThenElse (Boolean emptyLoc True)
                            [ Field "label" ( "x", loc 3 3 ) StringType ]
                            [ Field "label" ( "x", loc 4 4 ) StringType ]
                        ]
                    )
                    |> Expect.equal []
        , test "find duplicate ignoring type" <|
            \() ->
                duplicateQuestions
                    (Form
                        ( "", emptyLoc )
                        [ Field "StringQuestion" ( "x", loc 3 3 ) StringType
                        , Field "MoneyQuestion" ( "x", loc 4 4 ) IntegerType
                        ]
                    )
                    |> Expect.equal [ Error (DuplicateQuestionDefinition "x" [ loc 3 3, loc 4 4 ]) ]
        , test "find duplicate in if block" <|
            \() ->
                duplicateQuestions
                    (Form
                        ( "", emptyLoc )
                        [ Field "QuestionA" ( "x", loc 3 3 ) StringType
                        , IfThen (Boolean emptyLoc True) [ Field "QuestionB" ( "x", loc 4 4 ) StringType ]
                        ]
                    )
                    |> Expect.equal [ Error (DuplicateQuestionDefinition "x" [ loc 3 3, loc 4 4 ]) ]
        , test "find duplicate in ifThenElse block and merge into a single message" <|
            \() ->
                duplicateQuestions
                    (Form
                        ( "", emptyLoc )
                        [ IfThenElse (Boolean emptyLoc True)
                            [ Field "label" ( "x", loc 3 3 ) StringType ]
                            [ Field "label" ( "x", loc 4 4 ) StringType ]
                        , Field "QuestionA" ( "x", loc 7 7 ) StringType
                        ]
                    )
                    |> Expect.equal [ Error (DuplicateQuestionDefinition "x" [ loc 3 3, loc 4 4, loc 7 7 ]) ]
        ]
