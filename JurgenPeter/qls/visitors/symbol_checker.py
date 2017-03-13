
from misc.messages import *


class SymbolChecker:

    def __init__(self, ql_symboltable, errors=[]):
        self.ql_symbols = set(ql_symboltable.keys())
        self.qls_symbols = set()
        self.labels = []
        self.errors = errors

    def error(self, message):
        self.errors.append(ErrorMessage(message))

    def warn(self, message):
        self.errors.append(WarningMessage(message))

    def check(self, node):
        self.visit(node)
        for symbol in self.ql_symbols - self.qls_symbols:
            self.error("QL question name \"{}\" is not defined "
                       "in QLS layout".format(symbol))
        for symbol in self.qls_symbols - self.ql_symbols:
            self.error("QLS question anchor name \"{}\" is not defined "
                       "in QL form".format(symbol))

    def visit(self, node):
        node.accept(self)

    def visit_layout(self, node):
        for element in node.body:
            self.visit(element)

    def visit_styled_layout(self, node):
        self.visit_layout(node)

    def visit_page(self, node):
        for element in node.body:
            self.visit(element)

    def visit_styled_page(self, node):
        self.visit_page(node)

    def visit_section(self, node):
        for element in node.body:
            self.visit(element)

    def visit_styled_section(self, node):
        self.visit_section(node)

    def visit_question_anchor(self, node):
        if node.name not in self.qls_symbols:
            self.qls_symbols.add(node.name)
        else:
            self.error("question anchor name \"{}\" is already used".format(node.name))

    def visit_styled_question_anchor(self, node):
        self.visit_question_anchor(node)