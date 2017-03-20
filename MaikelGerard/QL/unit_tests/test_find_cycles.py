from QL.stages.parser import Parser as Parser
from QL.error_handler import ErrorHandler
from QL.stages.find_cycles import FindCycles

import unittest


class TestCycles(unittest.TestCase):
    parser = Parser()
    handler = ErrorHandler()

    def setUp(self):
        self.handler.clear_errors()

    def tearDown(self):
        pass

    def test_cycles1(self):
        # Cycle : x, y, z
        form = """
            form taxOfficeExample {
                "q?" r: boolean
                if (x) { "Y?" y: boolean }
                if (g) { "G?" a: integer }
                if (y) { "Z?" z: boolean }
                if (z) { "X?" x: boolean }
            }
        """
        parsed_form = self.parser.parse(form)
        FindCycles(parsed_form, self.handler).start_traversal()
        self.assertEqual(self.handler.error_count, 1)

    def test_cycles2(self):
        # Cycle : x, y
        form = """
            form taxOfficeExample {
                if (x) { "Y?" y: boolean }
                if (y) { "X?" x: boolean }
                "q?" r: boolean
            }
        """
        parsed_form = self.parser.parse(form)
        FindCycles(parsed_form, self.handler).start_traversal()
        self.assertEqual(self.handler.error_count, 1)

    def test_cycles3(self):
        # Cycle : x, y
        form = """
            form taxOfficeExample {
                if (x) {
                    "Y?" y: boolean
                    if (y) {
                        "X?" x: boolean
                    }
                    "q?" r2: boolean
                }
                "q?" r1: boolean
            }
        """
        parsed_form = self.parser.parse(form)
        FindCycles(parsed_form, self.handler).start_traversal()
        self.assertEqual(self.handler.error_count, 1)

    def test_find_computed_cycle(self):
        form = """
            form taxOfficeExample {
                if (x) {
                    "Q" q : boolean = (q)
                }
            }
        """
        parsed_form = self.parser.parse(form)
        FindCycles(parsed_form, self.handler).start_traversal()
        self.assertEqual(self.handler.error_count, 1)

    def test_no_cycles(self):
        form = """
            form taxOfficeExample {
                if (x) { "Y?" y: boolean }
                if (y) {
                    "Z?" z: boolean
                    if (z || x) {
                        "Q?" q: boolean
                    }
                }
            }
        """
        parsed_form = self.parser.parse(form)
        FindCycles(parsed_form, self.handler).start_traversal()
        self.assertEqual(self.handler.error_count, 0)

if __name__ == '__main__':
    unittest.main()
