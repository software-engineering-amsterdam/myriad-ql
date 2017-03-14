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
          , Just (SingleConfig Spinbox)
          )
        , ( "single configuration property only allowed for widget"
          , "width: 400"
          , Nothing
          )
        , ( "multi configuration property"
          , "{width: 400}"
          , Just (MultiConfig [ Width 400 ] Nothing)
          )
        , ( "multi configuration widget"
          , "{ widget spinbox }"
          , Just (MultiConfig [] (Just Spinbox))
          )
        , ( "multi configuration property spacing"
          , "{ width: 400 }"
          , Just (MultiConfig [ Width 400 ] Nothing)
          )
        ]
