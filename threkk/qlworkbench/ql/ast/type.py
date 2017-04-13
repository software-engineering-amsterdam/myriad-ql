# -*- coding: utf-8 -*-
"""
The type module contains the information about the different types supported by
the AST. Classes were not strictly necessary (a bunch of functions in the
module could have make it too) but being able to pass a class made the whole
process much easier.
"""
from tkinter import BooleanVar, DoubleVar, StringVar
from tkinter import ttk


# We want singletons but they are broken in python. Not worth the time.
class Type(object):
    """Abstract parent class. Not supposed to be instantiated"""
    def __str__(self):
        return self.__class__.__name__

    def __eq__(self, other):
        """
        Two types are equal when they both inherit from Type and have the same
        class.
        """
        return (issubclass(other.__class__, Type) and
                other.__class__.__name__ == self.__class__.__name__)

    def __ne__(self, other):
        """Two types are not equal when equals is False"""
        return not self.__eq__(other)


class Undefined(Type):
    """
    The Undefined type is used by the typechecker to refer to the type of an
    undefined unknown variable. This type should never pass the typechecker and
    reach the UI building as the pressence of this type is detected as a
    blocking grammar error.
    """
    def __str__(self):
        return self.__class__.__name__

    @staticmethod
    def allowed_operations():
        """List of allowed operations for the nodes of this type"""
        return []

    @staticmethod
    def default_value():
        """
        Default value to use by the variables of this type in case they do not
        have any value.
        """
        return None

    @staticmethod
    def create_variable():
        """Initialises a UI variable of this type"""
        return None

    @staticmethod
    def init_field(ui, variable, assignation=False):
        """
        Returns an input field of the type for declarations and assignations.
        """
        return None


class Boolean(Type):
    """Boolean (true/false) type"""
    @staticmethod
    def allowed_operations():
        """List of allowed operations for the nodes of this type"""
        return ['&&', '||', '!', '==', '!=']

    @staticmethod
    def default_value():
        """
        Default value to use by the variables of this type in case they do not
        have any value.
        """
        return False

    @staticmethod
    def create_variable():
        """Initialises a UI variable of this type"""
        return BooleanVar()

    @staticmethod
    def init_field(ui, variable, assignation=False):
        """
        Returns an input field of the type for declarations and assignations.
        """
        if assignation:
            return ttk.Checkbutton(ui.mainframe, text='', command=None,
                                   onvalue=True, offvalue=False,
                                   variable=variable, state='disabled')
        else:
            return ttk.Checkbutton(ui.mainframe, text='', command=None,
                                   onvalue=True, offvalue=False,
                                   variable=variable)


class Decimal(Type):
    """Decimal (numeric) type"""
    @staticmethod
    def allowed_operations():
        """List of allowed operations for the nodes of this type"""
        return ['+', '-', '*', '/', '<', '<=', '>', '>=', '==', '!=']

    @staticmethod
    def default_value():
        """
        Default value to use by the variables of this type in case they do not
        have any value.
        """
        return 0

    @staticmethod
    def create_variable():
        """Initialises a UI variable of this type"""
        return DoubleVar()

    @staticmethod
    def init_field(ui, variable, assignation=False):
        """
        Returns an input field of the type for declarations and assignations.
        """
        if assignation:
            return ttk.Entry(ui.mainframe, textvariable=variable,
                             state='readonly')
        else:
            # Executed when an invlidad decimal is detected. In a normal case,
            # it will not update the value in the entry box as it would make it
            # invalidad (the user will press the key but nothing will happen).
            # However, if the new value is an empty string, which means that
            # the user decided to "delete" the value, it will be substituted by
            # a zero instead. This makes sure that the register does not crash
            # with an invalidad value.
            return ttk.Entry(ui.mainframe, textvariable=variable,
                             validate='key', validatecommand=(ui.root.register(
                                Decimal.__decimal_validator), '%P'),
                             invalidcommand=(ui.root.register(
                                lambda x: variable.set(0) if (x == '') else x),
                                '%P'))

    @staticmethod
    def __decimal_validator(value):
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


class String(Type):
    """String (characters) type"""
    @staticmethod
    def allowed_operations():
        """List of allowed operations for the nodes of this type"""
        return ['==', '!=']

    @staticmethod
    def default_value():
        """
        Default value to use by the variables of this type in case they do not
        have any value.
        """
        return ''

    @staticmethod
    def create_variable():
        """Initialises a UI variable of this type"""
        return StringVar()

    @staticmethod
    def init_field(ui, variable, assignation=False):
        """
        Returns an input field of the type for declarations and assignations.
        """
        if assignation:
            return ttk.Entry(ui.mainframe, textvariable=variable,
                             state='readonly')
        else:
            return ttk.Entry(ui.mainframe, textvariable=variable)
