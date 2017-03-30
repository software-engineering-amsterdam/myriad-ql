from misc.visitor import Visitor
from ql.traversals.evaluator import Evaluator


class QlGuiUpdater(Visitor):

    def __init__(self, app, environment):
        self.app = app
        self.environment = environment
        self.evaluator = Evaluator(environment)
        self.visible_questions = []

    def update(self, node):
        self.visit(node, True)
        return self.visible_questions

    def visit_form(self, node, visible):
        for element in node.body:
            self.visit(element, visible)

    def visit_if_conditional(self, node, visible):
        condition = self.evaluator.evaluate(node.condition)
        for element in node.ifbody:
            self.visit(element, (visible and condition))
        return condition

    def visit_ifelse_conditional(self, node, visible):
        condition = self.visit_if_conditional(node, visible)
        for element in node.elsebody:
            self.visit(element, not (visible and condition))

    def visit_question(self, node, visible):
        if visible:
            self.app.show_widget(node.name)
            self.visible_questions.append(node.name)
        else:
            self.app.hide_widget(node.name)

    def visit_computed_question(self, node, visible):
        self.visit_question(node, visible)
        self.app.set_widget(node.name, self.environment[node.name])
