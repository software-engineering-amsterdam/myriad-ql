# coding=utf-8
from unittest import TestCase

from decimal import Decimal

from pql.main import ql


class TestEvaluator(TestCase):

    def test_eval_arithmetic_both_integers(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = 10 + 2
        }
        """
        environment = ql(input_string)
        expected_identifier = 'hasSoldHouse'
        self.assertTrue(expected_identifier in environment, "Environment should contain key hasSoldHouse")
        self.assertEqual(12, environment[expected_identifier], "Evaluation should result in 12")

    def test_eval_arithmetic_integer_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = 10 + 2.0
        }
        """
        environment = ql(input_string)
        expected_identifier = 'hasSoldHouse'
        self.assertTrue(expected_identifier in environment, "Environment should contain key hasSoldHouse")
        self.assertEqual(12.0, environment[expected_identifier], "Evaluation should result in 12.0")

    def test_eval_arithmetic_field(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: integer = 10 + 2
            "Price with inflation" inflationPrice: integer = hasSoldHouse * 1.1
        }
        """
        environment = ql(input_string)
        expected_identifier_1 = 'hasSoldHouse'
        self.assertTrue(expected_identifier_1 in environment, "Environment should contain key hasSoldHouse")
        self.assertEqual(12, environment[expected_identifier_1], "Evaluation should result in 12")

        expected_identifier_2 = 'inflationPrice'
        self.assertTrue(expected_identifier_2 in environment, "Environment should contain key inflationPrice")
        self.assertEqual(Decimal('13.2'), environment[expected_identifier_2], "Evaluation should result in 13.2")
