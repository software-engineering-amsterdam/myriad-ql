class SymbolChecker:

    def __init__(self):
        self.symboltable = {}
        self.labels = []
        self.success = True

    def visit(self, node):
        node.accept(self)
        return self.success, self.symboltable

    def visit_form(self, node):
        for statement in node.statements:
            statement.accept(self)

    def visit_question(self, node):
        if node.label in self.labels:
            print("Warning: label \"{}\" is already used".format(node.label))
        else:
            self.labels.append(node.label)

        if node.identifier not in self.symboltable:
            self.symboltable[node.identifier] = node.datatype
        else:
            print("Error: question indentifier \"{}\" "
                  "is already used".format(node.identifier))
            self.success = False

    def visit_computed_question(self, node):
        if node.label in self.labels:
            print("Warning: label \"{}\" "
                  "is already used".format(node.label))
        else:
            self.labels.append(node.label)

        if node.identifier not in self.symboltable:
            self.symboltable[node.identifier] = node.datatype
        else:
            print("Error: question indentifier \"{}\" "
                  "is already used".format(node.identifier))
            self.success = False

    def visit_if_conditional(self, node):
        for statement in node.ifstatements:
            statement.accept(self)

    def visit_ifelse_conditional(self, node):
        for statement in node.ifstatements:
            statement.accept(self)
        for statement in node.elsestatements:
            statement.accept(self)
