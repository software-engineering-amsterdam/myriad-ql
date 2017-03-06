# coding=utf-8
import PyQt5
from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QLineEdit
from PyQt5.QtWidgets import QVBoxLayout

from pql.gui.QuestionairWizard import QuestionairWizard, Page


class Gui(object):
    def __init__(self, environment, ql_ast):
        self.environment = environment
        self.ql_ast = ql_ast

    def build(self):
        wiz = QuestionairWizard()
        [wiz.add_page(form.apply(self, wiz)) for form in self.ql_ast]
        return wiz

    def form(self, node, parent):
        page = Page(parent, node.name, "Temp subtitle")
        [statement.apply(self) for statement in node.children]

    def conditional_if_else(self, node):
        self.conditional_if(node)
        [statement.apply(self) for statement in node.else_statement_list]

    def conditional_if(self, node):
        [statement.apply(self) for statement in node.statements]

    def field(self, node):
        grid = QVBoxLayout()
        title = QLabel(node.title)
        answer_edit = QLineEdit()
        grid.addWidget(title, 1, 0)
        grid.addWidget(answer_edit, 1, 1)
        return grid
