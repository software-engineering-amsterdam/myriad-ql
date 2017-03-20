from user_interface.ui import FormController,QuestionController, \
                              ComputedQuestionController,ReadOnlyController, \
                              BooleanController,IntegerController, \
                              TextController
import operations.ql
from .ql import *
from tkinter import IntVar,StringVar

class BuildGui(QlAlg):
    def __init__(self, parent, environment):
        self.environment = environment
        self.parent = parent

    def Literal(self, value):
        class _anon():
            execute = None
        return _anon()

    def Form(self, name, block):
        def _register():
            form = FormController(self.parent)
            block.execute(form)
            return form

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Block(self, statements):
        class _anon():
            execute = lambda self, form: [statement.execute(form)
                for _, statement in enumerate(statements)]
        return _anon()

    def Variable(self, name, datatype):
        def _register():
            variable = datatype.execute(name)
            return variable

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
        def _register(form):
            question = QuestionController(
                self.parent, label.execute(), variable.execute())
            form.add_element(question)

        class _anon():
            execute = lambda self, form: _register(form)
        return _anon()

    def ifThen(self, expression, block):
        def _register(form):
            expression.execute()
            block.execute(form)

        class _anon():
            execute = lambda self, form: _register(form)
        return _anon()

    def ComputedQuestion(self, variable, label, expression):
        def _register(form):
            question = ComputedQuestionController(
                self.parent, label.execute(), ReadOnlyController(self.parent, variable.execute()))

            form.add_element(question)

        class _anon():
            execute=lambda self, form: _register(form)
        return _anon()

    def Boolean(self, value=False):
        def _register(key):
            var = IntVar()
            self.environment.update_var(key, var)
            controller = BooleanController(self.parent, var)

            return controller

        class _anon():
            execute = lambda self, key : _register(key)
        return _anon()

    def Money(self, value=False):
        def _register(key):
            var =  IntVar()
            self.environment.update_var(key, var)
            return IntegerController(self.parent, var)

        class _anon():
            execute=lambda self, key: _register(key)
        return _anon()

    def Substraction(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute=lambda self: _register()
        return _anon()

    def UnaryNegation(self, lhs):
        def _register():
            lhs.execute()

        class _anon():
            execute=lambda self: _register()
        return _anon()

    def Addition(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute=lambda self: _register()
        return _anon()

    def GreaterThan(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute=lambda self: _register()
        return _anon()

    def GreaterThanEquals(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute=lambda self: _register()
        return _anon()

    def LessThan(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute=lambda self: _register()
        return _anon()

    def LessThanEquals(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute=lambda self: _register()
        return _anon()

    def Multiplication(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute=lambda self: _register()
        return _anon()

    def LogicalAnd(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute=lambda self: _register()
        return _anon()

    def LogicalOr(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute=lambda self: _register()
        return _anon()

    def Division(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute=lambda self: _register()
        return _anon()

    def Integer(self, value):
        def _register(key):
            var = IntVar()
            self.environment.update_var(key, var)
            return IntegerController(self.parent, var)

        class _anon():
            execute=lambda self, key: _register(key)
        return _anon()

    def StringLiteral(self, value):
        def _register():
            return value

        class _anon():
            execute=lambda self: _register()
        return _anon()

    def String(self, value):
        def _register(key):
            var = StringVar()
            self.environment.update_var(key, var)
            return TextController(self.parent, var)

        class _anon():
            execute=lambda self, key: _register(key)
        return _anon()
