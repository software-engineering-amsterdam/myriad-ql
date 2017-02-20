class TypeChecking(object):
    def __init__(self, ast):
        self.env = {}

        ast.root.accept(self)

    def question_node(self, question_node):
        self.env[question_node.name] = question_node.type

    def conditional_node(self, conditional_node):
        pass

    def expression_node(self, question_node):
        pass

    @staticmethod
    def int_node(int_node):
        return int_node.value

    @staticmethod
    def bool_node(bool_node):
        return bool_node.value

    @staticmethod
    def var_node(var_node):
        return var_node.value

    @staticmethod
    def decimal_node(decimal_node):
        return decimal_node
