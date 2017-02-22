from ErrorHandler import ErrorHandler
from TypeEnums import TypeEnums as Type


class TypeChecker(object):
    def __init__(self, ast):
        """ :type ast: AST.QuestionnaireAST """
        self.ast = ast

        self.env = {}
        self.handler = ErrorHandler()
        self.context = "TypeChecker"

    def start_traversal(self):
        # Ensure the environment and error log are empty.
        self.env = {}
        self.handler.clear_errors()
        self.ast.root.accept(self)

        # Print errors afterwards.
        self.handler.print_errors()

    @staticmethod
    def highest_number_type(left, right):
        if left == Type.DECIMAL or right == Type.DECIMAL:
            return Type.DECIMAL
        elif left == Type.MONEY or right == Type.MONEY:
            return Type.MONEY
        elif left == Type.INTEGER and right == Type.INTEGER:
            return Type.INTEGER
        else:
            assert False, "Not both operands are of number type!"

    def is_numeric_binop(self, left, right):
        if left in Type.NUMERIC and right in Type.NUMERIC:
            return True
        return False

    def get_monop_type(self, node):
        return node.expression.accept(self)

    def get_binop_types(self, node):
        return node.left.accept(self), node.right.accept(self)

    def is_valid_monop(self, node, var_type, valid_types):
        error = "Invalid type for '{}': {}".format(node.operator, var_type)

        if var_type not in valid_types:
            self.handler.add_error(self.context, node, error)
            return False
        return True

    def is_valid_binop(self, node, left, right, valid_types):
        op = node.operator
        error = "Invalid types for '{}': {}, {}".format(op, left, right)

        # Equal types is always correct, if in valid_types.
        if left == right:
            if left not in valid_types:
                self.handler.add_error(self.context, node, error)
                return False
            return True
        # Only numbers can be used in combination.
        elif self.is_numeric_binop(left, right):
            if left not in valid_types and right not in valid_types:
                return False
            return True

        # Incompatible combination of types.
        self.handler.add_error(self.context, node, error)
        return False

    def question_node(self, question_node):
        """ :type question_node: AST.QuestionNode """
        self.env[question_node.name.val] = question_node.type

    def comp_question_node(self, comp_question_node):
        """ :type comp_question_node: AST.CompQuestionNode """
        self.env[comp_question_node.name.val] = comp_question_node.type
        comp_question_node.expression.accept(self)

    def if_node(self, if_node):
        """ :type if_node: AST.IfNode """
        if_node.expression.accept(self)
        if_node.if_block.accept(self)

    def if_else_node(self, if_else_node):
        """ :type if_else_node: AST.IfElseNode """
        if_else_node.expression.accept(self)
        if_else_node.if_block.accept(self)
        if_else_node.else_block.accept(self)

    def mon_op_node(self, mon_op_node):
        """ Default behavior for monOps. """
        var_type = self.get_monop_type(mon_op_node)
        valid = self.is_valid_monop(mon_op_node, var_type, Type.NUMERIC)
        return Type.DECIMAL if not valid else var_type

    def neg_node(self, neg_node):
        """ '!' has different behavior than the other monOps."""
        var_type = self.get_monop_type(neg_node)
        is_valid = self.is_valid_monop(neg_node, var_type, Type.BOOLEAN)
        return Type.BOOLEAN if not is_valid else var_type

    def min_node(self, min_node):
        return self.mon_op_node(min_node)

    def plus_node(self, plus_node):
        return self.mon_op_node(plus_node)

    def arithmetic_expr_node(self, expr_node):
        """ Default behavior for arithmetic binOps. """
        left, right = self.get_binop_types(expr_node)
        valid = self.is_valid_binop(expr_node, left, right, Type.NUMERIC)
        if not valid:
            return Type.DECIMAL
        return self.highest_number_type(left, right)

    def add_node(self, add_node):
        """ '+' has different behavior than the other arithmetic binOps. """
        left, right = self.get_binop_types(add_node)
        numeric = self.is_numeric_binop(left, right)
        valid = self.is_valid_binop(add_node, left, right, Type.ALPHANUMERIC)

        if not valid:
            # TODO: case ("abc" + 1) + (1 + 1) gives additional errors.
            return Type.DECIMAL
        elif numeric:
            return self.highest_number_type(left, right)
        return Type.STRING

    def sub_node(self, sub_node):
        return self.arithmetic_expr_node(sub_node)

    def mul_node(self, mul_node):
        return self.arithmetic_expr_node(mul_node)

    def comparison_expr_node(self, expr_node):
        """ Default behavior for comparison binOps. """
        left, right = self.get_binop_types(expr_node)
        self.is_valid_binop(expr_node, left, right, Type.NOT_BOOLEAN)

        return Type.BOOLEAN

    def lt_node(self, lt_node):
        return self.comparison_expr_node(lt_node)

    def lte_node(self, lte_node):
        return self.comparison_expr_node(lte_node)

    def gt_node(self, gt_node):
        return self.comparison_expr_node(gt_node)

    def gte_node(self, gte_node):
        return self.comparison_expr_node(gte_node)

    def eq_node(self, eq_node):
        return self.comparison_expr_node(eq_node)

    def neq_node(self, neq_node):
        return self.comparison_expr_node(neq_node)

    def logical_expr_node(self, expr_node):
        left, right = self.get_binop_types(expr_node)
        self.is_valid_binop(expr_node, left, right, Type.BOOLEAN)

        return Type.BOOLEAN

    def and_node(self, and_node):
        return self.logical_expr_node(and_node)

    def or_node(self, or_node):
        return self.logical_expr_node(or_node)

    @staticmethod
    def string_node(_):
        return Type.STRING

    @staticmethod
    def int_node(_):
        return Type.INTEGER

    @staticmethod
    def bool_node(_):
        return Type.BOOLEAN

    def var_node(self, var_node):
        error = "Variable '{}' is not defined!".format(var_node.val)

        if var_node.val not in self.env:
            self.handler.add_error(self.context, var_node, error)
            return None
        return self.env[var_node.val]

    @staticmethod
    def decimal_node(_):
        return Type.DECIMAL

    @staticmethod
    def date_node(_):
        return Type.DATE
