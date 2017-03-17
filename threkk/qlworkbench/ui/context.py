# -*- coding: utf-8 -*-
"""
"""


class UIContext(object):
    def __init__(self):
        self.register = {}

    def get_value(self, id):
        return self.register[id]['value'].get()

    def set_value(self, id, new_value):
        self.register[id]['value'].set(new_value)

    def get_type(self, id):
        return self.register[id]['type']

    def add(self, id, type, value, ui, conditions, expr=None):
        self.register[id] = {
            'conditions': conditions,
            'dependants': [],
            'expr': expr,
            'type': type,
            'ui': ui,
            'value': value
        }

        # Add possible dependants to the right register. The typechecker makes
        # sure that they are not going to refer to something that has not been
        # added yet.
        if expr:
            dependencies = expr.depends_on()
            for dependency in dependencies:
                # You cannot "depend on" yourself
                if dependency != id:
                    self.register[dependency]['dependants'].append(id)

        for condition in conditions:
            dependencies = condition.depends_on()
            for dependency in dependencies:
                # You cannot "depend on" yourself
                if dependency != id:
                    self.register[dependency]['dependants'].append(id)

    def update(self, variable):
        self.__update_register(self.register[variable])
        # Initialise the list of variables to update.
        dependants = self.register[variable]['dependants']

        for dependant in dependants:
            self.__update_register(self.register[dependant])

    def update_all(self):
        for id, reg in self.register.items():
            self.__update_register(reg)

    def __update_register(self, reg):
        if reg['expr']:
            reg['value'].set(reg['expr'].read(self))

        if reg['conditions']:
            visible = True
            for condition in reg['conditions']:
                visible = visible and condition.read(self)
                if visible:
                    reg['ui']['label'].grid()
                    reg['ui']['field'].grid()
                else:
                    reg['ui']['label'].grid_remove()
                    reg['ui']['field'].grid_remove()
