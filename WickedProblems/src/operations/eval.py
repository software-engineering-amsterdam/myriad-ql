from .ql import ExpressionAlg


class Eval(ExpressionAlg):

    def __init__(self, environment):
        self.environment = environment

    def RefVariable(self, name):
        return lambda : self.environment.get_value(name)

    def Literal(self):
        pass

    def Variable(self):
        pass

    def String(self, value):
        return lambda : str(value)

    def Integer(self, value):
        return lambda : int(value)

    def Money(self, value):
        return lambda : value

    def Boolean(self, value):
        return lambda : value

    def Substraction(self, lhs, rhs):
        return lambda : lhs() - rhs()

    def Addition(self, lhs, rhs):

        return lambda : lhs() + rhs()

    def GreaterThan(self, lhs, rhs):
        return lambda : 1 if lhs() > rhs() else 0

    def GreaterThanEquals(self, lhs, rhs):
        return lambda : 1 if lhs() >= rhs() else 0

    def LessThan(self, lhs, rhs):
        return lambda : 1 if lhs() < rhs() else 0

    def LessThanEquals(self, lhs, rhs):
        return lambda : 1 if lhs() <= rhs() else 0

    def Equality(self, lhs, rhs):
        return lambda : 1 if lhs() == rhs() else 0

    def Inequality(self, lhs, rhs):
        return lambda : 1 if lhs() != rhs() else 0

    def Division(self, lhs, rhs):
        return lambda : lhs() / rhs() if (lhs() != 0 and rhs() != 0) else 0

    def Multiplication(self, lhs, rhs):
        return lambda : lhs() * rhs() if (lhs() != 0 and rhs() != 0) else 0

    def LogicalAnd(self, lhs, rhs):
        return lambda : lhs() and rhs()

    def LogicalOr(self, lhs, rhs):
        return lambda : lhs() or rhs()

    def UnaryNegation(self, lhs):
        return lambda : True if lhs() == 0 else False
