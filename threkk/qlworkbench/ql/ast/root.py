# -*- coding: utf-8 -*-
from .typechecker import TypeChecker


class QLAST(object):
    def __init__(self):
        super().__init__()
        self.build_order = []
        self.typechecker = TypeChecker(self)
        self.register = {}
        self.title = 'QL Workbench'

    def set_title(self, title):
        self.title = title

    def register_node(self, node):
        variable_name = node.variable.name
        self.typechecker.update(node)

        # Looking for duplicated labels before adding it. If anyone is found,
        # it will raise a warning in the typechecker.
        labels = [item.text for key, item in self.register.items()]
        if (node.text in labels):
            msg = 'Duplicated field label {}'
            self.register_warning(node, msg.format(node.text))

        # Duplicate question declarations with different types.
        #
        # The project description says that it should only detect duplicated
        # questions if they have different types. However, it makes more sense
        # detect any duplicate variable. Therefore, we do both.
        if variable_name in self.register:
            msg = 'Duplicated question detected. It was {}'

            existing_node = self.register[variable_name]
            if (existing_node.variable.type != node.variable.type):
                msg = 'Duplicated question with different type. It was {}'
            self.register_error(node, msg.format(existing_node))
        else:
            # Here we take the design decision of keeping the existing one and
            # ignoring the new one.
            self.register[variable_name] = node
            self.build_order.append(variable_name)

    def register_error(self, node, message):
        self.typechecker.add_error(node, message)

    def register_warning(self, node, message):
        self.typechecker.add_warning(node, message)

    def get_errors(self):
        return self.typechecker.errors

    def get_warnings(self):
        return self.typechecker.warns
