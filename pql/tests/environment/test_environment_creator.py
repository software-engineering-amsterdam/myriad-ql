# coding=utf-8
from unittest import TestCase

from pql.environment.environmentcreator import EnvironmentCreator
from pql.parser.parser import parse


class TestEnvironmentCreator(TestCase):

    def test_parse_field_money_default(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money
        }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result

        environemnt = EnvironmentCreator().visit(form_node)
        self.assertEqual(environemnt['valueResidue'], float(0.0), 'valueResidue should be in environment')

    def test_parse_field_integer_default(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: integer
        }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result

        environemnt = EnvironmentCreator().visit(form_node)
        self.assertEqual(environemnt['valueResidue'], int(0), 'valueResidue should be in environment')

    def test_parse_field_boolean_default(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean
        }
        """
        parse_result = parse(input_string).asList()
        form_node = parse_result

        environemnt = EnvironmentCreator().visit(form_node)
        self.assertEqual(environemnt['valueResidue'], False, 'valueResidue should be in environment')
