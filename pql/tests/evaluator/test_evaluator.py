# coding=utf-8
from pql.environment.environmentcreator import EnvironmentCreator
from pql.evaluator.evaluator import Evaluator
from tests.shared import Shared


class TestEvaluator(Shared):

    def test_eval_arithmetic_integer_positive(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = +2
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'hasSoldHouse'
        self.assertTrue(expected_identifier in environment, "Environment should contain key hasSoldHouse")
        self.assertEqual(2, environment[expected_identifier], "Evaluation should result in 2")

    def test_eval_arithmetic_integer_negative(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = -2
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'hasSoldHouse'
        self.assertTrue(expected_identifier in environment, "Environment should contain key hasSoldHouse")
        self.assertEqual(-2, environment[expected_identifier], "Evaluation should result in -2")

    def test_eval_arithmetic_both_integers(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = 10 + 2
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'hasSoldHouse'
        self.assertTrue(expected_identifier in environment, "Environment should contain key hasSoldHouse")
        self.assertEqual(12, environment[expected_identifier], "Evaluation should result in 12")

    def test_eval_arithmetic_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = 10 + 2.0
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'hasSoldHouse'
        self.assertTrue(expected_identifier in environment, "Environment should contain key hasSoldHouse")
        self.assertEqual(12.0, environment[expected_identifier], "Evaluation should result in 12.0")

    def test_eval_arithmetic_double_field(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = 10 + 2
            "Price with inflation" inflationPrice: integer = hasSoldHouse * 1.1
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertEqual(12, environment[expected_identifier_1], "Evaluation should result in 12")

        expected_identifier_2 = 'inflationPrice'
        self.assertTrue(expected_identifier_2 in environment, "Environment should contain key inflationPrice")
        self.assertEqual(float((10 + 2) * 1.1), environment[expected_identifier_2], "Evaluation should result in 13.2")

    def test_eval_boolean_and_simple(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = true && false
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertFalse(environment[expected_identifier_1], "Evaluation should result in false")

    def test_eval_string_plus(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: string = 'abc' + 'bcd'
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertEqual("abcbcd", environment[expected_identifier_1], "Evaluation should result in abcbcd ")

    def test_eval_boolean_equality_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = true == false
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertFalse(environment[expected_identifier_1], "Evaluation should result in false")

    def test_eval_boolean_inequality_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = true != false
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertTrue(environment[expected_identifier_1], "Evaluation should result in true")

    def test_eval_boolean_inequality_integer(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = 10 != 11
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertTrue(environment[expected_identifier_1], "Evaluation should result in true")

    def test_eval_boolean_inequality_money(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = 10.00 != 11.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertTrue(environment[expected_identifier_1], "Evaluation should result in true")

    def test_eval_boolean_equality_money_integer(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = 10.00 == 10
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertTrue(environment[expected_identifier_1], "Evaluation should result in true")

    def test_eval_boolean_equality_money(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = 10.00 == 10.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertTrue(environment[expected_identifier_1], "Evaluation should result in true")

    def test_eval_boolean_greater_equal_money(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = 10.00 >= 10.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertTrue(environment[expected_identifier_1], "Evaluation should result in true")

    def test_eval_boolean_smaller_equal_money(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = 10.00 <= 10.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertTrue(environment[expected_identifier_1], "Evaluation should result in true")

    def test_eval_boolean_smaller_money(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = 10.00 < 10.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertFalse(environment[expected_identifier_1], "Evaluation should result in true")

    def test_eval_boolean_greater_money(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = 10.00 > 10.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertFalse(environment[expected_identifier_1], "Evaluation should result in true")

    def test_eval_integer_subtraction(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = 10.00 - 1
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertEqual(float(9.00), environment[expected_identifier_1], "Evaluation should result in 9.00")

    def test_eval_integer_division(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = 20.00 / 4
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertEqual(float(5.00), environment[expected_identifier_1], "Evaluation should result in 5.00")

    def test_eval_money_division(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = 20.00 / 4.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertEqual(float(5.00), environment[expected_identifier_1], "Evaluation should result in 5.00")

    def test_eval_money_division_zero_money(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = 20.00 / 0.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertEqual(float(0.00), environment[expected_identifier_1], "Evaluation should result in 5.00")

    def test_eval_money_division_zero_integer(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = 20.00 / 0
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertEqual(float(0.00), environment[expected_identifier_1], "Evaluation should result in 5.00")

    def test_eval_if_money_division(self):
        input_string = """
        form taxOfficeExample {
            if(true){
                "Did you sell a house in 2010?" hasSoldHouse: integer = 20.00 / 4.00
            }
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertEqual(float(5.00), environment[expected_identifier_1], "Evaluation should result in 5.00")

    def test_eval_if_else_false_money_division(self):
        input_string = """
        form taxOfficeExample {
            if(true == false){
                "Did you sell a house in 2010?" hasSoldHouse: integer = 20.00 / 4.00
            }
            else {
                "Did you sell a house in 2010?" hasBoughtHouse: integer = 20.00 / 4.00
            }
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        expected_identifier_2 = 'hasBoughtHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertTrue(expected_identifier_2 in environment, "Environment should contain key hasBoughtHouse")
        self.assertEqual(float(0.00), environment[expected_identifier_1], "Evaluation should result in 5.00")
        self.assertEqual(float(5.00), environment[expected_identifier_2], "Evaluation should result in 0.00")

    def test_eval_if_else_true_money_division(self):
        input_string = """
        form taxOfficeExample {
            if(true){
                "Did you sell a house in 2010?" hasSoldHouse: integer = 20.00 / 4.00
            }
            else {
                "Did you sell a house in 2010?" hasBoughtHouse: integer = 20.00 / 4.00
            }
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        expected_identifier_2 = 'hasBoughtHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertTrue(expected_identifier_2 in environment, "Environment should contain key hasBoughtHouse")
        self.assertEqual(float(5.00), environment[expected_identifier_1], "Evaluation should result in 5.00")
        self.assertEqual(float(0.00), environment[expected_identifier_2], "Evaluation should result in 0.00")

    def test_eval_boolean_negation(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = !true
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertFalse(environment[expected_identifier_1], "Evaluation should result in false")

    def test_eval_boolean_and_or(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean = true
            "Did you sell a house in 2011?" hasSoldHouse11: boolean = (hasSoldHouse || false) && false
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertTrue(environment[expected_identifier_1], "Evaluation should result in false")

        expected_identifier_2 = 'hasSoldHouse11'
        self.assertFalse(environment[expected_identifier_2], "Evaluation should result in false")

    def test_eval_boolean_greater_than(self):
        input_string = """
        form taxOfficeExample {
        "House selling price?" sellingPrice: money = 23000.87
        "House selling price, adjusted for inflation?" sellingPriceWithInflation: money = 23000.87 * 1.1
        "Did you pay more because of inflation?" wasMoreExpensive: boolean = sellingPriceWithInflation >= sellingPrice
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier_1 = 'sellingPrice'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertTrue(environment[expected_identifier_1], "Evaluation should result in false")

        expected_identifier_2 = 'sellingPriceWithInflation'
        self.assertTrue(expected_identifier_2 in environment, "Environment should contain key hasSoldHouse")
        self.assertEqual(float(23000.87 * 1.1), environment[expected_identifier_2], "Output should equal 25300.957")

        expected_identifier_3 = 'wasMoreExpensive'
        self.assertTrue(expected_identifier_3 in environment, "Environment should contain key hasSoldHouse")
        self.assertTrue(environment[expected_identifier_3], "Evaluation should result in true")

    def test_eval_cyclic_value_dependency(self):
        input_string = """
        form taxOfficeExample {
            "q1" v1: integer = v2 * 2
            "q2" v2: integer
        }
        """
        ast = self.acquire_ast(input_string)
        evaluator = Evaluator(EnvironmentCreator, ast)
        environment = evaluator.visit()

        expected_identifier_1 = 'v1'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key v1")
        self.assertEqual(0, environment[expected_identifier_1], "Evaluation should result in 0")
        expected_identifier_2 = 'v2'
        self.assertTrue(expected_identifier_2 in environment, "Environment should contain key v2")
        self.assertEqual(0, environment[expected_identifier_2], "Evaluation should result in 0")

        new_evaluator = evaluator.update_value(expected_identifier_2, 2)
        self.assertTrue(expected_identifier_1 in new_evaluator, "Environment should contain key v1")
        self.assertEqual(4, environment[expected_identifier_1], "Evaluation should result in 0")
        expected_identifier_2 = 'v2'
        self.assertTrue(expected_identifier_2 in new_evaluator, "Environment should contain key v2")
        self.assertEqual(2, environment[expected_identifier_2], "Evaluation should result in 0")

    def test_eval_cyclic_value_dependency_loop(self):
        input_string = """
        form taxOfficeExample {
            "q1" v1: integer = v2 * 2
            "q2" v2: integer = v3
            "q3" v3: integer
        }
        """
        ast = self.acquire_ast(input_string)
        evaluator = Evaluator(EnvironmentCreator, ast)
        environment = evaluator.visit()

        expected_identifier_1 = 'v1'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key v1")
        self.assertEqual(0, environment[expected_identifier_1], "Evaluation should result in 0")
        expected_identifier_2 = 'v2'
        self.assertTrue(expected_identifier_2 in environment, "Environment should contain key v2")
        self.assertEqual(0, environment[expected_identifier_2], "Evaluation should result in 0")
        expected_identifier_3 = 'v3'
        self.assertTrue(expected_identifier_3 in environment, "Environment should contain key v3")
        self.assertEqual(0, environment[expected_identifier_3], "Evaluation should result in 0")

        environment = evaluator.update_value(expected_identifier_3, 2)
        self.assertEqual(4, environment[expected_identifier_1], "Evaluation should result in 4")
        self.assertEqual(2, environment[expected_identifier_2], "Evaluation should result in 2")
        self.assertEqual(2, environment[expected_identifier_3], "Evaluation should result in 2")