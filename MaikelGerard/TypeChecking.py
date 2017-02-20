class TypeChecking(object):
    def __init__(self, ast):
        self.env = {}
        self.errors = []

        ast.root.accept(self)

    def question_node(self, question_node):
        """ :type question_node: AST.QuestionNode """
        self.env[question_node.name] = question_node.type

    def computed_question_node(self, computed_question_node):
        """ :type computed_question_node: AST.ComputedQuestionNode """
        self.env[computed_question_node.name] = computed_question_node.type

        # Determine whether the expression is valid.
        computed_question_node.expression.accept(self)

    def if_node(self, if_node):
        """ :type if_node: AST.IfNode """
        if_node.expression.accept(self)
        if_node.if_block.accept(self)

    def if_else_node(self, if_else_node):
        """ :type if_else_node: AST.IfElseNode """
        if_else_node.expression.accept(self)
        if_else_node.if_block.accept(self)
        if_else_node.else_block.accept(self)

    # def expression_node(self, question_node):
    #     pass

    def monop_node(self, monop_node):
        """ :type monop_node: AST.MonOpNode """
        monop_node.right.accept(self)

    def binop_node(self, binop_node):
        """ :type binop_node: AST.BinOpNode """
        binop_node.left.accept(self)
        binop_node.right.accept(self)

    @staticmethod
    def string_node(string_node):
        return string_node.val

    @staticmethod
    def int_node(int_node):
        return int_node.val

    @staticmethod
    def bool_node(bool_node):
        return bool_node.val

    @staticmethod
    def var_node(var_node):
        return var_node.val

    @staticmethod
    def decimal_node(decimal_node):
        return decimal_node.val

    @staticmethod
    def date_node(date_node):
        return date_node.val
