module QL.TypeChecker.Expressions.ConditionTypesTests exposing (all)

import QL.AST exposing (..)
import QL.ASTTestUtil exposing (emptyLoc)
import Dict
import Expect
import Test exposing (Test, describe, test)
import QL.TypeChecker.Messages exposing (..)
import QL.TypeChecker.Expressions.ComputedFieldTypes exposing (computedFieldTypeErrors)


-- TODO: Implement these


all : Test
all =
    describe "ConditionTypes.conditionTypeErrors"
        []
