module Tests exposing (all)

import Combine.ExtraTests
import Parser.ExpressionTests as ExpressionTests
import Parser.FormTests as FormTests
import Parser.TokenTests as TokenTests
import TypeChecker.TypeCheckerTests as TypeCheckerTests
import EvaluatorTests
import UI.FormDataTests
import Test exposing (Test, describe)


all : Test
all =
    describe "QL"
        [ FormTests.all
        , Combine.ExtraTests.all
        , ExpressionTests.all
        , TokenTests.all
        , UI.FormDataTests.all
        , TypeCheckerTests.all
        , EvaluatorTests.all
        ]
