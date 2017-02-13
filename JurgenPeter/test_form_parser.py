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
    cases = [
        (Grammar.expression,
         "2 + 3", BinOp(Const(2, Datatype.integer),
                        Operator["+"],
                        Const(3, Datatype.integer))),
        (Grammar.expression,
         "2 + 3 + 4", BinOp(BinOp(Const(2, Datatype.integer),
                                  Operator["+"],
                                  Const(3, Datatype.integer)),
                            Operator["+"],
                            Const(4, Datatype.integer))),
        (Grammar.expression,
         "2 * 3 + 4", BinOp(BinOp(Const(2, Datatype.integer),
                                  Operator["*"],
                                  Const(3, Datatype.integer)),
                            Operator["+"],
                            Const(4, Datatype.integer))),
        (Grammar.expression,
         "2 + 3 * 4", BinOp(Const(2, Datatype.integer),
                            Operator["+"],
                            BinOp(Const(3, Datatype.integer),
                                  Operator["*"],
                                  Const(4, Datatype.integer)))),
        (Grammar.expression,
         "x <= 3", BinOp(Iden("x"),
                         Operator["<="],
                         Const(3, Datatype.integer))),
        (Grammar.expression,
         "x * 3", BinOp(Iden("x"),
                        Operator["*"],
                        Const(3, Datatype.integer))),
        (Grammar.expression,
         "x + 4 / y", BinOp(Iden("x"),
                            Operator["+"],
                            BinOp(Const(4, Datatype.integer),
                                  Operator["/"],
                                  Iden("y")))),
        (Grammar.expression,
         "x || true", BinOp(Iden("x"),
                            Operator["||"],
                            Const(True, Datatype.boolean))),
        (Grammar.expression,
         "false == true", BinOp(Const(False, Datatype.boolean),
                                Operator["=="],
                                Const(True, Datatype.boolean))),
        (Grammar.expression,
         "true && !false", BinOp(Const(True, Datatype.boolean),
                                 Operator["&&"],
                                 UnOp(Operator["!"],
                                      Const(False, Datatype.boolean)))),

        (Grammar.question,
         "x : \"y\" integer", Quest(Iden("x"),
                                    "y",
                                    Datatype.integer)),
        (Grammar.question,
         "x : \"y\" integer = 2 + 3", Quest(Iden("x"),
                                            "y",
                                            Datatype.integer,
                                            BinOp(Const(2, Datatype.integer),
                                                  Operator["+"],
                                                  Const(3, Datatype.integer)))),

        (Grammar.conditional, "if true { }", Cond(True, [])),
        (Grammar.conditional, "if true { } else { }", Cond(True, [], [])),

        (Grammar.form, "form FormName { }", Form("FormName", []))

    ]

    def testParseExpression(self):
        for grammar, sentence, tree in self.cases:
            print(sentence)

            parsedTree = grammar.parseString(sentence, parseAll=True)[0]
            print(tree)
            print(parsedTree)
            self.assertEqual(parsedTree, tree)


    # def testParseExpression2(self):
    #     forms = [
    #         ("2 + 3", "(2 + 3)"),
    #         ("2 + 3 + 4", "((2 + 3) + 4)"),
    #         ("2 * 3 + 4", "((2 * 3) + 4)"),
    #         ("2 + 3 * 4", "(2 + (3 * 4))"),
    #         ("(2 + 3) * 4", "((2 + 3) * 4)"),
    #         ("+2", "+2"),
    #         ("2 + - 7", "(2 + -7)"),
    #         ("2 / x", "(2 / x)"),
    #         ("2 + x + y", "((2 + x) + y)"),
    #         ("true", "True"),
    #         ("false", "False"),
    #         ("true && x || !y", "((True && x) || !y)"),
    #         ("2 + 7.0", "(2 + 7.0)"),
    #         ("2 + 8", "(2 + 8)"),
    #         ("2 + 7.", "(2 + 7.0)"),
    #         ("3 <= 6 || x", "((3 <= 6) || x)")
    #     ]
    #     for form, result in forms:
    #         self.assertEqual(str(Grammar.expression.parseString(form, parseAll=True)), result,
    #             "incorrect ast")

    # def testParseConditional(self):
    #     forms = [
    #         ("if true { }", "if True [\n\n]"),
    #         ("if true { } else { }", "if True [\n\n]\nelse [\n\n]"),
    #     ]
    #     for form, result in forms:
    #         self.assertEqual(str(Parser.parse_conditional(
    #             Grammar.conditional.parseString(form, parseAll=True))),
    #             result, "incorrect ast")


if __name__ == "__main__":
    main()
