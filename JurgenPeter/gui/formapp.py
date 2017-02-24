from appJar import gui

from ql.ast import Datatype
from ql.visitors.question_finder import QuestionFinder
from gui.widgets import *
from gui.visitors.update_computations import UpdateComputations
from gui.visitors.update_gui import UpdateGUI


default_widgets = {
    Datatype.integer: IntegerEntryWidget,
    Datatype.decimal: DecimalEntryWidget,
    Datatype.boolean: CheckBoxWidget,
    Datatype.string:  EntryWidget}


class FormApp:

    def __init__(self, ast):
        self.ast = ast
        self.environment = {}
        self.widgets = {}

        self.app = gui(ast.name)
        self.app.bindKey("<KeyPress>", self.update_gui)

        questions = QuestionFinder().visit(ast)

        # TODO merge visitor with widget creation
        for question in questions:
            widget = default_widgets[question.datatype](self.app, question)
            widget.set_listener(self.update_gui)
            self.widgets[question.name] = widget
            self.environment[question.name] = None

    def start(self):
        self.update_gui(None)
        self.app.go()

    def stop(self):
        self.app.stop()

    def update_gui(self, _):
        for name, widget in self.widgets.items():
            self.environment[name] = widget.get_value()
        UpdateComputations(self.environment).visit(self.ast)
        UpdateGUI(self, self.environment).visit(self.ast)

    def show_widget(self, name):
        self.widgets[name].show()

    def hide_widget(self, name):
        self.widgets[name].hide()

    def disable_widget(self, name):
        self.widgets[name].disable()

    def set_widget(self, name, value):
        self.widgets[name].set_value(value)
