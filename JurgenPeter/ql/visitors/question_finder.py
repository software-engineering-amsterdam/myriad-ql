class QuestionFinder:

    def __init__(self):
        self.questions = []

    def visit(self, node):
        node.accept(self)
        return self.questions

    def visit_form(self, node):
        for statement in node.body:
            statement.accept(self)

    def visit_question(self, node):
        self.questions.append(node)

    def visit_computed_question(self, node):
        self.questions.append(node)

    def visit_if_conditional(self, node):
        for statement in node.ifbody:
            statement.accept(self)

    def visit_ifelse_conditional(self, node):
        for statement in node.ifbody:
            statement.accept(self)
        for statement in node.elsebody:
            statement.accept(self)
