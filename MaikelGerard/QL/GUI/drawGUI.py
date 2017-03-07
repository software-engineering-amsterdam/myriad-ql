# -*- coding: utf-8 -*-
from appJar import gui
from collections import OrderedDict

from QL.undefined import Undefined
from QL.GUI.evaluateDrawState import EvaluateDrawState
from QL.GUI.saveQuestionaire import SaveQuestionaire
from QL.stages.updateComputedVariables import UpdateComputedVariables


class DrawGUI(object):
    def __init__(self, ast, env, error_handler):
        self.ast = ast
        self.env = env
        self.handler = error_handler

        self.update_computed_variables = UpdateComputedVariables(ast, env)
        self.set_draw_state = EvaluateDrawState(self, ast, env, error_handler)

        self.main = gui("QL Language Form - © 2017")
        self.row = 0
        self.add_header()

        self.widgets = OrderedDict()

    def start(self):
        self.ast.accept(self.update_computed_variables)
        self.update_variables()
        self.set_draw_state.start_traversal()
        self.add_buttons()
        self.main.go()

    def redraw(self):
        self.update_variables()
        self.ast.accept(self.update_computed_variables)
        self.set_draw_state.start_traversal()

    def update_variables(self):
        for widget_id in self.widgets:
            question_node = self.env.variables[widget_id]["node"]

            new_value = self.get_widget_val(widget_id)
            if new_value == '':
                self.env.set_var_value(widget_id, Undefined)
                continue

            question_type = question_node.type
            new_value = question_type.parse_value(new_value)
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
        save_obj = SaveQuestionaire(self.ast, self.env, self.handler)
        save_obj.start_traversal()
