module Tests exposing (all)

import Test exposing (Test, concat)
import Combine.ExtraTests
import QL.Tests
import QLS.Tests
import UI.FormUtilTests


all : Test
all =
    concat
        [ Combine.ExtraTests.all
        , QL.Tests.all
        , QLS.Tests.all
        , UI.FormUtilTests.all
        ]
