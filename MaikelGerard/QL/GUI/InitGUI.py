from QL.GUI.FormGUI import FormGUI
import QL.GUI.Widgets as Widgets


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
        self.gui = FormGUI()

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
        identifier = question_node.get_identifier()
        question_str = question_node.get_question()

        # Visit the type node to get the draw function to use.
        widget_class = question_node.type.accept(self)
        self.gui.add_widget(widget_class, identifier, question_str)

    def comp_question_node(self, comp_question):
        identifier = comp_question.get_identifier()
        question_str = comp_question.get_question()

        # Draw the value of the computed question within a label.
        widget_class = Widgets.ComputedLabelWidget
        self.gui.add_widget(widget_class, identifier, question_str)

    def bool_type_node(self, _):
        return Widgets.CheckBoxWidget

    def int_type_node(self, _):
        return Widgets.SpinBoxWidget

    def money_type_node(self, _):
        return Widgets.NumericWidget

    def decimal_type_node(self, _):
        return Widgets.NumericWidget

    def string_type_node(self, _):
        return Widgets.EntryWidget

#    def date_type_node(self, _):
#        return self.gui.add_datepicker_question
