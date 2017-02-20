class SymbolChecker:

    def __init__(self):
        self.symboltable = {}
        self.success = True

    def execute(self, node):
        node.accept(self)
        return self.symboltable, self.success

    def visit_form(self, node):
        for statement in node.statements:
            statement.accept(self)

    def visit_question(self, node):
        if node.identifier not in self.symboltable:
            self.symboltable[node.identifier] = node.datatype
        else:
            print("Error: question indentifier \"{}\" is already used".format(node.identifier))
            self.success = False

    def visit_conditional(self, node):
        for statement in node.statements:
            statement.accept(self)
        if node.alternatives is not None:
            for statement in node.alternatives(self):
                statement.accept(self)