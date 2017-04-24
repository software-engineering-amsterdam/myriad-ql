# -*- coding: utf-8 -*-
"""
The `ui.context` module defines all the classes necessary to describe the
context of an user interface. Although it has been designed with the module
`ui.builder` in mind, it should be easily substituted.
"""
from .event_manager import EventManager


class UIContext(object):
    """
    The UIContext class is esentially a wrapper of a native dictionary which
    stores in every entry a pair of variable name (key) and register (value).
    The functions defined add extra features and checks to the original
    dictionary. Every register is an instance of the UIRegister class.

    It also holds an event manager to deal with the updates of the variables of
    the registers.
    """
    def __init__(self):
        """Initialises the dictionary and event manager"""
        self.register = {}
        self.event_manager = EventManager(self.__update_subscriber)

    def get_value(self, id):
        """Retrieves the value of the variable with name `id`"""
        return self.register[id].get_value()

    def set_value(self, id, new_value):
        """Sets the value of the register with variable name `id`"""
        self.register[id].set_value(new_value)

    def add(self, variable, value, ui, conditions=[], expression=None):
        """
        This function will add a new entry to the register. The optional
        paramenters allow adding both `declaration` and `assignation` nodes to
        the register. Conditions are optional, depending on the node.
        """
        register = _UIRegister(variable, value, ui, conditions, expression)

        # If the variable has expressions or conditions, its value depends on
        # them. Therefore, we subscribe the variable to any update of the
        # conditions or expressions.
        if expression:
            dependencies = expression.depends_on()
            for dependency in dependencies:
                # You cannot "depend on" yourself
                if dependency != variable.name:
                    self.event_manager.subscribe(variable.name, dependency)

        for condition in conditions:
            dependencies = condition.depends_on()
            for dependency in dependencies:
                # You cannot "depend on" yourself
                if dependency != variable.name:
                    self.event_manager.subscribe(variable.name, dependency)

        self.register[variable.name] = register

    def update_all(self):
        """
        Updates the status of all the registers. Some of the values of the
        register have an uncertain status as they depend on runtime values.
        This function makes sure that all of them have the right values with
        the default runtime values.
        """
        for publisher in self.event_manager.get_publishers():
            self.event_manager.publish(publisher)

    def update(self, variable_name):
        """
        Given the name of a variable, it updates its status and the status of
        all the other variables that depend on it.
        """
        self.event_manager.publish(variable_name)

    def __update_subscriber(self, subscriber):
        """Updates the status of the register with the given context."""
        if subscriber in self.register:
            self.register[subscriber].update(self)


class _UIRegister(object):
    """
    UIRegister is a private class which stores the information of a register.
    """
    def __init__(self, variable, value, ui, conditions, expression=None):
        self.variable = variable
        self.conditions = conditions
        self.expression = expression
        self.ui = ui
        self.value = value

    def get_value(self):
        """Retrieves the value of the field."""
        return self.value.get()

    def set_value(self, new_value):
        """Sets the value of the field."""
        self.value.set(new_value)

    def get_label(self):
        """Returns the label of the field."""
        return self.ui['label']

    def get_field(self):
        """Returns the UI field."""
        return self.ui['field']

    def update(self, context):
        """
        Updates the status of the register with the given context.

        This function has two steps:
            1) If the variable is an assignation, updates the value based on
            the computed value of the expression.

            2) Computes all the conditions. If they are true, the element is
            displayed. If not, it is hidden.
        """
        if self.expression:
            self.set_value(self.expression.get_value(context))

        if self.conditions:
            visible = True
            for condition in self.conditions:
                visible = visible and condition.get_value(context)
                if visible:
                    self.get_label().grid()
                    self.get_field().grid()
                else:
                    self.get_label().grid_remove()
                    self.get_field().grid_remove()
