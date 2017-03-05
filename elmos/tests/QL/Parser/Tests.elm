module QL.Parser.Tests exposing (all)

import QL.Parser.ExpressionTests as ExpressionTests
import QL.Parser.FormTests as FormTests
import QL.Parser.TokenTests as TokenTests
import Test exposing (Test, describe)


all : Test
all =
    describe "Parser"
        [ TokenTests.all
        , ExpressionTests.all
        , FormTests.all
        ]
