module QL.TypeChecker.Tests exposing (all)

import Test exposing (Test, describe)
import QL.TypeChecker.BadReferencesTests as BadReferenceTests
import QL.TypeChecker.DuplicateQuestionsTests as DuplicateQuestionsTests
import QL.TypeChecker.CheckerUtilTests as CheckerUtilTests
import QL.TypeChecker.ExpressionsTests as ExpressionsTests


all : Test
all =
    describe "TypeChecker"
        [ BadReferenceTests.all
        , DuplicateQuestionsTests.all
        , CheckerUtilTests.all
        , ExpressionsTests.all
        ]
