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
         "2 + 3", BinOp(Constant(2, Datatype.integer),
                        Operator["+"],
                        Constant(3, Datatype.integer))),
        (Grammar.expression,
         "2 + 3 + 4", BinOp(BinOp(Constant(2, Datatype.integer),
                                  Operator["+"],
                                  Constant(3, Datatype.integer)),
                            Operator["+"],
                            Constant(4, Datatype.integer))),
        (Grammar.expression,
         "2 * 3 + 4", BinOp(BinOp(Constant(2, Datatype.integer),
                                  Operator["*"],
                                  Constant(3, Datatype.integer)),
                            Operator["+"],
                            Constant(4, Datatype.integer))),
        (Grammar.expression,
         "2 + 3 * 4", BinOp(Constant(2, Datatype.integer),
                            Operator["+"],
                            BinOp(Constant(3, Datatype.integer),
                                  Operator["*"],
                                  Constant(4, Datatype.integer)))),
        (Grammar.expression,
         "x <= 3", BinOp(Variable("x"),
                         Operator["<="],
                         Constant(3, Datatype.integer))),
        (Grammar.expression,
         "x * 3", BinOp(Variable("x"),
                        Operator["*"],
                        Constant(3, Datatype.integer))),
        (Grammar.expression,
         "x + 4 / y", BinOp(Variable("x"),
                            Operator["+"],
                            BinOp(Constant(4, Datatype.integer),
                                  Operator["/"],
                                  Variable("y")))),
        (Grammar.expression,
         "x || true", BinOp(Variable("x"),
                            Operator["||"],
                            Constant(True, Datatype.boolean))),
        (Grammar.expression,
         "false == true", BinOp(Constant(False, Datatype.boolean),
                                Operator["=="],
                                Constant(True, Datatype.boolean))),
        (Grammar.expression,
         "true && !false", BinOp(Constant(True, Datatype.boolean),
                                 Operator["&&"],
                                 UnOp(Operator["!"],
                                      Constant(False, Datatype.boolean)))),

        (Grammar.question,
         "x : \"y\" integer", Question(Variable("x"),
                                    "y",
                                    Datatype.integer)),
        (Grammar.question,
         "x : \"y\" integer = 2 + 3", Question(Variable("x"),
                                            "y",
                                            Datatype.integer,
                                            BinOp(Constant(2, Datatype.integer),
                                                  Operator["+"],
                                                  Constant(3, Datatype.integer)))),

        (Grammar.conditional,
         "if true { x : \"y\" integer }",
         Condition(Constant(True, Datatype.boolean),
              [Question(Variable("x"),
                     "y",
                     Datatype.integer)])),
        (Grammar.conditional,
         "if true { }", Condition(Constant(True, Datatype.boolean), [])),
        (Grammar.conditional,
         "if true { } else { }", Condition(Constant(True, Datatype.boolean), [], [])),

        (Grammar.form, "form FormName { }", Form("FormName", [])),

        (Grammar.form,
         """form FormName { x: \"xLabel\" integer = 2 * 3
         if x > 6 { y: \"yLabel\" boolean = true }
         else { y: \"yLabel\" boolean = false }}""",
         Form("FormName",
              [Question(Variable("x"),
                     "xLabel",
                     Datatype.integer,
                     BinOp(Constant(2, Datatype.integer),
                           Operator["*"],
                           Constant(3, Datatype.integer))),
               Condition(BinOp(Variable("x"),
                         Operator[">"], Constant(6,
                         Datatype.integer)),
                         [Question(Variable("y"),
                                   "yLabel",
                                   Datatype.boolean,
                                   Constant(True, Datatype.boolean))],
                         [Question(Variable("y"),
                                   "yLabel",
                                   Datatype.boolean,
                                   Constant(False, Datatype.boolean))])])),
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
