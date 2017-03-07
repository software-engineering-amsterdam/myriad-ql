from QL.undefined import Undefined
from QL.stages.expressionEvaluator import ExpressionEvaluator


class EvaluateDrawState(object):
    def __init__(self, form_gui, ast, env, error_handler):
        """
        :type ast: QL.AST.FormNode
        :type env: QL.environment.Environment
        :type error_handler: QL.errorHandler.ErrorHandler
        :type form_gui: QL.GUI.drawGUI.DrawGUI
        """
        self.ast = ast
        self.env = env
        self.evaluator = ExpressionEvaluator(env)
        self.handler = error_handler
        self.form_gui = form_gui

    def start_traversal(self):
        self.ast.accept(self, True)

    def if_node(self, if_node, widget_visible):
        condition = if_node.condition.accept(self.evaluator)
        condition = condition if condition != Undefined else False

        if_node.if_block.accept(self, condition and widget_visible)

    def if_else_node(self, if_else_node, widget_visible):
        condition = if_else_node.condition.accept(self.evaluator)
        condition = condition if condition != Undefined else False

        if_else_node.if_block.accept(self, widget_visible and condition)
        if_else_node.else_block.accept(self, widget_visible and not condition)

    def question_node(self, question_node, widget_visible):
        identifier = question_node.name
        if not widget_visible:
            self.form_gui.hide_widget(identifier)
        else:
            value = self.env.get_var_value(identifier)
            if value == Undefined:
                value = ''
            self.form_gui.show_widget(identifier)
            self.form_gui.set_widget_val(identifier, value)

    def comp_question_node(self, comp_question_node, widget_visible):
        identifier = comp_question_node.name
        if not widget_visible:
            self.form_gui.hide_widget(identifier)
        else:
            value = self.env.get_var_value(identifier)
            if value != Undefined:
                self.form_gui.set_widget_val(identifier, value)
                self.form_gui.show_widget(identifier)
            else:
                self.form_gui.hide_widget(identifier)
