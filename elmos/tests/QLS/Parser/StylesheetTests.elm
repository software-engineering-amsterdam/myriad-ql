module QLS.Parser.StylesheetTests exposing (all)

import Test exposing (..)
import QLS.Parser.Stylesheet exposing (stylesheet, page, section, question)
import ParserTestUtil exposing (testWithParser)
import QLS.AST exposing (..)
import AST exposing (Location(Location), ValueType(BooleanType, MoneyType))


all : Test
all =
    describe "Stylesheet"
        [ stylesheetTests
        , pageTests
        , sectionTests
        , questionTests
        ]


stylesheetTests : Test
stylesheetTests =
    testWithParser stylesheet
        "example stylesheet"
        [ ( "stylesheet"
          , """stylesheet taxOfficeExample
  page Housing {
    section "Buying"
      question hasBoughtHouse
        widget checkbox
    section "Loaning"
      question hasMaintLoan
  }

  page Selling {
    section "Selling" {
      question hasSoldHouse
        widget radio("Yes", "No")
      section "You sold a house" {
        question sellingPrice
          widget spinbox
        question privateDebt
          widget spinbox
        question valueResidue
        default money {
          width: 400
          font: "Arial"
          fontsize: 14
          color: #999999
          widget spinbox
        }
      }
    }
    default boolean widget radio("Yes", "No")
  }"""
          , Just
                { id = ( "taxOfficeExample", Location 1 11 )
                , pages =
                    [ { name = "Housing"
                      , sections =
                            [ SingleChildSection "Buying"
                                (Field
                                    (ConfiguredQuestion
                                        ( "hasBoughtHouse", Location 3 14 )
                                        (SingleConfig (WidgetConfig Checkbox))
                                    )
                                )
                            , SingleChildSection "Loaning" (Field (Question ( "hasMaintLoan", Location 6 14 )))
                            ]
                      , defaults = []
                      }
                    , { name = "Selling"
                      , sections =
                            [ MultiChildSection "Selling"
                                ([ Field (ConfiguredQuestion ( "hasSoldHouse", Location 11 14 ) (SingleConfig (WidgetConfig (Radio [ "Yes", "No" ]))))
                                 , SubSection
                                    (MultiChildSection "You sold a house"
                                        ([ Field (ConfiguredQuestion ( "sellingPrice", Location 14 16 ) (SingleConfig (WidgetConfig Spinbox)))
                                         , Field (ConfiguredQuestion ( "privateDebt", Location 16 16 ) (SingleConfig (WidgetConfig Spinbox)))
                                         , Field (Question ( "valueResidue", Location 18 16 ))
                                         , Config (DefaultValueConfig MoneyType (MultiConfig ([ StyleConfig (Width 400), StyleConfig (Font "Arial"), StyleConfig (FontSize 14), StyleConfig (Color "#999"), WidgetConfig Spinbox ])))
                                         ]
                                        )
                                    )
                                 ]
                                )
                            ]
                      , defaults = [ DefaultValueConfig BooleanType (SingleConfig (WidgetConfig (Radio [ "Yes", "No" ]))) ]
                      }
                    ]
                }
          )
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
