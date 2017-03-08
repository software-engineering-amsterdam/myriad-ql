# coding=utf-8
from collections import defaultdict

from pql.identifierchecker.identifierchecker import IdentifierChecker
from pql.traversal.FormVisitor import FormVisitor


class EnvironmentCreator(IdentifierChecker):
    def __init__(self):
        super(EnvironmentCreator, self).__init__()
        self.__environment_dict = dict()

    def visit(self, pql_ast):
        self.__environment_dict.clear()
        [form.apply(self) for form in pql_ast]
        return self.__environment_dict

    def field(self, node):
        self.__environment_dict[node.name.name] = (node.data_type.default_value())
