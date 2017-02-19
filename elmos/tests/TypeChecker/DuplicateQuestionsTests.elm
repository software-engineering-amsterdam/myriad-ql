module TypeChecker.DuplicateQuestionsTests exposing (..)

import AST exposing (..)
import DictList
import Expect
import Parser.Form exposing (form)
import ParserTestUtil exposing (parseToMaybe)
import Test exposing (..)
import TypeChecker.DuplicateQuestions exposing (QuestionIndex, duplicateQuestionIdentifiers, questionIndexFromBlock)
import TypeChecker.Messages exposing (..)


loc : Int -> Int -> Location
loc =
    Location


emptyLoc : Location
emptyLoc =
    loc 0 0


all : Test
all =
    describe "DuplicateQuestions"
        [ questionIndexFromBlockTest
        , duplicateQuestionsTest
        ]


questionIndexFromBlockTest : Test
questionIndexFromBlockTest =
    describe "testFindQuestionDefinitions"
        [ test "definition for question shared by if and else branch" <|
            \() ->
                questionIndexFromBlock
                    [ IfThenElse (Boolean emptyLoc True)
                        [ Field "label" ( "x", loc 3 3 ) StringType ]
                        [ Field "label" ( "x", loc 4 4 ) StringType ]
                    ]
                    |> Expect.equal (DictList.singleton "x" [ loc 3 3, loc 4 4 ])
        , test "no definition on rootscope for ifthenelse block with no shared definitions " <|
            \() ->
                questionIndexFromBlock
                    [ IfThenElse (Boolean emptyLoc True)
                        [ Field "label" ( "x", loc 3 3 ) StringType ]
                        [ Field "label" ( "y", loc 4 4 ) StringType ]
                    ]
                    |> Expect.equal DictList.empty
        , test "no definition on rootscope for single ifThen block" <|
            \() ->
                questionIndexFromBlock
                    [ IfThen (Boolean emptyLoc True)
                        [ Field "label" ( "x", loc 3 3 ) StringType ]
                    ]
                    |> Expect.equal (DictList.empty)
        , test "no duplicate definitions in questionIndex for double ifthenelse blocks" <|
            \() ->
                questionIndexFromBlock
                    [ Field "label" ( "x", loc 3 3 ) StringType
                    , Field "label" ( "x", loc 4 4 ) StringType
                    ]
                    |> Expect.equal (DictList.singleton "x" [ loc 4 4 ])
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
                    |> Expect.equal (DictList.singleton "x" [ loc 5 5, loc 7 7 ])
        ]


testWithQuestionIndex : String -> Form -> QuestionIndex -> Test
testWithQuestionIndex message form expectedIndex =
    test message <|
        \() -> questionIndexFromBlock form.items |> Expect.equal expectedIndex


duplicateQuestionsTest : Test
duplicateQuestionsTest =
    describe "TypeChecker.DuplicateQuestions.duplicateQuestionIdentifiers"
        [ test "no duplicate question defintion for shared definition in ifthenelse block" <|
            \() ->
                duplicateQuestionIdentifiers
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
                duplicateQuestionIdentifiers
                    (Form
                        ( "", emptyLoc )
                        [ Field "StringQuestion" ( "x", loc 3 3 ) StringType
                        , Field "MoneyQuestion" ( "x", loc 4 4 ) IntegerType
                        ]
                    )
                    |> Expect.equal [ Error (DuplicateQuestionDefinition "x" [ loc 3 3, loc 4 4 ]) ]
        , test "find duplicate ignoring type" <|
            \() ->
                duplicateQuestionIdentifiers
                    (Form
                        ( "", emptyLoc )
                        [ Field "StringQuestion" ( "x", loc 3 3 ) StringType
                        , Field "MoneyQuestion" ( "x", loc 4 4 ) IntegerType
                        ]
                    )
                    |> Expect.equal [ Error (DuplicateQuestionDefinition "x" [ loc 3 3, loc 4 4 ]) ]
        , test "find duplicate in if block" <|
            \() ->
                duplicateQuestionIdentifiers
                    (Form
                        ( "", emptyLoc )
                        [ Field "QuestionA" ( "x", loc 3 3 ) StringType
                        , IfThen (Boolean emptyLoc True) [ Field "QuestionB" ( "x", loc 4 4 ) StringType ]
                        ]
                    )
                    |> Expect.equal [ Error (DuplicateQuestionDefinition "x" ([ loc 3 3, loc 4 4 ])) ]
        , test "find duplicate in ifThenElse block" <|
            \() ->
                duplicateQuestionIdentifiers
                    (Form
                        ( "", emptyLoc )
                        [ IfThenElse (Boolean emptyLoc True)
                            [ Field "label" ( "x", loc 3 3 ) StringType ]
                            [ Field "label" ( "x", loc 4 4 ) StringType ]
                        , Field "QuestionA" ( "x", loc 7 7 ) StringType
                        ]
                    )
                    |> Expect.equal [ Error (DuplicateQuestionDefinition "x" ([ loc 3 3, loc 4 4, loc 7 7 ])) ]
        ]


parseAndExpectDuplicates : String -> String -> List Message -> Test
parseAndExpectDuplicates message input expectedDuplicates =
    test message <|
        \() ->
            parseAndFindDuplicates input
                |> Expect.equal (Just expectedDuplicates)


parseAndFindDuplicates : String -> Maybe (List Message)
parseAndFindDuplicates rawForm =
    Maybe.map duplicateQuestionIdentifiers (parseToMaybe form rawForm)
