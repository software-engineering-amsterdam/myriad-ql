'''
1.  If the current token is a '(', add a new node as the left child of the
    current node, and descend to the left child.

2.  If the current token is in the list ['+','-','/','*'], set the root value
    of the current node to the operator represented by the current token.
    Add a new node as the right child of the current node and descend to
    the right child.

3.  If the current token is a number, set the root value of the current node
    to the number and return to the parent.

4.  If the current token is a ')', go to the parent of the current node.
'''

class Node(object):
    indent = 0
    # Base class of all nodes
    def __init__(self, identifier):
        # variables are now internal (rather than private)
        self._children = []
        self._identifier = identifier

    def add_child(self, child):
        if child and not isinstance(child, Node):
            raise TypeError("Child is not an instance of Node")
        self._children.append(child)

class Root(Node):
    def __init__(self, identifier, children):
        Node.__init__(self, "form")
        self._identifier = identifier
        self._children = children

    def __str__(self):
        __ret = "{}{} \"{}\", Children: {}\n".format("\t" * Node.indent,
                                                    self.__class__.__name__,
                                                    self._identifier,
                                                    len(self._children))
        Node.indent += 1
        for __child in self._children:
            __ret += "{}{}".format("\t" * Node.indent,__child)
        Node.indent -= 1
        return __ret

    __repr__ = __str__

class Question(Node):
    def __init__(self, text, identifier, field_type):
        Node.__init__(self, "question")
        self._text = text
        self._identifier = identifier
        self._field_type = field_type

    def __str__(self):
        return "{} \"{}\", Field Type: {}, Question: \"{}\"\n".format(
                                                    self.__class__.__name__,
                                                    self._identifier,
                                                    self._field_type,
                                                    self._text)
    __repr__ = __str__

class Conditional(Node):
    def __init__(self, evaluation, children):
        Node.__init__(self, "conditional")
        self._evaluation = evaluation
        self._children = children

    def __str__(self):
        __ret = "{} Evaluation: \"{}\", Children: {}\n".format(
                                                    self.__class__.__name__,
                                                    self._evaluation,
                                                    len(self._children))
        Node.indent += 1
        for __child in self._children:
            __ret += "{}{}\n".format("\t" * Node.indent, __child)
        Node.indent -= 1
        return __ret

    __repr__ = __str__

class Statement(Node):
    def __init__(self, text, identifier, field_type, children):
        Node.__init__(self, "statement")
        self._identifier = identifier
        self._field_type = field_type
        self._children = children

    def __str__(self):
        __ret = "{} \"{}\", Field Type: {} Children: {}\n".format(
                                                self.__class__.__name__,
                                                self._identifier,
                                                self._field_type,
                                                len(self._children))
        Node.indent += 1
        for __child in self._children:
            __ret += "{}{}\n".format("\t" * Node.indent, __child)
        Node.indent -= 1
        return __ret

    __repr__ = __str__
'''
https://www.codingunit.com/unary-and-binary-operator-table
'''
class BinaryOperation(Node):
    def __init__(self, identifier, left_child, right_child):
        Node.__init__(self, identifier)
        self.left_child = left_child
        self.right_child = right_child

    def __call__(self, left_child, right_child):
        self.left_child = left_child
        self.right_child = right_child
        return self

class UnaryOperation(Node):
    def __init__(self, identifier, child):
        Node.__init__(self, identifier)
        self.right_child = child

# Binary Operations
class Inequality(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "inequality", left_child, right_child)

class LogicalAnd(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "logical_and", left_child, right_child)

class Multiplication(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "multiplication", left_child, right_child)

class Addition(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "addition", left_child, right_child)

class Substraction(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "substraction", left_child, right_child)

    __call__ = __init__

class Division(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "division", left_child, right_child)

class LessThan(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "less_than", left_child, right_child)

class LessThanEquals(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "less_than_equals", left_child, right_child)

class Assignment(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "assignment", left_child, right_child)

class Equality(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "equality", left_child, right_child)

class GreaterThan(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "greater_than", left_child, right_child)

class GreaterThanEquals(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "greater_than_equals", left_child, right_child)

class LogicalOr(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "logical_or", left_child, right_child)

    __call__ = __init__

# Unary Operations
class LogicalNot(UnaryOperation):
    def __init__(self, child):
        UnaryOperation.__init__(self, "logical_not", child)

class UnaryPlus(UnaryOperation):
    def __init__(self, child):
        UnaryOperation.__init__(self, "unary_plus", child)

class UnaryNegation(UnaryOperation):
    def __init__(self, child):
        UnaryOperation.__init__(self, "unary_negation", child)

class Evaluation(Node):
    def __init__(self, child):
        UnaryOperation.__init__(self, "evaluation", child[0]) # it really sorta is

class Variable(Node):
    def __init__(self, value):
        Node.__init__(self, "variable")
        self._identifier = value

    # def __str__(self):
    #     return "Variable Object: " + self._identifier
    #
    # __repr__ = __str__

class FieldType(Node):
    def __init__(self):
        Node.__init__(self, "field_type")

class Boolean(FieldType):
    def __init__(self):
        FieldType.__init__(self)

class Integer(FieldType):
    def __init__(self):
        FieldType.__init__(self)

class Data(FieldType):
    def __init__(self):
        FieldType.__init__(self)

class Decimal(FieldType):
    def __init__(self):
        FieldType.__init__(self)

class Money(FieldType):
    def __init__(self):
        FieldType.__init__(self)
