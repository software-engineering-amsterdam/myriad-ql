from unittest import TestCase
from pql.parser.parser import parse
from pyparsing import ParseException


class TestParser(TestCase):
    def test_parse_simple_empty_form(self):
        input_string = "form taxOfficeExample {}"
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('Empty form block should not be possible')

    def test_parse_simple_form_no_name(self):
        input_string = """form  {
                            "Did you sell a house in 2010?" hasSoldHouse: boolean
                       }"""
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('Form should always have a name')

    def test_parse_simple_form_invalid_name(self):
        input_string = """form $$$$$ {
                            "Did you sell a house in 2010?" hasSoldHouse: boolean
                       }"""
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('Form is not allowed to contain $')

    def test_parse_simple_form_missing_left_curly(self):
        input_string = """form example
                            "Did you sell a house in 2010?" hasSoldHouse: boolean
                       }"""
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('Form needs a left curly after the name')

    def test_parse_simple_form_missing_right_curly(self):
        input_string = """form example {
                            "Did you sell a house in 2010?" hasSoldHouse: boolean
                       """
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('Form needs a right curly after its statements')

    def test_parse_form_single_single_field_wrong_type_declaration(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse boolean
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('Field should have a colon denoting the type after its name')

    def test_parse_form_single_single_field_unknown_type(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: magic
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('This type should not be recognized as valid')

    def test_parse_form_single_single_field_incorrect_question(self):
        input_string = """
        form taxOfficeExample {
            Did you sell a house in 2010? hasSoldHouse: string
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('Question title needs to have quotes surrounding it')

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
            self.fail('If statement needs to have an expression inside the parenthesis')

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
            self.fail('If statement needs to have an expression inside the parenthesis')

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
            self.fail('If statement needs to have a parenthesis next to the expression')

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
            self.fail('If statement needs to have a parenthesis next to the expression')

    def test_parse_form_if_missing_right_curly(self):
        input_string = """
        form taxOfficeExample {
            if (abc) {
                "Did you sell a house in 2010?" hasSoldHouse: boolean

        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('If statement needs to have a curly bracket surrounding the statements')

    def test_parse_form_if_else_inside(self):
        input_string = """
        form taxOfficeExample {
            if (abc) {
                else {
                    "Did you sell a house in 2010?" hasSoldHouse: boolean
                }
            }
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('An else statements needs to have an if preceding it')

    def test_parse_form_single_assignment_incorrect_equals(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money := (sellingPrice - privateDebt)
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('Assignment needs to only have an assignment literal')

    def test_parse_form_single_assignment_missing_equals(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money  (sellingPrice - privateDebt)
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('Assignment needs an assignment literal')

    def test_parse_form_single_assignment_missing_second_expression(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = sellingPrice -
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('Assignment needs two operands')

    def test_parse_form_single_assignment_missing_expression(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('Assignment needs an expression')

    def test_parse_form_single_assignment_missing_type(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue:  = selling - buying
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('Assignment needs a type')

    def test_parse_form_single_assignment_missing_identifier(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" : money  = selling - buying
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('Assignment needs an identifier')

    def test_parse_form_single_assignment_missing_title(self):
        input_string = """
        form taxOfficeExample {
             hasSold : money  = selling - buying
        }
        """
        with self.assertRaises(ParseException):
            parse(input_string)
            self.fail('Assignment needs a title')
