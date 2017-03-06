from ql.visitors.evaluator import *


class ComputationUpdater:

    def __init__(self, environment):
        self.environment = environment
        self.evaluator = Evaluator(environment)

    def visit(self, node):
        node.accept(self)

    def visit_form(self, node):
        for element in node.body:
            element.accept(self)

    def visit_question(self, node):
        pass

    def visit_computed_question(self, node):
        self.environment[node.name] = self.evaluator.visit(node.computation)

    def visit_if_conditional(self, node):
        for element in node.ifbody:
            element.accept(self)

    def visit_ifelse_conditional(self, node):
        for element in node.ifbody:
            element.accept(self)
        for element in node.elsebody:
            element.accept(self)
# TODO: fixpoint
