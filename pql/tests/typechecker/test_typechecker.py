# coding=utf-8
from unittest import TestCase
from pql.parser.parser import parse
from pql.main import check_type


class TestTypechecker(TestCase):
    def test_parse_field(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean
        }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result
        type_checker_result = check_type(form_node)

    def test_parse_field_assignment(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = (sellingPrice - privateDebt)
        }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result
        type_checker_result = check_type(form_node)

