class SymbolChecker:

    def __init__(self):
        self.symboltable = {}
        self.labels = []
        self.errors = []
        self.warnings = []

    def error(self, message):
        self.errors.append(message)

    def warn(self, message):
        self.warnings.append(message)

    def visit(self, node):
        node.accept(self)
        return self.errors, self.warnings, self.symboltable

    def visit_form(self, node):
        for element in node.body:
            element.accept(self)

    def visit_question(self, node):
        if node.label not in self.labels:
            self.labels.append(node.label)
        else:
            self.warn("label \"{}\" is already used".format(node.label))

        if node.name not in self.symboltable:
            self.symboltable[node.name] = node.datatype
        else:
            self.error("question name \"{}\" is already used".format(node.name))

    def visit_computed_question(self, node):
        if node.label not in self.labels:
            self.labels.append(node.label)
        else:
            self.warn("label \"{}\" is already used".format(node.label))

        if node.name not in self.symboltable:
            self.symboltable[node.name] = node.datatype
        else:
            self.error("question name \"{}\" is already used".format(node.name))

    def visit_if_conditional(self, node):
        for element in node.ifbody:
            element.accept(self)

    def visit_ifelse_conditional(self, node):
        for element in node.ifbody:
            element.accept(self)
        for element in node.elsebody:
            element.accept(self)
