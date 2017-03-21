# coding=utf-8
from tests.shared import Shared


class TestIntegerTypeChecker(Shared):
    def test_integer_addition_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 + 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_addition_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 + 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_subtraction_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 - 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_subtraction_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 - 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_division_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 / 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_division_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 / 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_multiplication_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 * 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_multiplication_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 * 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_positive(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = +2
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_negative(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = -2
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

