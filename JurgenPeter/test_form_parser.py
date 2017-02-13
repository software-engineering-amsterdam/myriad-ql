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

        (Grammar.conditional,
         "if true { x : \"y\" integer }",
         Cond(Const(True, Datatype.boolean),
              [Quest(Iden("x"),
                     "y",
                     Datatype.integer)])),
        (Grammar.conditional,
         "if true { }", Cond(Const(True, Datatype.boolean), [])),
        (Grammar.conditional,
         "if true { } else { }", Cond(Const(True, Datatype.boolean), [], [])),

        (Grammar.form, "form FormName { }", Form(Iden("FormName"), [])),

        (Grammar.form,
         """form FormName { x: \"xLabel\" integer = 2 * 3
         if x > 6 { y: \"yLabel\" boolean = true }
         else { y: \"yLabel\" boolean = false }}""",
         Form(Iden("FormName"),
              [Quest(Iden("x"),
                     "xLabel",
                     Datatype.integer,
                     BinOp(Const(2, Datatype.integer),
                           Operator["*"],
                           Const(3, Datatype.integer))),
               Cond(BinOp(Iden("x"), Operator[">"], Const(6, Datatype.integer)),
                    [Quest(Iden("y"),
                           "yLabel",
                           Datatype.boolean,
                           Const(True, Datatype.boolean))],
                    [Quest(Iden("y"),
                           "yLabel",
                           Datatype.boolean,
                           Const(False, Datatype.boolean))])])),
        #TODO: Test relational expression using boolean
        #TODO: Test more datatypes (e.g. Money, decimal)
        #TODO: Test Parenthesis
        #TODO: Test unOps
    ]

    def testParseExpression(self):
        for grammar, sentence, tree in self.cases:
            result = grammar.parseString(sentence, parseAll=True)[0]
            self.assertEqual(result, tree)

if __name__ == "__main__":
    main()
