from unittest import TestCase, main
from pyparsing import ParseException

import ql.grammar as grammar
from ql.ast import *
from ql.datatypes import *


class TestGrammar(TestCase):

    correctSentences = [
        (grammar.expression, "3"),
        (grammar.expression, "+3"),
        (grammar.expression, "-3"),
        (grammar.expression, "3.0"),
        (grammar.expression, "3."),
        (grammar.expression, "\"three\""),
        (grammar.expression, "true"),
        (grammar.expression, "false"),
        (grammar.expression, "!true"),
        (grammar.expression, "x"),
        (grammar.expression, "hasBoughtHouse"),

        (grammar.expression, "2 + + 2"),
        (grammar.expression, "x <= 3"),
        (grammar.expression, "1 / 0"),
        (grammar.expression, "2 == 2.0"),
        (grammar.expression, "x * 3"),
        (grammar.expression, "x * 3 + 5"),
        (grammar.expression, "x * (3 + 5)"),
        (grammar.expression, "x && true"),
        (grammar.expression, "false || 3 > 2 && true"),
        (grammar.expression, "x == 3 != false"),
        (grammar.expression, "3 != 3.0"),
        (grammar.expression, "2 < 16"),
        (grammar.expression, "10. > 4 && hasBoughtHouse"),

        (grammar.question, "x : \"y\" integer"),
        (grammar.question, "x : \"y\" decimal"),
        (grammar.question, "x : \"y\" boolean"),
        (grammar.question, "x : \"y\" string"),
        (grammar.computed_question, "x : \"y\" integer = 2 + 3"),
        (grammar.computed_question, "x : \"y\" string = \"hello world\""),
        (grammar.conditional, "if true { }"),
        (grammar.conditional, "if true { } else { }"),
        (grammar.form, "form FormName { }")
    ]

    def testCorrectSentences(self):
        for parser, sentence in self.correctSentences:
            self.assertTrue(parser.parseString(sentence, parseAll=True))

    incorrectSentences = [
        (grammar.expression, "x * * 3"),
        (grammar.expression, "x * 3 *"),
        (grammar.expression, "f(x)"),
        (grammar.expression, "x + form"),
        (grammar.expression, "x + if"),
        (grammar.expression, "x + else"),
        (grammar.question, "x : y integer"),
        (grammar.question, "\"x\" : \"y\" integer"),
        (grammar.question, "x : \"y\" float"),
        (grammar.question, "x : \"y\" integer == 3"),
        (grammar.question, "x : \"y\" integer ="),
        (grammar.question, "x : \"y\" integer = 3 * * 3"),
        (grammar.conditional, "if true then { }"),
        (grammar.conditional, "if true { } else false { }"),
        (grammar.form, "form { }"),
    ]

    def testIncorrectSentences(self):
        for parser, sentence in self.incorrectSentences:
            self.assertRaises(ParseException,
                              lambda parser, sentence: parser.parseString(
                                  sentence, parseAll=True), parser, sentence)

    expectedParseResults = [
        (grammar.expression,
         "-2", MinOp(IntegerConstant(2))),
        (grammar.expression,
         "+x", PlusOp(Variable("x"))),

        (grammar.expression,
         "2 + 3", AddOp(IntegerConstant(2),
                        IntegerConstant(3))),
        (grammar.expression,
         "+2 + -3", AddOp(PlusOp(IntegerConstant(2)),
                          MinOp(IntegerConstant(3)))),
        (grammar.expression,
         "2 + 3 + 4", AddOp(AddOp(IntegerConstant(2),
                                  IntegerConstant(3)),
                            IntegerConstant(4))),

        (grammar.expression,
         "2 + 3 - 4", SubOp(AddOp(IntegerConstant(2),
                                  IntegerConstant(3)),
                            IntegerConstant(4))),
        (grammar.expression,
         "2 * 3 + 4", AddOp(MulOp(IntegerConstant(2),
                                  IntegerConstant(3)),
                            IntegerConstant(4))),
        (grammar.expression,
         "2 + 3 * 4", AddOp(IntegerConstant(2),
                            MulOp(IntegerConstant(3),
                                  IntegerConstant(4)))),
        (grammar.expression,
         "(2 + 3) * 4", MulOp(AddOp(IntegerConstant(2),
                                    IntegerConstant(3)),
                              IntegerConstant(4))),
        (grammar.expression,
         "1 / (2 / 4)", DivOp(IntegerConstant(1),
                              DivOp(IntegerConstant(2),
                                    IntegerConstant(4)))),
        (grammar.expression,
         "(1 / 2) / 4", DivOp(DivOp(IntegerConstant(1),
                                    IntegerConstant(2)),
                              IntegerConstant(4))),
        (grammar.expression,
         "x <= 3", LeOp(Variable("x"),
                        IntegerConstant(3))),
        (grammar.expression,
         "x * 3", MulOp(Variable("x"),
                        IntegerConstant(3))),
        (grammar.expression,
         "x + 4 / y", AddOp(Variable("x"),
                            DivOp(IntegerConstant(4),
                                  Variable("y")))),
        (grammar.expression,
         "x || true", OrOp(Variable("x"),
                           BooleanConstant(True))),
        (grammar.expression,
         "false == true", EqOp(BooleanConstant(False),
                               BooleanConstant(True))),
        (grammar.expression,
         "true && !false", AndOp(BooleanConstant(True),
                                 NotOp(BooleanConstant(False)))),

        (grammar.question,
         "x : \"y\" integer", Question("x",
                                       "y",
                                       IntegerDatatype())),
        (grammar.computed_question,
         "x : \"y\" integer = 2 + 3",
         ComputedQuestion("x",
                          "y",
                          IntegerDatatype(),
                          AddOp(IntegerConstant(2),
                                IntegerConstant(3)))),

        (grammar.conditional,
         "if true { x : \"y\" integer }",
         IfConditional(BooleanConstant(True),
                       [Question("x",
                                 "y",
                                 IntegerDatatype())])),
        (grammar.conditional,
         "if true { }", IfConditional(BooleanConstant(True), [])),
        (grammar.conditional,
         "if true { } else { }", IfElseConditional(BooleanConstant(True),
                                                   [],
                                                   [])),

        (grammar.form, "form FormName { }", Form("FormName", [])),

        (grammar.form,
         """
         form FormName {
            x: \"xLabel\" integer = 2 * 3
            if x > 6 {
                y: \"yLabel\" boolean = true
            }
            else {
                y: \"yLabel\" boolean
            }
        }""",
         Form("FormName",
              [ComputedQuestion("x",
                                "xLabel",
                                IntegerDatatype(),
                                MulOp(IntegerConstant(2),
                                      IntegerConstant(3))),
               IfElseConditional(GtOp(Variable("x"),
                                      IntegerConstant(6)),
                                 [ComputedQuestion("y",
                                                   "yLabel",
                                                   BooleanDatatype(),
                                                   BooleanConstant(True))],
                                 [Question("y",
                                           "yLabel",
                                           BooleanDatatype())])])),
    ]

    def testParsing(self):
        for parser, sentence, expected in self.expectedParseResults:
            parse_result = parser.parseString(sentence, parseAll=True)[0]
            self.assertEqual(parse_result, expected)

if __name__ == "__main__":
    main()
