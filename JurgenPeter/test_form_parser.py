from unittest import TestCase, main
from form_parser import *


class TestParser(TestCase):
    def testParseExpression(self):
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

    def testGrammar(self):
        forms = [(Grammar.expression, "x <= false"),
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
                 ]
        for grammar, form in forms:
            self.assertRaises(ParseException,
                              lambda grammar, form: grammar.parseString(
                                  form, parseAll=True), grammar, form)


if __name__ == "__main__":
    main()
