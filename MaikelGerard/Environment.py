from TypeEnums import TypeEnums as Type
from decimal import Decimal
from datetime import date


class Environment(object):
    default_vals = {Type.BOOLEAN: False, Type.DECIMAL: Decimal('0.00'),
                    Type.MONEY: Decimal('0.00'), Type.INTEGER: Decimal('0'),
                    Type.STRING: "", Type.DATE: date(day=1, month=1, year=2000)}

    def __init__(self, error_handler):
        self.type_env = {}
        self.value_env = {}
        self.error_handler = error_handler
        self.context = "Environment"

    def clear_env(self):
        self.type_env = {}
        self.value_env = {}

    def get_type(self, var_node):
        if var_node.val not in self.type_env:
            self.error_handler.add_undecl_var_error(self.context, var_node)
            return None
        return self.type_env[var_node.val]

    def get_value(self, var_name):
        assert var_name in self.value_env, \
            "Var name '{}' not in value environment!".format(var_name)
        return self.value_env[var_name]

    def set_type(self, question_node):
        var_name = question_node.name.val
        var_type = question_node.type
        if var_name in self.type_env:
            self.error_handler.add_duplicate_error(self.context, question_node)
            return False
        self.type_env[var_name] = var_type
        self.value_env[var_name] = self.default_vals[var_type]
        return True

    def set_value(self, var_name, value):
        assert var_name in self.value_env, \
            "Var name '{}' not in value environment!".format(var_name)
        self.value_env[var_name] = value
