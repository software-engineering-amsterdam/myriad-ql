# coding=utf-8
from collections import defaultdict


class EnvironmentCreator(object):
    def __init__(self):
        self.__symbol_table = defaultdict(list)

    def visit(self, pql_ast):
        self.__symbol_table.clear()
        [form.apply(self) for form in pql_ast]
        return self.__symbol_table

    def form(self, node):
        [statement.apply(self) for statement in node.statements]

    def conditional_if_else(self, node):
        self.conditional_if(node)
        [statement.apply(self) for statement in node.else_statement_list]

    def conditional_if(self, node):
        [statement.apply(self) for statement in node.statements]

    def field(self, node):
        self.__symbol_table[node.name.name] = node.data_type.default_value()
