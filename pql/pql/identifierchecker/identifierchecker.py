# coding=utf-8
from collections import defaultdict

from pql.traversal.FormVisitor import FormVisitor


class IdentifierChecker(FormVisitor):
    def visit(self, pql_ast):
        def recursively_build_dictionary(items, dct):
            for dictionary in items:
                if isinstance(dictionary, list):
                    recursively_build_dictionary(dictionary, dct)
                else:
                    for d_key, d_value in dictionary.items():
                        dct[d_key].append(d_value)

        def build_error_list(identifiers):
            errors = list()
            for key, value in identifiers.items():
                if len(value) > 1:
                    errors.append("Key: {} contained multiple entries, the following: {}".format(key, value))
            return errors

        def normalize(identifiers):
            normalized_dictionary = dict()
            for key, value_list in identifiers.items():
                for value in value_list:
                    normalized_dictionary[key] = value
            return normalized_dictionary

        identifier_dictionary = defaultdict(list)
        recursively_build_dictionary([form.apply(self) for form in pql_ast], identifier_dictionary)
        return normalize(identifier_dictionary), build_error_list(identifier_dictionary)

    def form(self, node):
        return [statement.apply(self) for statement in node.statements]

    def conditional_if_else(self, node):
        return self.conditional_if(node) + [statement.apply(self) for statement in node.else_statement_list]

    def conditional_if(self, node):
        return [statement.apply(self) for statement in node.statements]

    def field(self, node):
        return {node.name.name: node.data_type}
