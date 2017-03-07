from QL.Undefined import Undefined


class EvaluateDrawState(object):
    def __init__(self, form_gui, ast, env, evaluator, error_handler):
        """
        :type ast: QL.AST.FormNode
        :type env: QL.Environment.Environment
        :type error_handler: QL.ErrorHandler.ErrorHandler
        :type evaluator: QL.Stages.Evaluator.Evaluate
        :type form_gui: QL.GUI.DrawGUI.DrawGUI
        """
        self.ast = ast
        self.env = env
        self.evaluator = evaluator
        self.handler = error_handler
        self.form_gui = form_gui
        self.show_stack = []

    def start_traversal(self):
        self.show_stack = []
        self.ast.accept(self)

    def traverse_branch(self, node_branch, condition):
        self.show_stack.append(condition)
        node_branch.accept(self)
        self.show_stack.pop()

    def if_node(self, if_node):
        condition = if_node.expression.accept(self.evaluator)
        condition = condition if condition != Undefined else False

        self.traverse_branch(if_node.if_block, condition)

    def if_else_node(self, if_else_node):
        condition = if_else_node.expression.accept(self.evaluator)
        condition = condition if condition != Undefined else False

        self.traverse_branch(if_else_node.if_block, condition)
        self.traverse_branch(if_else_node.else_block, not condition)

    def is_shown(self):
        return all(self.show_stack)

    def question_node(self, question_node):
        identifier = question_node.name
        if not self.is_shown():
            self.form_gui.hide_widget(identifier)
        else:
            value = self.env.get_var_value(identifier)
            if value == Undefined:
                value = ''
            self.form_gui.show_widget(identifier)
            self.form_gui.set_widget_val(identifier, value)

    def comp_question_node(self, comp_question_node):
        identifier = comp_question_node.name
        if not self.is_shown():
            self.form_gui.hide_widget(identifier)
        else:
            value = self.env.get_var_value(identifier)
            if value != Undefined:
                self.form_gui.set_widget_val(identifier, value)
                self.form_gui.show_widget(identifier)
            else:
                self.form_gui.hide_widget(identifier)
