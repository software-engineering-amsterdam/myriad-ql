from QL.stages.parser import QuestionnaireParser as Parser
from QL.stages.expressionEvaluator import ExpressionEvaluator
from QL.environment import Environment
from QL.errorHandler import ErrorHandler
from QL.undefined import Undefined
import QL.AST as AST

import unittest
import decimal


class TestEvaluator(unittest.TestCase):
    parser = Parser()
    q_parser = parser.question
    exp_parser = parser.expression
    cond_parser = parser.conditional

    def setUp(self):
        self.handler = ErrorHandler()
        self.env = Environment(self.handler)
        self.evaluator = ExpressionEvaluator(self.env)

    def tearDown(self):
        pass

    def eval_binop_expr(self, expression, expected_res):
        expr = self.exp_parser.parseString(expression, parseAll=True)[0]
        result = expr.accept(self.evaluator)
        self.assertEqual(result, expected_res)

    def testArthemeticEvaluator(self):
        left = decimal.Decimal("5.0")
        right = decimal.Decimal("2")

        self.eval_binop_expr("5.0 + 2", left + right)
        self.eval_binop_expr("5.0 - 2", left - right)
        self.eval_binop_expr("5.0 * 2", left * right)
        self.eval_binop_expr("5.0 / 2", left / right)

        # Div through 0
        self.eval_binop_expr("5.0 / 0.0", Undefined)

    def testComparisonEvalutor(self):
        left = decimal.Decimal("5.0")
        right = decimal.Decimal("2")

        self.eval_binop_expr("5.0 > 2", left > right)
        self.eval_binop_expr("5.0 > 5.0", left > left)

        self.eval_binop_expr("5.0 >= 2", left >= right)
        self.eval_binop_expr("5.0 >= 5.0", left >= left)

        self.eval_binop_expr("5.0 < 2", left < right)
        self.eval_binop_expr("5.0 < 5.0", left < left)

        self.eval_binop_expr("5.0 <= 2", left <= right)
        self.eval_binop_expr("5.0 <= 5.0", left <= left)

        self.eval_binop_expr("5.0 == 2", left == right)
        self.eval_binop_expr("5.0 != 2", left != right)

    def testLogicalExpr(self):
        self.eval_binop_expr("true && true", True and True)
        self.eval_binop_expr("true && false", True and False)
        self.eval_binop_expr("false && true", False and True)
        self.eval_binop_expr("false && false", False and False)

        self.eval_binop_expr("true || true", True or True)
        self.eval_binop_expr("true || false", True or False)
        self.eval_binop_expr("false || true", False or True)
        self.eval_binop_expr("false || false", False or False)

    def testUndefinedExpr(self):
        expr = "var * 10"
        self.env.add_var(AST.QuestionNode("q", "var", "money"))
        parse_res = self.exp_parser.parseString(expr, parseAll=True)[0]
        self.assertEqual(parse_res.accept(self.evaluator), Undefined)

        self.env.set_var_value("var", decimal.Decimal("10"))
        self.assertEqual(parse_res.accept(self.evaluator),
                         decimal.Decimal("100"))
        self.env.clear_env()

if __name__ == '__main__':
    unittest.main()
