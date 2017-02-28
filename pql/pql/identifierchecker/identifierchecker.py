# coding=utf-8
from collections import defaultdict


class IdentifierChecker(object):
    def __init__(self):
        # TODO: Dictionary niet als instance variable
        self.identifier_dict = defaultdict(list)

    def visit(self, pql_ast):
        [form.apply(self) for form in pql_ast]
        return self.__compute_result(self.identifier_dict)

    def form(self, node):
        [statement.apply(self) for statement in node.children]

    def conditional(self, node):
        [statement.apply(self) for statement in node.statements]
        if node.else_statement_list is not None:
            [statement.apply(self) for statement in node.else_statement_list]

    def field(self, node):
        self.identifier_dict[node.name.name].append(node.data_type)

    def __compute_result(self, dictionary):
        def normalize_dictionary(dictionary_):
            # TODO: Mooier maken evt?
            dict_ = {}
            for key, value in dictionary_.items():
                for v in value:
                    dict_[key] = v
            return dict_

        # TODO: Mooier maken evt?
        errors = []
        for key, value in dictionary.items():
            if len(value) > 1:
                errors.append('Key: {} contained multiple entries, the following: {}'.format(key, value))
        return normalize_dictionary(dictionary), errors
