# coding=utf-8
from PyQt5.QtCore import QLocale
from PyQt5.QtCore import QObject
from PyQt5.QtCore import QRegExp
from PyQt5.QtGui import QDoubleValidator
from PyQt5.QtGui import QIntValidator
from PyQt5.QtWidgets import QCheckBox
from PyQt5.QtWidgets import QDoubleSpinBox
from PyQt5.QtWidgets import QGroupBox
from PyQt5.QtWidgets import QHBoxLayout
from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QLineEdit
from PyQt5.QtWidgets import QSpinBox
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
        self.conditional_if_list = list(tuple())
        self.conditional_if_else_list = list(tuple())

    def show(self):
        self.ql_wizard.show()

    def visit(self, ql_ast):
        self.pql_ast = ql_ast
        for form in self.pql_ast:
            page = form.apply(self)
            page.setParent(self.ql_wizard)
            self.ql_wizard.add_page(page)
            del page
        self.trigger_conditional_if()
        self.trigger_conditional_if_else()
        return self.ql_wizard

    def form(self, node):
        page = Page(node.name, "Temp subtitle", self.ql_wizard)
        layout = QVBoxLayout()
        for statement in node.statements:
            statement.parent = page
            layout.addWidget(statement.apply(self))
        page.add_layout(layout)
        page.set_layout()
        return page

    def conditional_if_else(self, node):
        if_else_container = QGroupBox(node.parent)
        if_else_layout = QVBoxLayout()

        if_container = self.create_conditional_container(node, node.statements)
        else_container = self.create_conditional_container(node, node.else_statement_list)

        if_else_layout.addWidget(if_container)
        if_else_layout.addWidget(else_container)
        if_else_container.setLayout(if_else_layout)
        self.conditional_if_else_list.append((if_container, else_container, node))
        return if_else_container

    def conditional_if(self, node):
        container = self.create_conditional_container(node, node.statements)
        self.conditional_if_list.append((container, node))
        return container

    def create_conditional_container(self, node, statements):
        container = QGroupBox(node.parent)
        layout = QVBoxLayout()
        for statement in statements:
            statement.parent = node.parent
            layout.addWidget(statement.apply(self))
        container.setLayout(layout)
        return container

    def trigger_conditional_if(self):
        for if_block_container, node in self.conditional_if_list:
            result = self.evaluator.expression(node.condition)
            cond = (result is not None and result)
            if_block_container.setEnabled(cond)
            if cond:
                if_block_container.show()
            else:
                if_block_container.hide()

    def trigger_conditional_if_else(self):
        for if_container, else_container, node in self.conditional_if_else_list:
            result = self.evaluator.expression(node.condition)
            cond = (result is not None and result)
            if_container.setEnabled(cond)
            else_container.setEnabled(not cond)
            if cond:
                if_container.show()
                else_container.hide()
            else:
                if_container.hide()
                else_container.show()

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
        widget = QDoubleSpinBox()
        widget.setDecimals(2)
        widget.setMaximum(10**10)
        widget.setMinimum(-(10**10))
        widget.valueChanged[float].connect(lambda value: self.update_trigger_numeric(widget, value, QDoubleSpinBox))
        widget.valueChanged.connect(self.trigger_conditional_if)
        widget.valueChanged.connect(self.trigger_conditional_if_else)
        return widget

    def integer(self, node):
        widget = QSpinBox()
        widget.setMinimum(-(10**10))
        widget.setMaximum(10**10)
        widget.setMaxLength(8)
        widget.valueChanged[int].connect(lambda value: self.update_trigger_numeric(widget, value, QSpinBox))
        widget.valueChanged.connect(self.trigger_conditional_if)
        widget.valueChanged.connect(self.trigger_conditional_if_else)
        return widget

    def boolean(self, node):
        widget = QCheckBox()
        widget.setChecked(False)
        widget.stateChanged.connect(lambda value: self.update_trigger_boolean(widget, bool(value)))
        widget.stateChanged.connect(self.trigger_conditional_if)
        widget.stateChanged.connect(self.trigger_conditional_if_else)
        return widget

    def update_trigger_numeric(self, widget, value, type):
        self.evaluator.update_value(widget.objectName(), value)
        self.update_visible_values(type, self.update_value_numeric)

    def update_trigger_boolean(self, widget, value):
        self.evaluator.update_value(widget.objectName(), value)
        self.update_visible_values(QCheckBox, self.update_value_boolean)

    def update_visible_values(self, widget_type, function):
        environment = self.evaluator.visit(self.pql_ast)
        for key, value in environment.items():
            widget = self.ql_wizard.findChild(widget_type, key)
            if widget is not None:
                function(widget, value)

    def update_value_boolean(self, widget, value):
        if value is not None:
            widget.setChecked(value)

    def update_value_numeric(self, widget, value):
        if value is not None:
            widget.setValue(value)
