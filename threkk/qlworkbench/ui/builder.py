# -*- coding: utf-8 -*-
"""
This module takes care of the construction of the UI that the user will
interact with.
"""
from tkinter import Tk
from tkinter import ttk
from tkinter import BooleanVar, DoubleVar, StringVar
from .context import UIContext


class UIBuilder(object):
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

    def add_boolean_question(self, text, variable, conditions):
        """
        Adds a boolean checkbox for a certain variable. It is initialised as
        False.
        """
        label = ttk.Label(self.mainframe, text=text, wraplength=200)
        value = BooleanVar()
        field = ttk.Checkbutton(self.mainframe, text='', command=None,
                                onvalue=True, offvalue=False,
                                variable=value)

        value.trace('w', lambda *args: self.__tracer(variable))
        ui = {'field': field, 'label': label}

        self.elements.append(ui)
        self.register.add(variable, 'boolean', value, ui, conditions)
        self.register.set_value(variable, False)

    def add_decimal_question(self, text, variable, conditions):
        """
        Adds a decimal entry for a variable. This entry has a strong validate
        function attached to it. It will only accept character which produce a
        valid decimal value. It will also accept an empty string, which will be
        substituted by a zero. It is initialised as 0.
        """
        label = ttk.Label(self.mainframe, text=text, wraplength=200)
        value = DoubleVar()
        field = ttk.Entry(self.mainframe, textvariable=value, validate='key',
                          validatecommand=(self.root.register(
                              self.__decimal_validator), '%P'),
                          invalidcommand=(self.root.register(
                              self.__decimal_empty_fix), variable, '%P'))

        value.trace('w', lambda *args: self.__tracer(variable))
        ui = {'field': field, 'label': label}

        self.elements.append(ui)
        self.register.add(variable, 'decimal', value, ui, conditions)
        self.register.set_value(variable, 0)

    def add_string_question(self, text, variable, conditions):
        """
        Adds a string entry for a certain variable. It is initialised as empty
        string.
        """
        label = ttk.Label(self.mainframe, text=text, wraplength=200)
        value = StringVar()
        field = ttk.Entry(self.mainframe, textvariable=value)

        value.trace('w', lambda *args: self.__tracer(variable))
        ui = {'field': field, 'label': label}

        self.elements.append(ui)
        self.register.add(variable, 'string', value, ui, conditions)
        self.register.set_value(variable, '')

    def add_boolean_assignation(self, text, variable, expr, conditions):
        """
        Defines a read-only boolean checkbox which contains the result of an
        assignation, like checking if two existing declarations are true.
        """
        label = ttk.Label(self.mainframe, text=text, wraplength=200)
        value = BooleanVar()
        field = ttk.Checkbutton(self.mainframe, text='', command=None,
                                onvalue=True, offvalue=False,
                                variable=value, state='disabled')

        value.trace('w', lambda *args: self.__tracer(variable))
        ui = {'field': field, 'label': label}

        self.elements.append(ui)
        self.register.add(variable, 'boolean', value, ui, conditions, expr)

    def add_decimal_assignation(self, text, variable, expr, conditions):
        """
        Defines a read-only decimal entry which contains the result of an
        assignation, like the difference between two existing declarations.
        """
        label = ttk.Label(self.mainframe, text=text, wraplength=200)
        value = DoubleVar()
        field = ttk.Entry(self.mainframe, textvariable=value, state='readonly')

        value.trace('w', lambda *args: self.__tracer(variable))
        ui = {'field': field, 'label': label}

        self.elements.append(ui)
        self.register.add(variable, 'decimal', value, ui, conditions, expr)

    def add_string_assignation(self, text, variable, expr, conditions):
        """
        Defines a read-only string entry which contains the result of
        an assignation. In practice, it is barely used as there are no
        expressions which make use of strings but the identity expression.
        """
        label = ttk.Label(self.mainframe, text=text, wraplength=200)
        value = StringVar()
        field = ttk.Entry(self.mainframe, textvariable=value, state='readonly')

        value.trace('w', lambda *args: self.__tracer(variable))
        ui = {'field': field, 'label': label}

        self.elements.append(ui)
        self.register.add(variable, 'string', value, ui, conditions, expr)

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

    def __decimal_validator(self, value):
        """
        Used in the decimal entry box to make sure that the number written is a
        valid decimal. It takes advantage of the float exceptions to detect the
        format.
        """
        try:
            float(value)
            return True
        except ValueError:
            return False

    def __decimal_empty_fix(self, variable, new_value):
        """
        Executed when an invlidad decimal is detected. In a normal case, it
        will not update the value in the entry box as it would make it
        invalidad (the user will press the key but nothing will happen).
        However, if the new value is an empty string, which means that the user
        decided to "delete" the value, it will be substituted by a zero
        instead. This makes sure that the register does not crash with an
        invalidad value.
        """
        if new_value == '':
            self.register.set_value(variable, 0)

    def __tracer(self, variable):
        """
        The tracer is attached to every pair input-variable to update the
        values of the register once it is updated. It updates the variable and
        all the other variables that depends on it.
        """
        self.register.update(variable)
