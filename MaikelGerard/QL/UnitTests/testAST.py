import unittest

from QL import AST
from QL.UnitTests.testUtils import create_node


class TestAST(unittest.TestCase):

    def test_create_Node(self):
        node = create_node(AST.BoolNode, "true")
        self.assertEqual(node.node_type, "boolean")
        self.assertEqual(node.val, True)
        self.assertEqual(str(node), "True")

    def test_add_child(self):
        node1 = create_node(AST.BoolNode, "true")
        node2 = create_node(AST.IntNode, "100")
        node1.add_child(node2)
        self.assertEqual(node2, node1.children[0])
        self.assertTrue(node2 in node1.children)

    def test_eq(self):
        node1 = create_node(AST.BoolNode, "true")
        node2 = create_node(AST.BoolNode, "true")
        self.assertEqual(node1, node2)

        node3 = create_node(AST.BoolNode, "false")
        self.assertNotEqual(node3, node2)
        self.assertNotEqual(node3, node1)

if __name__ == '__main__':
    unittest.main()
