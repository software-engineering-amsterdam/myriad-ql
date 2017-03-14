# coding=utf-8
from pql.parser.parser import parse
from tests.shared import Shared


class TestDependenciesChecker(Shared):
    def test_simple_circular(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = hasBoughtHouse
            "Did you buy a house in 2010?" hasBoughtHouse: boolean = hasSoldHouse
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_circular_references(form_node)
        self.fail('This test is not implemented yet')
        # self.assertEqual(len(errors), 1, "There should be exactly 1 error")
