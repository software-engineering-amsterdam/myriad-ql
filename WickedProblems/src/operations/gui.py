from user_interface.ui import FormController, QuestionController, \
    ComputedQuestionController, ReadOnlyController, \
    BooleanController, IntegerController, \
    TextController

from .ql import *
from tkinter import IntVar, StringVar


class BuildGui(QlAlg):

    def __init__(self, parent, environment):
        self.environment = environment
        self.parent = parent

    def Literal(self, value):
        return None

    def Form(self, name, block):
        def _statements():
            form = FormController(self.parent)
            block(form)
            return form
        return _statements

    def Block(self, statements):
        return lambda form: [statement(form)
                             for _, statement in enumerate(statements)]

    def Variable(self, name, datatype):
        def _statements():
            variable = datatype(name)
            return variable

        return _statements

    def RefVariable(self, name):
        def _statements():
            self.environment.add_ref((name))

        return  _statements

    def Question(self, variable, label):
        def _statements(form):
            question = QuestionController(
                self.parent, label(), variable())
            form.add_element(question)

        return _statements

    def ifThen(self, expression, block):
        def _statements(form):
            expression()
            block(form)

        return _statements

    def ComputedQuestion(self, variable, label, expression):
        def _statements(form):
            question = ComputedQuestionController(
                self.parent, label(), ReadOnlyController(self.parent, variable()))
            form.add_element(question)

        return _statements

    def Boolean(self, value=False):
        def _statements(key):
            var = IntVar()
            self.environment.update_var(key, var)
            controller = BooleanController(self.parent, var)
            return controller

        return _statements

    def Money(self, value=False):
        def _statements(key):
            var = IntVar()
            self.environment.update_var(key, var)
            return IntegerController(self.parent, var)

        return _statements

    def Substraction(self, lhs, rhs):
        def _statements():
            lhs()
            rhs()

        return _statements

    def UnaryNegation(self, lhs):
        def _statements():
            lhs()

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
        def _statements(key):
            var = IntVar()
            self.environment.update_var(key, var)
            return IntegerController(self.parent, var)

        return _statements

    def StringLiteral(self, value):
        def _statements():
            return value

        return _statements

    def String(self, value):
        def _statements(key):
            var = StringVar()
            self.environment.update_var(key, var)
            return TextController(self.parent, var)

        return _statements
