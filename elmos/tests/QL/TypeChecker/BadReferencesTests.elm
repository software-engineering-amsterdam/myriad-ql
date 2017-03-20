module QL.TypeChecker.BadReferencesTests exposing (all)

import QL.AST exposing (..)
import QL.ASTTestUtil exposing (emptyLoc, loc)
import QL.TypeChecker.BadReferences exposing (badReferences)
import QL.TypeChecker.Messages exposing (Message(Error), ErrorMessage(ReferenceToUndefinedQuestion))
import QL.Parser.Form exposing (form)
import Test exposing (..)
import ParserTestUtil exposing (parseToMaybe)
import Expect


all : Test
all =
    describe "BadReferences"
        [ testExamplesWithoutBadRefences
        , testBadReferences
        ]


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


testBadReferences : Test
testBadReferences =
    describe "testBadReferences"
        [ test "bad reference in If block" <|
            \() ->
                badReferences
                    (Form
                        ( "", emptyLoc )
                        [ IfThen (Var ( "x", loc 1 1 )) [] ]
                    )
                    |> Expect.equal [ Error <| ReferenceToUndefinedQuestion ( "x", loc 1 1 ) ]
        , test "bad reference in ComputedField" <|
            \() ->
                badReferences
                    (Form
                        ( "", emptyLoc )
                        [ ComputedField "question" ( "someId", emptyLoc ) StringType (Var ( "x", loc 1 1 )) ]
                    )
                    |> Expect.equal [ Error <| ReferenceToUndefinedQuestion ( "x", loc 1 1 ) ]
        , test "bad reference in ComputedField" <|
            \() ->
                badReferences
                    (Form
                        ( "", emptyLoc )
                        [ IfThen (Boolean emptyLoc True)
                            [ IfThen (Boolean emptyLoc True)
                                [ ComputedField "question" ( "someId", emptyLoc ) StringType (Var ( "x", loc 3 3 )) ]
                            ]
                        ]
                    )
                    |> Expect.equal [ Error <| ReferenceToUndefinedQuestion ( "x", loc 3 3 ) ]
        ]


testExamplesWithoutBadRefences : Test
testExamplesWithoutBadRefences =
    describe "testExamplesWithoutBadRefences"
        [ parseAndFindExpectedBadReferences "order of definition/usage should not matter 1" goodExample1 []
        , parseAndFindExpectedBadReferences "order of definition/usage should not matter 2" goodExample2 []
        , parseAndFindExpectedBadReferences "order of definition/usage should not matter 3" goodExample3 []
        , parseAndFindExpectedBadReferences "should not find any bad references" goodExample4 []
        , parseAndFindExpectedBadReferences "should not find any bad references in nested example" goodExample5 []
        , parseAndFindExpectedBadReferences "should not find any bad references in complex nested example" goodExample6 []
        ]


parseAndFindExpectedBadReferences : String -> String -> List Message -> Test
parseAndFindExpectedBadReferences message input expectedBadReferences =
    test message <|
        \() ->
            parseAndGetBadReferences input
                |> Expect.equal (Just expectedBadReferences)


parseAndGetBadReferences : String -> Maybe (List Message)
parseAndGetBadReferences rawForm =
    Maybe.map badReferences (parseToMaybe form rawForm)
