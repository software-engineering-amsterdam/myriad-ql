module TypeChecker.BadReferencesTests exposing (all)

import TypeChecker.BadReferences exposing (badReferences)
import Parser.Form exposing (form)
import Test exposing (..)
import ParserTestUtil exposing (parseToMaybe)
import Set
import Expect


badReferencesExample1 : String
badReferencesExample1 =
    """form taxOfficeExample {
        if (hasSoldHouse) {
            "What was the selling price?"
            sellingPrice: money
        }
      }"""


badReferencesExample2 : String
badReferencesExample2 =
    """form taxOfficeExample {
        "What was the selling price?"
        sellingPrice: money = price * 2
      }"""


badReferencesExample3 : String
badReferencesExample3 =
    """form taxOfficeExample {
        if(true){
          if(true){
              "Question ?"
              y: integer = sellingPrice
          }
        }
      }"""


badReferencesExample4 : String
badReferencesExample4 =
    """form taxOfficeExample {
        if(true){
            "Question ?"
            y: integer = sellingPrice
        }else{
            "QuestionB ?"
            y: integer = sellingPrice
        }

        if(true){
          if(true){
            "Question C>"
            y: integer = sellingPrice
          }
        }
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


goodExample5 : String
goodExample5 =
    """ form taxOfficeExample {
      if (true) {
        "conditionL"
        amount: integer

        if(amount < 3) {
          "Final1"
          finalAmount : integer = amount * 2
        }
      }
    }"""


goodExample6 : String
goodExample6 =
    """ form taxOfficeExample {
      if (true) {
        "amountL"
        amount : integer

        "conditionL"
        condition: boolean

        if(condition) {
          "Final1"
          finalAmount : integer = amount * 2
        } else {
          "Final2"
          finalAmount : integer = amount
        }
      } else {
        "Final3"
        finalAmount : integer
      }
    }"""


all : Test
all =
    describe "BadReferences"
        [ testExamplesWithoutBadRefences
        , testFindBadReferences
        ]


testFindBadReferences : Test
testFindBadReferences =
    describe "testFindBadReferences"
        [ parseAndFindExpectedBadReferences "Bad reference in If block" badReferencesExample1 (Set.fromList [ "hasSoldHouse" ])
        , parseAndFindExpectedBadReferences "Bad reference in question" badReferencesExample2 (Set.fromList [ "price" ])
        , parseAndFindExpectedBadReferences "Bad reference in nested If block" badReferencesExample3 (Set.fromList [ "sellingPrice" ])
        ]


testExamplesWithoutBadRefences : Test
testExamplesWithoutBadRefences =
    describe "testExamplesWithoutBadRefences"
        [ parseAndFindExpectedBadReferences "Order of definition/usage should not matter 1" goodExample1 Set.empty
        , parseAndFindExpectedBadReferences "Order of definition/usage should not matter 2" goodExample2 Set.empty
        , parseAndFindExpectedBadReferences "Order of definition/usage should not matter 3" goodExample3 Set.empty
        , parseAndFindExpectedBadReferences "Should not find any undefined used vars" goodExample4 Set.empty
        , parseAndFindExpectedBadReferences "Should not find any undefined used vars in nested example" goodExample5 Set.empty
        , parseAndFindExpectedBadReferences "Should not find any undefined used vars in complex nested example" goodExample6 Set.empty
        ]


parseAndFindExpectedBadReferences : String -> String -> Set.Set String -> Test
parseAndFindExpectedBadReferences message input expectedBadReferences =
    test message <|
        \() ->
            parseAndGetBadReferences input
                |> Expect.equal (Just (expectedBadReferences))


parseAndGetBadReferences : String -> Maybe (Set.Set String)
parseAndGetBadReferences rawForm =
    Maybe.map badReferences (parseToMaybe form rawForm)
