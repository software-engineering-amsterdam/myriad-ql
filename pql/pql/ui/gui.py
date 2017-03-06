# coding=utf-8
from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QLineEdit
from PyQt5.QtWidgets import QVBoxLayout

from pql.gui.QuestionairWizard import QuestionairWizard, Page
from pql.traversal.FormVisitor import FormVisitor


class Gui(FormVisitor):
    def __init__(self, environment, ql_ast):
        self.environment = environment
        self.ql_ast = ql_ast

    def visit(self, pql_ast):
        wiz = QuestionairWizard()
        [wiz.add_page(form.apply(self)) for form in self.ql_ast]
        return wiz

    def form(self, node):
        page = Page(parent, node.name, "Temp subtitle")
        [statement.apply(self) for statement in node.children]

    def conditional_if_else(self, node):
        self.conditional_if(node)
        [statement.apply(self) for statement in node.else_statement_list]

    def conditional_if(self, node):
        [statement.apply(self) for statement in node.statements]

    def field(self, node):
        grid = QVBoxLayout()
        label = QLabel(node.title)
        value = QLineEdit()
        label.setBuddy(value)
        grid.addWidget(label, 1, 0)
        grid.addWidget(value, 1, 1)
        return grid


