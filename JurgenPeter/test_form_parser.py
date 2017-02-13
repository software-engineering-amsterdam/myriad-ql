from unittest import TestCase, main
from form_parser import *


class TestGrammar(TestCase):
    correctSentences = [
        (Grammar.expression, "x <= 3"),
        (Grammar.expression, "x * 3"),
        (Grammar.expression, "x + y / 4"),
        (Grammar.expression, "x && true"),
        (Grammar.expression, "false == true"), # FIXME fails test due to grammar bug
        (Grammar.expression, "true || !false"),
        (Grammar.question, "x : \"y\" integer"),
        (Grammar.question, "x : \"y\" integer = 2 + 3"),
        (Grammar.conditional, "if true { }"),
        (Grammar.conditional, "if true { } else { }"),
        (Grammar.form, "form FormName { }")
    ]

    incorrectSentences = [
        (Grammar.expression, "x <= false"),
        (Grammar.expression, "x * * 3"),
        (Grammar.expression, "x + form"),
        (Grammar.expression, "x && 0"),
        (Grammar.expression, "false == 0"),
        (Grammar.expression, "true * false"),
        (Grammar.expression, ""),
        (Grammar.question, "x : y integer"),
        (Grammar.question, "\"x\" : \"y\" integer"),
        (Grammar.question, "x : \"y\" float"),
        (Grammar.question, "x : \"y\" integer == 3"),
        (Grammar.question, "x : \"y\" integer = 3 * * 3"),
        (Grammar.question, "x : \"y\" integer ="),
        (Grammar.conditional, "if 2 { }"),
        (Grammar.conditional, "if true { pass }"),
        (Grammar.conditional, "if true && 3 { }"),
        (Grammar.conditional, "if true { } else false { }"),
    ]

    def testCorrectSentences(self):
        for grammar, sentence in self.correctSentences:
            self.assertTrue(grammar.parseString(sentence, parseAll=True))

    def testIncorrectSentences(self):
        for grammar, sentence in self.incorrectSentences:
            self.assertRaises(ParseException,
                              lambda grammar, sentence: grammar.parseString(
                                  sentence, parseAll=True), grammar, sentence)


class TestParser(TestCase):
    expressions = [
        ("2 + 3", BinOp(Const(2, Datatype.integer),
                                 Operator["+"],
                                 Const(3, Datatype.integer))),
        ("2 + 3 + 4", BinOp(BinOp(Const(2,
                                                             Datatype.integer),
                                                    Operator["+"],
                                                    Const(3,
                                                             Datatype.integer)),
                                     Operator["+"],
                                     Const(4, Datatype.integer))),
        ("2 * 3 + 4", BinOp(BinOp(Const(2,
                                                             Datatype.integer),
                                                    Operator["*"],
                                                    Const(3,
                                                             Datatype.integer)),
                                     Operator["+"],
                                     Const(4, Datatype.integer))),
        ("2 + 3 * 4", BinOp(Const(2, Datatype.integer),
                                     Operator["+"],
                                     BinOp(Const(3,
                                                             Datatype.integer),
                                                    Operator["*"],
                                                    Const(4,
                                                             Datatype.integer))))
    ]

    def testParseExpression(self):
        for sentence, tree in self.expressions:
            self.assertEqual(Parser.parse_expression(
                Grammar.expression.parseString(sentence, parseAll=True)), tree)


    def testParseExpression2(self):
        forms = [
            ("2 + 3", "(2 + 3)"),
            ("2 + 3 + 4", "((2 + 3) + 4)"),
            ("2 * 3 + 4", "((2 * 3) + 4)"),
            ("2 + 3 * 4", "(2 + (3 * 4))"),
            ("(2 + 3) * 4", "((2 + 3) * 4)"),
            ("+2", "+2"),
            ("2 + - 7", "(2 + -7)"),
            ("2 / x", "(2 / x)"),
            ("2 + x + y", "((2 + x) + y)"),
            ("true", "True"),
            ("false", "False"),
            ("true && x || !y", "((True && x) || !y)"),
            ("2 + 7.0", "(2 + 7.0)"),
            ("2 + 8", "(2 + 8)"),
            ("2 + 7.", "(2 + 7.0)"),
            ("3 <= 6 || x", "((3 <= 6) || x)")
        ]
        for form, result in forms:
            self.assertEqual(str(Parser.parse_expression(
                Grammar.expression.parseString(form, parseAll=True))), result,
                "incorrect ast")

    def testParseConditional(self):
        forms = [
            ("if true { }", "if True [\n\n]"),
            ("if true { } else { }", "if True [\n\n]\nelse [\n\n]"),
        ]
        for form, result in forms:
            self.assertEqual(str(Parser.parse_conditional(
                Grammar.conditional.parseString(form, parseAll=True))),
                result, "incorrect ast")


if __name__ == "__main__":
    main()
