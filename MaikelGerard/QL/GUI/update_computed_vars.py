from QL.GUI.expression_evaluator import ExpressionEvaluator


class UpdateComputedVars(object):
    def __init__(self, ast, env):
        self.ast = ast
        self.env = env

        self.changed = False
        self.expr_eval = ExpressionEvaluator(env)

    def start_traversal(self):
        while True:
            self.changed = False
            self.ast.accept(self)

            if not self.changed:
                break

    def if_node(self, if_node):
        if_node.if_block.accept(self)

    def if_else_node(self, if_else_node):
        if_else_node.else_block.accept(self)
        if_else_node.if_block.accept(self)

    def question_node(self, question_node):
        pass

    def comp_question_node(self, comp_question_node):
        identifier = comp_question_node.name
        new_value = comp_question_node.expression.accept(self.expr_eval)

        if new_value != self.env.get_var_value(identifier):
            self.changed = True
        self.env.set_var_value(identifier, new_value)
