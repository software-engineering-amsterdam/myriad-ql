from unittest import TestCase
from pql.parser.parser import parse
from pyparsing import ParseException


class TestParser(TestCase):
    def test_parse_simple_empty_form(self):
        input_string = "form taxOfficeExample {}"
        actual_result = parse(input_string)
        self.assertIsNotNone(actual_result)

    def test_parse_simple_form_no_name(self):
        input_string = "form {}"
        with self.assertRaises(ParseException):
            parse(input_string)

    def test_parse_simple_form_invalid_name(self):
        input_string = "form $$$$$ {}"
        with self.assertRaises(ParseException):
            parse(input_string)

    def test_parse_simple_form_missing_left_curly(self):
        input_string = "form example }"
        with self.assertRaises(ParseException):
            parse(input_string)

    def test_parse_simple_form_missing_right_curly(self):
        input_string = "form example {"
        with self.assertRaises(ParseException):
            parse(input_string)

    def test_parse_form_single_single_field_wrong_type_declaration(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse boolean
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)

    def test_parse_form_single_single_field_unknown_type(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: magic
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)

    def test_parse_form_single_single_field_incorrect_question(self):
        input_string = """
        form taxOfficeExample {
            Did you sell a house in 2010? hasSoldHouse: magic
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)

    def test_parse_form_empty_if(self):
        input_string = """
        form taxOfficeExample {
            if (hasSoldHouse) { }
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('Empty if block should not be possible')

    def test_parse_form_if_empty_expression(self):
        input_string = """
        form taxOfficeExample {
            if () {
                "Did you sell a house in 2010?" hasSoldHouse: boolean
            }
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)

    def test_parse_form_if_missing_left_curly(self):
        input_string = """
        form taxOfficeExample {
            if (abc)
                "Did you sell a house in 2010?" hasSoldHouse: boolean
            }
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)

    def test_parse_form_if_missing_right_parenthesis(self):
        input_string = """
        form taxOfficeExample {
            if (abc {
                "Did you sell a house in 2010?" hasSoldHouse: boolean
            }
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)

    def test_parse_form_if_missing_left_parenthesis(self):
        input_string = """
        form taxOfficeExample {
            if abc) {
                "Did you sell a house in 2010?" hasSoldHouse: boolean
            }
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)

    def test_parse_form_if_missing_right_curly(self):
        input_string = """
        form taxOfficeExample {
            if (abc) {
                "Did you sell a house in 2010?" hasSoldHouse: boolean

        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)

    def test_parse_form_single_assignment_incorrect_equals(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money := (sellingPrice - privateDebt)
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)

    def test_parse_form_single_assignment_missing_equals(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money  (sellingPrice - privateDebt)
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)

    def test_parse_form_single_assignment_missing_second_expression(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = sellingPrice -
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)

    def test_parse_form_single_assignment_missing_first_expression(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  - sellingPrice
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)

    def test_parse_form_single_assignment_missing_first_expression_alternative(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  -sellingPrice
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)

    def test_parse_form_if_inside_if(self):
        input_string = """
        form taxOfficeExample {
            if (hasSoldHouse) {
                "What was the selling price?"        sellingPrice: money
                    if (blaat) {
                        "What was the buying price?"        buyingPrice: money
                    }
            }
        }
        """
        parse(input_string)

    def test_parse_form_if_inside_if_no_field(self):
        input_string = """
        form taxOfficeExample {
        "What was the selling price?"        sellingPrice: money
            if (hasSoldHouse) {
                if (blaat) {
                    "What was the buying price?"        buyingPrice: money
                }
            }
        }
        """
        parse(input_string)
