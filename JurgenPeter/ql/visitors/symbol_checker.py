from misc.visitor import CheckerVisitor


class SymbolChecker(CheckerVisitor):

    def __init__(self, symboltable, errors=[]):
        self.symboltable = symboltable
        self.labels = []
        self.errors = errors

    def check(self, node):
        self.visit(node)

    def visit_form(self, node):
        for element in node.body:
            self.visit(element)

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
            self.visit(element)

    def visit_ifelse_conditional(self, node):
        self.visit_if_conditional(node)
        for element in node.elsebody:
            self.visit(element)
