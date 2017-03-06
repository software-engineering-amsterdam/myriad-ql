# -*- coding: utf-8 -*-
from appJar import gui
from collections import OrderedDict
import json


class FormGUI(object):
    def __init__(self):
        # Random comment; the 'cget' function can be used to retrieve
        # properties of the Tkinter object reference.
        self.main = gui("QL Language Form - © 2017")
        self.row = 0
        self.add_header()
        self.redraw_function = None

        self.widgets = OrderedDict()

    def start(self):
        self.add_buttons()
        self.main.go()

    def add_buttons(self):
        self.main.addButtons(
            ["Save details", "Exit"], self.button_action, self.row, 0, 2
        )

    def add_header(self):
        self.main.addLabel("header", "Please fill in the form!", self.row, 0, 2)
        self.row += 1

    def add_widget(self, widget_class, identifier, question):
        new_widget = widget_class(self, identifier, question, self.row)
        self.widgets[identifier] = new_widget
        self.row += 1

    def hide_widget(self, identifier):
        self.widgets[identifier].hide()

    def show_widget(self, identifier):
        self.widgets[identifier].show()

    def get_widget_val(self, identifier):
        return self.widgets[identifier].get_entry()

    def set_widget_val(self, identifier, value):
        self.widgets[identifier].set_entry(value)

    def get_question_values(self):
        widget_values = OrderedDict()
        for identifier in self.widgets:
            widget_values[identifier] = self.get_widget_val(identifier)
        return widget_values

    def force_redraw(self, _):
        assert self.redraw_function is not None, \
            "Force redraw function not initialized!"
        # Request all form values, adjust the environment.
        question_values = self.get_question_values()
        self.redraw_function(question_values)

    def button_action(self, button_pressed):
        if button_pressed == "Save details":
            self.save_data()
        else:
            self.main.stop()

    def save_data(self):
        json_dict = self.get_question_values()
        with open("./form_output.txt", "w+") as form_output:
            json.dump(json_dict, form_output, indent=4)
