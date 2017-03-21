# coding=utf-8
from tests.shared import Shared


class TestMoneyEvaluator(Shared):
    def test_money_addition_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 + 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(6.00, environment.value(expected_identifier), "Evaluation should result in 6.00")

    def test_money_addition_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 + 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(3.00, environment.value(expected_identifier), "Evaluation should result in 3.00")

    def test_money_subtraction_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 - 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(-4.00, environment.value(expected_identifier), "Evaluation should result in -4.00")

    def test_money_subtraction_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 - 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(-1.00, environment.value(expected_identifier), "Evaluation should result in -1.00")

    def test_money_division_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 / 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(0.2, environment.value(expected_identifier), "Evaluation should result in 0.2")

    def test_money_division_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 / 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(0.5, environment.value(expected_identifier), "Evaluation should result in 0.5")

    def test_money_multiplication_money_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 * 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(5.00, environment.value(expected_identifier), "Evaluation should result in 5.00")

    def test_money_multiplication_money_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = 1.00 * 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(2.00, environment.value(expected_identifier), "Evaluation should result in 2.00")

    def test_money_positive(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = +2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(2.00, environment.value(expected_identifier), "Evaluation should result in 2.00")

    def test_money_negative(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = -2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(-2.00, environment.value(expected_identifier), "Evaluation should result in -2.00")
