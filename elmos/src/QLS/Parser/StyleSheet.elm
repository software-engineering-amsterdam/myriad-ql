module QLS.Parser.StyleSheet exposing (..)

import Combine exposing (..)
import Combine.Extra exposing (trimmed, whitespace1)
import QL.Parser.Token exposing (quotedString, identifier)
import QL.Parser.Form exposing (valueType)
import QLS.AST exposing (..)
import QLS.Parser.Configuration exposing (configuration)


styleSheet : Parser s StyleSheet
styleSheet =
    trimmed
        (StyleSheet
            <$> (string "stylesheet" *> whitespace1 *> identifier)
            <*> (whitespace1 *> sepBy1 whitespace1 page)
        )


page : Parser s Page
page =
    Page
        <$> (string "page" *> whitespace1 *> pageId <* whitespace <* string "{")
        <*> (whitespace *> sepBy1 whitespace1 section)
        <*> (whitespace1 *> sepBy whitespace1 defaultValueConfig <* whitespace)
        <* string "}"


pageId : Parser s String
pageId =
    regex "[A-Z][a-zA-Z0-9]*"


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


question : Parser s Question
question =
    choice
        [ ConfiguredQuestion
            <$> (string "question" *> whitespace1 *> identifier)
            <*> (whitespace1 *> configuration)
        , Question
            <$> (string "question" *> whitespace1 *> identifier)
        ]
