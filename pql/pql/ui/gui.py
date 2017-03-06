# coding=utf-8
from PyQt5.QtWidgets import QHBoxLayout
from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QLineEdit
from PyQt5.QtWidgets import QTextEdit

from pql.gui.QuestionairWizard import QuestionairWizard, Page
from pql.traversal.FormVisitor import FormVisitor


class Gui(FormVisitor):
    def __init__(self, environment, evaluator):
        self.environment = environment
        self.ql_wizard = QuestionairWizard()
        self.evaluator = evaluator

    def show(self):
        self.ql_wizard.show()

    def visit(self, pql_ast):
        for form in pql_ast:
            page = form.apply(self)
            page.setParent(self.ql_wizard)
            self.ql_wizard.add_page(page)
            del page
        return self.ql_wizard

    def form(self, node):
        page = Page(node.name, "Temp subtitle", self.ql_wizard)
        for statement in node.children:
            page.add_layout(statement.apply(self))
        page.set_layout()
        return page

    def conditional_if_else(self, node):
        self.conditional_if(node)
        [statement.apply(self) for statement in node.else_statement_list]

    def conditional_if(self, node):

        [statement.apply(self) for statement in node.statements]

    def field(self, node):
        grid = QHBoxLayout()
        label = QLabel(node.title)
        #TODO : Find a way to dynamically determine widget for node
        # if(typeIsInteger)
        #     QLineEdit()

        # else if (typeIsMoney)
        # QLineEdit() maar met andere input, namelijk met .
        # else if(typeIsBoolean)
        #     CheckBox ofzo

        value = QLineEdit()
        value.textChanged.connect(self.triggered)
        value.setEnabled(node.expression is None)
        label.setBuddy(value)
        grid.addWidget(label)
        grid.addWidget(value)
        return grid

    def triggered(self, text):
        print(text+text)


