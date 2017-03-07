from QL.undefined import Undefined

# TODO: Split evaluator in multiple evaluators to split concerns.
# TODO: Create expression evaluator to ensure correct order.


class ExpressionEvaluator(object):
    def __init__(self, env):
        self.env = env

    @staticmethod
    def eval_monop(result, lambda_expr):
        if result == Undefined:
            return Undefined
        return lambda_expr(result)

    def neg_node(self, neg_node):
        result = neg_node.expression.accept(self)
        return self.eval_monop(result, lambda x: not x)

    def min_node(self, min_node):
        result = min_node.expression.accept(self)
        return self.eval_monop(result, lambda x: -x)

    def plus_node(self, plus_node):
        result = plus_node.expression.accept(self)
        return self.eval_monop(result, lambda x: +x)

    @staticmethod
    def eval_binop(left, right, lambda_expr):
        if left == Undefined or right == Undefined:
            return Undefined
        return lambda_expr(left, right)

    def mul_node(self, mul_node):
        left, right = mul_node.left.accept(self), mul_node.right.accept(self)
        return self.eval_binop(left, right, lambda x, y: x * y)

    def div_node(self, div_node):
        right = div_node.right.accept(self)

        if right == 0:
            return Undefined
        left = div_node.left.accept(self)
        return self.eval_binop(left, right, lambda x, y: x / y)

    def add_node(self, add_node):
        left, right = add_node.left.accept(self), add_node.right.accept(self)
        return self.eval_binop(left, right, lambda x, y: x + y)

    def sub_node(self, sub_node):
        left, right = sub_node.left.accept(self), sub_node.right.accept(self)
        return self.eval_binop(left, right, lambda x, y: x - y)

    def lt_node(self, lt_node):
        left, right = lt_node.left.accept(self), lt_node.right.accept(self)
        return self.eval_binop(left, right, lambda x, y: x < y)

    def lte_node(self, lte_node):
        left, right = lte_node.left.accept(self), lte_node.right.accept(self)
        return self.eval_binop(left, right, lambda x, y: x <= y)

    def gt_node(self, gt_node):
        left, right = gt_node.left.accept(self), gt_node.right.accept(self)
        return self.eval_binop(left, right, lambda x, y: x > y)

    def gte_node(self, gte_node):
        left, right = gte_node.left.accept(self), gte_node.right.accept(self)
        return self.eval_binop(left, right, lambda x, y: x >= y)

    def eq_node(self, eq_node):
        left, right = eq_node.left.accept(self), eq_node.right.accept(self)
        return self.eval_binop(left, right, lambda x, y: x == y)

    def neq_node(self, neq_node):
        left, right = neq_node.left.accept(self), neq_node.right.accept(self)
        return self.eval_binop(left, right, lambda x, y: x != y)

    def and_node(self, and_node):
        left, right = and_node.left.accept(self), and_node.right.accept(self)
        return self.eval_binop(left, right, lambda x, y: x and y)

    def or_node(self, or_node):
        left, right = or_node.left.accept(self), or_node.right.accept(self)
        return self.eval_binop(left, right, lambda x, y: x or y)

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
        return self.env.get_var_value(var_node.name)

    @staticmethod
    def decimal_node(decimal_node):
        """ :type decimal_node: AST.DecimalNode """
        return decimal_node.val

    @staticmethod
    def date_node(date_node):
        """ :type date_node: AST.DateNode """
        return date_node.val
