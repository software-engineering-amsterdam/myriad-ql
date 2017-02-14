module TypeChecker.TypeCheckerTests exposing (all)

import Test exposing (Test, describe)
import TypeChecker.BadReferencesTests as BadReferenceTests


all : Test
all =
    describe "TypeChecker tests"
        [ BadReferenceTests.all ]
