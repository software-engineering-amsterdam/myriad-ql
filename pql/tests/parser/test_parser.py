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

