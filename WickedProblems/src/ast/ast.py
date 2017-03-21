from .base_nodes import *
from .field_types import *

class BinaryOperation(Node):
    def __init__(self, left_operand, right_operand):
        self.left_operand = left_operand
        self.right_operand = right_operand

    def alg(self, _alg):
        return _alg.BinaryOperation(self.left_operand.alg(_alg),
                                    self.right_operand.alg(_alg))

class UnaryOperation(Node):
    def __init__(self, operand):
        self.operand = operand

class LogicalAnd(BinaryOperation):
    def alg(self, _alg):
        return _alg.LogicalAnd(self.left_operand.alg(_alg),
                               self.right_operand.alg(_alg))

class Multiplication(BinaryOperation):
    def alg(self, _alg):
        return _alg.Multiplication(self.left_operand.alg(_alg),
                                   self.right_operand.alg(_alg))

class Addition(BinaryOperation):
    def alg(self, _alg):
        return _alg.Addition(self.left_operand.alg(_alg),
                             self.right_operand.alg(_alg))

class GreaterThan(BinaryOperation):
    def alg(self, _alg):
        return _alg.GreaterThan(self.left_operand.alg(_alg),
                             self.right_operand.alg(_alg))

class GreaterThanEquals(BinaryOperation):
    def alg(self, _alg):
        return _alg.GreaterThanEquals(self.left_operand.alg(_alg),
                             self.right_operand.alg(_alg))

class Equality(BinaryOperation):
    def alg(self, _alg):
        return _alg.Equality(self.left_operand.alg(_alg),
                             self.right_operand.alg(_alg))

class Inequality(BinaryOperation):
    def alg(self, _alg):
        return _alg.Inequality(self.left_operand.alg(_alg),
                             self.right_operand.alg(_alg))

class Substraction(BinaryOperation):
    def alg(self, _alg):
        return _alg.Substraction(self.left_operand.alg(_alg),
                                 self.right_operand.alg(_alg))

class Division(BinaryOperation):
    def alg(self, _alg):
        return _alg.Division(self.left_operand.alg(_alg),
                             self.right_operand.alg(_alg))

class LessThan(BinaryOperation):
    def alg(self, _alg):
        return _alg.LessThan(self.left_operand.alg(_alg),
                             self.right_operand.alg(_alg))

class LessThanEquals(BinaryOperation):
    def alg(self, _alg):
        return _alg.LessThanEquals(self.left_operand.alg(_alg),
                                   self.right_operand.alg(_alg))

class Assignment(BinaryOperation):
    def alg(self, _alg):
        return _alg.Assignment(self.left_operand.alg(_alg),
                               self.right_operand.alg(_alg))

class LogicalOr(BinaryOperation):
    def alg(self, _alg):
        return _alg.LogicalOr(self.left_operand.alg(_alg),
                              self.right_operand.alg(_alg))

class LogicalNot(UnaryOperation):
    def alg(self, _alg):
        return _alg.UnaryNegation(self.operand.alg(_alg))

class UnaryPlus(UnaryOperation):
    def alg(self, _alg):
        return _alg.UnaryNegation(self.operand.alg(_alg))

class UnaryNegation(UnaryOperation):
    def alg(self, _alg):
        return _alg.UnaryNegation(self.operand.alg(_alg))

class RefVariable(Node):
    def __init__(self, name):
        self.name = name

    def alg(self, _alg):
        return _alg.RefVariable(self.name)

class Variable(Node):
    def __init__(self, name, datatype):
        self.name = name
        self.datatype = datatype

    def alg(self, _alg):
        return _alg.Variable(self.name, self.datatype.alg(_alg))
