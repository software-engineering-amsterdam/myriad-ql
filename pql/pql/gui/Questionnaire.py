# coding=utf-8
from PyQt5.QtWidgets import QGroupBox
from PyQt5.QtWidgets import QHBoxLayout
from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QVBoxLayout
from PyQt5.QtWidgets import QWidget

from pql.environment.environmentcreator import EnvironmentCreator
from pql.evaluator.evaluator import Evaluator
from pql.gui.Page import Page
from pql.gui.Wizard import Wizard
from pql.gui.widgets import IntegerInput, MoneyInput, BooleanInput, StringInput
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.TypeVisitor import TypeVisitor


class Questionnaire(FormVisitor, TypeVisitor):
    def __init__(self, ast):
        self.wizard = Wizard()
        self.evaluator = Evaluator(ast)
        self.ast = ast
        self.__environment = EnvironmentCreator(self.ast).visit()

    def export(self):
        return self.__environment

    def connect_finished(self, fn):
        self.wizard.connect_finished(fn)

    def show(self):
        self.wizard.show()

    def visit(self):
        self.wizard.add_page(self.ast.apply(self))
        self.initial_ui()
        self.render_conditionals()
        return self

    def render_conditionals(self):
        self.wizard.trigger_conditionals(self.evaluator, self.__environment)

    def form(self, node, args=None):
        page = Page(node.name, self.wizard)
        layout = QVBoxLayout()
        for statement in node.statements:
            layout.addWidget(statement.apply(self, page))
        page.set_layout(layout)
        return page

    def conditional_if_else(self, node, parent=None):
        if_else_container = QGroupBox(parent)
        if_else_layout = QVBoxLayout()

        if_container = self.create_conditional_container(node.statements, parent)
        else_container = self.create_conditional_container(node.else_statement_list, parent)

        if_else_layout.addWidget(if_container)
        if_else_layout.addWidget(else_container)
        if_else_container.setLayout(if_else_layout)
        parent.conditional_if_else_list.append((if_container, else_container, node))
        return if_else_container

    def conditional_if(self, node, parent=None):
        container = self.create_conditional_container(node.statements, parent)
        parent.conditional_if_list.append((container, node))
        return container

    def create_conditional_container(self, statements, parent):
        container = QGroupBox(parent)
        layout = QVBoxLayout()
        for statement in statements:
            layout.addWidget(statement.apply(self, parent))
        container.setLayout(layout)
        return container

    def create_container(self, node, enabled, parent):
        container = QGroupBox(parent)
        layout = QHBoxLayout()
        label = QLabel(node.title)

        widget = node.data_type.apply(self)
        widget.setParent(parent)
        widget.setEnabled(enabled)
        widget.setObjectName(node.name.name)
        parent.registerField(node.name.name, widget)

        label.setBuddy(widget)
        layout.addWidget(label)
        layout.addWidget(widget)
        container.setLayout(layout)
        return container

    def field(self, node, parent=None):
        return self.create_container(node, True, parent)

    def assignment(self, node, parent=None):
        return self.create_container(node, (node.expression is None), parent)

    def money(self, node, args=None):
        widget = self.numeric(float, MoneyInput)
        return widget

    def integer(self, node, args=None):
        widget = self.numeric(int, IntegerInput)
        return widget

    def boolean(self, node, args=None):
        widget = BooleanInput()
        self.connect_triggers(widget, widget.stateChanged, bool)
        return widget

    def string(self, node, args=None):
        widget = StringInput()
        self.connect_triggers(widget, widget.textChanged, str)
        return widget

    def connect_triggers(self, widget, trigger_event, type_conversion):
        trigger_event.connect(lambda value: self.update(widget.objectName(), type_conversion(value)))
        self.connect_conditionals(trigger_event)

    def numeric(self, value_type, widget_type):
        widget = widget_type()
        self.connect_triggers(widget, widget.valueChanged[value_type], value_type)
        return widget

    def connect_conditionals(self, signal):
        signal.connect(self.render_conditionals)

    def update(self, widget_name, value):
        environment = self.evaluator.visit(self.__environment.update(widget_name, value))
        self.update_values_in_ui(environment.items())

    def initial_ui(self):
        environment = self.evaluator.visit(self.__environment)
        self.update_values_in_ui(environment.items())

    def update_values_in_ui(self, items):
        for key, value in items:
            widget = self.wizard.findChild(QWidget, key)
            widget.set_value(value)
