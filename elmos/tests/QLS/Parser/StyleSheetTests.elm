module QLS.Parser.StyleSheetTests exposing (all)

import Test exposing (..)
import QLS.Parser.StyleSheet exposing (styleSheet, page, section, question)
import ParserTestUtil exposing (testWithParser)
import QLS.AST exposing (..)
import QL.AST exposing (Location(Location), ValueType(BooleanType, MoneyType))


all : Test
all =
    describe "StyleSheet"
        [ styleSheetTests
        , pageTests
        , sectionTests
        , questionTests
        ]


styleSheetTests : Test
styleSheetTests =
    testWithParser styleSheet
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
                    [ Page
                        "Housing"
                        [ SingleChildSection "Buying"
                            (Field
                                (ConfiguredQuestion
                                    ( "hasBoughtHouse", Location 3 14 )
                                    (SingleConfig Checkbox)
                                )
                            )
                        , SingleChildSection "Loaning" (Field (Question ( "hasMaintLoan", Location 6 14 )))
                        ]
                        []
                    , Page "Selling"
                        [ MultiChildSection "Selling"
                            [ Field (ConfiguredQuestion ( "hasSoldHouse", Location 11 14 ) (SingleConfig ((Radio [ "Yes", "No" ]))))
                            , SubSection
                                (MultiChildSection "You sold a house"
                                    [ Field (ConfiguredQuestion ( "sellingPrice", Location 14 16 ) (SingleConfig Spinbox))
                                    , Field (ConfiguredQuestion ( "privateDebt", Location 16 16 ) (SingleConfig Spinbox))
                                    , Field (Question ( "valueResidue", Location 18 16 ))
                                    ]
                                    [ DefaultValueConfig (Location 19 7)
                                        MoneyType
                                        (MultiConfig
                                            [ Width 400
                                            , Font "Arial"
                                            , FontSize 14
                                            , Color "#999999"
                                            ]
                                            (Just Spinbox)
                                        )
                                    ]
                                )
                            ]
                            []
                        ]
                        [ DefaultValueConfig (Location 28 3) BooleanType (SingleConfig (Radio [ "Yes", "No" ])) ]
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
                (Page "Foo"
                    [ SingleChildSection "Selling"
                        (Field (Question ( "foo", Location 1 38 )))
                    ]
                    []
                )
          )
        , ( "page with default but no section"
          , "page Foo { default boolean widget radio(\"Yes\", \"No\") }"
          , Nothing
          )
        , ( "page with section and default"
          , "page Foo { section \"Selling\" question foo default boolean widget radio(\"Yes\", \"No\") }"
          , Just
                (Page
                    "Foo"
                    [ SingleChildSection "Selling"
                        (Field (Question ( "foo", Location 1 38 )))
                    ]
                    [ DefaultValueConfig (Location 1 42) BooleanType (SingleConfig (Radio [ "Yes", "No" ])) ]
                )
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
          , "section \"Selling\" { default boolean widget radio(\"Yes\",\"No\") }"
          , Just
                (MultiChildSection "Selling"
                    []
                    [ DefaultValueConfig (Location 1 20)
                        BooleanType
                        (SingleConfig
                            (Radio [ "Yes", "No" ])
                        )
                    ]
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
                    []
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
                    (SingleConfig Spinbox)
                )
          )
        , ( "question multi config"
          , "question foo {\nwidget spinbox}"
          , Just
                (ConfiguredQuestion
                    ( "foo", Location 1 9 )
                    (MultiConfig [] (Just Spinbox))
                )
          )
        ]
