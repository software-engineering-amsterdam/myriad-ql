# coding=utf-8
from pql.environment.environment import Environment
from pql.traversal.FormVisitor import FormVisitor


class EnvironmentCreator(FormVisitor):
    def __init__(self, ast):
        self.__environment = Environment()
        self.ast = ast

    def visit(self):
        self.__environment.clear()
        self.ast.apply(self)
        return self.__environment

    def form(self, node, args=None):
        for statement in node.statements:
            statement.apply(self)

    def conditional_if_else(self, node, args=None):
        self.conditional_if(node)
        for statement in node.else_statement_list:
            statement.apply(self)

    def conditional_if(self, node, args=None):
        for statement in node.statements:
            statement.apply(self)

    def field(self, node, args=None):
        self.__environment.update(node.name.name, node.data_type.value)

    def assignment(self, node, args=None):
        self.field(node)
