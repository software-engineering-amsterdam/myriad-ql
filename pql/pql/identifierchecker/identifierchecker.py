# coding=utf-8
from collections import defaultdict


class IdentifierChecker(object):
    def __init__(self):
        self.identifier_map = {}
        self.messages = []

    def visit(self, pql_ast):
        self.messages = []
        self.identifier_map = {}
        [form.apply(self) for form in pql_ast]
        return self.__compute_result(self.identifier_map)

    def form(self, node):
        return self.__flatten_dictionaries([statement.apply(self) for statement in node.children])

    def conditional(self, node):
        pass

    def field(self, node):
        return {node.name.name: node.data_type}

    def __flatten_dictionaries(self, dicts):
        # TODO: Eventueel inner for in map
        res = defaultdict(list)
        for d in dicts:
            for k, v in d.items():
                res[k].append(v)
        return res
        # return {key: value for dic_entry in dicts for key, value in dic_entry.items()}

    def __compute_result(self, dictionaries):
        return dictionaries, self.__compute_errors(dictionaries)

    def __compute_errors(self, dictionaries):
        pass
