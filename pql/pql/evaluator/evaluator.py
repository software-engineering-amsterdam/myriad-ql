# coding=utf-8
from pql.visitor.visitor import Visitor


class Evaluator(Visitor):
    def __init__(self, environment):
        self.environment = environment

    def or_(self, node):
        pass

    def value(self, node):
        return node.value

    def greater_inclusive(self, node):
        pass

    def lower_exclusive(self, node):
        pass

    def and_(self, node):
        pass

    def greater_exclusive(self, node):
        pass

    def division(self, node):
        pass

    def form(self, node):
        pass

    def subtraction(self, node):
        pass

    def visit(self, pql_ast):
        pass

    def conditional(self, node):
        pass

    def lower_inclusive(self, node):
        pass

    def field(self, node):
        pass

    def identifier(self, node):
        pass

    def equality(self, node):
        pass

    def addition(self, node):
        pass

    def multiplication(self, node):
        pass

    def inequality(self, node):
        pass


