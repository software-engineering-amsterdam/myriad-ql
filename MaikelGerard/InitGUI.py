from FormGUI import FormGUI
from Undefined import Undefined
from AST import BoolTypeNode


class InitGUI(object):
    def __init__(self, ast, env, evaluator, error_handler):
        """
        :type env: Environment.Environment
        :type error_handler: ErrorHandler.ErrorHandler
        """
        self.env = env
        self.ast = ast
        self.error_handler = error_handler
        self.evaluator = evaluator

        # Create a GUI instance.
        self.gui = FormGUI(self)

    def start_traversal(self):
        self.error_handler.clear_errors()

        # Set context for outputting errors; start drawing.
        prev_context = self.env.context
        self.env.context = "InitGUI"

        # Add and build up all the questions in the environment.
        self.ast.root.accept(self)

        # Output errors afterwards.
        self.error_handler.print_errors()
        self.env.context = prev_context

    def if_node(self, if_node):
        if_node.if_block.accept(self)

    def if_else_node(self, if_else_node):
        if_else_node.if_block.accept(self)
        if_else_node.else_block.accept(self)

    def question_node(self, question_node):
        identifier = question_node.name.val
        question_str = question_node.question.val

        # Value is always undefined, we evaluate the boolean question to False as
        # it is not logical to expect a user to check a checkbox to un-check to get
        # a defined checkbox value.
        if question_node.type == BoolTypeNode():
            question_node.is_defined = True
        var_value = question_node.get_default_val()

        # Visit the type node to get the draw function to use.
        draw_function = question_node.type.accept(self)
        draw_function(identifier, question_str, var_value)

    def comp_question_node(self, comp_question):
        identifier = comp_question.name.val
        question_str = comp_question.question.val

        # Draw the value of the computed question within a label.
        self.gui.add_computed_question(identifier, question_str, Undefined)

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
