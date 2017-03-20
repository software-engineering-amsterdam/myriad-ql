# -*- coding: utf-8 -*-
from appJar import gui
from collections import OrderedDict

from QL.undefined import Undefined
from QL.GUI.evaluateDrawState import EvaluateDrawState
from QL.GUI.saveQuestionaire import SaveQuestionaire
from QL.stages.updateComputedVars import UpdateComputedVars
import QL.GUI.widgets as Widgets


class DrawGUI(object):
    def __init__(self, ast, env):
        self.ast = ast
        self.env = env

        self.main = gui("QL Language Form - © 2017")
        self.main.addLabel("header", "Please fill in the form!", 0, 0, 2)
        self.row = 1

        self.widgets = OrderedDict()
        self.update_computed_variables = UpdateComputedVars(ast, env)
        self.set_draw_state = EvaluateDrawState(self.widgets, ast, env)

    def show(self):
        self.ast.accept(self.update_computed_variables)
        self.update_variables()

        self.set_draw_state.start_traversal()
        self.add_buttons()
        self.main.go()

    def start_traversal(self):
        self.ast.accept(self)
        self.show()

    def redraw(self, _):
        self.update_variables()
        self.ast.accept(self.update_computed_variables)
        self.set_draw_state.start_traversal()

    def add_buttons(self):
        self.main.addButtons(
            ["Save details", "Exit"], self.button_action, self.row, 0, 2
        )

    def update_variables(self):
        for widget_id in self.widgets:
            question_node = self.env.variables[widget_id]["node"]
            widget = self.widgets[widget_id]
            new_value = widget.get_entry()

            if new_value == '':
                self.env.set_var_value(widget_id, Undefined)
                continue

            question_type = question_node.type
            new_value = question_type.parse_value(new_value)
            self.env.set_var_value(widget_id, new_value)

    def button_action(self, button_pressed):
        if button_pressed == "Save details":
            self.save_data()
        else:
            self.main.stop()

    def save_data(self):
        save_obj = SaveQuestionaire(self.ast, self.widgets)
        save_obj.start_traversal()

    def if_node(self, if_node):
        if_node.if_block.accept(self)

    def if_else_node(self, if_else_node):
        if_else_node.if_block.accept(self)
        if_else_node.else_block.accept(self)

    def add_widget(self, widget_class, identifier, question):
        new_widget = widget_class(self, identifier, question, self.row)
        self.widgets[identifier] = new_widget
        self.row += 1

    def question_node(self, question_node):
        identifier = question_node.name
        question_str = question_node.question

        # Visit the type node to get the draw function to use.
        widget_class = question_node.type.accept(self)
        self.add_widget(widget_class, identifier, question_str)

    def comp_question_node(self, comp_question):
        identifier = comp_question.name
        question_str = comp_question.question

        # Draw the value of the computed question within a label.
        widget_class = Widgets.ComputedLabelWidget
        self.add_widget(widget_class, identifier, question_str)

    @staticmethod
    def bool_type_node(_):
        return Widgets.CheckBoxWidget

    @staticmethod
    def int_type_node(_):
        return Widgets.SpinBoxWidget

    @staticmethod
    def decimal_type_node(_):
        return Widgets.NumericWidget

    @staticmethod
    def string_type_node(_):
        return Widgets.EntryWidget

    @staticmethod
    def date_type_node(_):
        return Widgets.DateWidget
