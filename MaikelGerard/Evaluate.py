class Evaluate(object):
    def __init__(self, ast):
        ast.accept()

    def question_node(self, question):
        pass

    def conditional_node(self, conditional):
        pass

    def expression_node(self, question):
        pass
