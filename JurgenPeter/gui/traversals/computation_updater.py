from misc.visitor import Visitor
from ql.traversals.evaluator import Evaluator


class ComputationUpdater(Visitor):

    def __init__(self, environment):
        self.environment = environment
        self.evaluator = Evaluator(environment)

    def update(self, node):
        original = self.hash_dictionary(self.environment)
        self.visit(node)
        return original != self.hash_dictionary(self.environment)

    def visit_form(self, node):
        for element in node.body:
            self.visit(element)

    def visit_question(self, node):
        pass

    def visit_computed_question(self, node):
        self.environment[node.name] = self.evaluator.evaluate(node.computation)

    def visit_if_conditional(self, node):
        for element in node.ifbody:
            self.visit(element)

    def visit_ifelse_conditional(self, node):
        self.visit_if_conditional(node)
        for element in node.elsebody:
            self.visit(element)

    @staticmethod
    def hash_dictionary(dictionary):
        return hash(frozenset(dictionary.items()))
