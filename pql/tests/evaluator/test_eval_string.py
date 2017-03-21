# coding=utf-8
from tests.shared import Shared


class TestStringEvaluator(Shared):
    def test_string_addition_string_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' + 'abc'
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual('abcdabc', environment.value(expected_identifier), "Evaluation should result in abcdabc")
