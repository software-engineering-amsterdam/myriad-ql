# coding=utf-8
from collections import defaultdict

from pql.traversal.FormVisitor import FormVisitor


class TypeEnvironment(FormVisitor):
    def __init__(self, ast):
        self.ast = ast

    def visit(self):
        return self.ast.apply(self, defaultdict())

    def form(self, node, environment=None):
        for statement in node.statements:
            statement.apply(self, environment)
        return environment

    def conditional_if_else(self, node, environment=None):
        self.conditional_if(node, environment)
        for statement in node.else_statement_list:
            statement.apply(self, environment)

    def conditional_if(self, node, environment=None):
        for statement in node.statements:
            statement.apply(self, environment)

    def field(self, node, environment=None):
        environment[node.name.name] = node.data_type

    def assignment(self, node, environment=None):
        self.field(node, environment)
