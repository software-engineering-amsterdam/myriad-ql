# coding=utf-8
from pql.identifierchecker.identifierchecker import IdentifierChecker


class EnvironmentCreator(IdentifierChecker):
    def visit(self, pql_ast):
        def recursively_build_dictionary(items, dct):
            for dictionary in items:
                if isinstance(dictionary, list):
                    recursively_build_dictionary(dictionary, dct)
                else:
                    for d_key, d_value in dictionary.items():
                        dct[d_key] = d_value
        flattened_dict = dict()
        recursively_build_dictionary([form.apply(self) for form in pql_ast], flattened_dict)
        return flattened_dict

    def field(self, node):
        return {node.name.name: node.data_type.default_value()}
