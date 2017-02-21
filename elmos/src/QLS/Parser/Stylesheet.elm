module QLS.Parser.Stylesheet exposing (..)

import Combine exposing (..)
import Combine.Extra exposing (trimmed, whitespace1)
import Parser.Token exposing (quotedString, identifier)
import QLS.AST exposing (..)
import QLS.Parser.Configuration exposing (configuration)
import Parser.Form exposing (valueType)


--
-- stylesheet : Parser s Stylesheet
-- stylesheet =
--     succeed Stylesheet
--         <*> (string "stylesheet" *> whitespace1 *> identifier)
--         <*> (whitespace1 *> pages)
--
--
-- pages : Parser s (List Page)
-- pages =
--     sepBy whitespace1 page
--
--
-- page : Parser s Page
-- page =
--     succeed Page
--         <*> (string "page" *> regex "[A-Z][a-zA-Z0-9]*")
--         <*> braces (sepBy whitespace1 pageChild)
--
--
-- pageChild : Parser s PageChild
-- pageChild =
--     PageSection <$> section
--
--
-- sections : Parser s (List Section)
-- sections =
--     sepBy whitespace1 section
--
--


section : Parser s Section
section =
    lazy <|
        \() ->
            choice
                [ SingleChildSection
                    <$> (string "section" *> whitespace1 *> quotedString)
                    <*> (whitespace1 *> sectionChild)
                , MultiChildSection
                    <$> (string "section" *> whitespace1 *> quotedString)
                    <*> (whitespace1 *> braces (trimmed (sepBy whitespace1 sectionChild)))
                ]


sectionChild : Parser s SectionChild
sectionChild =
    lazy <|
        \() ->
            choice
                [ SubSection <$> section
                , Field <$> question
                , Config <$> defaultValueConfig
                ]


defaultValueConfig : Parser s DefaultValueConfig
defaultValueConfig =
    DefaultValueConfig
        <$> (string "default" *> whitespace1 *> valueType)
        <*> (whitespace1 *> configuration)



--
--
-- multiSectionChildBlock : Parser s (List SectionChild)
-- multiSectionChildBlock =
--     lazy <|
--         \() -> braces (trimmed (many sectionChild))
--
--
-- sectionChild : Parser s SectionChild
-- sectionChild =
--     lazy <|
--         \() -> subSection <|> field
--
--
-- subSection : Parser s SectionChild
-- subSection =
--     SubSection <$> section
--
--
-- field : Parser s SectionChild
-- field =
--     Field <$> question


question : Parser s Question
question =
    choice
        [ ConfiguredQuestion
            <$> (string "question" *> whitespace1 *> identifier)
            <*> (whitespace1 *> configuration)
        , Question
            <$> (string "question" *> whitespace1 *> identifier)
        ]



--
--
-- configurations : Parser s a
-- configurations =
--     fail "To be implemented"
--
--
-- defaults : Parser s a
-- defaults =
--     fail "To be implemented"
