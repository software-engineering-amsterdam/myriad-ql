class InitEnvironment(object):
    def __init__(self, ast, env, error_handler):
        self.ast = ast
        self.env = env
        self.handler = error_handler

        self.conditional_vars = {}
        self.labels = []

    def start_traversal(self):
        # Ensure the environment and error log are empty.
        self.env.clear_env()
        self.conditional_vars = {}
        self.labels = []

        self.ast.accept(self)

        # Check if all variables found in the if statements are defined as
        # question, if not, report the first occurrence of the undeclared
        # variable in the program to the error handler.
        for identifier in self.conditional_vars:
            if identifier not in self.env.variables:
                var_node = self.conditional_vars[identifier]
                self.handler.add_undecl_var_error(var_node)

    def add_var_to_env(self, question_node):
        self.env.add_var(question_node)
        if question_node.question in self.labels:
            self.handler.add_dup_label_warning(question_node)
        else:
            self.labels.append(question_node.question)

    def question_node(self, question_node):
        self.add_var_to_env(question_node)

    def comp_question_node(self, comp_question_node):
        self.add_var_to_env(comp_question_node)
        comp_question_node.expression.accept(self)

    def if_node(self, if_node):
        if_node.condition.accept(self)
        if_node.if_block.accept(self)

    def if_else_node(self, if_else_node):
        if_else_node.condition.accept(self)

        if_else_node.if_block.accept(self)
        if_else_node.else_block.accept(self)

    def neg_node(self, neg_node):
        neg_node.expression.accept(self)

    def min_node(self, min_node):
        min_node.expression.accept(self)

    def plus_node(self, plus_node):
        plus_node.expression.accept(self)

    def traverse_expr(self, node):
        node.left.accept(self)
        node.right.accept(self)

    def add_node(self, add_node):
        self.traverse_expr(add_node)

    def sub_node(self, sub_node):
        self.traverse_expr(sub_node)

    def mul_node(self, mul_node):
        self.traverse_expr(mul_node)

    def div_node(self, mul_node):
        self.traverse_expr(mul_node)

    def lt_node(self, lt_node):
        self.traverse_expr(lt_node)

    def lte_node(self, lte_node):
        self.traverse_expr(lte_node)

    def gt_node(self, gt_node):
        self.traverse_expr(gt_node)

    def gte_node(self, gte_node):
        self.traverse_expr(gte_node)

    def eq_node(self, eq_node):
        self.traverse_expr(eq_node)

    def neq_node(self, neq_node):
        self.traverse_expr(neq_node)

    def and_node(self, and_node):
        self.traverse_expr(and_node)

    def or_node(self, or_node):
        self.traverse_expr(or_node)

    def string_node(self, _):
        pass

    def int_node(self, _):
        pass

    def bool_node(self, _):
        pass

    def var_node(self, var_node):
        if var_node.name in self.conditional_vars:
            return
        self.conditional_vars[var_node.name] = var_node

    def decimal_node(self, _):
        pass

    def date_node(self, _):
        pass
