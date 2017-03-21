# coding=utf-8
from tests.shared import Shared


class TestMoneyTypeChecker(Shared):
    def test_money_addition_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 + 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_addition_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 + 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_subtraction_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 - 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_subtraction_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 - 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_division_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 / 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_division_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 / 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_multiplication_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 * 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_multiplication_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 * 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_positive(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = +2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_negative(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = -2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))
