from collections import OrderedDict
from Undefined import Undefined


class Environment(object):
    def __init__(self, error_handler):
        self.variables = OrderedDict()
        self.error_handler = error_handler
        self.context = "Environment"

    def clear_env(self):
        self.variables = OrderedDict()

    def add_var(self, question_node):
        var_name = question_node.name.val

        if var_name in self.variables:
            self.error_handler.add_duplicate_error(self.context, question_node)
            return False
        self.variables[var_name] = {"node": question_node, "value": Undefined}
        return True

    def get_node(self, identifier):
        return self.variables[identifier]["node"]

    def get_var_type(self, var_node):
        if var_node.val not in self.variables:
            self.error_handler.add_undecl_var_error(self.context, var_node)
            return None
        return self.variables[var_node.val]["node"].type

    def get_var_value(self, var_name):
        assert var_name in self.variables, \
            "Var name '{}' not in value environment!".format(var_name)
        return self.variables[var_name]["value"]

    def set_var_value(self, var_name, value):
        assert var_name in self.variables, \
            "Var name '{}' not in value environment!".format(var_name)
        self.variables[var_name]["value"] = value
