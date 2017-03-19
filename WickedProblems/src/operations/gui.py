import ql
from ast import *
from Tkinter import *
import user_interface.ui
import operations.ql
import user_interface.ui as ui


class BuildGui(ql.QlAlg):

    def __init__(self, parent, environment):
        self.environment = environment
        self.parent = parent

    def Literal(self, value):
        class _anon():
            execute = None

        return _anon()

    def Form(self, name, block):
        def _register():
            form = ui.FormController(self.parent)
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
            
            variable = datatype.execute( name)
        
            return variable

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def RefVariable(self, name):
        def _register():
            self.environment.add_ref((name))
            print self.environment.is_registerd(name)

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Question(self, variable, label):
        def _register(form):
            # self.environment.add_question(variable.execute(),
            # label.execute())
            question = ui.QuestionController(
                None, label.execute(), variable.execute())
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
            variable.execute()
            label.execute()
            # expression.execute()
            question = ui.ComputedQuestionController(
                None, label.execute(), ui.ReadOnlyController(None, StringVar()))
            form.add_element(question)

        class _anon():
            execute=lambda self, form: _register(form)
        return _anon()


    def Boolean(self, value=False):
        def _register(key):
            #get variable to store
            var = IntVar()
            self.environment.update_var(key, var)
            controller = ui.BooleanController(None, var)

            return controller

        class _anon():
            execute = lambda self, key : _register(key)
        return _anon()

    def Money(self, value=False):
        def _register(key):
            var =  IntVar()
            self.environment.update_var(key, var)
            return ui.IntegerController(None, var)

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

    def Integer(self, value):
        def _register(key):
            var = IntVar()
            self.environment.update_var(key, var)
            return ui.IntegerController(None, var)

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
            return ui.TextController(None, var)

        class _anon():
            execute=lambda self, key: _register(key)
        return _anon()
