import unittest
from decimal import Decimal

import pyparsing as pp

from QL import AST
from QL.Stages import Parser


class TestParser(unittest.TestCase):
    parser = Parser.QuestionnaireParser()
    q_parser = parser.question
    exp_parser = parser.expression
    cond_parser = parser.conditional

    def validate_node(self, parse_method, parse_str, test_node):
        parse_res = self.parse(parse_method, parse_str)[0]
        self.assertEqual(parse_res, test_node)

    def invalidate_node(self, parse_method, parse_str, test_node):
        parse_res = self.parse(parse_method, parse_str)[0]
        self.assertNotEqual(parse_res, test_node)

    def check_parse_exception(self, parse_method, parse_str, raised_exception):
        with self.assertRaises(raised_exception) as excep:
            self.parse(parse_method, parse_str)
        self.assertEqual(excep.expected, raised_exception)

    @staticmethod
    def parse(parse_method, input_str):
        return parse_method.parseString(input_str, parseAll=True)

    def test_question_parsing(self):
        def format_question(q, n, var_type):
            return "\"{}\" {}: {}".format(q, n, var_type)

        question = "Did you sell a house in 2010?"
        name = "hasSoldHouse"
        q_type = AST.BoolTypeNode()
        q_str = format_question(question, name, "boolean")
        test_node = AST.QuestionNode(question, name, q_type)
        self.validate_node(self.q_parser, q_str, test_node)

        question = "Did you sell a house in 2010?"
        q_type = AST.MoneyTypeNode()
        q_str = format_question(question, name, "money")
        test_node = AST.QuestionNode(question, name, q_type)
        self.validate_node(self.q_parser, q_str, test_node)

        question = "question?"
        name = "moneyVar"
        q_type = AST.DecimalTypeNode()
        q_str = format_question(question, name, "decimal")
        test_node = AST.QuestionNode(question, name, q_type)
        self.validate_node(self.q_parser, q_str, test_node)

        question_str = "name: \"question?\" boolean "
        self.check_parse_exception(self.q_parser, question_str,
                                   pp.ParseException)
        question_str = "\"question\" name boolean "
        self.check_parse_exception(self.q_parser, question_str,
                                   pp.ParseException)
        question_str = "\"question?\" boolean: name"
        self.check_parse_exception(self.q_parser, question_str,
                                   pp.ParseException)

    def test_computed_questions(self):
        def format_question(q, n, var_type, expr):
            return "\"{}\" {}: {} = ({})".format(q, n, var_type, expr)

        question = "Did you sell a house in 2010?"
        name = "hasSoldHouse"
        q_type = AST.BoolTypeNode()
        q_expr = "-var >= 600"
        node_expr = AST.GTENode(AST.MinNode(AST.VarNode("var")),
                                AST.IntNode(int("600")))

        q_str = format_question(question, name, "boolean", q_expr)
        test_node = AST.ComputedQuestionNode(question, name, q_type, node_expr)
        self.validate_node(self.q_parser, q_str, test_node)

    def test_boolean_lit_in_expr(self):
        bool_true = AST.BoolNode(True)
        self.validate_node(self.exp_parser, "true", bool_true)

        bool_false = AST.BoolNode(False)
        self.validate_node(self.exp_parser, "false", bool_false)

        self.invalidate_node(self.exp_parser, "false", bool_true)
        self.invalidate_node(self.exp_parser, "true", bool_false)

    def test_var_lit_in_expr(self):
        name = "varName"
        var_node1 = AST.VarNode(name)
        self.validate_node(self.exp_parser, name, var_node1)

        name = "var_name_129"
        var_node2 = AST.VarNode(name)
        self.validate_node(self.exp_parser, name, var_node2)

        name = "v"
        var_node3 = AST.VarNode(name)
        self.validate_node(self.exp_parser, name, var_node3)

        self.check_parse_exception(self.exp_parser, "_hallo", pp.ParseException)

    def test_int_lit_in_expr(self):
        integer = "1000"
        int_node1 = AST.IntNode(int(integer))
        self.validate_node(self.exp_parser, integer, int_node1)

        integer = "10000000000000000"
        int_node2 = AST.IntNode(int(integer))
        self.validate_node(self.exp_parser, integer, int_node2)

        self.check_parse_exception(self.exp_parser, "0x900", pp.ParseException)

    def test_string_lit_in_expr(self):
        string = '@#%$^&*()'
        string_node1 = AST.StringNode(string)
        self.validate_node(self.exp_parser, '"{}"'.format(string), string_node1)

        string = 'Hello world'
        string_node2 = AST.StringNode(string)
        self.validate_node(self.exp_parser, '"{}"'.format(string), string_node2)

        self.check_parse_exception(self.exp_parser, '"Hello', pp.ParseException)

    def test_decimal_lit_in_expr(self):
        decimal_val = "100.005"
        dec_node1 = AST.DecimalNode(Decimal(decimal_val))
        self.validate_node(self.exp_parser, decimal_val, dec_node1)

        decimal_val = "100."
        dec_node2 = AST.DecimalNode(Decimal(decimal_val))
        self.validate_node(self.exp_parser, decimal_val, dec_node2)

        decimal_val = ".50"
        dec_node3 = AST.DecimalNode(Decimal(decimal_val))
        self.validate_node(self.exp_parser, decimal_val, dec_node3)

    def test_monops(self):
        monop_node1 = AST.NegNode(AST.BoolNode(True))
        self.validate_node(self.exp_parser, "!true", monop_node1)

        monop_node2 = AST.NegNode(monop_node1)
        self.validate_node(self.exp_parser, "!!true", monop_node2)

        monop_node3 = AST.MinNode(AST.IntNode(int("30")))
        self.validate_node(self.exp_parser, "-30", monop_node3)

        monop_node4 = AST.PlusNode(AST.IntNode(int("40")))
        self.validate_node(self.exp_parser, "+40", monop_node4)

    def test_binops(self):
        binop1 = ["1000", ">", "100.0"]
        binop_node1 = AST.GTNode(AST.IntNode(int(binop1[0])),
                                 AST.DecimalNode(Decimal(binop1[2])))
        self.validate_node(self.exp_parser, "".join(binop1), binop_node1)

        binop2 = binop1 + ["==", "true"]
        binop_node2 = AST.EqNode(binop_node1, AST.BoolNode(True))
        self.validate_node(self.exp_parser, "".join(binop2), binop_node2)

        # (100 + var) * (true) + "String"
        lit_bool = AST.BoolNode(True)
        lit_int = AST.IntNode(int("100"))
        lit_var = AST.VarNode("var")
        lit_string = AST.StringNode("String")

        binop_node3 = AST.AddNode(lit_int, lit_var)
        binop_node3 = AST.MulNode(binop_node3, lit_bool)
        binop_node3 = AST.AddNode(binop_node3, lit_string)
        self.validate_node(self.exp_parser, "(100 + var) * true + \"String\"",
                           binop_node3)

        self.check_parse_exception(self.exp_parser, '20 +', pp.ParseException)
        self.check_parse_exception(self.exp_parser, '20 ** 30',
                                   pp.ParseException)
        self.check_parse_exception(self.exp_parser, '30 // 40',
                                   pp.ParseException)

    def test_expression_combinations(self):
        # !(5.0 + +10 / var1 / 2 - !var2 && (19. * .12) || false)
        # = invalid eq but is parseable.
        int1 = AST.IntNode(int("10"))
        int2 = AST.IntNode(int("2"))
        dec1 = AST.DecimalNode(Decimal("5.0"))
        dec2 = AST.DecimalNode(Decimal("19."))
        dec3 = AST.DecimalNode(Decimal(".12"))
        var1 = AST.VarNode("var1")
        var2 = AST.VarNode("var2")
        bool1 = AST.BoolNode(False)

        # First prefix +, then division
        expr = AST.DivNode(AST.PlusNode(int1), var1)
        expr = AST.DivNode(expr, int2)
        # Secondly + and -
        expr = AST.AddNode(dec1, expr)
        expr = AST.SubNode(expr, AST.NegNode(var2))
        # Thirdly &&, last || and the very last !
        expr = AST.AndNode(expr, AST.MulNode(dec2, dec3))
        expr = AST.NegNode(AST.OrNode(expr, bool1))

        string_expr = "!(5.0 + +10 / var1 / 2 - !var2 && (19. * .12) || false)"
        self.validate_node(self.exp_parser, string_expr, expr)

    def test_conditionalIf(self):
        if_expr = AST.VarNode("var")
        if_block_question = AST.QuestionNode("q?", "var", AST.BoolTypeNode())
        if_block = AST.BlockNode([if_block_question])
        if_cond = AST.IfNode(if_expr, if_block)

        cond_str = 'if (var) { "q?" var : boolean }'
        self.validate_node(self.cond_parser, cond_str, if_cond)

        self.check_parse_exception(self.cond_parser, 'if (var) {',
                                   pp.ParseException)

    def test_conditionalIfElse(self):
        if_expr = AST.VarNode("var")
        if_block_question = AST.QuestionNode("q?", "var", AST.BoolTypeNode())
        if_block = AST.BlockNode([if_block_question])
        if_else_cond = AST.IfElseNode(if_expr, if_block, if_block)

        parse_str = 'if (var) { "q?" var : boolean } else { "q?" var : boolean}'
        self.validate_node(self.cond_parser, parse_str, if_else_cond)

        self.check_parse_exception(self.cond_parser, 'if (var) {',
                                   pp.ParseException)

    def test_parse_form(self):
        form1 = """
                form TestForm {
                    "question1?" var1 : money
                    if (var1 > 200) {
                        "question2?" var2 : string
                    }
                }
                """

        q1 = AST.QuestionNode("question1?", "var1", AST.MoneyTypeNode())
        q2 = AST.QuestionNode("question2?", "var2", AST.StringTypeNode())
        expr = AST.GTNode(AST.VarNode("var1"), AST.IntNode(int("200")))
        if_block = AST.IfNode(expr, AST.BlockNode([q2]))
        form_block = AST.BlockNode([q1, if_block])
        form_node = AST.FormNode("TestForm", form_block)
        self.validate_node(self.parser.grammar, form1, form_node)

        form2 = """
                form TestForm {
                    "question1?" var1 : money
                    if (var1 > 200) {
                        "question2?" var2 : string
                    }
                    else {
                        "question1?" var1 : money
                    }
                    "question2?" var2 : string
                }
                """

        if_else_block = AST.IfElseNode(expr, AST.BlockNode([q2]),
                                       AST.BlockNode([q1]))
        form_block2 = AST.BlockNode([q1, if_else_block, q2])
        form_node = AST.FormNode("TestForm", form_block2)
        self.validate_node(self.parser.grammar, form2, form_node)

if __name__ == '__main__':
    unittest.main()
