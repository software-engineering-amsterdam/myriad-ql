module QL.TypeChecker.Tests exposing (all)

import Test exposing (Test, describe)
import QL.TypeChecker.BadReferencesTests
import QL.TypeChecker.DuplicateQuestionsTests
import QL.TypeChecker.CheckerUtilTests
import QL.TypeChecker.ExpressionsTests
import QL.TypeChecker.CyclicDependenciesTests


all : Test
all =
    describe "TypeChecker"
        [ QL.TypeChecker.BadReferencesTests.all
        , QL.TypeChecker.DuplicateQuestionsTests.all
        , QL.TypeChecker.CheckerUtilTests.all
        , QL.TypeChecker.ExpressionsTests.all
        , QL.TypeChecker.CyclicDependenciesTests.all
        ]
