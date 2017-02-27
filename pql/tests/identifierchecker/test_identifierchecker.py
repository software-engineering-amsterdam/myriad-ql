# coding=utf-8
from unittest import TestCase
from pql.parser.parser import parse
from pql.main import acquire_identifiers


class TestIdentifierChecker(TestCase):
    def test_field(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean
            "Did you buy a house in 2010?" hasBoughtHouse: boolean
        }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result
        identifier_checker_result, errors = acquire_identifiers(form_node)

    def test_duplicate_field(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean
            "Did you sell a house in 2010?" hasSoldHouse: boolean
        }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result
        identifier_checker_result, errors = acquire_identifiers(form_node)
        self.assertEqual(len(errors), 1, "There should be exactly 1 error")

    def test_parse_field_assignment(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money = (sellingPrice - privateDebt)
        }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result
        identifier_checker_result, errors = acquire_identifiers(form_node)
