module TypeChecker.CheckerUtilTests exposing (all)

import Expect
import Test exposing (Test, describe, test)
import TypeChecker.CheckerUtil exposing (..)


all : Test
all =
    describe
        "TypeCheckerUtil"
        [ testRemoveListFrom
        , testIntersectLists
        ]


exampleFormItems : String
exampleFormItems =
    """"Did you sell a house in 2010?"
    hasSoldHouse: boolean

    "Did you buy a house in 2010?"
    hasBoughtHouse: boolean"""


testRemoveListFrom : Test
testRemoveListFrom =
    test "should correctly remove list" <|
        \() -> removeListFrom [ 2, 3, 4 ] [ 1, 2, 3, 4 ] |> Expect.equal [ 1 ]


testIntersectLists : Test
testIntersectLists =
    test "should correctly intersect list" <|
        \() -> intersectLists [ 1, 2, 3, 4 ] [ 2, 3, 4 ] |> Expect.equal [ 2, 3, 4 ]



-- testFindUsedVarsFromList : Test
-- testFindUsedVarsFromList =
--     test "Should find duplicate questions with different types" <|
--         \() ->
--             Maybe.map
--                 declaredVarsFromList
--                 (ParserTestUtil.parseToMaybe formItems exampleFormItems)
--                 |> Maybe.map DictSet.values
--                 |> Maybe.withDefault []
--                 |> Expect.equalLists
--                     [ ( "hasSoldHouse", AST.BooleanType )
--                     , ( "hasBoughtHouse", AST.BooleanType )
--                     ]
