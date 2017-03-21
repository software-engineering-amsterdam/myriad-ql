# coding=utf-8
from tests.shared import Shared


class TestIntegerEvaluator(Shared):
    def test_integer_addition_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 + 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(6, environment.value(expected_identifier), "Evaluation should result in 6")

    def test_integer_addition_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 + 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(3.00, environment.value(expected_identifier), "Evaluation should result in 3.00")

    def test_integer_subtraction_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 - 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(-4, environment.value(expected_identifier), "Evaluation should result in -4")

    def test_integer_subtraction_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 - 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(-1.00, environment.value(expected_identifier), "Evaluation should result in -1.00")

    def test_integer_division_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 / 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(0.2, environment.value(expected_identifier), "Evaluation should result in 0.2")

    def test_integer_division_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 / 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(0.5, environment.value(expected_identifier), "Evaluation should result in 0.5")

    def test_integer_multiplication_integer_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 * 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(5, environment.value(expected_identifier), "Evaluation should result in 5")

    def test_integer_multiplication_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = 1 * 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(2.00, environment.value(expected_identifier), "Evaluation should result in 2.00")

    def test_integer_positive(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = +2
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(2, environment.value(expected_identifier), "Evaluation should result in 2")

    def test_integer_negative(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer = -2
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertTrue(environment.contains(expected_identifier), "Environment should contain key valueResidue")
        self.assertEqual(-2, environment.value(expected_identifier), "Evaluation should result in -2")

