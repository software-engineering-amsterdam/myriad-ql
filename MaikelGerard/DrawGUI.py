from FormGUI import FormGUI
from Undefined import Undefined
from AST import BoolTypeNode


class DrawGUI(object):
    def __init__(self, env, error_handler):
        """
        :type env: Environment.Environment
        :type error_handler: ErrorHandler.ErrorHandler
        """
        self.env = env
        self.handler = error_handler

        # Create a GUI instance.
        self.gui = FormGUI(self)

    def start_traversal(self):
        self.handler.clear_errors()

        # Set context for outputting errors; start drawing.
        prev_context = self.env.context
        self.env.context = "DrawGUI"

        # Draw all the questions in the environment.
        for question in self.env.variables.values():
            question["node"].accept(self)
        self.gui.start()

        # Output errors afterwards.
        self.handler.print_errors()
        self.env.context = prev_context

    def get_question_val(self, identifier, question_node):
        var_value = self.env.get_var_value(identifier)
        if var_value != Undefined:
            return var_value

        # Value is undefined, we evaluate the boolean question to False as it is not logical to
        # expect a user to check a checkbox to un-check to get a defined checkbox value.
        if question_node.type == BoolTypeNode:
            return False
        return question_node.type.default

    def question_node(self, question_node):
        identifier = question_node.name.val
        question_str = question_node.question.val

        # Check on undefined questions, and get their default value.
        var_value = self.get_question_val(identifier, question_node)
        self.env.set_var_value(identifier, var_value)

        # Visit the type node to get the draw function to use.
        draw_function = question_node.type.accept(self)
        draw_function(identifier, question_str, var_value)

    def comp_question_node(self, comp_question):
        identifier = comp_question.name.val
        question_str = comp_question.question.val

        # Get any value of the computed question.
        var_value = self.env.get_var_value(identifier)

        # Draw the value of the computed question within a label.
        self.gui.add_computed_question(identifier, question_str, var_value)

    def bool_type_node(self, _):
        return self.gui.add_checkbox_question

    def int_type_node(self, _):
        return self.gui.add_spinbox_question

    def money_type_node(self, _):
        return self.gui.add_numeric_entry_question

    def decimal_type_node(self, _):
        return self.gui.add_numeric_entry_question

    def string_type_node(self, _):
        return self.gui.add_entry_question

    def date_type_node(self, _):
        return self.gui.add_datepicker_question
