# -*- coding: utf-8 -*-
"""
The root module holds the definition of the root of the AST tree. As it is the
root, it hold extra information, logic and syntax that make it unique from the
rest of the nodes.
"""
from .typechecker import TypeChecker


class QLAST(object):
    """
    Initialises a root node. It will also initialise an empty register with all
    the variables and nodes, a queue with the order in which the nodes must be
    displayed and the typechecker.
    """
    def __init__(self):
        super().__init__()
        self.build_order = []
        self.typechecker = TypeChecker(self)
        self.register = {}
        self.title = 'QL Workbench'

    def set_title(self, title):
        """Sets the title/name of the app"""
        self.title = title

    def register_node(self, node):
        """
        Adds a node to the AST. It will also check for certain errors detected
        on declaration time like duplicated questions and labels (warning) and
        will report them to the typechecker which will process them.
        """
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
        """Registers an error in the typechcker"""
        self.typechecker.add_error(node, message)

    def register_warning(self, node, message):
        """Registers a warning in the typechecker"""
        self.typechecker.add_warning(node, message)

    def get_errors(self):
        """Retrieves all the errors from the typechecker"""
        return self.typechecker.errors

    def get_warnings(self):
        """Retrieves all the warnings from the typechecker"""
        return self.typechecker.warns
