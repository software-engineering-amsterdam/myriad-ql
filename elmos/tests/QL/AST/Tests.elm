module QL.AST.Tests exposing (all)

import Test exposing (Test, describe)
import QL.AST.CollectorsTests


all : Test
all =
    describe "AST"
        [ QL.AST.CollectorsTests.all
        ]
