from unittest import TestCase, main
from grammar import *


class TestGrammar(TestCase):

    correctSentences = [
        (Grammar.expression, "3"),
        (Grammar.expression, "+3"),
        (Grammar.expression, "-3"),
        (Grammar.expression, "3.0"),
        (Grammar.expression, "3."),
        (Grammar.expression, "\"three\""),
        (Grammar.expression, "true"),
        (Grammar.expression, "false"),
        (Grammar.expression, "!true"),
        (Grammar.expression, "x"),
        (Grammar.expression, "hasBoughtHouse"),

        (Grammar.expression, "2 + + 2"),
        (Grammar.expression, "x <= 3"),
        (Grammar.expression, "1 / 0"),
        (Grammar.expression, "2 == 2.0"),
        (Grammar.expression, "x * 3"),
        (Grammar.expression, "x * 3 + 5"),
        (Grammar.expression, "x * (3 + 5)"),
        (Grammar.expression, "x && true"),
        (Grammar.expression, "false || 3 > 2 && true"),
        (Grammar.expression, "x == 3 != false"),
        (Grammar.expression, "3 != 3.0"),
        (Grammar.expression, "2 < 16"),
        (Grammar.expression, "10. > 4 && hasBoughtHouse"),

        (Grammar.question, "x : \"y\" integer"),
        (Grammar.question, "x : \"y\" decimal"),
        (Grammar.question, "x : \"y\" boolean"),
        (Grammar.question, "x : \"y\" money"),
        (Grammar.question, "x : \"y\" string"),
        (Grammar.question, "x : \"y\" integer = 2 + 3"),
        (Grammar.question, "x : \"y\" string = \"hello world\""),
        (Grammar.conditional, "if true { }"),
        (Grammar.conditional, "if true { } else { }"),
        (Grammar.form, "form FormName { }")
    ]

    def testCorrectSentences(self):
        for grammar, sentence in self.correctSentences:
            self.assertTrue(grammar.parseString(sentence, parseAll=True))

    incorrectSentences = [
        (Grammar.expression, "x * * 3"),
        (Grammar.expression, "x * 3 *"),
        (Grammar.expression, "f(x)"),
        (Grammar.expression, "x + form"),
        (Grammar.expression, "x + if"),
        (Grammar.expression, "x + else"),
        (Grammar.question, "x : y integer"),
        (Grammar.question, "\"x\" : \"y\" integer"),
        (Grammar.question, "x : \"y\" float"),
        (Grammar.question, "x : \"y\" integer == 3"),
        (Grammar.question, "x : \"y\" integer ="),
        (Grammar.question, "x : \"y\" integer = 3 * * 3"),
        (Grammar.conditional, "if true then { }"),
        (Grammar.conditional, "if true { } else false { }"),
        (Grammar.form, "form { }"),
    ]

    def testIncorrectSentences(self):
        for grammar, sentence in self.incorrectSentences:
            self.assertRaises(ParseException,
                              lambda grammar, sentence: grammar.parseString(
                                  sentence, parseAll=True), grammar, sentence)

    expectedParseResults = [
        (Grammar.expression,
         "-2", UnOp(Operator["-"],
                    Constant(2, Datatype.integer))),
        (Grammar.expression,
         "+x", UnOp(Operator["+"],
                    Variable("x"))),

        (Grammar.expression,
         "2 + 3", BinOp(Constant(2, Datatype.integer),
                        Operator["+"],
                        Constant(3, Datatype.integer))),
        (Grammar.expression,
         "+2 + -3", BinOp(UnOp(Operator["+"],
                               Constant(2, Datatype.integer)),
                          Operator["+"],
                          UnOp(Operator["-"],
                               Constant(3, Datatype.integer)))),
        (Grammar.expression,
         "2 + 3 + 4", BinOp(BinOp(Constant(2, Datatype.integer),
                                  Operator["+"],
                                  Constant(3, Datatype.integer)),
                            Operator["+"],
                            Constant(4, Datatype.integer))),
        (Grammar.expression,
         "2 + 3 - 4", BinOp(BinOp(Constant(2, Datatype.integer),
                                  Operator["+"],
                                  Constant(3, Datatype.integer)),
                            Operator["-"],
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
         "(2 + 3) * 4", BinOp(BinOp(Constant(2, Datatype.integer),
                                    Operator["+"],
                                    Constant(3, Datatype.integer)),
                              Operator["*"],
                              Constant(4, Datatype.integer))),
        (Grammar.expression,
         "1 / (2 / 4)", BinOp(Constant(1, Datatype.integer),
                              Operator["/"],
                              BinOp(Constant(2, Datatype.integer),
                                    Operator["/"],
                                    Constant(4, Datatype.integer)))),
        (Grammar.expression,
         "(1 / 2) / 4", BinOp(BinOp(Constant(1, Datatype.integer),
                                    Operator["/"],
                                    Constant(2, Datatype.integer)),
                              Operator["/"],
                              Constant(4, Datatype.integer))),
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
         "x : \"y\" integer", Question("x",
                                       "y",
                                       Datatype.integer)),
        (Grammar.question,
         "x : \"y\" integer = 2 + 3", Question("x",
                                               "y",
                                               Datatype.integer,
                                               BinOp(Constant(2, Datatype.integer),
                                                     Operator["+"],
                                                     Constant(3, Datatype.integer)))),

        (Grammar.conditional,
         "if true { x : \"y\" integer }",
         Conditional(Constant(True, Datatype.boolean),
                     [Question("x",
                               "y",
                               Datatype.integer)])),
        (Grammar.conditional,
         "if true { }", Conditional(Constant(True, Datatype.boolean), [])),
        (Grammar.conditional,
         "if true { } else { }", Conditional(Constant(True, Datatype.boolean),
                                             [],
                                             [])),

        (Grammar.form, "form FormName { }", Form("FormName", [])),

        (Grammar.form,
         """
         form FormName {
            x: \"xLabel\" integer = 2 * 3
            if x > 6 {
                y: \"yLabel\" boolean = true
            }
            else {
                y: \"yLabel\" boolean = false
            }
        }""",
         Form("FormName",
              [Question("x",
                        "xLabel",
                        Datatype.integer,
                        BinOp(Constant(2, Datatype.integer),
                              Operator["*"],
                              Constant(3, Datatype.integer))),
               Conditional(BinOp(Variable("x"),
                                 Operator[">"],
                                 Constant(6, Datatype.integer)),
                           [Question("y",
                                     "yLabel",
                                     Datatype.boolean,
                                     Constant(True, Datatype.boolean))],
                           [Question("y",
                                     "yLabel",
                                     Datatype.boolean,
                                     Constant(False, Datatype.boolean))])])),
    ]

    def testParsing(self):
        for grammar, sentence, expected in self.expectedParseResults:
            parse_result = grammar.parseString(sentence, parseAll=True)[0]
            self.assertEqual(parse_result, expected)

if __name__ == "__main__":
    main()
