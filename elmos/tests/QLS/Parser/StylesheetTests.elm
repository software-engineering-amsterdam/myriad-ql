module QLS.Parser.StylesheetTests exposing (all)

import Test exposing (..)
import QLS.Parser.Stylesheet exposing (page, section, question)
import ParserTestUtil exposing (testWithParser)
import QLS.AST exposing (..)
import AST exposing (Location(Location), ValueType(BooleanType))


all : Test
all =
    describe "Stylesheet"
        [ pageTests
        , sectionTests
        , questionTests
        ]


pageTests : Test
pageTests =
    testWithParser page
        "page"
        [ ( "page without children"
          , "page Foo {}"
          , Nothing
          )
        , ( "page with single section"
          , "page Foo { section \"Selling\" question foo }"
          , Just
                { name = "Foo"
                , sections =
                    [ SingleChildSection "Selling"
                        (Field (Question ( "foo", Location 1 38 )))
                    ]
                , defaults = []
                }
          )
        , ( "page with default but no section"
          , "page Foo { default boolean widget radio(\"Yes\", \"No\") }"
          , Nothing
          )
        , ( "page with section and default"
          , "page Foo { section \"Selling\" question foo default boolean widget radio(\"Yes\", \"No\") }"
          , Just
                { name = "Foo"
                , sections =
                    [ SingleChildSection "Selling"
                        (Field (Question ( "foo", Location 1 38 )))
                    ]
                , defaults = [ DefaultValueConfig BooleanType (SingleConfig (WidgetConfig (Radio [ "Yes", "No" ]))) ]
                }
          )
        ]


sectionTests : Test
sectionTests =
    testWithParser section
        "section"
        [ ( "section with single field"
          , "section \"Selling\" question foo"
          , Just (SingleChildSection "Selling" (Field (Question ( "foo", Location 1 27 ))))
          )
        , ( "section with default widget config"
          , "section \"Selling\" default boolean widget radio(\"Yes\",\"No\")"
          , Just
                (SingleChildSection "Selling"
                    (Config
                        (DefaultValueConfig BooleanType
                            (SingleConfig
                                (WidgetConfig (Radio [ "Yes", "No" ]))
                            )
                        )
                    )
                )
          )
        , ( "section with single nested section"
          , "section \"Buying\" section \"Selling\" question foo"
          , Just
                (SingleChildSection "Buying"
                    (SubSection
                        (SingleChildSection "Selling"
                            (Field (Question ( "foo", Location 1 44 )))
                        )
                    )
                )
          )
        , ( "section with multile children"
          , "section \"Buying\" { question foo section \"Selling\" question bar }"
          , Just
                (MultiChildSection "Buying"
                    [ Field (Question ( "foo", Location 1 28 ))
                    , SubSection
                        (SingleChildSection "Selling"
                            (Field (Question ( "bar", Location 1 59 )))
                        )
                    ]
                )
          )
        ]


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
