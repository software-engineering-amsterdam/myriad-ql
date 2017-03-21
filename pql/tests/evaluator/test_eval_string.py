# coding=utf-8
from tests.shared import Shared


class TestStringTypeChecker(Shared):
    def test_string_addition_string_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' + 'abc'
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(expected_identifier in environment, "Environment should contain key valueResidue")
        self.assertEqual('abcdabc', environment[expected_identifier], "Evaluation should result in abcdabc")
