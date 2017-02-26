from FormGUI import FormGUI
from Undefined import Undefined
from AST import BoolTypeNode


class DrawGUI(object):
    def __init__(self, ast, env, evaluator, error_handler):
        """
        :type env: Environment.Environment
        :type error_handler: ErrorHandler.ErrorHandler
        """
        self.env = env
        self.ast = ast
        self.handler = error_handler
        self.evaluator = evaluator

        # Create a GUI instance.
        self.gui = FormGUI(self)

    def start_traversal(self):
        self.handler.clear_errors()

        # Set context for outputting errors; start drawing.
        prev_context = self.env.context
        self.env.context = "DrawGUI"

        # Draw all the questions in the environment.
        self.ast.root.accept(self)
        self.gui.start()

        # Output errors afterwards.
        self.handler.print_errors()
        self.env.context = prev_context

    def adjust_env(self, question_values):
        for question in question_values:
            question_node = self.env.variables[question]["node"]

            new_value = question_values[question]
            if new_value == "@undefined":
                continue

            question_type = question_node.type
            new_value = question_type.convert_to_type(new_value)

            # Value is non default, update the environment.
            if question_node.is_defined or new_value != question_type.default:
                self.env.set_var_value(question, new_value)
                question_node.is_defined = True

    def if_node(self, if_node):
        if_node.if_block.accept(self)

    def if_else_node(self, if_else_node):
        if_else_node.if_block.accept(self)
        if_else_node.else_block.accept(self)

    def get_question_val(self, identifier, question_node):
        var_value = self.env.get_var_value(identifier)
        if var_value != Undefined:
            return var_value

        # Value is undefined, we evaluate the boolean question to False as it is not logical to
        # expect a user to check a checkbox to un-check to get a defined checkbox value.
        if question_node.type == BoolTypeNode():
            question_node.is_defined = True
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
