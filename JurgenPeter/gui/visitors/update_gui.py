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

    def visit(self, node):
        node.accept(self)

    def visit_form(self, node):
        for statement in node.body:
            statement.accept(self)

    def visit_if_conditional(self, node):
        state = self.evaluator.visit(node.condition)
        self.push(state)
        for statement in node.ifbody:
            statement.accept(self)
        self.pop()

    def visit_ifelse_conditional(self, node):
        state = self.evaluator.visit(node.condition)
        self.push(state)
        for statement in node.ifbody:
            statement.accept(self)
        self.pop()

        self.push(not state)
        for statement in node.elsebody:
            statement.accept(self)
        self.pop()

    def visit_question(self, node):
        if all(self.stack):
            self.form_app.show_widget(node.name)
        else:
            self.form_app.hide_widget(node.name)

    def visit_computed_question(self, node):
        self.visit_question(node)
        self.form_app.set_widget(node.name, self.environment[node.name])
