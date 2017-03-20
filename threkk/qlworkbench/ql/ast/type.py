# -*- coding: utf-8 -*-
from tkinter import BooleanVar, DoubleVar, StringVar
from tkinter import ttk


class Type(object):
    def __str__(self):
        return self.__class__.__name__


class Boolean(object):
    @staticmethod
    def default_value():
        return False

    @staticmethod
    def init_variable():
        return BooleanVar()

    @staticmethod
    def init_field(ui, variable, assignation=False):
        if assignation:
            return ttk.Checkbutton(ui.mainframe, text='', command=None,
                                   onvalue=True, offvalue=False,
                                   variable=variable, state='disabled')
        else:
            return ttk.Checkbutton(ui.mainframe, text='', command=None,
                                   onvalue=True, offvalue=False,
                                   variable=variable)


class Decimal(object):
    @staticmethod
    def default_value():
        return 0

    @staticmethod
    def init_variable():
        return DoubleVar()

    @staticmethod
    def init_field(ui, variable, assignation=False):
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


class String(object):
    @staticmethod
    def default_value():
        return ''

    @staticmethod
    def init_variable():
        return StringVar()

    @staticmethod
    def init_field(ui, variable, assignation=False):
        if assignation:
            return ttk.Entry(ui.mainframe, textvariable=variable,
                             state='readonly')
        else:
            return ttk.Entry(ui.mainframe, textvariable=variable)
