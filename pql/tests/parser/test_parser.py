import unittest

from pql.Amain import *


class TestMain(unittest.TestCase):
    def test_parse_simple_empty_form(self):
        self.input_string = "form taxOfficeExample {}"
        self.result = parse(self.input_string)
        self.assertTrue(len(self.result[0]), 2)
        self.assertTrue(self.result[0][0], "form")
        self.assertTrue(self.result[0][1], "taxOfficeExample")

    def test_parse_simple_form_invalid_name(self):
        self.input_string = "form $$$$$ {}"
        with self.assertRaises(ParseException):
            parse(self.input_string)

    def test_parse_simple_form_missing_left_curly(self):
        self.input_string = "form example }"
        with self.assertRaises(ParseException):
            parse(self.input_string)

    def test_parse_simple_form_missing_right_curly(self):
        self.input_string = "form example {"
        with self.assertRaises(ParseException):
            parse(self.input_string)

    def test_parse_form_single_field(self):
        self.input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean
        }
        """
        self.result = parse(self.input_string)
        self.assertTrue(len(self.result[0]), 2)
        self.assertTrue(len(self.result[1][0]), 3)
        self.assertTrue(self.result[1][0][0], "Did you sell a house in 2010?")
        self.assertTrue(self.result[1][0][1], "hasSoldHouse")
        self.assertTrue(self.result[1][0][2], "boolean")

    def test_parse_form_single_single_field_wrong_type_declaration(self):
        self.input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse boolean
        }
        """
        with self.assertRaises(ParseException):
            parse(self.input_string)

    def test_parse_form_single_single_field_unknown_type(self):
        self.input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: magic
        }
        """
        with self.assertRaises(ParseException):
            parse(self.input_string)

    def test_parse_form_single_single_field_incorrect_question(self):
        self.input_string = """
        form taxOfficeExample {
            Did you sell a house in 2010? hasSoldHouse: magic
        }
        """
        with self.assertRaises(ParseException):
            parse(self.input_string)

    def test_parse_form_multiple_field(self):
        self.input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean
            "Did you buy a house in 2010?"  hasBoughtHouse: boolean
        }
        """
        self.result = parse(self.input_string)
        self.assertTrue(len(self.result[0]), 3)
        self.assertTrue(len(self.result[1][0]), 3)

        self.first_field = self.result[1][0]
        self.assertTrue(self.first_field[0], "Did you sell a house in 2010?")
        self.assertTrue(self.first_field[1], "hasSoldHouse")
        self.assertTrue(self.first_field[2], "boolean")

        self.second_field = self.result[1][1]
        self.assertTrue(self.second_field[0], "Did you buy a house in 2010?")
        self.assertTrue(self.second_field[1], "hasBoughtHouse")
        self.assertTrue(self.second_field[2], "boolean")
