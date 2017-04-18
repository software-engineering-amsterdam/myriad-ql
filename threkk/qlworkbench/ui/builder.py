# -*- coding: utf-8 -*-
"""
This module takes care of the construction of the UI that the user will
interact with. This only contains the TK related logic, the application logic,
result of the parsing, is located in a context class so it can be easily
modified or substituted.
"""
from tkinter import Tk
from tkinter import ttk

from .context import UIContext


class Visitor(object):
    """
    Implements the visitor pattern. Given an AST and a GUI builder, it will go
    trough the AST to retrieve all the nodes and use them to build the user
    interface.
    """
    def __init__(self, root):
        self.__ast = root
        self.__ui = Builder()

    def execute(self):
        self.__ui.set_title(self.__ast.text)

        for node in self.__ast.nodes:
            node.build_ui(self.__ui)

        return self.__ui


class Builder(object):
    """
    This class represents a UI object. It initalises the environment and allows
    to place different types of widgets. The available widgets depends on the
    variable type (boolean, decimal or string) and they can be set as writing
    widgets which allow the users to change their values, or to as reading
    widgets which will be used to display the information of calcualted
    variables.
    """
    def __init__(self):
        """
        Constructor. It sets up the main window and the main frame that will be
        used place the widgets. It also initialises the `UIContext` which will
        store the values during runtime.
        """
        self.root = Tk()
        self.register = UIContext()
        self.elements = []  # Queue to place the widgets in the right order.

        # Make the root resizeable.
        self.root.winfo_toplevel().rowconfigure(0, weight=1)
        self.root.winfo_toplevel().columnconfigure(0, weight=1)
        self.root.resizable(False, False)

        # Define the main frame.
        self.mainframe = ttk.Frame(self.root, padding='10 10 10 10')
        self.mainframe.columnconfigure(0, weight=1)
        self.mainframe.rowconfigure(0, weight=1)
        self.mainframe.grid(column=0, row=0, sticky='nwse')

    def set_title(self, text='QL Workbench'):
        """
        Assigns the given title to the UI. In case no parameter is passed, it
        uses 'QL Workbench'
        """
        self.root.title(text)

    def add_question(self, text, variable, conditions):
        """
        Adds a question to the form using the given text, variable and
        conditions. The type and format of the questions will depend on the
        type of the variable given.
        """
        label = ttk.Label(self.mainframe, text=text[1:-1], wraplength=200)
        value = variable.type.create_variable()
        field = variable.type.init_field(self, value)

        value.trace('w', lambda *args: self.__emit_event(variable))
        ui = {'field': field, 'label': label}

        self.elements.append(ui)
        self.register.add(variable, value, ui, conditions=conditions)
        self.register.set_value(variable.name, variable.type.default_value())

    def add_assignation(self, text, variable, expr, conditions):
        """
        Adds an assignation to the form using the given text, variable,
        expression and conditions. The type and format of the questions will
        depend on the type of the variable given.
        """
        label = ttk.Label(self.mainframe, text=text[1:-1], wraplength=200)
        value = variable.type.create_variable()
        field = variable.type.init_field(self, value, assignation=True)

        value.trace('w', lambda *args: self.__emit_event(variable))
        ui = {'field': field, 'label': label}

        self.elements.append(ui)
        self.register.add(variable, value, ui, conditions=conditions,
                          expression=expr)

    def run(self):
        """
        Loads the elements in the GUI. Every element is a set of label + field
        which are placed in different rows. They are placed in the same order
        as they were defined. After that, it grabs the window's focus and
        leaves the application in a running state.
        """
        for current_row, element in enumerate(self.elements):
            element['label'].grid(column=0, row=current_row, columnspan=2,
                                  sticky='nw', padx=5)
            element['field'].grid(column=2, row=current_row, columnspan=1,
                                  sticky='nw', padx=5)

        # Updates all the register to calculate the right values of the
        # expressions and conditions.
        self.register.update_all()

        # Get focus and execute.
        self.root.grab_set_global()
        self.root.mainloop()

    def __emit_event(self, variable):
        """
        The tracer is attached to every pair input-variable to update the
        values of the register once it is updated. It updates the variable and
        all the other variables that depends on it.
        """
        self.register.update(variable.name)
