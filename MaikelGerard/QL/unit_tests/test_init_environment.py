from QL.stages.parser import Parser as Parser
from QL.stages.init_environment import InitEnvironment
from QL.environment import Environment
from QL.error_handler import ErrorHandler

import QL.AST as AST
import unittest


# TODO: Tests with Money not included as we do not parse it into AST nodes.
# TODO: Add tests for Date-type.
class TestTypeChecker(unittest.TestCase):
    parser = Parser()

    def setUp(self):
        self.handler = ErrorHandler()
        self.env = Environment(self.handler)

    def tearDown(self):
        pass

    def clear_variables(self):
        self.env.clear_env()
        self.handler.clear_errors()

    def check_bad_form(self, example, num_warnings, num_errors):
        ast = self.parser.parse(example)
        InitEnvironment(ast, self.env, self.handler).start_traversal()

        # Determine whether the result is as expected.
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

if __name__ == '__main__':
    unittest.main()
