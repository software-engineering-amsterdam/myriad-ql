from .ql import QlAlg,RefVariable,Variable
from .environment import Environment
from ast.ast import *
from ast.base_nodes import *
from ast.field_types import *

class RegisterConditions(QlAlg):
    def __init__(self, environment):
        self.environment = environment

    def Literal(self, value):
        return lambda: None

    def Form(self, name, block):    
        return lambda: block(None)

    def Block(self, statements):
        return lambda condition: [k(condition)
                                    for _, k in enumerate(statements)]

    def Variable(self, name, datatype):
        return lambda: name

    def RefVariable(self, name):
        return lambda: RefVariable(name)


    def Question(self, variable, label):
        def _statements(condition):
            if condition:
                self.environment.add_conditional(variable(), condition)

        return _statements

    def ifThen(self, expression, block):
        def _statements(condition):
            expression()
            block(expression())

        return _statements

    def ComputedQuestion(self, variable, label, expression):
        def _statements(condition):
            if condition:
                self.environment.add_conditional(variable(), condition)

        return _statements

    def Boolean(self, value=False):
        return lambda: 'boolean'

    def Money(self, value=False):
        return lambda: 'Money'

    def Substraction(self, lhs, rhs):
        return lambda: Substraction(lhs(), rhs())

    def UnaryNegation(self, lhs):
        return lambda: UnaryNegation(lhs())

    def Addition(self, lhs, rhs):
        return  lambda: Addition(lhs(), rhs())


    def GreaterThan(self, lhs, rhs):
        return lambda: GreaterThan(lhs(), rhs())


    def GreaterThanEquals(self, lhs, rhs):
        return lambda: GreaterThanEquals(lhs(), rhs())

    def LessThan(self, lhs, rhs):
        return lambda: LessThan(lhs(), rhs())

    def Equality(self, lhs, rhs):
        return lambda: Equality(lhs(), rhs())

    def Inequality(self, lhs, rhs):
        return lambda: Inequality(lhs(), rhs())


    def Division(self, lhs, rhs):
        return lambda: Division(lhs(), rhs())

    def Multiplication(self, lhs, rhs):
        return lambda: Multiplication(lhs(), rhs())
    
    def LogicalAnd(self, lhs, rhs):
        return lambda: LogicalAnd(lhs(), rhs())

    def LogicalOr(self, lhs, rhs):
        return lambda: LogicalOr(lhs(), rhs())

    def Integer(self, value):
        return lambda: 'integer'
    
    def StringLiteral(self, value):
        return lambda: value

    def String(self, value):
        return lambda:"string"
