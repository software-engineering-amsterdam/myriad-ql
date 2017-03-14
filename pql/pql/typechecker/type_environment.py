# coding=utf-8
from collections import defaultdict

from pql.identifierchecker.identifierchecker import IdentifierChecker


class TypeEnvironment(IdentifierChecker):
    def __init__(self):
        super(TypeEnvironment, self).__init__()
        self.__symbol_table = defaultdict()

    def visit(self, pql_ast):
        self.__symbol_table.clear()
        [form.apply(self) for form in pql_ast]
        return self.__symbol_table

    def field(self, node):
        self.__symbol_table[node.name.name] = node.data_type
