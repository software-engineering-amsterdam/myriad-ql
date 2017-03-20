# coding=utf-8
from tests.shared import Shared


class TestTypeChecker(Shared):
    def test_money_addition_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 + 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_addition_money_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 + true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_addition_money_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 + 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_addition_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 + 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_subtraction_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 - 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_subtraction_money_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 - true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_subtraction_money_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 - 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_subtraction_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 - 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_division_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 / 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_division_money_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 / true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_division_money_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 / 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_division_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 / 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_multiplication_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 * 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_multiplication_money_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 * true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_multiplication_money_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 * 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_multiplication_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 * 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_positive(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = +2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_negative(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = -2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_negation(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = !2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_and_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 && 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_and_money_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 && true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_and_money_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 && 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_and_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 && 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_or_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 || 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_or_money_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 || true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_or_money_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 || 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_or_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 || 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_equality_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 == 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_equality_money_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 == true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_equality_money_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 == 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_equality_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 == 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_inequality_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 != 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_inequality_money_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 != true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_inequality_money_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 != 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_inequality_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 != 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_greater_inclusive_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 >= 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_greater_inclusive_money_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 >= true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_greater_inclusive_money_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 >= 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_greater_inclusive_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 >= 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_greater_exclusive_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 > 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_greater_exclusive_money_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 > true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_greater_exclusive_money_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 > 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_greater_exclusive_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 > 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_smaller_inclusive_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 <= 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_smaller_inclusive_money_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 <= true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_smaller_inclusive_money_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 <= 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_smaller_inclusive_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 <= 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_smaller_exclusive_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 < 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_smaller_exclusive_money_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 < true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_smaller_exclusive_money_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 < 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_money_smaller_exclusive_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = 1.00 < 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))
