from ql.visitors.evaluator import *


class GuiUpdater:

    def __init__(self, form_app, environment):
        self.form_app = form_app
        self.environment = environment
        self.evaluator = Evaluator(environment)

    def update(self, node):
        self.visit(node)

    def visit(self, node, visible=True):
        node.accept(self, visible=visible)

    def visit_form(self, node, visible=True):
        for element in node.body:
            self.visit(element, visible=visible)

    def visit_if_conditional(self, node, visible=True):
        condition = self.evaluator.evaluate(node.condition)
        for element in node.ifbody:
            self.visit(element, visible=(visible and condition))

    def visit_ifelse_conditional(self, node, visible=True):
        condition = self.evaluator.evaluate(node.condition)
        for element in node.ifbody:
            self.visit(element, visible=(visible and condition))
        for element in node.elsebody:
            self.visit(element, visible=not(visible and condition))

    def visit_question(self, node, visible=True):
        if visible:
            self.form_app.show_widget(node.name)
        else:
            self.form_app.hide_widget(node.name)

    def visit_computed_question(self, node, visible=True):
        self.visit_question(node, visible=visible)
        self.form_app.set_widget(node.name, self.environment[node.name])
