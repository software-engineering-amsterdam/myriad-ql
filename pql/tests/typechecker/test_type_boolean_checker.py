# coding=utf-8
from tests.shared import Shared


class TestBooleanTypeChecker(Shared):
    def test_boolean_addition_boolean_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true + 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_addition_boolean_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true + true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_addition_boolean_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true + 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_addition_boolean_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true + 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_subtraction_boolean_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true - 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_subtraction_boolean_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true - true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_subtraction_boolean_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true - 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_subtraction_boolean_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true - 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_division_boolean_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true / 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_division_boolean_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true / true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_division_boolean_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true / 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_division_boolean_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true / 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_multiplication_boolean_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true * 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_multiplication_boolean_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true * true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_multiplication_boolean_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true * 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_multiplication_boolean_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true * 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_positive(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = +true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_negative_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = -true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_negative_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = -2
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_negative_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = -2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_negative_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = -true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_negation_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = !2
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_negation_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = !2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_negation_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = !true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_negation_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = !'abcd'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_and_boolean_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true && 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_and_boolean_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true && true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_and_boolean_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true && 'abcd'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_and_boolean_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true && 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_or_boolean_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true || 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_or_boolean_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true || true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_or_boolean_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true || 'abcd'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_or_boolean_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true || 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_equality_boolean_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true == 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_equality_boolean_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true == true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_equality_boolean_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true == 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_equality_boolean_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true == 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_inequality_boolean_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true != 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_inequality_boolean_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true != true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_inequality_boolean_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true != 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_inequality_boolean_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true != 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_greater_inclusive_boolean_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true >= 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_greater_inclusive_boolean_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true >= true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_greater_inclusive_boolean_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true >= 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_greater_inclusive_boolean_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true >= 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_greater_exclusive_boolean_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true > 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_greater_exclusive_boolean_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true > true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_greater_exclusive_boolean_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true > 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_greater_exclusive_boolean_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true > 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_smaller_inclusive_boolean_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true <= 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_smaller_inclusive_boolean_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true <= true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_smaller_inclusive_boolean_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true <= 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_smaller_inclusive_boolean_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true <= 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_smaller_exclusive_boolean_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true < 5
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_smaller_exclusive_boolean_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true < true
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_smaller_exclusive_boolean_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true < 'abc'
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_smaller_exclusive_boolean_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true < 2.00
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
        self.assertEqual(len(type_checker_result), 1, "This cases is supposed to fail: following errors {}"
                         .format(type_checker_result))
