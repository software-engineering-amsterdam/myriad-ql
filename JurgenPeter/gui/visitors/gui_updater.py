from ql.visitors.evaluator import *


class GuiUpdater:

    def __init__(self, form_app, environment):
        self.form_app = form_app
        self.environment = environment
        self.evaluator = Evaluator(environment)

    def update(self, node):
        self.visit(node, True)

    def visit(self, node, visible):
        node.accept(self, visible)

    def visit_form(self, node, visible):
        for element in node.body:
            self.visit(element, visible)

    def visit_if_conditional(self, node, visible):
        condition = self.evaluator.evaluate(node.condition)
        for element in node.ifbody:
            self.visit(element, (visible and condition))

    def visit_ifelse_conditional(self, node, visible):
        condition = self.evaluator.evaluate(node.condition)
        for element in node.ifbody:
            self.visit(element, (visible and condition))
        for element in node.elsebody:
            self.visit(element, not (visible and condition))

    def visit_question(self, node, visible):
        if visible:
            self.form_app.show_widget(node.name)
        else:
            self.form_app.hide_widget(node.name)

    def visit_computed_question(self, node, visible):
        self.visit_question(node, visible)
        self.form_app.set_widget(node.name, self.environment[node.name])
