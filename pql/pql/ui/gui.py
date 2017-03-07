# coding=utf-8
from PyQt5.QtCore import QLocale
from PyQt5.QtCore import QRegExp
from PyQt5.QtGui import QDoubleValidator
from PyQt5.QtGui import QIntValidator
from PyQt5.QtWidgets import QCheckBox
from PyQt5.QtWidgets import QHBoxLayout
from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QLineEdit
from PyQt5.QtWidgets import QTextEdit
from PyQt5.QtWidgets import QVBoxLayout
from PyQt5.QtWidgets import QWidget

from pql.evaluator.evaluator import Evaluator
from pql.gui.QuestionairWizard import QuestionairWizard, Page
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.TypeVisitor import TypeVisitor


class Gui(FormVisitor, TypeVisitor):
    def __init__(self, environment):
        self.ql_wizard = QuestionairWizard()
        self.evaluator = Evaluator(environment)

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
            statement.parent = page
            page.add_layout(statement.apply(self))
        page.set_layout()
        return page

    def conditional_if_else(self, node):
        if self.evaluator.conditional_if(node):
            [statement.apply(self) for statement in node.statements]
        else:
            [statement.apply(self) for statement in node.else_statement_list]

    def conditional_if(self, node):
        container = QWidget()
        layout = QVBoxLayout()
        for statement in node.children:
            layout.addLayout(statement.apply(self))

        widgets = self.widgets_of_layout(layout, {QLineEdit, QCheckBox})
        result = self.evaluator.conditional_if(node)
        cond = (result is not None and result)
        container.setLayout(layout)
        container.setEnabled(cond)
        if cond:
            container.show()
        else:
            container.hide()

    def field(self, node):
        grid = QHBoxLayout()
        label = QLabel(node.title)

        widget = node.data_type.apply(self)
        widget.setEnabled(node.expression is None)
        widget.setObjectName(node.name.name)
        node.parent.registerField(node.name.name, widget)

        label.setBuddy(widget)
        grid.addWidget(label)
        grid.addWidget(widget)
        return grid

    def triggered(self, value):
        # self.print_children()
        print(value)

    # def print_children(self):
    #     wtypes = [QLabel, QLineEdit, QCheckBox]
    #     qreg = QRegExp(r'.*')
    #     mywidgets = {}
    #     for t in wtypes:
    #         mywidgets[t] = self.ql_wizard.findChildren(t, qreg)
    #     for button in mywidgets:
    #         "button:", button.objectName()

    def widgets_of_layout(self, layout, wtype=QLineEdit, qreg=QRegExp(r'.*')):
        #TODO fancy maken
        c = layout.children()
        children = self.layout_widgets(layout)
        matches = []
        for widget in children:
            if isinstance(widget, wtype):
                matches.append(widget)
        return matches

    def layout_widgets(self, layout):
        count = layout.count()
        return (layout.itemAt(i) for i in range(layout.count()))

    def money(self, node):
        widget = QLineEdit()
        validator = QDoubleValidator()
        validator.setDecimals(2)
        widget.setValidator(validator)
        widget.setMaxLength(10)
        widget.textChanged.connect(self.triggered)
        return widget

    def integer(self, node):
        widget = QLineEdit()
        widget.setValidator(QIntValidator())
        widget.setMaxLength(8)
        widget.textChanged.connect(self.triggered)
        return widget

    def boolean(self, node):
        widget = QCheckBox()
        widget.setChecked(False)
        widget.stateChanged.connect(self.triggered)
        return widget
