# coding=utf-8
from PyQt5.QtCore import QLocale
from PyQt5.QtCore import QObject
from PyQt5.QtCore import QRegExp
from PyQt5.QtGui import QDoubleValidator
from PyQt5.QtGui import QIntValidator
from PyQt5.QtWidgets import QCheckBox
from PyQt5.QtWidgets import QGroupBox
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
        self.pql_ast = None

    def show(self):
        self.ql_wizard.show()

    def visit(self, ql_ast):
        self.pql_ast = ql_ast
        for form in self.pql_ast:
            page = form.apply(self)
            page.setParent(self.ql_wizard)
            self.ql_wizard.add_page(page)
            del page
        return self.ql_wizard

    def form(self, node):
        page = Page(node.name, "Temp subtitle", self.ql_wizard)
        layout = QVBoxLayout()
        for statement in node.children:
            statement.parent = page
            layout.addWidget(statement.apply(self))
        page.add_layout(layout)
        page.set_layout()
        return page

    def conditional_if_else(self, node):
        # TODO Implement like conditional_if
        if self.evaluator.conditional_if(node):
            [statement.apply(self) for statement in node.statements]
        else:
            [statement.apply(self) for statement in node.else_statement_list]

    def conditional_if(self, node):
        container = QGroupBox(node.parent)
        layout = QVBoxLayout()
        for statement in node.statements:
            statement.parent = node.parent
            layout.addWidget(statement.apply(self))
        container.setLayout(layout)
        return container

    def trigger_conditional_if(self, node, container):
        result = self.evaluator.conditional_if(node)
        cond = (result is not None and result)
        container.setEnabled(cond)
        if cond:
            container.show()
        else:
            container.hide()

    def update_trigger_numeric(self, widget, value):
        self.evaluator.update_value(widget.objectName(), value)
        self.update_visible_values(self.update_value_numeric)

    def update_trigger_boolean(self, widget, value):
        self.evaluator.update_value(widget.objectName(), bool(value))
        self.update_visible_values(self.update_value_boolean)

    def update_visible_values(self, function):
        environment = self.evaluator.visit(self.pql_ast)
        for key, value in environment.items():
            widget = self.ql_wizard.findChild(QWidget, key)
            function(widget, value)

    def update_value_boolean(self, widget, value):
        if value is not None:
            widget.setChecked(value)

    def update_value_numeric(self, widget, value):
        widget.setText(value)

    def field(self, node):
        container = QGroupBox(node.parent)
        layout = QHBoxLayout()
        label = QLabel(node.title)

        widget = node.data_type.apply(self)
        widget.setParent(node.parent)
        widget.setEnabled(node.expression is None)
        widget.setObjectName(node.name.name)
        node.parent.registerField(node.name.name, widget)

        label.setBuddy(widget)
        layout.addWidget(label)
        layout.addWidget(widget)
        container.setLayout(layout)
        return container

    def money(self, node):
        widget = QLineEdit()
        validator = QDoubleValidator()
        validator.setDecimals(2)
        widget.setValidator(validator)
        widget.setMaxLength(10)
        widget.textChanged.connect(lambda value: self.update_trigger_numeric(widget, value))
        return widget

    def integer(self, node):
        widget = QLineEdit()
        widget.setValidator(QIntValidator())
        widget.setMaxLength(8)
        widget.textChanged.connect(lambda value: self.update_trigger_numeric(widget, value))
        return widget

    def boolean(self, node):
        widget = QCheckBox()
        widget.setChecked(False)
        widget.stateChanged.connect(lambda value: self.update_trigger_boolean(widget, value))
        return widget
