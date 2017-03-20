from .ql import ExpressionAlg

class Eval(ExpressionAlg):
    def __init__(self, environment):
        self.environment = environment

    def RefVariable(self, name):
        class _anon():
            execute = lambda _ : self.environment.get_value(name)
        return _anon()

    def Literal(self):
        pass

    def String(self, value):
        class _anon():
            execute = lambda self: str(value)
        return _anon()

    def Integer(self, value):
        class _anon():
            execute = lambda self: int(value)
        return _anon()

    def Money(self, value):
        class _anon():
            execute = lambda self: value
        return _anon()

    def Boolean(self, value):
        class _anon():
            execute = lambda self: value
        return _anon()

    def Substraction(self, lhs, rhs):
        class _anon():
            execute = lambda self: lhs.execute() - rhs.execute()
        return _anon()

    def Addition(self, lhs, rhs):
        class _anon():
            execute = lambda self: lhs.execute() + rhs.execute()
        return _anon()

    def GreaterThan(self, lhs, rhs):
        class _anon():
            execute = lambda self: 1 if lhs.execute() > rhs.execute() else 0
        return _anon()

    def Division(self, lhs, rhs):
        class _anon():
            execute = lambda self: lhs.execute() / rhs.execute() if (lhs.execute() != 0 and rhs.execute() != 0) else 0
        return _anon()

    def Multiplication(self, lhs, rhs):
        class _anon():
            execute = lambda self: lhs.execute() * rhs.execute() if (lhs.execute() != 0 and rhs.execute() != 0) else 0
        return _anon()

    def LogicalAnd(self, lhs, rhs):
        class _anon():
            execute = lambda self: lhs.execute() and rhs.execute()
        return _anon()

    def LogicalOr(self, lhs, rhs):
        class _anon():
            execute = lambda self: lhs.execute() or rhs.execute()
        return _anon()

    def UnaryNegation(self, lhs):
        class _anon():
            execute = lambda self: True if lhs.execute() == 0 else False
        return _anon()
