from .ql import QlAlg
from .environment import Environment

class GetVariables(QlAlg):
    def __init__(self, environment_vars):
        self.environment = Environment()

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
        def _register():
            self.environment.add_var((name, datatype.execute()))
            return (name, datatype.execute())

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def RefVariable(self, name):
        def _register():
            self.environment.add_ref((name))

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Question(self, variable, label):
        def _register():
            self.environment.add_question(variable.execute(), label.execute())

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

            variable.execute()
            label.execute()
            expression.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Boolean(self, value=False):
        def _register(self):
            return 'boolean'

        class _anon():
            execute = lambda self: _register(self)
        return _anon()

    def UnaryNegation(self, value=False):
        def _register(self):
            return 'negation'

        class _anon():
            execute = lambda self: _register(self)
        return _anon()

    def Money(self, value=False):
        def _register():
            return 'money'

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Substraction(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

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

    def Division(self, lhs, rhs):
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
