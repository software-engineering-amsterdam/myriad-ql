# coding=utf-8
from tests.shared import Shared


class TestStringTypeChecker(Shared):
    def test_string_addition_string_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' + 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_addition_string_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' + true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_addition_string_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' + 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_addition_string_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' + 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_subtraction_string_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' - 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_subtraction_string_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' - true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_subtraction_string_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' - 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_subtraction_string_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' - 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_division_string_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' / 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_division_string_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' / true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_division_string_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' / 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_division_string_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' / 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_multiplication_string_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' * 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_multiplication_string_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' * true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_multiplication_string_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' * 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_multiplication_string_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' * 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_positive(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = +'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_negative_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = -'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_negative_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = -2
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_negative_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = -2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_negative_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = -true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_negation_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = !2
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_negation_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = !2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_negation_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = !true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_negation_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = !'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_and_string_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' && 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_and_string_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' && true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_and_string_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' && 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_and_string_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' && 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_or_string_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' || 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_or_string_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' || true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_or_string_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' || 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_or_string_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' || 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_equality_string_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' == 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_equality_string_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' == true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_equality_string_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' == 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_equality_string_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' == 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_inequality_string_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' != 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_inequality_string_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' != true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_inequality_string_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' != 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_inequality_string_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' != 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_greater_inclusive_string_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' >= 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_greater_inclusive_string_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' >= true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_greater_inclusive_string_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' >= 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_greater_inclusive_string_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' >= 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_greater_exclusive_string_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' > 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_greater_exclusive_string_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' > true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_greater_exclusive_string_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' > 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_greater_exclusive_string_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' > 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_smaller_inclusive_string_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' <= 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_smaller_inclusive_string_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' <= true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_smaller_inclusive_string_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' <= 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_smaller_inclusive_string_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' <= 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_smaller_exclusive_string_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' < 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_smaller_exclusive_string_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' < true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_smaller_exclusive_string_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' < 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_string_smaller_exclusive_string_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: string = 'abcd' < 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))
