# coding=utf-8
from unittest import TestCase
from pql.parser.parser import parse
from pql.main import check_type, acquire_identifiers


class TestTypeChecker(TestCase):

    def apply_type_checking(self, input_string):
        parse_result = parse(input_string).asList()
        form_node = parse_result
        ql_identifier_check_result, errors = acquire_identifiers(form_node)
        type_checker_result = check_type(form_node, ql_identifier_check_result)
        return type_checker_result

    def test_parse_field(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean
        }
        """
        type_checker_result = self.apply_type_checking(input_string)

    def test_parse_field_assignment(self):
        input_string = """
        form taxOfficeExample {
            "What was the selling price?" sellingPrice: money
            "What is your private debt0?" privateDebt: money
            "Value residue:" valueResidue: money = (sellingPrice - privateDebt)
        }
        """
        type_checker_result = self.apply_type_checking(input_string)

    def test_typecheck_arithmetic_integers(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = (8 - 4)
        }
        """
        type_checker_result = self.apply_type_checking(input_string)

    def test_typecheck_arithmetic_integer_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = (8 - 4.14)
        }
        """
        type_checker_result = self.apply_type_checking(input_string)
