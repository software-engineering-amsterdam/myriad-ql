module QL.TypeChecker.Tests exposing (all)

import QL.TypeChecker.BadReferencesTests
import QL.TypeChecker.CyclicDependenciesTests
import QL.TypeChecker.QuestionIndexTests
import QL.TypeChecker.DuplicateLabelsTests
import QL.TypeChecker.DuplicateQuestionsTests
import QL.TypeChecker.ExpressionsTests
import Test exposing (Test, describe)


all : Test
all =
    describe "TypeChecker"
        [ QL.TypeChecker.BadReferencesTests.all
        , QL.TypeChecker.DuplicateQuestionsTests.all
        , QL.TypeChecker.QuestionIndexTests.all
        , QL.TypeChecker.ExpressionsTests.all
        , QL.TypeChecker.CyclicDependenciesTests.all
        , QL.TypeChecker.DuplicateLabelsTests.all
        ]
