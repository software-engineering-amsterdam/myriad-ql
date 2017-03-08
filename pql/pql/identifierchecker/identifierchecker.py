# coding=utf-8
from collections import defaultdict

from pql.traversal.FormVisitor import FormVisitor


class IdentifierChecker(FormVisitor):
    def __init__(self):
        # TODO: Dictionary niet als instance variable (hoe dan wel? het is nu een "hidden" instance variable), het zelfde geval als in evaluator waar het heel snel heel dirty wordt
        self.__identifier_dict = defaultdict(list)

    def visit(self, pql_ast):
        self.__identifier_dict.clear()
        [form.apply(self) for form in pql_ast]

        normalized_dictionary = dict()
        for key, value_list in self.__identifier_dict.items():
            for value in value_list:
                normalized_dictionary[key] = value

        errors = list()
        for key, value in self.__identifier_dict.items():
            if len(value) > 1:
                errors.append("Key: {} contained multiple entries, the following: {}".format(key, value))

        return normalized_dictionary, errors

    def form(self, node):
        [statement.apply(self) for statement in node.statements]

    def conditional_if_else(self, node):
        self.conditional_if(node)
        [statement.apply(self) for statement in node.else_statement_list]

    def conditional_if(self, node):
        [statement.apply(self) for statement in node.statements]

    def field(self, node):
        self.__identifier_dict[node.name.name].append(node.data_type)
