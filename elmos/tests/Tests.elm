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
import QLS.Parser.ConfigurationTests
import QLS.Parser.StyleTests
import QLS.Parser.WidgetTests


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
        , QLS.Parser.ConfigurationTests.all
        , QLS.Parser.StyleTests.all
        , QLS.Parser.WidgetTests.all
        ]
