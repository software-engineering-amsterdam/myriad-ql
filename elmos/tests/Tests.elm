module Tests exposing (all)

import Combine.ExtraTests
import Parser.ExpressionTests as ExpressionTests
import Parser.FormTests as FormTests
import Parser.TokenTests as TokenTests
import TypeChecker.TypeCheckerTests as TypeCheckerTests
import EvaluatorTests
import EnvironmentTests
import FormUtilTests
import Test exposing (Test, describe)


all : Test
all =
    describe "QL"
        [ TokenTests.all
        , ExpressionTests.all
        , FormTests.all
        , Combine.ExtraTests.all
        , EnvironmentTests.all
        , TypeCheckerTests.all
        , EvaluatorTests.all
        , FormUtilTests.all
        ]
