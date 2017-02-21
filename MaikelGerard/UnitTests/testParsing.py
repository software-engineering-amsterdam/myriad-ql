import Parser
import AST
import pyparsing as pp
import unittest
from UnitTests.testUtils import create_node


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
        q_type = "boolean"
        q_str = format_question(question, name, q_type)
        test_node = create_node(AST.QuestionNode,
                                     [create_node(AST.StringNode, question),
                                      create_node(AST.VarNode, name), q_type])
        self.validate_node(self.q_parser, q_str, test_node)

        question = "Did you sell a house in 2010?"
        q_type = "money"
        q_str = format_question(question, name, q_type)
        test_node = create_node(AST.QuestionNode,
                                     [create_node(AST.StringNode, question),
                                      create_node(AST.VarNode, name), q_type])
        self.validate_node(self.q_parser, q_str, test_node)

        question = "question?"
        name = "moneyVar"
        q_type = "decimal"
        q_str = format_question(question, name, q_type)
        test_node = create_node(AST.QuestionNode,
                                     [create_node(AST.StringNode, question),
                                      create_node(AST.VarNode, name), q_type])
        self.validate_node(self.q_parser, q_str, test_node)

        self.check_parse_exception(self.q_parser, "name: \"question?\" boolean ", pp.ParseException)
        self.check_parse_exception(self.q_parser, "\"question\" name boolean ", pp.ParseException)
        self.check_parse_exception(self.q_parser, "\"question?\" boolean: name", pp.ParseException)

    def test_computed_questions(self):
        def format_question(q, n, var_type, expr):
            return "\"{}\" {}: {} = ({})".format(q, n, var_type, expr)

        question = "Did you sell a house in 2010?"
        name = "hasSoldHouse"
        q_type = "boolean"
        q_expr = "-var >= 600"
        node_expr = create_node(AST.BinOpNode, create_node(AST.MonOpNode, ["-",
                                     create_node(AST.VarNode, "var")]), ">=",
                                     create_node(AST.IntNode, "600"))

        q_str = format_question(question, name, q_type, q_expr)
        test_node = create_node(AST.ComputedQuestionNode,
                                     [create_node(AST.StringNode, question),
                                      create_node(AST.VarNode, name), q_type, node_expr])
        self.validate_node(self.q_parser, q_str, test_node)

    def test_boolean_lit_in_expr(self):
        bool_true = create_node(AST.BoolNode, "true")
        self.validate_node(self.exp_parser, "true", bool_true)

        bool_false = create_node(AST.BoolNode, "false")
        self.validate_node(self.exp_parser, "false", bool_false)

        self.invalidate_node(self.exp_parser, "false", bool_true)
        self.invalidate_node(self.exp_parser, "true", bool_false)

    def test_var_lit_in_expr(self):
        name = "varName"
        var_node1 = create_node(AST.VarNode, name)
        self.validate_node(self.exp_parser, name, var_node1)

        name = "var_name_129"
        var_node2 = create_node(AST.VarNode, name)
        self.validate_node(self.exp_parser, name, var_node2)

        name = "v"
        var_node3 = create_node(AST.VarNode, name)
        self.validate_node(self.exp_parser, name, var_node3)

        self.check_parse_exception(self.exp_parser, "_hallo", pp.ParseException)

    def test_int_lit_in_expr(self):
        integer = "1000"
        int_node1 = create_node(AST.IntNode, integer)
        self.validate_node(self.exp_parser, integer, int_node1)

        integer = "10000000000000000"
        int_node2 = create_node(AST.IntNode, integer)
        self.validate_node(self.exp_parser, integer, int_node2)

        self.check_parse_exception(self.exp_parser, "0x900", pp.ParseException)

    def test_string_lit_in_expr(self):
        string = '@#%$^&*()'
        string_node1 = create_node(AST.StringNode, string)
        self.validate_node(self.exp_parser, '"{}"'.format(string), string_node1)

        string = 'Hello world'
        string_node2 = create_node(AST.StringNode, string)
        self.validate_node(self.exp_parser, '"{}"'.format(string), string_node2)

        self.check_parse_exception(self.exp_parser, '"Hello', pp.ParseException)

    def test_decimal_lit_in_expr(self):
        decimal_val = "100.005"
        dec_node1 = create_node(AST.DecimalNode, decimal_val)
        self.validate_node(self.exp_parser, decimal_val, dec_node1)

        decimal_val = "100."
        dec_node2 = create_node(AST.DecimalNode, decimal_val)
        self.validate_node(self.exp_parser, decimal_val, dec_node2)

        decimal_val = ".50"
        dec_node3 = create_node(AST.DecimalNode, decimal_val)
        self.validate_node(self.exp_parser, decimal_val, dec_node3)

    def test_monops(self):
        monop_node1 = create_node(AST.MonOpNode, ["!", create_node(AST.BoolNode, "true")])
        self.validate_node(self.exp_parser, "!true", monop_node1)

        monop_node2 = create_node(AST.MonOpNode, ["!", monop_node1])
        self.validate_node(self.exp_parser, "!!true", monop_node2)

        monop_node3 = create_node(AST.MonOpNode, ["-", create_node(AST.IntNode, "30")])
        self.validate_node(self.exp_parser, "-30", monop_node3)

        monop_node4 = create_node(AST.MonOpNode, ["+", create_node(AST.IntNode, "40")])
        self.validate_node(self.exp_parser, "+40", monop_node4)

    def test_binops(self):
        binop1 = ["1000", ">", "100.0"]
        binop_node1 = create_node(AST.BinOpNode, create_node(AST.IntNode, binop1[0]),
                                       binop1[1], create_node(AST.DecimalNode, binop1[2]))
        self.validate_node(self.exp_parser, "".join(binop1), binop_node1)

        binop2 = binop1 + ["==", "true"]
        binop_node2 = create_node(AST.BinOpNode, binop_node1, binop2[-2],
                                       create_node(AST.BoolNode, binop2[-1]))
        self.validate_node(self.exp_parser, "".join(binop2), binop_node2)

        # (100 + var) * (true) + "String"
        lit_bool = create_node(AST.BoolNode, "true")
        lit_int = create_node(AST.IntNode, "100")
        lit_var = create_node(AST.VarNode, "var")
        lit_string = create_node(AST.StringNode, "String")

        binop_node3 = create_node(AST.BinOpNode, lit_int, "+", lit_var)
        binop_node3 = create_node(AST.BinOpNode, binop_node3, "*", lit_bool)
        binop_node3 = create_node(AST.BinOpNode, binop_node3, "+", lit_string)
        self.validate_node(self.exp_parser, "(100 + var) * true + \"String\"", binop_node3)

        self.check_parse_exception(self.exp_parser, '20 +', pp.ParseException)
        self.check_parse_exception(self.exp_parser, '20 ** 30', pp.ParseException)
        self.check_parse_exception(self.exp_parser, '30 // 40', pp.ParseException)

    def test_expression_combinations(self):
        # !(5.0 + +10 / var1 / 1 - !var2 && (19. * .12) || false) = invalid eq but is parseable.
        int1 = create_node(AST.IntNode, "10")
        int2 = create_node(AST.IntNode, "1")
        dec1 = create_node(AST.DecimalNode, "5.0")
        dec2 = create_node(AST.DecimalNode, "19.")
        dec3 = create_node(AST.DecimalNode, ".12")
        var1 = create_node(AST.VarNode, "var1")
        var2 = create_node(AST.VarNode, "var2")
        bool1 = create_node(AST.BoolNode, "false")

        monop = AST.MonOpNode
        binop = AST.BinOpNode

        # First prefix +, then division
        expr = create_node(binop, create_node(monop, ["+", int1]), "/", var1)
        expr = create_node(binop, expr, "/", int2)
        # Secondly + and -
        expr = create_node(binop, dec1, "+", expr)
        expr = create_node(binop, expr, "-", create_node(monop, ["!", var2]))
        # Third &&, last || and the very last !
        expr = create_node(binop, expr, "&&", create_node(binop, dec2, "*", dec3))
        expr = create_node(monop, ["!", create_node(binop, expr, "||", bool1)])

        self.validate_node(self.exp_parser,
                           "!(5.0 + +10 / var1 / 1 - !var2 && (19. * .12) || false)", expr)

    def test_conditionalIf(self):
        if_expr = create_node(AST.VarNode, "var")
        if_block_question = create_node(AST.QuestionNode, [
                                             create_node(AST.StringNode, "q?"),
                                             create_node(AST.VarNode, "var"), "boolean"])
        if_block = create_node(AST.BlockNode, [if_block_question])
        if_cond = create_node(AST.IfNode, ["@if", if_expr, if_block])

        self.validate_node(self.cond_parser, 'if (var) { "q?" var : boolean }', if_cond)

        self.check_parse_exception(self.cond_parser, 'if (var) {', pp.ParseException)

    def test_conditionalIfElse(self):
        if_expr = create_node(AST.VarNode, "var")
        if_block_question = create_node(AST.QuestionNode, [
                                             create_node(AST.StringNode, "q?"),
                                             create_node(AST.VarNode, "var"), "boolean"])
        if_block = create_node(AST.BlockNode, [if_block_question])
        if_else_cond = create_node(AST.IfElseNode,
                                        ["@if", if_expr, if_block, "@else", if_block])

        parse_str = 'if (var) { "q?" var : boolean } else { "q?" var : boolean}'
        self.validate_node(self.cond_parser, parse_str, if_else_cond)

        self.check_parse_exception(self.cond_parser, 'if (var) {', pp.ParseException)

    def test_parse_form(self):
        form1 = """
                form TestForm {
                    "question1?" var1 : money
                    if (var1 > 200) {
                        "question2?" var2 : string
                    }
                }
                """
        ast_var = AST.VarNode
        ast_q = AST.QuestionNode
        ast_if = AST.IfNode
        ast_if_else = AST.IfElseNode
        ast_str = AST.StringNode
        ast_int = AST.IntNode
        ast_root = AST.QuestionnaireAST

        q1 = create_node(ast_q, [create_node(ast_str, "question1?"),
                                      create_node(ast_var, "var1"), "money"])
        q2 = create_node(ast_q, [create_node(ast_str, "question2?"),
                                      create_node(ast_var, "var2"), "string"])
        expr = create_node(AST.BinOpNode, create_node(ast_var, "var1"), ">",
                                create_node(ast_int, "200"))
        if_block = create_node(ast_if, ["@if", expr, create_node(AST.BlockNode, [q2])])
        form_block = create_node(AST.BlockNode, [q1, if_block])
        form_node = create_node(AST.FormNode, ["@form", create_node(ast_var, "TestForm"),
                                                    form_block])
        form_node = create_node(ast_root, form_node)
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

        if_else_block = create_node(ast_if_else, [
            "@if", expr, create_node(AST.BlockNode, [q2]),
            "@else", create_node(AST.BlockNode, [q1])])
        form_block2 = create_node(AST.BlockNode, [q1, if_else_block, q2])
        form_node = create_node(AST.FormNode, ["@form", create_node(ast_var, "TestForm"),
                                               form_block2])
        form_node = create_node(ast_root, form_node)
        self.validate_node(self.parser.grammar, form2, form_node)

if __name__ == '__main__':
    unittest.main()
