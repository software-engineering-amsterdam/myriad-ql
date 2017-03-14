# coding=utf-8
from collections import defaultdict

from pql.traversal.FormVisitor import FormVisitor


class TypeEnvironment(FormVisitor):
    def __init__(self):
        super(TypeEnvironment, self).__init__()
        self.__symbol_table = defaultdict()

    def visit(self, pql_ast):
        self.__symbol_table.clear()
        [form.apply(self) for form in pql_ast]
        return self.__symbol_table

    def form(self, node):
        for statement in node.statements:
            statement.apply(self)

    def conditional_if_else(self, node):
        self.conditional_if(node)
        for statement in node.else_statement_list:
            statement.apply(self)

    def conditional_if(self, node):
        for statement in node.statements:
            statement.apply(self)

    def field(self, node):
        self.__symbol_table[node.name.name] = node.data_type
