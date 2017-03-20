from QL.undefined import Undefined
from QL.stages.expression_evaluator import ExpressionEvaluator


class EvaluateDrawState(object):
    def __init__(self, widgets, ast, env):
        self.ast = ast
        self.env = env
        self.evaluator = ExpressionEvaluator(env)
        self.widgets = widgets

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
        widget = self.widgets[question_node.name]
        if not widget_visible:
            widget.hide()
        else:
            value = self.env.get_var_value(identifier)
            if value == Undefined:
                value = ''
            widget.set_entry(value)
            widget.show()

    def comp_question_node(self, comp_question_node, widget_visible):
        identifier = comp_question_node.name
        widget = self.widgets[identifier]
        if not widget_visible:
            widget.hide()
        else:
            value = self.env.get_var_value(identifier)
            if value != Undefined:
                widget.set_entry(value)
                widget.show()
            else:
                widget.hide()
