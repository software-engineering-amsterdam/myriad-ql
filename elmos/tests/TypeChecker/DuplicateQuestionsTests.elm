module TypeChecker.DuplicateQuestionsTests exposing (..)

import TypeChecker.DuplicateQuestions exposing (duplicateQuestions)
import Parser.Form exposing (form)
import Test exposing (..)
import ParserTestUtil exposing (parseToMaybe)
import Expect


duplicateQuestionExample1 : String
duplicateQuestionExample1 =
    """form taxOfficeExample {
          "somelabel?"
          sellingPrice: integer = 3
          "Label?"
          sellingPrice: money = 4
      }"""


duplicateQuestionExample2 : String
duplicateQuestionExample2 =
    """form taxOfficeExample {
    "Label"
    sellingPrice: integer = 3

    if (true) {
        "Label"
        sellingPrice: integer = 3
    }
    """


duplicateQuestionExample3 : String
duplicateQuestionExample3 =
    """form taxOfficeExample {
        if (true) {
            "Label"
            sellingPrice: integer = 3
        } else {
            "Label"
            sellingPrice: integer = 4
        }

        "Label2"
        sellingPrice: money = sellingPrice * 2

      }"""


goodExample1 : String
goodExample1 =
    """form taxOfficeExample {
        if (true) {
            "What was the selling price?"
            sellingPrice: integer = 3
        } else {
            "What was the selling price?"
            sellingPrice: integer = 4
        }
      }"""


all : Test
all =
    describe "DuplicateQuestions"
        [ testExamplesWithoutDuplicates, testExamplesWithDuplicates ]


testExamplesWithDuplicates : Test
testExamplesWithDuplicates =
    describe "testExamplesWithDuplicates"
        [ parseAndExpectDuplicates "Should find duplicates" duplicateQuestionExample1 [ "sellingPrice" ]
        , parseAndExpectDuplicates "Should find duplicates in nested if" duplicateQuestionExample2 [ "sellingPrice" ]
        , parseAndExpectDuplicates "Should find duplicates with if else" duplicateQuestionExample3 [ "sellingPrice" ]
        ]


testExamplesWithoutDuplicates : Test
testExamplesWithoutDuplicates =
    describe "testExamplesWithoutDuplicates"
        [ parseAndExpectDuplicates "Should find no duplicates" goodExample1 []
        ]


parseAndExpectDuplicates : String -> String -> List String -> Test
parseAndExpectDuplicates message input expectedDuplicates =
    test message <|
        \() ->
            parseAndFindDuplicates input
                |> Expect.equal (Just expectedDuplicates)


parseAndFindDuplicates : String -> Maybe (List String)
parseAndFindDuplicates rawForm =
    Maybe.map duplicateQuestions (parseToMaybe form rawForm)
