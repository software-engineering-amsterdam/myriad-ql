# coding=utf-8
from tests.shared import Shared


class TestIntegerTypeChecker(Shared):
    def test_integer_addition_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 + 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_addition_integer_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 + true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_addition_integer_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 + 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_addition_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 + 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_subtraction_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 - 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_subtraction_integer_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 - true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_subtraction_integer_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 - 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_subtraction_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 - 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_division_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 / 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_division_integer_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 / true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_division_integer_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 / 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_division_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 / 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_multiplication_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 * 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_multiplication_integer_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 * true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_multiplication_integer_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 * 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_multiplication_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 * 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_positive(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = +2
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_negative(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = -2
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_negation(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = !2
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_and_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 && 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_and_integer_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 && true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_and_integer_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 && 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_and_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 && 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_or_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 || 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_or_integer_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 || true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_or_integer_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 || 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_or_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 || 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_equality_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 == 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_equality_integer_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 == true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_equality_integer_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 == 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_equality_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 == 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_inequality_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 != 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_inequality_integer_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 != true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_inequality_integer_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 != 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_inequality_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 != 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_greater_inclusive_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 >= 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_greater_inclusive_integer_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 >= true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_greater_inclusive_integer_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 >= 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_greater_inclusive_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 >= 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_greater_exclusive_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 > 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_greater_exclusive_integer_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 > true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_greater_exclusive_integer_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 > 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_greater_exclusive_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 > 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_smaller_inclusive_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 <= 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_smaller_inclusive_integer_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 <= true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_smaller_inclusive_integer_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 <= 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_smaller_inclusive_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 <= 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_smaller_exclusive_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 < 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_smaller_exclusive_integer_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 < true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_smaller_exclusive_integer_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 < 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_integer_smaller_exclusive_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 < 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))
