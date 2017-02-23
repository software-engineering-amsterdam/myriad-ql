from gui.formapp import *
from ql.visitors.evaluator import *

class UpdateGUI:

    def __init__(self, form_app, environment):
        self.form_app = form_app
        self.evaluator = Evaluator(environment)
        self.stack = []

    def push(self, element):
        self.stack.append(element)

    def pop(self):
        self.stack.pop()

    def read(self):
        if self.stack:
            return self.stack[-1]
        return True

    def visit(self, node):
        node.accept(self)

    def visit_form(self, node):
        for statement in node.body:
            statement.accept(self)

    def visit_if_conditional(self, node):
        print(self.read())
        if self.read():
            self.push(self.evaluator.visit(node.condition))
        else:
            self.push(False)

        for statement in node.ifbody:
            statement.accept(self)

        self.pop()

    def visit_ifelse_conditional(self, node):
        if self.read():
            state = self.evaluator.visit(node.condition)
        else:
            state = False

        print(state)
        self.push(state)

        for statement in node.ifbody:
            statement.accept(self)
        self.pop()
        self.push(not state)
        for statement in node.elsebody:
            statement.accept(self)
        self.pop()

    def visit_question(self, node):
        if self.read():
            self.form_app.show(node.name)
        else:
            self.form_app.hide(node.name)

    def visit_computed_question(self, node):
        if self.read():
            self.form_app.show(node.name)
        else:
            self.form_app.hide(node.name)

# form_appJar widgets:
# WINDOW = 0
# LABEL = 1
# ENTRY = 2
# BUTTON = 3
# CHECKBOX = 4
# SCALE = 5
# RADIOBUTTON = 6
# LISTBOX = 7
# MESSAGE = 8
# SPIN = 9
# OPTION = 10
# TEXTAREA = 11
# LINK = 12
# METER = 13
# IMAGE = 14
# PIECHART = 15
# PROPERTIES = 16
# GRID = 17
