module QLS.Tests exposing (all)

import Test exposing (Test, describe)
import QLS.Parser.ConfigurationTests
import QLS.Parser.StyleTests
import QLS.Parser.WidgetTests
import QLS.Parser.StyleSheetTests


all : Test
all =
    describe "QLS"
        [ QLS.Parser.ConfigurationTests.all
        , QLS.Parser.StyleTests.all
        , QLS.Parser.WidgetTests.all
        , QLS.Parser.StyleSheetTests.all
        ]
