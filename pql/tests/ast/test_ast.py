import unittest

from pql.Amain import *
from pql.ast import ast


class TestAst(unittest.TestCase):
    def test_parse_simple_empty_form(self):
        self.input_string = "form taxOfficeExample {}"
        self.parse_result = parse(self.input_string)
        self.ast_root = ast.RootNode(self.parse_result).root
        self.form_node = self.ast_root.children[0]

        self.assertEqual(len(self.ast_root.children), 1, "Empty form should only have 1 node in total")
        self.assertEqual('taxOfficeExample', self.form_node.name, "Name of node should equal the declared name")
        self.assertEqual(0, len(self.form_node.children), "Empty form should have no child nodes")

    def test_parse_simple_form_single_question(self):
        self.input_string = "form taxOfficeExample {}"
        self.parse_result = parse(self.input_string)
        self.ast_root = ast.RootNode(self.parse_result).root
        self.form_node = self.ast_root.children[0]

        self.assertEqual(len(self.ast_root.children), 1)
        self.assertEqual('taxOfficeExample', self.form_node.name)
        self.assertEqual(0, len(self.form_node.children))