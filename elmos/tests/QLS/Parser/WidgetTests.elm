module QLS.Parser.WidgetTests exposing (all)

import Test exposing (..)
import QLS.Parser.Widget exposing (widget)
import ParserTestUtil exposing (testWithParser)
import QLS.AST exposing (..)


all : Test
all =
    testWithParser widget
        "widget"
        [ ( "radio widget"
          , "radio(\"Yes\", \"No\")"
          , Just (Radio [ "Yes", "No" ])
          )
        , ( "radio widget no spacing in values"
          , "radio(\"Yes\",\"No\")"
          , Just (Radio [ "Yes", "No" ])
          )
        , ( "radio widget no spacing befor parens"
          , "radio (\"Yes\",\"No\")"
          , Nothing
          )
        , ( "spinbox widget"
          , "spinbox"
          , Just Spinbox
          )
        , ( "checkbox widget"
          , "checkbox"
          , Just Checkbox
          )
        ]
