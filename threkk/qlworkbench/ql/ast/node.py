# -*- coding: utf-8 -*-
"""
This module holds the definition of the different nodes of the AST. The nodes
can be the `Declaration` of new questions or the `Assgination` of values.
"""


class Variable(object):
    """
    The variable class stores the name of a variable and its type. Every
    variable declared or assigned is stored as an instance of this class.
    """
    def __init__(self, variable, type):
        self.type = type
        self.name = variable

    def get_value(self, context):
        """
        Returns the value of the variable in a given context, usually the UI.
        """
        return context.get_value(self.name)

    def __str__(self):
        return '{} ({})'.format(self.name, self.type)


# ROOT
class Root(object):
    """
    Initialises a root node. The root node holds every other node existing in
    the tree. The result is a flatten tree in which the root has a list with
    every descendant declaration or assignation, which hold their own
    conditions.
    """
    def __init__(self):
        super().__init__()
        self.nodes = []
        self.text = 'Root'

    def add_node(self, node):
        self.nodes.append(node)

    def set_text(self, text):
        self.text = text

    def __str__(self):
        return '{}: {}'.format(self.text, self.nodes)


class Node(object):
    """
    Abstract parent class for `Declaration` and `Assignation`. It holds the
    shared logic between them. It is not supposed to be instantiated.
    """
    def __init__(self, variable, type):
        self.variable = Variable(variable, type)
        self.conditions = []
        self.expression = None

    def get_value(self, context):
        """
        Returns the value of the node in a given context, usually the UI.
        """
        return self.variable.get_value(context)

    def register(self, ql):
        """Adds the node the given AST."""
        ql.register_node(self)

    def add_condition(self, expression):
        """Adds a new condition to the display of the node"""
        self.conditions.append(expression)


# DECLARATIONS
class Declaration(Node):
    """Node which defines a new question."""
    def __init__(self, text, variable, type):
        super().__init__(variable, type)
        self.text = text

    def build_ui(self, ui):
        """Displays the declaration in the given UI."""
        ui.add_question(self.text, self.variable, self.conditions)

    def __str__(self):
        return '(declaration {})"'.format(self.variable)


# ASSIGNATIONS
class Assignation(Node):
    """Node which defines the assgination of values."""
    def __init__(self, text, variable, type, expression):
        super().__init__(variable, type)
        self.text = text
        self.expression = expression

    def build_ui(self, ui):
        ui.add_assignation(self.text, self.variable, self.expression,
                           self.conditions)

    def __str__(self):
        """Displays the declaration in the given UI."""
        return '(assignation {}, {})'.format(self.variable, self.expression)
