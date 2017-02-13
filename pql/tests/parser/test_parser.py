import unittest

from pql.parser.parser import PQLParser


class TestMain(unittest.TestCase):
    def test_parse_simple_empty_form(self):
        self.parser = PQLParser()
        self.input_string = "form taxOfficeExample {}"
        self.result = self.parser.start_parse_form_block(self.input_string)
        self.assertTrue(len(self.result), 2)
        self.assertTrue(self.result[0], "form")
        self.assertTrue(self.result[1], "taxOfficeExample")
