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
        if (sellingPrice){
            "Question ?"
            y: integer
        }

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
        [ testExamplesWithoutUnusedVars ]


testExamplesWithoutUnusedVars : Test
testExamplesWithoutUnusedVars =
    describe "testExamplesWithoutUnusedVars"
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
