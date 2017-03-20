# -*- coding: utf-8 -*-


class UIContext(object):
    def __init__(self):
        self.register = {}

    def get_value(self, id):
        return self.register[id].get_value()

    def set_value(self, id, new_value):
        self.register[id].set_value(new_value)

    def get_type(self, id):
        return self.register[id].get_value()

    def add(self, variable, value, ui, conditions=[], expression=None):
        register = _UIRegister(variable, value, ui, conditions, expression)

        # Add possible dependants to the right register. The typechecker makes
        # sure that they are not going to refer to something that has not been
        # added yet.
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
        for id, reg in self.register.items():
            self.__update_register(reg)

    def update(self, variable_name):
        self.__update_register(self.register[variable_name])
        # Initialise the list of variables to update.
        dependants = self.register[variable_name].get_dependants()

        for dependant in dependants:
            self.__update_register(self.register[dependant])

    def __update_register(self, reg):
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

    def get_type(self):
        return self.variable.type.__str__()

    def add_dependant(self, id):
        self.dependants.append(id)

    def get_dependants(self):
        return self.dependants

    def get_label(self):
        return self.ui['label']

    def get_field(self):
        return self.ui['field']
