from Tokens import ASTTokens


class TypeChecker(object):
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

    def question_node(self, question_node):
        """ :type question_node: AST.QuestionNode """
        self.env[question_node.name] = question_node.type

    def comp_question_node(self, comp_question_node):
        """ :type comp_question_node: AST.CompQuestionNode """
        self.env[comp_question_node.name] = comp_question_node.type

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

    def monop_node(self, monop_node):
        """ :type monop_node: AST.MonOpNode """
        monop_type = monop_node.right.accept(self)
        print "monop_traversal: {}".format(monop_type)

        # TODO: Determine which type to return.
        if monop_type not in ASTTokens.MONOP_ALLOWED[monop_node.op]:
            print "Monop argument not supported!"
            return None
        return monop_type

    def binop_node(self, binop_node):
        """ :type binop_node: AST.BinOpNode """
        left = binop_node.left.accept(self)
        right = binop_node.right.accept(self)
        print "binop_traversal: {} {} {}".format(left, binop_node.op, right)

        # TODO: Determine which type to return.
        return left

    @staticmethod
    def string_node(string_node):
        """ :type string_node: AST.StringNode """
        return string_node.node_type

    @staticmethod
    def int_node(int_node):
        """ :type int_node: AST.IntNode"""
        return int_node.node_type

    @staticmethod
    def bool_node(bool_node):
        """ :type bool_node: AST.BoolNode """
        print bool_node.node_type
        return bool_node.node_type

    def var_node(self, var_node):
        """ :type var_node: AST.VarNode """
        if var_node.val not in self.env:
            print "Variable '{}' is not defined!".format(var_node.val)
            return None
        return self.env[var_node.val]

    @staticmethod
    def decimal_node(decimal_node):
        """ :type decimal_node: AST.DecimalNode """
        return decimal_node.node_type

    @staticmethod
    def date_node(date_node):
        """ :type date_node: AST.DateNode """
        return date_node.node_type
