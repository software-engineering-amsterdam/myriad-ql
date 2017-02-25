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
            self.draw_question(question)
        self.gui.start()

        # Output errors afterwards.
        self.handler.print_errors()
        self.env.context = prev_context

    def draw_question(self, question):
        question_node = question["node"]
        identifier = question_node.name.val
        question_str = question_node.question.val

        # Check on undefined questions, and get their default value.
        if question["value"] == Undefined:
            value = question_node.type.default
            # A boolean question has always to be evaluated to false, and it is not logical to
            # expect a user to check a checkbox to un-check it to get the correct form displayed.
            if question_node.type == BoolTypeNode:
                question["value"] = False
        else:
            value = question["value"]

        # Visit the type node to get the draw function to use.
        draw_function = question_node.type.accept(self)
        draw_function(identifier, question_str, value)

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
