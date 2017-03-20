from .ql import QlAlg,RefVariable,Variable
from .environment import Environment
from ast.ast import *
from ast.base_nodes import *
from ast.field_types import *

class RegisterComputedQuestions(QlAlg):
    def __init__(self, environment):
        self.environment = environment

    def Literal(self, value):
        class _anon():
            execute = None

        return _anon()

    def Form(self, name, block):
        def _register():
            block.execute()
            return

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Block(self, statements):
        class _anon():
            execute = lambda self: [k.execute()
                                    for _, k in enumerate(statements)]
        return _anon()

    def Variable(self, name, datatype):
        class _anon():
            execute = lambda self: Variable(name, datatype)
        return _anon()

    def RefVariable(self, name):
        class _anon():
            execute = lambda self: RefVariable(name)
        return _anon()

    def Question(self, variable, label):
        def _register():
            pass
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
            self.environment.add_computed_question(variable.execute(), label.execute(), expression.execute())
        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Boolean(self, value=False):
        def _register(self):
            return 'boolean'

        class _anon():
            execute = lambda self: _register(self)
        return _anon()

    def UnaryNegation(self, lhs):
        class _anon():
            execute = lambda self: UnaryNegation(lhs.execute())
        return _anon()

    def Money(self, value=False):
        def _register():
            return 'money'

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Substraction(self, lhs, rhs):
        class _anon():
            execute = lambda self: Substraction(lhs.execute(), rhs.execute())
        return _anon()

    def Addition(self, lhs, rhs):
        class _anon():
            execute = lambda self: Addition(lhs.execute(), rhs.execute())
        return _anon()

    def GreaterThan(self, lhs, rhs):
        class _anon():
            execute = lambda self: GreaterThan(lhs.execute(), rhs.execute())
        return _anon()

    def GreaterThanEquals(self, lhs, rhs):
        class _anon():
            execute = lambda self: GreaterThanEquals(lhs.execute(), rhs.execute())
        return _anon()

    def LessThan(self, lhs, rhs):
        class _anon():
            execute = lambda self: LessThan(lhs.execute(), rhs.execute())
        return _anon()

    def LessThanEquals(self, lhs, rhs):
        class _anon():
            execute = lambda self: LessThanEquals(lhs.execute(), rhs.execute())
        return _anon()

    def Equality(self, lhs, rhs):
        class _anon():
            execute = lambda self: Equality(lhs.execute(), rhs.execute())
        return _anon()

    def Inequality(self, lhs, rhs):
        class _anon():
            execute = lambda self: Inequality(lhs.execute(), rhs.execute())
        return _anon()

    def Division(self, lhs, rhs):
        class _anon():
            execute = lambda self: Division(lhs.execute(), rhs.execute())
        return _anon()

    def Multiplication(self, lhs, rhs):
        class _anon():
            execute = lambda self: Multiplication(lhs.execute(), rhs.execute())
        return _anon()

    def LogicalAnd(self, lhs, rhs):
        class _anon():
            execute = lambda self: LogicalAnd(lhs.execute(), rhs.execute())
        return _anon()

    def LogicalOr(self, lhs, rhs):
        class _anon():
            execute = lambda self: LogicalOr(lhs.execute(), rhs.execute())
        return _anon()

    def Integer(self, value):
        def _register():
            return 'integer'

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
            return 'string'

        class _anon():
            execute = lambda self: _register()
        return _anon()
