from unittest import TestCase
from pql.parser.parser import parse


class TestMain(TestCase):
    def test_parse_arithmetic_notation(self):
        self.expected_result = ['2', '+', ['2', '*', '4']]

        self.input_string = """form taxOfficeExample {
            if(2 + 2 * 4){
                "This is a sample question, right?"
                    var_temp: money
            }
        }"""
        self.result = parse(self.input_string)
        self.parsed_result = self.result.form_statement_list.if_statement[0][0]
        self.assertListEqual(self.parsed_result.asList(), self.expected_result)

    def test_parse_arithmetic_boolean_notation(self):
        self.expected_result = [[['8', '/', '2'], '+', ['2', '*', '8']], '>=', '18']

        self.input_string = """form taxOfficeExample {
            if((8 / 2 + 2 * 8) >= 18){
                "This is a sample question, right?"
                    var_temp: money
            }
        }"""
        self.result = parse(self.input_string)
        self.parsed_result = self.result.form_statement_list.if_statement[0][0]
        self.assertListEqual(self.parsed_result.asList(), self.expected_result)