from QL.undefined import Undefined


class EvaluateDrawState(object):
    def __init__(self, form_gui, ast, env, evaluator, error_handler):
        """
        :type ast: QL.AST.FormNode
        :type env: QL.environment.Environment
        :type error_handler: QL.errorHandler.ErrorHandler
        :type evaluator: QL.Stages.evaluator.Evaluate
        :type form_gui: QL.GUI.drawGUI.DrawGUI
        """
        self.ast = ast
        self.env = env
        self.evaluator = evaluator
        self.handler = error_handler
        self.form_gui = form_gui

    def start_traversal(self):
        self.ast.accept(self, True)

    def if_node(self, if_node, show_state):
        condition = if_node.condition.accept(self.evaluator)
        condition = condition if condition != Undefined else False

        if_node.if_block.accept(self, condition and show_state)

    def if_else_node(self, if_else_node, show_state):
        condition = if_else_node.condition.accept(self.evaluator)
        condition = condition if condition != Undefined else False

        if_else_node.if_block.accept(self, show_state and condition)
        if_else_node.else_block.accept(self, show_state and not condition)

    def question_node(self, question_node, show_state):
        identifier = question_node.name
        if not show_state:
            self.form_gui.hide_widget(identifier)
        else:
            value = self.env.get_var_value(identifier)
            if value == Undefined:
                value = ''
            self.form_gui.show_widget(identifier)
            self.form_gui.set_widget_val(identifier, value)

    def comp_question_node(self, comp_question_node, show_state):
        identifier = comp_question_node.name
        if not show_state:
            self.form_gui.hide_widget(identifier)
        else:
            value = self.env.get_var_value(identifier)
            if value != Undefined:
                self.form_gui.set_widget_val(identifier, value)
                self.form_gui.show_widget(identifier)
            else:
                self.form_gui.hide_widget(identifier)
