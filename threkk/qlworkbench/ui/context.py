# -*- coding: utf-8 -*-
"""
The `ui.context` module defines all the classes necessary to describe the
context of an user interface. Although it has been designed with the module
`ui.builder` in mind, it should be easily substituted.
"""


class UIContext(object):
    """
    The UIContext class is esentially a wrapper of a native dictionary which
    stores in every entry a pair of variable name (key) and register (value).
    The functions defined add extra features and checks to the original
    dictionary.

    Every register is an instance of the UIRegister class.
    """
    def __init__(self):
        """Initialises the dictionary"""
        self.register = {}

    def get_value(self, id):
        return self.register[id].get_value()

    def set_value(self, id, new_value):
        self.register[id].set_value(new_value)

    def add(self, variable, value, ui, conditions=[], expression=None):
        """
        This function will add a new entry to the register. The optional
        paramenters allow adding both `declaration` and `assignation` nodes to
        the register. Conditions are optional, depending on the node.
        """
        register = _UIRegister(variable, value, ui, conditions, expression)

        # Add possible dependants to the right register.
        #
        # It is important to note that instead of storing who depends the node
        # on, we add the node to the dependants list of the nodes that it
        # depends on. The purpose of the depedants list is to update all the
        # nodes that depend on it when it is updated.
        #
        # The typechecker makes sure that they are not going to refer to
        # something that has not been added yet. We can assume type safety at
        # this point.
        if expression:
            dependencies = expression.depends_on()
            for dependency in dependencies:
                # You cannot "depend on" yourself
                if dependency != register.variable.name:
                    self.register[dependency].add_dependant(variable.name)

        for condition in conditions:
            dependencies = condition.depends_on()
            for dependency in dependencies:
                # You cannot "depend on" yourself
                if dependency != register.variable.name:
                    self.register[dependency].add_dependant(variable.name)

        self.register[variable.name] = register

    def update_all(self):
        """
        Updates the status of all the registers. Some of the values of the
        register have an uncertain status as they depend on runtime values.
        This function makes sure that all of them have the right values with
        the default runtime values.
        """
        for id, reg in self.register.items():
            self.__update_register(reg)

    def update(self, variable_name):
        """
        Given the name of a variable, it updates its status and the status of
        all the other variables that depend on it.
        """
        # We assume that the variable exists. If the context is being executed
        # is because the typechecker did not detect and undefined variable.
        self.__update_register(self.register[variable_name])
        # Initialise the list of variables to update.
        dependants = self.register[variable_name].get_dependants()

        for dependant in dependants:
            self.__update_register(self.register[dependant])

    def __update_register(self, reg):
        """
        This function has two steps:
            1) If the variable is an assignation, updates the value based on
            the computed value of the expression.

            2) Computes all the conditions. If they are true, the element is
            displayed. If not, it is hidden.
        """
        if reg.expression:
            reg.set_value(reg.expression.read(self))

        if reg.conditions:
            visible = True
            for condition in reg.conditions:
                visible = visible and condition.read(self)
                if visible:
                    reg.get_label().grid()
                    reg.get_field().grid()
                else:
                    reg.get_label().grid_remove()
                    reg.get_field().grid_remove()


class _UIRegister(object):
    """
    UIRegister is a private class which stores the information of a register.
    """
    def __init__(self, variable, value, ui, conditions, expression=None):
        self.variable = variable
        self.conditions = conditions
        self.dependants = []
        self.expression = expression
        self.ui = ui
        self.value = value

    def get_value(self):
        return self.value.get()

    def set_value(self, new_value):
        self.value.set(new_value)

    def add_dependant(self, id):
        self.dependants.append(id)

    def get_dependants(self):
        return self.dependants

    def get_label(self):
        return self.ui['label']

    def get_field(self):
        return self.ui['field']
