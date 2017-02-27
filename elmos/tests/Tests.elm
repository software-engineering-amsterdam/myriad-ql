module Tests exposing (all)

import Test exposing (Test, concat)
import Combine.ExtraTests
import QL.Tests
import QLS.Tests


all : Test
all =
    concat
        [ Combine.ExtraTests.all
        , QL.Tests.all
        , QLS.Tests.all
        ]
