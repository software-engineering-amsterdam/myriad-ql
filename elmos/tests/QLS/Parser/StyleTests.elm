module QLS.Parser.StyleTests exposing (all)

import Test exposing (..)
import QLS.Parser.Style exposing (style)
import ParserTestUtil exposing (testWithParser)
import QLS.AST exposing (..)


all : Test
all =
    testWithParser style
        "style"
        [ ( "space before colon"
          , "width : 400"
          , Nothing
          )
        , ( "width style"
          , "width: 400"
          , Just (Width 400)
          )
        , ( "font style"
          , "font: \"Arial\""
          , Just (Font "Arial")
          )
        , ( "font style no quotes"
          , "font: Arial"
          , Nothing
          )
        , ( "font size"
          , "fontsize: 14"
          , Just (FontSize 14)
          )
        , ( "color"
          , "color: #999"
          , Just (Color "#999")
          )
        , ( "color with six hex digit"
          , "color: #999999"
          , Just (Color "#999999")
          )
        , ( "color defaults"
          , "color: green"
          , Just (Color "green")
          )
        ]
