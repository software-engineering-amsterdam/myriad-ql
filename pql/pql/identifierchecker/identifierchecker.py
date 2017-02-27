# coding=utf-8
from collections import defaultdict


class IdentifierChecker(object):
    def visit(self, pql_ast):
        result = [form.apply(self) for form in pql_ast]
        identifier_dict = self.__flatten_list_of_dictionaries(result)
        del result
        return self.__compute_result(identifier_dict)

    def form(self, node):
        return [statement.apply(self) for statement in node.children]

    def conditional(self, node):
        pass

    def field(self, node):
        return {node.name.name: node.data_type}

    def __flatten_list_of_dictionaries(self, list_of_dicts):
        # TODO: Mooier maken evt?
        res = defaultdict(list)
        for l in list_of_dicts:
            for d in l:
                for k, v in d.items():
                    res[k].append(v)
        return res

    def __compute_result(self, dictionary):
        # TODO: Mooier maken evt?
        errors = []
        for key, value in dictionary.items():
            if len(value) > 1:
                errors.append('Key: {} contained multiple entries, the following: {}'.format(key, value))
        return dictionary, errors
