import sys


class Node(object):
    children = []
    nodes = {
        "node": "Node",
        "question": "Question",
        "computed_value": "Exp",
        "question_value": "DataType",
        "exp": "Exp",
        "assignment": "Assignment",
        "assignment": "Assignment",
        "data_type": "DataType",
        "variable": "Variable",
        "boolean": "boolean",
        "string": "String"
    }

    def __str__(self):
        return dir(self)

    def parseNodes(self, sub_tree):
        children = []
        for node_type, element in sub_tree.items():
            # print node_type, element
            children.append(self.toNode(node_type, element))

    def toNode(self, node_type, element):
        node_type = self.getNodeClass(node_type)
        print node_type
        if element == "None":
            return Node()
        else:
            node_class = getattr(sys.modules[__name__], node_type)
            print element
            return node_class(**element)

    def getNodeClass(self, node_type):
        node_class = None
        if node_type in self.nodes:
            node_class = self.nodes[node_type]
        return node_class


class Exp(Node):

    def __init__(self, lit):
        self.value = lit


class MathOp(Node):

    def __init__(self, lit):
        self.value = lit


class Assignment(Node):

    def __init__(self, data_type, exp):
        self.value = exp
        self.data_type = data_type


class Money(Node):

    def __init__(self, lit):
        self.value = lit


class DataType(Node):

    def __init__(self, lit):
        self.value = lit


class Boolean(Node):

    def __init__(self, lit):
        self.value = lit


class String(Node):

    def __init__(self, lit):
        self.value = lit


class Variable(Node):

    def __init__(self, lit):
        self.value = lit


class Question(Node):

    def __init__(self, label, variable,  data_type, value=None, computed_value=None):
        self.label = self.toNode("string", {"lit": label})
        self.variable = self.toNode("variable", {"lit": label})
        self.computed_value = self.parseNodes(computed_value)
        self.data_type = self.toNode("data_type", data_type)
