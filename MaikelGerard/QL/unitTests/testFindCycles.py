from QL.stages.parser import Parser as Parser
from QL.errorHandler import ErrorHandler
from QL.stages.findCycles import FindCycles

import unittest


class TestEvaluator(unittest.TestCase):
    parser = Parser()
    handler = ErrorHandler()

    def setUp(self):
        self.handler.clear_errors()

    def tearDown(self):
        pass

    def test_cycles(self):
        form = """
            form taxOfficeExample {
                if (x) { "Y?" y: boolean }
                if (g) { "G?" a: money }
                if (y) { "Z?" z: boolean }
                if (z) { "X?" x: boolean }
            }
        """
        parsed_form = self.parser.parse(form)
        find_cycles = FindCycles(parsed_form, self.handler)
        parsed_form.accept(find_cycles)

        self.assertEqual(self.handler.error_count, 1)

        self.handler.clear_errors()
        form = """
            form taxOfficeExample {
                if (x) { "Y?" y: boolean }
                if (y) { "X?" x: boolean }
            }
        """
        parsed_form = self.parser.parse(form)
        find_cycles = FindCycles(parsed_form, self.handler)
        parsed_form.accept(find_cycles)

        self.assertEqual(self.handler.error_count, 1)


if __name__ == '__main__':
    unittest.main()
