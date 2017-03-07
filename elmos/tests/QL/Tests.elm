module QL.Tests exposing (all)

import Test exposing (Test, describe)
import QL.AST.Tests
import QL.EvaluatorTests
import QL.EnvironmentTests
import QL.Parser.Tests
import QL.TypeChecker.Tests


all : Test
all =
    describe "QL"
        [ QL.AST.Tests.all
        , QL.Parser.Tests.all
        , QL.TypeChecker.Tests.all
        , QL.EnvironmentTests.all
        , QL.EvaluatorTests.all
        ]
