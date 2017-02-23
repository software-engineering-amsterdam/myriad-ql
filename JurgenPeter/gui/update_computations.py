from ql.visitors.evaluator import *


class UpdateComputations:

    def __init__(self, environment):
        self.environment = environment
        self.evaluator = Evaluator(environment)

    def visit(self, node):
        node.accept(self)

    def visit_question(self, node):
        pass

    def visit_computed_question(self, node):
        self.environment[node.name] = self.evaluator.visit(node.computation)