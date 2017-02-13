module Tests exposing (all)

import Combine.ExtraTests
import Parser.ExpressionTests as ExpressionTests
import Parser.FormTests as FormTests
import Parser.TokenTests as TokenTests
import UI.FormDataTests
import Test exposing (Test, describe)


all : Test
all =
    describe "QL Parser"
        [ FormTests.all
        , Combine.ExtraTests.all
        , ExpressionTests.all
        , TokenTests.all
        , UI.FormDataTests.all
        ]
