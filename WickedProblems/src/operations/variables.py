from .ql import QlAlg
from .environment import Environment


class GetVariables(QlAlg):

    def __init__(self, environment):
        self.environment = environment

    def Literal(self, value):
        return lambda: None

    def Form(self, name, block):
        return block

    def Block(self, statements):
        return lambda: [k() for _, k in enumerate(statements)]

    def Variable(self, name, datatype):
        def _statements():
            self.environment.add_var((name, datatype()))
            return (name, datatype())

        return _statements

    def RefVariable(self, name):
        return lambda: self.environment.add_ref((name))

    def Question(self, variable, label):
        return lambda: self.environment.add_question(variable(), label())

    def ifThen(self, expression, block):
        def _statements():
            expression()
            block()

        return _statements

    def ComputedQuestion(self, variable, label, expression):
        def _statements():
            variable()
            label()
            expression()

        return _statements

    def Boolean(self, value=False):
        return lambda: 'boolean'

    def UnaryNegation(self, value=False):
        return lambda: 'negation'

    def Money(self, value=False):
        return lambda: 'money'

    def Substraction(self, lhs, rhs):
        def _statements():
            lhs()
            rhs()

        return _statements

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

    def Division(self, lhs, rhs):
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

    def Integer(self, value):
        return lambda: 'integer'

    def StringLiteral(self, value):
        return lambda: value

    def String(self, value):
        return lambda: 'string'
