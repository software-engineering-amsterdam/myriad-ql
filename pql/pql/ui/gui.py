# coding=utf-8
from PyQt5.QtGui import QIntValidator
from PyQt5.QtWidgets import QCheckBox
from PyQt5.QtWidgets import QHBoxLayout
from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QLineEdit
from PyQt5.QtWidgets import QTextEdit

from pql.gui.QuestionairWizard import QuestionairWizard, Page
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.TypeVisitor import TypeVisitor


class Gui(FormVisitor, TypeVisitor):
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
        if self.evaluator.conditional_if(node):
            [statement.apply(self) for statement in node.statements]
        else:
            [statement.apply(self) for statement in node.else_statement_list]

    def conditional_if(self, node):
        if self.evaluator.conditional_if(node):
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

        widget = node.data_type.apply(self)
        widget.setEnabled(node.expression is None)

        label.setBuddy(widget)
        grid.addWidget(label)
        grid.addWidget(widget)
        return grid

    def triggered(self, text):
        print(text+text)

    def money(self, node):
        pass

    def boolean(self, node):
        widget = QCheckBox("Button1")
        widget.setChecked(False)
        widget.stateChanged.connect(self.triggered)
        return widget

    def integer(self, node):
        widget = QLineEdit()
        widget.setValidator(QIntValidator())
        widget.setMaxLength(8)
        widget.textChanged.connect(self.triggered)
        return widget


