from ql.visitors.evaluator import *


class GuiUpdater:

    def __init__(self, form_app, environment):
        self.form_app = form_app
        self.environment = environment
        self.evaluator = Evaluator(environment)
        self.previous_conditions = []

    def push_condition(self, element):
        self.previous_conditions.append(element)

    def pop_condition(self):
        self.previous_conditions.pop()

    def visit(self, node):
        node.accept(self)

    def visit_form(self, node):
        for element in node.body:
            element.accept(self)

    def visit_if_conditional(self, node):
        condition = self.evaluator.visit(node.condition)
        self.push_condition(condition)
        for element in node.ifbody:
            element.accept(self)
        self.pop_condition()

    def visit_ifelse_conditional(self, node):
        condition = self.evaluator.visit(node.condition)
        self.push_condition(condition)
        for element in node.ifbody:
            element.accept(self)
        self.pop_condition()

        self.push_condition(not condition)
        for element in node.elsebody:
            element.accept(self)
        self.pop_condition()

    def visit_question(self, node):
        if all(self.previous_conditions):
            self.form_app.show_widget(node.name)
        else:
            self.form_app.hide_widget(node.name)

    def visit_computed_question(self, node):
        self.visit_question(node)
        self.form_app.set_widget(node.name, self.environment[node.name])
