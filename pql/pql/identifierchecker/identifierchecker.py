# coding=utf-8
from collections import defaultdict

from pql.traversal.FormVisitor import FormVisitor


class IdentifierChecker(FormVisitor):
    def __init__(self):
        # TODO: Dictionary niet als instance variable (hoe dan wel? het is nu een "hidden" instance variable)
        self.__identifier_dict = defaultdict(list)

    def visit(self, pql_ast):
        self.__identifier_dict.clear()
        [form.apply(self) for form in pql_ast]
        return self.__compute_result(self.__identifier_dict)

    def form(self, node):
        [statement.apply(self) for statement in node.statements]

    def conditional_if_else(self, node):
        self.conditional_if(node)
        [statement.apply(self) for statement in node.else_statement_list]

    def conditional_if(self, node):
        [statement.apply(self) for statement in node.statements]

    def field(self, node):
        self.__identifier_dict[node.name.name].append(node.data_type)

    def __compute_result(self, dictionary):
        def normalize_dictionary(dictionary_):
            # TODO: Mooier maken evt?
            dict_ = {}
            for key, value_list in dictionary_.items():
                for value in value_list:
                    dict_[key] = value
            return dict_

        errors = ['Key: {} contained multiple entries, the following: {}'.format(key, value)
                  for (key, value) in dictionary.items() if len(value) > 1]
        return normalize_dictionary(dictionary), errors
