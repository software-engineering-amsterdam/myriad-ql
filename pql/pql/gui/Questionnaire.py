# coding=utf-8
from PyQt5.QtWidgets import QCheckBox
from PyQt5.QtWidgets import QDoubleSpinBox
from PyQt5.QtWidgets import QGroupBox
from PyQt5.QtWidgets import QHBoxLayout
from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QLineEdit
from PyQt5.QtWidgets import QSpinBox
from PyQt5.QtWidgets import QVBoxLayout
from PyQt5.QtWidgets import QWidget

from pql.environment.environmentcreator import EnvironmentCreator
from pql.evaluator.evaluator import Evaluator
from pql.gui.Wizard import Wizard, Page
from pql.gui.widgets import IntegerInput, MoneyInput, BooleanInput, StringInput
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.TypeVisitor import TypeVisitor


# TODO Overal waar node.parent gebruikt wordt, vervangen door de mogelijkheid om nu een argument mee te kunnen geven
class Questionnaire(FormVisitor, TypeVisitor):
    def __init__(self, ast):
        self.wizard = Wizard()
        self.evaluator = Evaluator(EnvironmentCreator, ast)
        self.ast = ast
        self.conditional_if_list = list(tuple())
        self.conditional_if_else_list = list(tuple())

    def visit(self):
        self.wizard.add_page(self.ast.apply(self))
        self.render_conditionals()
        self.initial_ui()
        return self.wizard

    def render_conditionals(self):
        self.trigger_conditional_if()
        self.trigger_conditional_if_else()

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
        self.conditional_if_else_list.append((if_container, else_container, node))
        return if_else_container

    def conditional_if(self, node, parent=None):
        container = self.create_conditional_container(node.statements, parent)
        self.conditional_if_list.append((container, node))
        return container

    def create_conditional_container(self, statements, parent):
        container = QGroupBox(parent)
        layout = QVBoxLayout()
        for statement in statements:
            layout.addWidget(statement.apply(self, parent))
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

    def money(self, node):
        widget = self.numeric(float, MoneyInput)
        return widget

    def integer(self, node):
        widget = self.numeric(int, IntegerInput)
        return widget

    def boolean(self, node):
        widget = BooleanInput()
        self.connect_triggers(widget, widget.stateChanged, bool)
        return widget

    def string(self, node):
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
        signal.connect(self.trigger_conditional_if)
        signal.connect(self.trigger_conditional_if_else)

    def update(self, widget_name, value):
        environment = self.evaluator.update_value(widget_name, value)
        self.update_values_in_ui(environment)

    def initial_ui(self):
        environment = self.evaluator.visit()
        self.update_values_in_ui(environment)

    def update_values_in_ui(self, environment):
        for key, value in environment.items():
            widget = self.wizard.findChild(QWidget, key)
            widget.set_value(value)
