import unittest

from pql.Amain import *

class TestMain(unittest.TestCase):
    def test_parse_arithmetic_notation(self):
        self.expected_result = ['2', '+', ['2', '*', '4']]

        self.input_string = """form taxOfficeExample {
            if(2 + 2 * 4){
                "This is a samle question, right?"
                    var_temp: money
            }
        }"""
        self.result = parse(self.input_string)
        self.parsed_result = self.result[1][0][0][1]
        self.assertListEqual(self.parsed_result.asList(), self.expected_result)

    def test_parse_arithmetic_boolean_notation(self):
        self.expected_result = [[['8', '/', '2'], '+', ['2', '*', '8']], '>=', '18']

        self.input_string = """form taxOfficeExample {
            if((8 / 2 + 2 * 8) >= 18){
                "This is a samle question, right?"
                    var_temp: money
            }
        }"""
        self.result = parse(self.input_string)
        self.parsed_result = self.result[1][0][0][1]
        self.assertListEqual(self.parsed_result.asList(), self.expected_result)