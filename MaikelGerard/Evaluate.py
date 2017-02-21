class Evaluate(object):
    def __init__(self, ast):
        """ :type ast: AST.QuestionnaireAST """
        self.ast = ast

        self.env = {}
        self.errors = []

    def start_traversal(self):
        # Ensure the environment and error log are empty.
        self.env = {}
        self.errors = []

        self.ast.root.accept(self)

    def comp_question_node(self, comp_question_node):
        """ :type comp_question_node: AST.CompQuestionNode """
        # TODO: Evaluate the computed question expression.

        comp_question_node.expression.accept(self)

    def if_node(self, if_node):
        # TODO: Evaluate the if condition.

        if_node.expression.accept(self)
        if_node.if_block.accept(self)

    def if_else_node(self, if_else_node):
        # TODO: Evaluate the if condition.

        if_else_node.expression.accept(self)
        if_else_node.if_block.accept(self)
        if_else_node.else_block.accept(self)

    def monop_node(self, monop_node):
        """ :type monop_node: AST.MonOpNode """
        monop_type = monop_node.right.accept(self)

    def binop_node(self, binop_node):
        """ :type binop_node: AST.BinOpNode """
        left = binop_node.left.accept(self)
        right = binop_node.right.accept(self)

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

    @staticmethod
    def var_node(var_node):
        """ :type var_node: AST.VarNode """
        return var_node.val

    @staticmethod
    def decimal_node(decimal_node):
        """ :type decimal_node: AST.DecimalNode """
        return decimal_node.val

    @staticmethod
    def date_node(date_node):
        """ :type date_node: AST.DateNode """
        return date_node.val
