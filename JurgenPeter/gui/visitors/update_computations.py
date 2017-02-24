from ql.visitors.evaluator import *


class UpdateComputations:

    def __init__(self, environment):
        self.environment = environment
        self.evaluator = Evaluator(environment)

    def visit(self, node):
        node.accept(self)

    def visit_form(self, node):
        for statement in node.body:
            statement.accept(self)

    def visit_question(self, node):
        pass

    def visit_computed_question(self, node):
        self.environment[node.name] = self.evaluator.visit(node.computation)

    def visit_if_conditional(self, node):
        for statement in node.ifbody:
            statement.accept(self)

    def visit_ifelse_conditional(self, node):
        for statement in node.ifbody:
            statement.accept(self)
        for statement in node.elsebody:
            statement.accept(self)
