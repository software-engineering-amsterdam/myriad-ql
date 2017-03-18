# coding=utf-8
from pql.parser.parser import parse
from tests.shared import Shared


class TestIdentifierChecker(Shared):
    def test_single_field(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_identifiers(form_node)
        self.assertEqual(len(errors), 0, "There should be no errors")

    def test_duplicate_field(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean
            "Did you sell a house in 2010?" hasSoldHouse: boolean
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_identifiers(form_node)
        self.assertEqual(len(errors), 1, "There should be exactly 1 error")

    def test_duplicate_field_inside_if(self):
        input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean
            if(condition){
                 "Did you sell a house in 2010?" hasSoldHouse: boolean
            }
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_identifiers(form_node)
        self.assertEqual(len(errors), 1, "There should be exactly 1 error")

    def test_duplicate_field_inside_if_and_else(self):
        input_string = """
        form taxOfficeExample {
            if(condition){
                 "Did you sell a house in 2010?" hasSoldHouse: boolean
            }
            else {
            "Did you sell a house in 2010?" hasSoldHouse: boolean
            }
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_identifiers(form_node)
        self.assertEqual(len(errors), 1, "There should be exactly 1 error")

    def test_duplicate_field_inside_if_and_else_if(self):
        input_string = """
        form taxOfficeExample {
            if(condition){
                 "Did you sell a house in 2010?" hasSoldHouse: boolean
            }
            else {
                if(condition) {
                    "Did you sell a house in 2010?" hasSoldHouse: boolean
                }
            }
        }
        """
        form_node = self.acquire_ast(input_string)
        errors = self.acquire_identifiers(form_node)
        self.assertEqual(len(errors), 1, "There should be exactly 1 error")
