# -*- coding: utf-8 -*-
from appJar import gui
from collections import OrderedDict
import json

from QL.Undefined import Undefined
from QL.GUI.EvaluateDrawState import EvaluateDrawState


class DrawGUI(object):
    def __init__(self, ast, env, evaluator, error_handler):
        # Random comment; the 'cget' function can be used to retrieve
        # properties of the Tkinter object reference.
        self.main = gui("QL Language Form - © 2017")
        self.row = 0
        self.add_header()
        self.env = env
        self.evaluator = evaluator
        self.handler = error_handler

        self.set_draw_state = EvaluateDrawState(self, ast, env,
                                                evaluator, error_handler)

        self.widgets = OrderedDict()

    def start(self):
        self.evaluator.start_traversal()
        self.set_draw_state.start_traversal()
        self.add_buttons()
        self.main.go()

    def redraw(self):
        self.adjust_env()
        self.evaluator.start_traversal()
        self.set_draw_state.start_traversal()

    def adjust_env(self):
        for widget_id in self.widgets:
            question_node = self.env.variables[widget_id]["node"]

            new_value = self.get_widget_val(widget_id)
            if new_value == '':
                self.env.set_var_value(widget_id, Undefined)
                continue

            question_type = question_node.type
            new_value = question_type.convert_to_type(new_value)
            self.env.set_var_value(widget_id, new_value)

    def force_redraw(self, _):
        # Request all form values, adjust the environment.
        self.redraw()

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

    def button_action(self, button_pressed):
        if button_pressed == "Save details":
            self.save_data()
        else:
            self.main.stop()

    def get_question_values(self):
        widget_values = OrderedDict()
        for identifier in self.widgets:
            widget_values[identifier] = self.get_widget_val(identifier)
        return widget_values

    def save_data(self):
        json_dict = self.get_question_values()
        with open("./form_output.txt", "w+") as form_output:
            json.dump(json_dict, form_output, indent=4)
