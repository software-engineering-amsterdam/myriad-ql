from unittest import TestCase
from pql.parser.parser import parse
from pyparsing import ParseException


class TestParser(TestCase):
    def test_parse_simple_empty_form(self):
        self.input_string = "form taxOfficeExample {}"
        self.expected_result = ['taxOfficeExample', []]
        self.actual_result = parse(self.input_string)
        self.assertListEqual(self.expected_result, self.actual_result.asList(), )

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
        self.expected_result = ['taxOfficeExample', [['Did you sell a house in 2010?', 'hasSoldHouse', 'boolean']]]
        self.actual_result = parse(self.input_string)
        self.assertListEqual(self.expected_result, self.actual_result.asList(), )

    def test_parse_form_single_assignment_string_type(self):
        self.input_string = """
        form taxOfficeExample {
            "Example:" testValue: string
        }
        """
        self.expected_result = ['taxOfficeExample', [['Example:', 'testValue', 'string']]]
        self.actual_result = parse(self.input_string)
        self.assertListEqual(self.expected_result, self.actual_result.asList(), )

    def test_parse_form_single_assignment_int_type(self):
        self.input_string = """
        form taxOfficeExample {
            "Example:" testValue: integer
        }
        """
        self.expected_result = ['taxOfficeExample', [['Example:', 'testValue', 'integer']]]
        self.actual_result = parse(self.input_string)
        self.assertListEqual(self.expected_result, self.actual_result.asList(), )

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
        self.expected_result = ['taxOfficeExample',
                                [['Did you sell a house in 2010?', 'hasSoldHouse', 'boolean'],
                                 ['Did you buy a house in 2010?', 'hasBoughtHouse', 'boolean']]]

        self.actual_result = parse(self.input_string)
        self.assertListEqual(self.expected_result, self.actual_result.asList(), )

    def test_parse_form_empty_if(self):
        self.input_string = """
        form taxOfficeExample {
            if (hasSoldHouse) { }
        }
        """
        with self.assertRaises(ParseException):
            parse(self.input_string)

    def test_parse_form_if_empty_expression(self):
        self.input_string = """
        form taxOfficeExample {
            if () {
                "Did you sell a house in 2010?" hasSoldHouse: boolean
            }
        }
        """
        with self.assertRaises(ParseException):
            parse(self.input_string)

    def test_parse_form_if(self):
        self.input_string = """
        form taxOfficeExample {
            if (hasSoldHouse) {
                "What was the selling price?"        sellingPrice: money
            }
        }
        """
        self.expected_result = ['taxOfficeExample',
                                [['hasSoldHouse', ['What was the selling price?', 'sellingPrice', 'money']]]]
        self.actual_result = parse(self.input_string)
        self.assertListEqual(self.expected_result, self.actual_result.asList(), )

    def test_parse_form_single_assignment(self):
        self.input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = (sellingPrice - privateDebt)
        }
        """
        self.expected_result = ['taxOfficeExample',
                                [['Value residue:', 'valueResidue', 'money', ['sellingPrice', '-', 'privateDebt']]]]

        self.actual_result = parse(self.input_string)
        self.assertListEqual(self.expected_result, self.actual_result.asList(), )

    def test_parse_form_single_assignment_incorrect_equals(self):
        self.input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money := (sellingPrice - privateDebt)
        }
        """
        with self.assertRaises(ParseException):
            parse(self.input_string)

    def test_parse_form_single_assignment_missing_equals(self):
        self.input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money  (sellingPrice - privateDebt)
        }
        """
        with self.assertRaises(ParseException):
            parse(self.input_string)

    def test_parse_form_single_assignment_without_parenthesis(self):
        self.input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  sellingPrice - privateDebt
        }
        """
        self.expected_result = ['taxOfficeExample',
                                [['Value residue:', 'valueResidue', 'money', ['sellingPrice', '-', 'privateDebt']]]]
        self.actual_result = parse(self.input_string)
        self.assertListEqual(self.expected_result, self.actual_result.asList(), )

    def test_parse_form_single_assignment_missing_second_expression(self):
        self.input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = sellingPrice -
        }
        """
        with self.assertRaises(ParseException):
            parse(self.input_string)

    def test_parse_form_single_assignment_missing_first_expression(self):
        self.input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  - sellingPrice
        }
        """
        with self.assertRaises(ParseException):
            parse(self.input_string)

    def test_parse_form_single_assignment_missing_first_expression_alternative(self):
        self.input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  -sellingPrice
        }
        """
        with self.assertRaises(ParseException):
            parse(self.input_string)

    def test_parse_form_if_inside_if(self):
        self.input_string = """
        form taxOfficeExample {
            if (hasSoldHouse) {
                "What was the selling price?"        sellingPrice: money
                    if (blaat) {
                        "What was the buying price?"        buyingPrice: money
                    }
            }
        }
        """
        parse(self.input_string)

    def test_parse_form_if_inside_if_no_field(self):
        self.input_string = """
        form taxOfficeExample {
        "What was the selling price?"        sellingPrice: money
            if (hasSoldHouse) {
                if (blaat) {
                    "What was the buying price?"        buyingPrice: money
                }
            }
        }
        """
        parse(self.input_string)
