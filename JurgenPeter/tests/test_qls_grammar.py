from unittest import TestCase, main
from pyparsing import ParseException

from ql.ast import Datatype
import qls.grammar as grammar
from qls.ast import *
from gui.widgets import *


class TestGrammar(TestCase):

    correctSentences = [
        (grammar.question, "question x"),
        (grammar.question, "question x widget spinbox"),
        (grammar.question, "question x widget spinbox(-10, 10)"),
        (grammar.question, "question x {}"),
        (grammar.question, "question x {color: #aabbcc}"),
        (grammar.question, "question x {color: #aabbcc widget text}"),
        (grammar.default, "default integer widget spinbox"),
        (grammar.default, "default integer {color: #aabbcc widget text}"),
        (grammar.section, "section \"x\" {}"),
        (grammar.section, "section \"x\" {question y}"),
        (grammar.section, "section \"x\" {question y question z}"),
        (grammar.section, "section \"x\" {section \"y\" {question z} question zz}"),
        (grammar.section, "section \"x\" {question y default string widget text}"),
        (grammar.page, "page p {}"),
        (grammar.page, "page p {section \"x\" {section \"y\" {question z} question zz}}"),
        (grammar.page, "page p {section \"x\" {} default string widget text}"),
        (grammar.layout, "stylesheet s {page p {} page q {}}"),
        (grammar.layout, "stylesheet s {page p {} default integer widget slider}"),
    ]

    def testCorrectSentences(self):
        for parser, sentence in self.correctSentences:
            self.assertTrue(parser.parseString(sentence, parseAll=True))

    incorrectSentences = [
    ]

    def testIncorrectSentences(self):
        for parser, sentence in self.incorrectSentences:
            self.assertRaises(ParseException,
                              lambda parser, sentence: parser.parseString(
                                  sentence, parseAll=True), parser, sentence)

    expectedParseResults = [
        (grammar.styling, "{color: #663311"
                          " size: 16"
                          " weight: bold"
                          " family: \"Roboto\""
                          " width: 400"
                          " widget checkbox}",
         [ColorAttribute("#663311"),
          FontSizeAttribute(16),
          FontWeightAttribute("bold"),
          FontFamilyAttribute("Roboto"),
          WidthAttribute(400),
          WidgetTypeAttribute(CheckBoxWidget)]),
        (grammar.styling, "{}", []),
        (grammar.styling, "widget checkbox",
                          [WidgetTypeAttribute(CheckBoxWidget)]),
        (grammar.question, "question x",
                           QuestionAnchor("x")),
        (grammar.question, "question x {"
                           "    color: #663311"
                           "}",
                           StyledQuestionAnchor("x",
                                                [ColorAttribute("#663311")])),
        (grammar.question, "question x widget checkbox",
                           StyledQuestionAnchor("x",
                                                [WidgetTypeAttribute(
                                                    CheckBoxWidget)])),
        (grammar.default, "default boolean {"
                          "     color: #663311"
                          "}",
                          DefaultStyling(Datatype.boolean,
                                         [ColorAttribute("#663311")])),
        (grammar.default, "default boolean widget checkbox",
                          DefaultStyling(Datatype.boolean,
                                         [WidgetTypeAttribute(
                                             CheckBoxWidget)])),
        (grammar.section, "section \"x\" {"
                          "     question y"
                          "     question z"
                          "}", Section("x",
                                       [QuestionAnchor("y"),
                                        QuestionAnchor("z")])),
        (grammar.section, "section \"x\" {"
                          "     question y"
                          "     question z"
                          "     default boolean widget checkbox"
                          "     default integer widget whole-number"
                          "}",
            StyledSection("x",
                          [QuestionAnchor("y"),
                           QuestionAnchor("z")],
                          [DefaultStyling(Datatype.boolean,
                                          [WidgetTypeAttribute(CheckBoxWidget)]),
                           DefaultStyling(Datatype.integer,
                                          [WidgetTypeAttribute(
                                             IntegerEntryWidget)])])),
        (grammar.section, "section \"x\" {"
                          "     section \"y\" {}"
                          "}",
                          Section("x",
                                  [Section("y",
                                           [])])),
        (grammar.page, "page x {"
                       "    section \"y\" {}"
                       "}",
                       Page("x",
                            [Section("y",
                                     [])])),
        (grammar.page, "page x {"
                       "    question y"
                       "    question z"
                       "}",
                       Page("x",
                            [QuestionAnchor("y"),
                             QuestionAnchor("z")])),
        (grammar.page, "page x {"
                       "    question y"
                       "    question z"
                       "    default boolean widget checkbox"
                       "    default integer widget whole-number"
                       "}",
                       StyledPage("x",
                                  [QuestionAnchor("y"),
                                   QuestionAnchor("z")],
                                  [DefaultStyling(Datatype.boolean,
                                                  [WidgetTypeAttribute(
                                                      CheckBoxWidget)]),
                                   DefaultStyling(Datatype.integer,
                                                  [WidgetTypeAttribute(
                                                      IntegerEntryWidget)])])),
        (grammar.layout, "stylesheet x {"
                         "      page y {}"
                         "      page z {}"
                         "}",
                         Layout("x",
                                [Page("y", []),
                                 Page("z", [])])),
        (grammar.layout, "stylesheet x {"
                         "      page y {}"
                         "      default decimal widget real-number"
                         "}",
                         StyledLayout("x",
                                      [Page("y", [])],
                                      [DefaultStyling(Datatype.decimal,
                                                      [WidgetTypeAttribute(
                                                          DecimalEntryWidget)
                                                       ])]))
    ]

    def testParsing(self):
        for parser, sentence, expected in self.expectedParseResults:
            parse_result = parser.parseString(sentence, parseAll=True)[0]
            self.assertEqual(parse_result, expected)


if __name__ == "__main__":
    main()