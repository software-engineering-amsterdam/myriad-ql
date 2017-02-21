module QLS.Parser.StylesheetTests exposing (all)

import Test exposing (..)
import QLS.Parser.Stylesheet exposing (question)
import ParserTestUtil exposing (testWithParser)
import QLS.AST exposing (..)
import AST exposing (Location(Location))


all : Test
all =
    describe "Stylesheet"
        [ questionTests ]


questionTests : Test
questionTests =
    testWithParser question
        "question"
        [ ( "question no config"
          , "question foo"
          , Just (Question ( "foo", Location 1 9 ))
          )
        , ( "question single config"
          , "question foo\nwidget spinbox"
          , Just
                (ConfiguredQuestion
                    ( "foo", Location 1 9 )
                    (SingleConfig (WidgetConfig Spinbox))
                )
          )
        , ( "question multi config"
          , "question foo {\nwidget spinbox}"
          , Just
                (ConfiguredQuestion
                    ( "foo", Location 1 9 )
                    (MultiConfig [ WidgetConfig Spinbox ])
                )
          )
        ]
