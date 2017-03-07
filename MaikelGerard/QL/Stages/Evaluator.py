from QL.Undefined import Undefined


class Evaluate(object):
    def __init__(self, ast, env, error_handler):
        """
        :type ast: AST.QuestionnaireAST
        :type env: Environment.Environment
        :type error_handler: ErrorHandler.ErrorHandler
        """
        self.ast = ast
        self.env = env
        self.handler = error_handler

    def start_traversal(self):
        self.handler.clear_errors()

        # Set context for outputting errors; start traversal.
        prev_context = self.env.context
        self.env.context = "Evaluate"
        self.ast.root.accept(self)

        # Output errors afterwards.
        self.handler.print_errors()
        self.env.context = prev_context

    def if_node(self, if_node):
        if_node.if_block.accept(self)

    def if_else_node(self, if_else_node):
        if_else_node.else_block.accept(self)
        if_else_node.if_block.accept(self)

    def question_node(self, question_node):
        pass

    def comp_question_node(self, comp_question_node):
        identifier = comp_question_node.get_identifier()
        new_value = comp_question_node.expression.accept(self)
        self.env.set_var_value(identifier, new_value)

    def neg_node(self, neg_node):
        result = neg_node.expression.accept(self)
        return (not result) if result != Undefined else Undefined

    def min_node(self, min_node):
        result = min_node.expression.accept(self)
        return (-result) if result != Undefined else Undefined

    def plus_node(self, plus_node):
        result = plus_node.expression.accept(self)
        return (+result) if result != Undefined else Undefined

    def is_defined_binop(self, left, right):
        if left == Undefined or right == Undefined:
            return False
        return True

    def mul_node(self, mul_node):
        left, right = mul_node.left.accept(self), mul_node.right.accept(self)
        return (left * right) if self.is_defined_binop(left, right) else Undefined

    def div_node(self, div_node):
        # When diving by zero show an warning, and change the return value to Undefined.
        right = div_node.right.accept(self)

        if right == 0:
            self.handler.add_zero_division_warning(self.env.context, div_node)
            return Undefined
        left = div_node.left.accept(self)
        return (left / right) if self.is_defined_binop(left, right) else Undefined

    def add_node(self, add_node):
        left, right = add_node.left.accept(self), add_node.right.accept(self)
        return (left + right) if self.is_defined_binop(left, right) else Undefined

    def sub_node(self, sub_node):
        left, right = sub_node.left.accept(self), sub_node.right.accept(self)
        return (left - right) if self.is_defined_binop(left, right) else Undefined

    def lt_node(self, lt_node):
        left, right = lt_node.left.accept(self), lt_node.right.accept(self)
        return (left < right) if self.is_defined_binop(left, right) else Undefined

    def lte_node(self, lte_node):
        left, right = lte_node.left.accept(self), lte_node.right.accept(self)
        return (left <= right) if self.is_defined_binop(left, right) else Undefined

    def gt_node(self, gt_node):
        left, right = gt_node.left.accept(self), gt_node.right.accept(self)
        return (left > right) if self.is_defined_binop(left, right) else Undefined

    def gte_node(self, gte_node):
        left, right = gte_node.left.accept(self), gte_node.right.accept(self)
        return (left >= right) if self.is_defined_binop(left, right) else Undefined

    def eq_node(self, eq_node):
        left, right = eq_node.left.accept(self), eq_node.right.accept(self)
        return (left == right) if self.is_defined_binop(left, right) else Undefined

    def neq_node(self, neq_node):
        left, right = neq_node.left.accept(self), neq_node.right.accept(self)
        return (left != right) if self.is_defined_binop(left, right) else Undefined

    def and_node(self, and_node):
        left, right = and_node.left.accept(self), and_node.right.accept(self)
        return (left and right) if self.is_defined_binop(left, right) else Undefined

    def or_node(self, or_node):
        left, right = or_node.left.accept(self), or_node.right.accept(self)
        return (left or right) if self.is_defined_binop(left, right) else Undefined

    @staticmethod
    def string_node(string_node):
        """ :type string_node: AST.StringNode """
        return string_node.val

    @staticmethod
    def int_node(int_node):
        """ :type int_node: AST.IntNode"""
        return int_node.val

    @staticmethod
    def bool_node(bool_node):
        """ :type bool_node: AST.BoolNode """
        return bool_node.val

    def var_node(self, var_node):
        """ :type var_node: AST.VarNode """
        return self.env.get_var_value(var_node.val)

    @staticmethod
    def decimal_node(decimal_node):
        """ :type decimal_node: AST.DecimalNode """
        return decimal_node.val

    @staticmethod
    def date_node(date_node):
        """ :type date_node: AST.DateNode """
        return date_node.val
