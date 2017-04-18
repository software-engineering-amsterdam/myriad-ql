from abc import ABCMeta, abstractmethod
from ast.ast import *
from ast.base_nodes import *
from ast.field_types import *


class ExpressionAlg(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def Literal(self, value):
        pass

    @abstractmethod
    def Variable(self, name, datatype):
        pass


class StatementAlg(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def Question(self, variable, label):
        pass

    @abstractmethod
    def ComputedQuestion(self, variable, label):
        pass

    @abstractmethod
    def Block(self, statements):
        pass


class QlAlg(ExpressionAlg, StatementAlg):
    __metaclass__ = ABCMeta

    @abstractmethod
    def Form(self, statements):
        pass


class VoidAlg(object):

    def Literal(self, value):
        return lambda: None

    def Form(self, name, block):
        return block

    def Block(self, statements):
        return lambda : [statement() for _, statement in enumerate(statements)]

    def Variable(self, name, datatype):
        return datatype

    def RefVariable(self, name):
        return lambda: None

    def Question(self, variable, label):
        def _statements():
            label()
            variable()

        return _statements

    def ifThen(self, expression, block):
        def _statements():
            expression()
            block()

        return _statements

    def ComputedQuestion(self, variable, label, expression):
        def _statements():
            label()
            variable()
            expression()

        return _statements

    def Boolean(self, value=False):
        return lambda: value

    def Money(self, value=False):
        return lambda: value

    def Substraction(self, lhs, rhs):
        def _statements():
            lhs()
            rhs()

        return _statements

    def UnaryNegation(self, lhs):
        return lhs

    def Addition(self, lhs, rhs):
        def _statements():
            lhs()
            rhs()

        return _statements

    def GreaterThan(self, lhs, rhs):
        def _statements():
            lhs()
            rhs()

        return _statements

    def GreaterThanEquals(self, lhs, rhs):
        def _statements():
            lhs()
            rhs()

        return _statements

    def LessThan(self, lhs, rhs):
        def _statements():
            lhs()
            rhs()

        return _statements

    def LessThanEquals(self, lhs, rhs):
        def _statements():
            lhs()
            rhs()

        return _statements

    def Equality(self, lhs, rhs):
        def _statements():
            lhs()
            rhs()

        return _statements

    def Inequality(self, lhs, rhs):
        def _statements():
            lhs()
            rhs()

        return _statements

    def Multiplication(self, lhs, rhs):
        def _statements():
            lhs()
            rhs()

        return _statements

    def LogicalAnd(self, lhs, rhs):
        def _statements():
            lhs()
            rhs()

        return _statements

    def LogicalOr(self, lhs, rhs):
        def _statements():
            lhs()
            rhs()

        return _statements

    def Division(self, lhs, rhs):
        def _statements():
            lhs()
            rhs()

        return _statements

    def Integer(self, value):
        return lambda: value

    def StringLiteral(self, value):
        return lambda: value

    def String(self, value):
        return lambda: value
