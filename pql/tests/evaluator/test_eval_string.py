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
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))
