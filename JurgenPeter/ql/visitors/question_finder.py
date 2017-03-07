class QuestionFinder:

    def __init__(self, name):
        self.name = name
        self.question = None

    def find(self, node):
        self.visit(node)
        return self.question

    def visit(self, node):
        node.accept(self)

    def visit_form(self, node):
        for element in node.body:
            element.accept(self)

    def visit_question(self, node):
        if self.name == node.name:
            self.question = node

    def visit_computed_question(self, node):
        self.visit_question(node)

    def visit_if_conditional(self, node):
        for element in node.ifbody:
            element.accept(self)

    def visit_ifelse_conditional(self, node):
        for element in node.ifbody:
            element.accept(self)
        for element in node.elsebody:
            element.accept(self)
