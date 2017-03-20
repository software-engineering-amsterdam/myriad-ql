class PrintHandler(object):
    def __init__(self):
        self.output = ""

    def print_ast(self, ast):
        self.output = ""
        self.form_block(ast)
        print(self.output)

    def form_block(self, form_node):
        self.output += "form {}: \n".format(form_node.name)
        form_node.form_block.accept(self, 1)

    def if_node(self, if_node, indent):
        condition = if_node.condition.accept(self)
        self.output += indent * "    " + "if ({}):\n".format(condition)
        if_node.if_block.accept(self, indent + 1)

    def if_else_node(self, if_else_node, indent):
        condition = if_else_node.condition.accept(self)
        self.output += indent * "    " + "if ({}):\n".format(condition)
        if_else_node.if_block.accept(self, indent + 1)
        self.output += indent * "    " + "else:\n"
        if_else_node.else_block.accept(self, indent + 1)

    def question_node(self, question_node, indent):
        self.output += indent * "    " + "question: {} {}: {}\n".format(
            question_node.question,
            question_node.name,
            question_node.type.accept(self)
        )

    def comp_question_node(self, comp_question_node, indent):
        self.output += indent * "    " + "question: {} {}: {}".format(
            comp_question_node.question,
            comp_question_node.name,
            comp_question_node.type.accept(self)
        )
        expr = comp_question_node.expression.accept(self)
        self.output += " = ({})\n".format(expr)

    def neg_node(self, neg_node):
        output = neg_node.expression.accept(self)
        return "!{}".format(output)

    def min_node(self, min_node):
        output = min_node.expression.accept(self)
        return "-{}".format(output)

    def plus_node(self, plus_node):
        output = plus_node.expression.accept(self)
        return "+{}".format(output)

    def mul_node(self, mul_node):
        left, right = mul_node.left.accept(self), mul_node.right.accept(self)
        return "({} * {})".format(left, right)

    def div_node(self, div_node):
        left, right = div_node.left.accept(self), div_node.right.accept(self)
        return "({} / {})".format(left, right)

    def add_node(self, add_node):
        left, right = add_node.left.accept(self), add_node.right.accept(self)
        return "({} + {})".format(left, right)

    def sub_node(self, sub_node):
        left, right = sub_node.left.accept(self), sub_node.right.accept(self)
        return "({} - {})".format(left, right)

    def lt_node(self, lt_node):
        left, right = lt_node.left.accept(self), lt_node.right.accept(self)
        return "({} < {})".format(left, right)

    def lte_node(self, lte_node):
        left, right = lte_node.left.accept(self), lte_node.right.accept(self)
        return "({} <= {})".format(left, right)

    def gt_node(self, gt_node):
        left, right = gt_node.left.accept(self), gt_node.right.accept(self)
        return "({} > {})".format(left, right)

    def gte_node(self, gte_node):
        left, right = gte_node.left.accept(self), gte_node.right.accept(self)
        return "({} >= {})".format(left, right)

    def eq_node(self, eq_node):
        left, right = eq_node.left.accept(self), eq_node.right.accept(self)
        return "({} == {})".format(left, right)

    def neq_node(self, neq_node):
        left, right = neq_node.left.accept(self), neq_node.right.accept(self)
        return "({} != {})".format(left, right)

    def and_node(self, and_node):
        left, right = and_node.left.accept(self), and_node.right.accept(self)
        return "({} && {})".format(left, right)

    def or_node(self, or_node):
        left, right = or_node.left.accept(self), or_node.right.accept(self)
        return "({} || {})".format(left, right)

    @staticmethod
    def bool_type_node(_):
        return "boolean"

    @staticmethod
    def int_type_node(_):
        return "integer"

    @staticmethod
    def decimal_type_node(_):
        return "decimal"

    @staticmethod
    def string_type_node(_):
        return "string"

    @staticmethod
    def date_type_node(_):
        return "date"

    @staticmethod
    def var_node(var_node):
        return str(var_node.name)

    @staticmethod
    def int_node(int_node):
        return str(int_node.val)

    @staticmethod
    def decimal_node(decimal_node):
        return str(decimal_node.val)

    @staticmethod
    def string_node(string_node):
        return "'{}'".format(string_node.val)

    @staticmethod
    def date_node(date_node):
        return date_node.val.strftime("%d-%m-%Y")
