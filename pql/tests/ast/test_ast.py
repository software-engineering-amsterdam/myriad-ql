import unittest

from pql.parser.parser import *


class TestAst(unittest.TestCase):
    def test_ast_simple_empty_form(self):
        self.input_string = "form taxOfficeExample {}"
        self.parse_result = parse(self.input_string).asList()
        self.form_node = self.parse_result[0]
        self.assertEqual('taxOfficeExample', self.form_node.name, "Name of node should equal the declared name")
        self.assertEqual(0, len(self.form_node.children), "Empty form should have no child nodes")

    def test_ast_single_question(self):
        self.input_string = """
        form taxOfficeExample {
            "Did you sell a house in 2010?" hasSoldHouse: boolean
        }
        """
        self.parse_result = parse(self.input_string).asList()
        self.form_node = self.parse_result[0]

        self.assertEqual('taxOfficeExample', self.form_node.name)
        self.field_node_1 = self.form_node.children[0]

        self.assertEqual(0, len(self.field_node_1.children))
        self.assertEqual('field', self.field_node_1.var_type)
        self.assertEqual('hasSoldHouse', self.field_node_1.name)
        self.assertEqual('Did you sell a house in 2010?', self.field_node_1.title)

    def test_ast_double_question(self):
        self.input_string = """
        form taxOfficeExample {
            "Did you buy a house in 2010?" hasBoughtHouse: boolean
            "Did you sell a house in 2010?" hasSoldHouse: boolean
        }
        """
        self.parse_result = parse(self.input_string).asList()
        self.form_node = self.parse_result[0]

        self.assertEqual('taxOfficeExample', self.form_node.name)
        self.assertEqual(2, len(self.form_node.children))
        self.field_node_1 = self.form_node.children[0]
        self.field_node_2 = self.form_node.children[1]

        self.assertEqual(0, len(self.field_node_1.children))
        self.assertEqual('field', self.field_node_1.var_type)
        self.assertEqual('hasBoughtHouse', self.field_node_1.name)
        self.assertEqual('Did you buy a house in 2010?', self.field_node_1.title)

        self.assertEqual(0, len(self.field_node_2.children))
        self.assertEqual('field', self.field_node_2.var_type)
        self.assertEqual('hasSoldHouse', self.field_node_2.name)
        self.assertEqual('Did you sell a house in 2010?', self.field_node_2.title)

    def test_ast_single_simple_assignment(self):
        self.input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  sellingPrice - privateDebt
        }
        """
        self.parse_result = parse(self.input_string).asList()
        self.form_node = self.parse_result[0]
        self.assertEqual('taxOfficeExample', self.form_node.name)
        self.assertEqual(1, len(self.form_node.children))

        self.field_node_1 = self.form_node.children[0]

        self.assertEqual(0, len(self.field_node_1.children))
        self.assertEqual('field', self.field_node_1.var_type)
        self.assertEqual('valueResidue', self.field_node_1.name)
        self.assertEqual('Value residue:', self.field_node_1.title)

    def test_ast_single_combi_assignment(self):
        self.input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  (sellingPrice - privateDebt) * debt
        }
        """
        self.parse_result = parse(self.input_string).asList()
        self.form_node = self.parse_result[0]
        self.assertEqual('taxOfficeExample', self.form_node.name)
        self.assertEqual(1, len(self.form_node.children))

        self.field_node_1 = self.form_node.children[0]

        self.assertEqual(0, len(self.field_node_1.children))
        self.assertEqual('field', self.field_node_1.var_type)
        self.assertEqual('hasSoldHouse', self.field_node_1.name)
        self.assertEqual('Did you sell a house in 2010?', self.field_node_1.title)

    def test_ast_single_combi_assignment_(self):
        self.input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: money =  sellingPrice - privateDebt * debt  /  salary + interest
        }
        """
        self.parse_result = parse(self.input_string).asList()
        self.form_node = self.parse_result[0]
        self.assertEqual('taxOfficeExample', self.form_node.name)
        self.assertEqual(1, len(self.form_node.children))
        self.field_node_1 = self.form_node.children[0]
        self.assertEqual(0, len(self.field_node_1.children))
        self.assertEqual('field', self.field_node_1.var_type)
        self.assertEqual('hasSoldHouse', self.field_node_1.name)
        self.assertEqual('Did you sell a house in 2010?', self.field_node_1.title)

    def test_ast_if_single_question(self):
        self.input_string = """
            form taxOfficeExample {
                if (hasSoldHouse) {
                    "What was the selling price?"        sellingPrice: money
                }
            }
        """
        self.parse_result = parse(self.input_string).asList()
        self.form_node = self.parse_result[0]
        self.assertEqual('taxOfficeExample', self.form_node.name)
        self.assertEqual(1, len(self.form_node.children))

        self.assertEqual('taxOfficeExample', self.form_node.name)
        self.assertEqual(2, len(self.form_node.children))

        self.field_node_1 = self.form_node.children[0]
        self.field_node_1 = self.form_node.children[1]

    def test_ast_question_with_if_single_question(self):
        self.input_string = """
            form taxOfficeExample {
                "Did you sell a house in 2010?" hasSoldHouse: boolean
                if (hasSoldHouse) {
                    "What was the selling price?"        sellingPrice: money
                }
            }
        """
        self.parse_result = parse(self.input_string).asList()
        self.form_node = self.parse_result[0]
        self.assertEqual('taxOfficeExample', self.form_node.name)
        self.assertEqual(2, len(self.form_node.children))
        self.field_node_1 = self.form_node.children[0]
        self.field_node_1 = self.form_node.children[1]