from ql.visitors.evaluator import *


class UpdateGUI:

    def __init__(self, form_app, environment):
        self.form_app = form_app
        self.environment = environment
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
            self.form_app.show_widget(node.name)
        else:
            self.form_app.hide_widget(node.name)

    def visit_computed_question(self, node):
        self.form_app.disable_widget(node.name)
        self.form_app.set_widget(node.name, self.environment[node.name])
        self.visit_question(node)
