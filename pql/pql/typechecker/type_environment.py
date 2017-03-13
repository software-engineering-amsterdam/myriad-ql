# coding=utf-8
from pql.identifierchecker.identifierchecker import IdentifierChecker


class TypeEnvironment(IdentifierChecker):
    def visit(self, pql_ast):
        def recursively_build_dictionary(items, dct):
            for dictionary in items:
                if isinstance(dictionary, list):
                    recursively_build_dictionary(dictionary, dct)
                else:
                    for d_key, d_value in dictionary.items():
                        dct[d_key] = d_value

        identifier_dictionary = dict()
        recursively_build_dictionary([form.apply(self) for form in pql_ast], identifier_dictionary)
        return identifier_dictionary

    def field(self, node):
        return {node.name.name: node.data_type}
