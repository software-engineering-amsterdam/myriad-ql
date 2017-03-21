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
        , ( "radio widget no spacing before parens"
          , "radio (\"Yes\",\"No\")"
          , Nothing
          )
        , ( "radio widget no spacing on inside of parens"
          , "radio( \"Yes\",\"No\" )"
          , Just (Radio [ "Yes", "No" ])
          )
        , ( "dropdown widget"
          , "radio( \"Yes\",\"No\", \"Maybe\" )"
          , Just (Radio [ "Yes", "No", "Maybe" ])
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
