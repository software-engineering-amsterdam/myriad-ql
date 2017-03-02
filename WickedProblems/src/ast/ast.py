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
    def __init__(self, identifier, left_child, right_child):
        Node.__init__(self, identifier)
        self.left_child = left_child
        self.right_child = right_child

    def __call__(self, left_child, right_child):
        self.left_child = left_child
        self.right_child = right_child
        return self

    def __iter__(self):
        return iter([self.left_child,self.right_child])

class UnaryOperation(Node):
    def __init__(self, identifier, child):
        Node.__init__(self, identifier)
        self.right_child = child

    def __iter__(self):
        return iter([self.right_child])

# Binary Operations
class Inequality(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "inequality", left_child, right_child)

    def eval(self):
        return bool(self.left_child != self.right_child)

class LogicalAnd(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "logical_and", left_child, right_child)

    def eval(self):
        return bool(self.left_child and self.right_child)

class Multiplication(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "multiplication", left_child, right_child)

    def eval(self):
        return (self.left_child * self.right_child)

class Addition(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "addition", left_child, right_child)

    def eval(self):
        return (self.left_child + self.right_child)

class Substraction(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "substraction", left_child, right_child)

    def eval(self):
        return (self.left_child - self.right_child)

class Division(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "division", left_child, right_child)

    def eval(self):
        return (self.left_child / self.right_child)

class LessThan(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "less_than", left_child, right_child)

    def eval(self):
        return bool(self.left_child < self.right_child)

class LessThanEquals(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "less_than_equals", left_child, right_child)

    def eval(self):
        return bool(self.left_child <= self.right_child)

class Assignment(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "assignment", left_child, right_child)

    def eval(self):
        return self.left_child

class Equality(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "equality", left_child, right_child)

    def eval(self):
        return bool(self.left_child == self.right_child)

class GreaterThan(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "greater_than", left_child, right_child)

    def eval(self):
        return bool(self.left_child > self.right_child)

class GreaterThanEquals(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "greater_than_equals", left_child, right_child)

    def eval(self):
        return bool(self.left_child >= self.right_child)

class LogicalOr(BinaryOperation):
    def __init__(self, left_child, right_child):
        BinaryOperation.__init__(self, "logical_or", left_child, right_child)

    def eval(self):
        return bool(self.left_child or self.right_child)

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
        UnaryOperation.__init__(self, "evaluation", child[0]) # it really sorta is
        self.right_child = child[0]

    def eval(self):
        return self.right_child

class Variable(Node):
    def __init__(self, identifier):
        Node.__init__(self, "variable")
        self._identifier = identifier
        self._value = Undefined()

    def eval(self):
        return self._value

    def __add__(self, other):
        return self._value + other._value

    def __sub__(self, other):
        print(self._value)
        print(other._value)
        return self._value - other._value
    # def __str__(self):
    #     return self._identifier
