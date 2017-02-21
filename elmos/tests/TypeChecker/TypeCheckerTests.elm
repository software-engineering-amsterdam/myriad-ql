module TypeChecker.TypeCheckerTests exposing (all)

import Test exposing (Test, describe)
import TypeChecker.BadReferencesTests as BadReferenceTests
import TypeChecker.DuplicateQuestionsTests as DuplicateQuestionsTests
import TypeChecker.CheckerUtilTests as CheckerUtilTests
import TypeChecker.ExpressionsTests as ExpressionsTests


all : Test
all =
    describe "TypeChecker tests"
        [ BadReferenceTests.all
        , DuplicateQuestionsTests.all
        , CheckerUtilTests.all
        , ExpressionsTests.all
        ]
