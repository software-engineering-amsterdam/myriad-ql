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
        class _anon():
            execute = lambda self: None
        return _anon()

    def Form(self, name, block):
        def _register():
            block.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Block(self, statements):
        class _anon():
            execute = lambda self: [statement.execute()
                                    for _, statement in enumerate(statements)]
        return _anon()

    def Variable(self, name, datatype):
        def _register():
            datatype.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def RefVariable(self, name):
        def _register():
            pass

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Question(self, variable, label):
        def _register():
            label.execute()
            variable.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def ifThen(self, expression, block):
        def _register():
            expression.execute()
            block.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def ComputedQuestion(self, variable, label, expression):
        def _register():
            label.execute()
            variable.execute()
            expression.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Boolean(self, value=False):
        def _register():
            pass

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Money(self, value=False):
        def _register():
            pass

        class _anon():
            execute = lambda self : _register()
        return _anon()

    def Substraction(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def UnaryNegation(self, lhs):
        def _register():
            lhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Addition(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def GreaterThan(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def GreaterThanEquals(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def LessThan(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def LessThanEquals(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Equality(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Inequality(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Multiplication(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def LogicalAnd(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def LogicalOr(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Division(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Integer(self, value):
        def _register():
            pass

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def StringLiteral(self, value):
        def _register():
            return value
        class _anon():
            execute = lambda self: _register()
        return _anon()

    def String(self, value):
        def _register():
            return value

        class _anon():
            execute = lambda self, : _register()
        return _anon()
