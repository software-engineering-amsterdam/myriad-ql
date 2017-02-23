from appJar import gui

from gui.visitors.update_computations import UpdateComputations
from gui.visitors.update_gui import UpdateGUI
from gui.widgets import *
from ql.ast import Datatype
from ql.visitors.questionfinder import QuestionFinder

default_widgets = {
    Datatype.integer: IntegerEntryWidget,
    Datatype.decimal: DecimalEntryWidget,
    Datatype.boolean: CheckBoxWidget,
    Datatype.string:  StringEntryWidget}


class FormApp:

    def __init__(self, ast):
        self.ast = ast
        self.environment = {}
        self.widgets = {}

        self.app = gui(ast.name)
        self.app.bindKey("<KeyPress>", self.update_gui)

        self.questions = QuestionFinder().visit(ast)
        for question in self.questions:
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

        computer = UpdateComputations(self.environment)
        for question in self.questions:
            computer.visit(question)

        UpdateGUI(self, self.environment).visit(self.ast)

    def show_widget(self, name):
        self.widgets[name].show()

    def hide_widget(self, name):
        self.widgets[name].hide()

    def disable_widget(self, name):
        self.widgets[name].disable()

    def set_widget(self, name, value):
        self.widgets[name].set_value(value)
