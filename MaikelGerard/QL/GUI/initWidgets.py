import QL.GUI.widgets as Widgets
from QL.GUI.drawGUI import DrawGUI

# TODO: See if we can merge InitWidgets and DrawGUI.


class InitWidgets(object):
    def __init__(self, ast, env, error_handler):
        """
        :type env: Environment.Environment
        :type error_handler: ErrorHandler.ErrorHandler
        """
        self.ast = ast
        self.env = env
        self.error_handler = error_handler

        # Create a GUI instance.
        self.gui = DrawGUI(ast, env, error_handler)

    def get_initialized_gui(self):
        return self.gui

    def start_traversal(self):
        self.ast.accept(self)

    def if_node(self, if_node):
        if_node.if_block.accept(self)

    def if_else_node(self, if_else_node):
        if_else_node.if_block.accept(self)
        if_else_node.else_block.accept(self)

    def question_node(self, question_node):
        identifier = question_node.name
        question_str = question_node.question

        # Visit the type node to get the draw function to use.
        widget_class = question_node.type.accept(self)
        self.gui.add_widget(widget_class, identifier, question_str)

    def comp_question_node(self, comp_question):
        identifier = comp_question.name
        question_str = comp_question.question

        # Draw the value of the computed question within a label.
        widget_class = Widgets.ComputedLabelWidget
        self.gui.add_widget(widget_class, identifier, question_str)

    @staticmethod
    def bool_type_node(_):
        return Widgets.CheckBoxWidget

    @staticmethod
    def int_type_node(_):
        return Widgets.SpinBoxWidget

    @staticmethod
    def money_type_node(_):
        return Widgets.NumericWidget

    @staticmethod
    def decimal_type_node(_):
        return Widgets.NumericWidget

    @staticmethod
    def string_type_node(_):
        return Widgets.EntryWidget

    @staticmethod
    def date_type_node(_):
        return Widgets.DateWidget
