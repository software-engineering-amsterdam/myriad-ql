module QL.TypeChecker.ExpressionsTests exposing (all)

import Test exposing (Test, describe)
import QL.TypeChecker.Expressions.ExpressionTypesTests as ExpressionTypeTests
import QL.TypeChecker.Expressions.ComputedQuestionTypesTests as ComputedQuestionTypesTests
import QL.TypeChecker.Expressions.ConditionTypesTests as ConditionTypesTests


all : Test
all =
    describe
        "Expressions"
        [ ExpressionTypeTests.all
        , ComputedQuestionTypesTests.all
        , ConditionTypesTests.all
        ]
