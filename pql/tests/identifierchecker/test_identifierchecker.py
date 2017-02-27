# coding=utf-8
from unittest import TestCase
from pql.parser.parser import parse
from pql.main import acquire_identifiers


class TestIdentifierChecker(TestCase):
    def test_parse_field(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean
            "Did you buy a house in 2010?" hasBoughtHouse: boolean
        }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result
        identifier_checker_result, errors = acquire_identifiers(form_node)

    def test_parse_field_assignment(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = (sellingPrice - privateDebt)
        }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result
        identifier_checker_result, errors = acquire_identifiers(form_node)
