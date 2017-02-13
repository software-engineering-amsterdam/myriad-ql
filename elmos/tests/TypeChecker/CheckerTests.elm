module TypeChecker.CheckerTests exposing (all)

import Parser.Form exposing (form)
import Test exposing (..)
import ParserTestUtil exposing (parseToMaybe)
import TypeChecker.Checker exposing (..)
import Set
import Expect


-- Hoe kan je dit nu mooier maken?


undefinedVarExample1 : String
undefinedVarExample1 =
    """form taxOfficeExample {
        if (hasSoldHouse) {
            "What was the selling price?"
            sellingPrice: money
        }
      }"""


undefinedVarExample2 : String
undefinedVarExample2 =
    """form taxOfficeExample {
        "What was the selling price?"
        sellingPrice: money = price * 2
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


goodExample2 : String
goodExample2 =
    """form taxOfficeExample {
      "Q?"
      x: money = sellingPrice

      if (true) {
          "What was the selling price?"
          sellingPrice: integer = 3
      } else {
          "What was the selling price?"
          sellingPrice: integer = 4
      }
    }"""


goodExample3 : String
goodExample3 =
    """form taxOfficeExample {
    if (true) {
        "Q?"
        variable: money = sellingPrice
    }


    if (true) {
        "What was the selling price?"
        sellingPrice: money = 3
    } else {
        "What was the selling price?"
        sellingPrice: money = 4
    }
  }"""


goodExample4 : String
goodExample4 =
    """form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
}
"""


all : Test
all =
    describe "CheckerTests"
        [ testExamplesWithoutUnusedVars ]


testExamplesWithoutUnusedVars : Test
testExamplesWithoutUnusedVars =
    describe "testExamplesWithoutUnusedVars"
        [ parseAndFindNoUndefinedVariableReferences "Order of definition/usage should not matter 1" goodExample1
        , parseAndFindNoUndefinedVariableReferences "Order of definition/usage should not matter 2" goodExample2
        , parseAndFindNoUndefinedVariableReferences "Order of definition/usage should not matter 3" goodExample3
        , parseAndFindNoUndefinedVariableReferences "Should not find any undefined used vars" goodExample4
        ]


parseAndFindNoUndefinedVariableReferences : String -> String -> Test
parseAndFindNoUndefinedVariableReferences message input =
    test message <|
        \() ->
            let
                output =
                    parseAndGetUndefinedVariables input
            in
                Expect.equal output <| Just (Set.empty)


parseAndGetUndefinedVariables : String -> Maybe (Set.Set String)
parseAndGetUndefinedVariables rawForm =
    Maybe.map checkUndefinedVarReferences (parseToMaybe form rawForm)
