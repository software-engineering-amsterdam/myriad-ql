module QL.TypeChecker.ExpressionsTests exposing (all)

import Test exposing (Test, describe)
import QL.TypeChecker.Expressions.ExpressionTypesTests as ExpressionTypeTests
import QL.TypeChecker.Expressions.ComputedFieldTypesTests as ComputedFieldTypesTests
import QL.TypeChecker.Expressions.ConditionTypesTests as ConditionTypesTests


all : Test
all =
    describe
        "Expressions"
        [ ExpressionTypeTests.all
        , ComputedFieldTypesTests.all
        , ConditionTypesTests.all
        ]
