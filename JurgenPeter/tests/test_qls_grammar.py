from unittest import TestCase, main
from pyparsing import ParseException

import qls.grammar as grammar
from qls.ast import *


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
        (grammar.stylesheet, "stylesheet s {page p {} page q {}}"),
        (grammar.stylesheet, "stylesheet s {page p {} default integer widget slider}"),
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


if __name__ == "__main__":
    main()