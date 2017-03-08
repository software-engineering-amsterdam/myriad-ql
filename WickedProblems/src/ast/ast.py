from .base_nodes import *
from .field_types import *
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


'''
https://www.codingunit.com/unary-and-binary-operator-table
'''
class BinaryOperation(Node):
    def __init__(self, identifier, left_hand_side, right_hand_side):
        Node.__init__(self, identifier)
        self.left_hand_side = left_hand_side
        self.right_hand_side = right_hand_side

    def __call__(self, left_hand_side, right_hand_side):
        self.left_hand_side = left_hand_side
        self.right_hand_side = right_hand_side
        return self

    def __iter__(self):
        return iter([self.left_hand_side,self.right_hand_side])

class UnaryOperation(Node):
    def __init__(self, identifier, child):
        Node.__init__(self, identifier)
        self.right_hand_side = child

    def __iter__(self):
        return iter([self.right_hand_side])

# Binary Operations
class Inequality(BinaryOperation):
    def __init__(self, left_hand_side, right_hand_side):
        BinaryOperation.__init__(self, "inequality", left_hand_side,
                                 right_hand_side)
    def eval(self):
        return bool(self.left_hand_side != self.right_hand_side)

class LogicalAnd(BinaryOperation):
    def __init__(self, left_hand_side, right_hand_side):
        BinaryOperation.__init__(self, "logical_and", left_hand_side,
                                 right_hand_side)
    def eval(self):
        return bool(self.left_hand_side and self.right_hand_side)

class Multiplication(BinaryOperation):
    def __init__(self, left_hand_side, right_hand_side):
        BinaryOperation.__init__(self, "multiplication", left_hand_side,
                                 right_hand_side)

    def eval(self):
        return (self.left_hand_side * self.right_hand_side)

class Addition(BinaryOperation):
    def __init__(self, left_hand_side, right_hand_side):
        BinaryOperation.__init__(self, "addition", left_hand_side,
                                 right_hand_side)
    def eval(self):
        return (self.left_hand_side + self.right_hand_side)

class Substraction(BinaryOperation):
    def __init__(self, left_hand_side, right_hand_side):
        BinaryOperation.__init__(self, "substraction", left_hand_side,
                                 right_hand_side)
    def eval(self):
        return (self.left_hand_side - self.right_hand_side)

class Division(BinaryOperation):
    def __init__(self, left_hand_side, right_hand_side):
        BinaryOperation.__init__(self, "division", left_hand_side,
                                 right_hand_side)
    def eval(self):
        return (self.left_hand_side / self.right_hand_side)

class LessThan(BinaryOperation):
    def __init__(self, left_hand_side, right_hand_side):
        BinaryOperation.__init__(self, "less_than", left_hand_side,
                                 right_hand_side)
    def eval(self):
        return bool(self.left_hand_side < self.right_hand_side)

class LessThanEquals(BinaryOperation):
    def __init__(self, left_hand_side, right_hand_side):
        BinaryOperation.__init__(self, "less_than_equals", left_hand_side,
                                 right_hand_side)

    def eval(self):
        return bool(self.left_hand_side <= self.right_hand_side)

class Assignment(BinaryOperation):
    def __init__(self, left_hand_side, right_hand_side):
        BinaryOperation.__init__(self, "assignment", left_hand_side,
                                 right_hand_side)
    def eval(self):
        return self.left_hand_side

class Equality(BinaryOperation):
    def __init__(self, left_hand_side, right_hand_side):
        BinaryOperation.__init__(self, "equality", left_hand_side,
                                 right_hand_side)
    def eval(self):
        return bool(self.left_hand_side == self.right_hand_side)

class GreaterThan(BinaryOperation):
    def __init__(self, left_hand_side, right_hand_side):
        BinaryOperation.__init__(self, "greater_than", left_hand_side,
                                 right_hand_side)
    def eval(self):
        return bool(self.left_hand_side > self.right_hand_side)

class GreaterThanEquals(BinaryOperation):
    def __init__(self, left_hand_side, right_hand_side):
        BinaryOperation.__init__(self, "greater_than_equals", left_hand_side,
                                 right_hand_side)
    def eval(self):
        return bool(self.left_hand_side >= self.right_hand_side)

class LogicalOr(BinaryOperation):
    def __init__(self, left_hand_side, right_hand_side):
        BinaryOperation.__init__(self, "logical_or", left_hand_side,
                                 right_hand_side)
    def eval(self):
        return bool(self.left_hand_side or self.right_hand_side)

# Unary Operations
class LogicalNot(UnaryOperation):
    def __init__(self, child):
        UnaryOperation.__init__(self, "logical_not", child)

    def eval(self):
        return not child

class UnaryPlus(UnaryOperation):
    def __init__(self, child):
        UnaryOperation.__init__(self, "unary_plus", child)

class UnaryNegation(UnaryOperation):
    def __init__(self, child):
        UnaryOperation.__init__(self, "unary_negation", child)

    def eval(self):
        return -child

class Evaluation(Node):
    def __init__(self, child):
        UnaryOperation.__init__(self, "evaluation", child[0])
        self.right_hand_side = child[0]

    def eval(self):
        return self.right_hand_side

class Variable(Node):
    def __init__(self, identifier):
        Node.__init__(self, "variable")
        self._identifier = identifier
        self._value = Undefined()

    def __str__(self):
        return "{}".format(self._value)

    def __repr__(self):
        return self

    def eval(self):
        return self._value

    def __add__(self, other):
        return self.eval() + other.eval()

    def __sub__(self, other):
        return self.eval() - other.eval()

    def __mul__(self, other):
        return self.eval() * other.eval()

    def __truediv__(self, other):
        return self.eval() / other.eval()

    def __or__(self, other):
        return (self.eval() | other.eval())

    def __and__(self, other):
        return (self.eval() & other.eval())

    def __invert__(self):
        return ~self.eval()

    def __lt__(self, other):
        return self.eval() < other.eval()

    def __gt__(self, other):
        return self.eval() > other.eval()

    def __ge__(self, other):
        return self.eval() >= other.eval()

    def ___le__(self, other):
        return self.eval() <= other.eval()

    def __ne__(self, other):
        return self.eval() != other.eval()

    def __eq__(self, other):
        return self.eval() == other.eval()
