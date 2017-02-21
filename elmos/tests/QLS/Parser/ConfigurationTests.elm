module QLS.Parser.ConfigurationTests exposing (all)

import Test exposing (Test)
import QLS.Parser.Configuration exposing (configuration)
import ParserTestUtil exposing (testWithParser)
import QLS.AST exposing (..)


all : Test
all =
    testWithParser configuration
        "configuration"
        [ ( "single configuration widget"
          , "widget spinbox"
          , Just (SingleConfig (WidgetConfig Spinbox))
          )
        , ( "single configuration property"
          , "width: 400"
          , Just (SingleConfig (StyleConfig (Width 400)))
          )
        ]
