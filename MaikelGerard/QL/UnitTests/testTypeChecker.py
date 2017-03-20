from QL.Stages.Parser import QuestionnaireParser as Parser
from QL.Stages.TypeChecker import TypeChecker
from QL.Environment import Environment
from QL.ErrorHandler import ErrorHandler

import QL.AST as AST
import unittest


# TODO: Tests with Money not included as we do not parse it into AST nodes.
# TODO: Add tests for Date-type.
class TestTypeChecker(unittest.TestCase):
    parser = Parser()
    q_parser = parser.question
    exp_parser = parser.expression
    cond_parser = parser.conditional

    def setUp(self):
        self.handler = ErrorHandler()
        self.env = Environment(self.handler)
        self.typechecker = TypeChecker(None, self.env, self.handler)

    def tearDown(self):
        pass

    def clear_variables(self):
        self.env.clear_env()
        self.typechecker.labels = []
        self.handler.clear_errors()

    def check_bad_form(self, example, num_warnings, num_errors):
        ast = self.parser.parse(example)
        ast.root.accept(self.typechecker)

        # Determine whether the typechecker result is as expected.
        correct_warnings = self.handler.warn_count == num_warnings
        correct_errors = self.handler.error_count == num_errors
        self.assertTrue(correct_warnings and correct_errors)

        # Clear the error handler and environment.
        self.clear_variables()

    def test_form(self):
        form_duplicate = """
            form taxOfficeExample {
                "Did you sell a house in 2010?"
                    hasBoughtHouse: boolean
                "Did you sell a house in 2010?"
                    hasBoughtHouse: boolean = (true)
            }
        """
        self.check_bad_form(form_duplicate, 1, 1)

        form_wrong_if = """
            form taxOfficeExample {
                if (8) {
                    "Did you sell a house in 2010?"
                        hasBoughtHouse: boolean
                }
            }
        """
        self.check_bad_form(form_wrong_if, 0, 1)

    def check_good_expression(self, example, correct_type):
        expr = self.exp_parser.parseString(example, parseAll=True)[0]
        result_type = expr.accept(self.typechecker)

        # Determine if the result type is correct.
        self.assertTrue(len(self.handler.error_list) == 0)
        self.assertEqual(result_type, correct_type)

        # Clear the error handler and environment.
        self.clear_variables()

    def check_bad_expression(self, example, num_warnings, num_errors):
        expr = self.exp_parser.parseString(example, parseAll=True)[0]
        expr.accept(self.typechecker)

        # Determine whether the typechecker result is as expected.
        correct_warnings = self.handler.warn_count == num_warnings
        correct_errors = self.handler.error_count == num_errors
        self.assertTrue(correct_warnings and correct_errors)

        # Clear the error handler and environment.
        self.clear_variables()

    def test_monop(self):
        self.check_good_expression("+10", AST.IntTypeNode())
        self.check_good_expression("!true", AST.BoolTypeNode())

        self.check_bad_expression("+\"example\"", 0, 1)
        self.check_bad_expression("!\"example\"", 0, 1)

    def test_arithmetic_expr(self):
        self.check_good_expression("10 * 20", AST.IntTypeNode())
        self.check_good_expression("10.0 * 20", AST.DecimalTypeNode())
        self.check_good_expression("10 + 20", AST.IntTypeNode())
        self.check_good_expression("10.0 + 20", AST.DecimalTypeNode())
        self.check_good_expression("\"abc\" + \"def\"", AST.StringTypeNode())

        self.check_bad_expression("10 * \"abc\"", 0, 1)
        self.check_bad_expression("10 + true", 0, 1)
        self.check_bad_expression("10 + \"abc\"", 0, 1)

    def test_comparison_expr(self):
        self.check_good_expression("10 >= 10", AST.BoolTypeNode())
        self.check_good_expression("10.0 >= 10", AST.BoolTypeNode())
        self.check_good_expression("\"abc\" >= \"def\"", AST.BoolTypeNode())

        self.check_bad_expression("true >= false", 0, 1)
        self.check_bad_expression("true >= 10", 0, 1)
        self.check_bad_expression("10 >= \"abc\"", 0, 1)

    def test_logical_expressions(self):
        self.check_good_expression("true && false", AST.BoolTypeNode())
        self.check_good_expression("true || true", AST.BoolTypeNode())

        self.check_bad_expression("true && 10", 0, 1)

if __name__ == '__main__':
    unittest.main()
