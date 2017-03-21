# coding=utf-8
from pql.environment.environment import Environment
from pql.traversal.FormVisitor import FormVisitor


class EnvironmentCreator(FormVisitor):
    def __init__(self, ast):
        self.ast = ast

    def visit(self):
        return self.ast.apply(self, Environment())

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
        environment.update(node.name.name, node.data_type.value)

    def assignment(self, node, environment=None):
        self.field(node, environment)
