# coding=utf-8
from PyQt5.QtWidgets import QCheckBox
from PyQt5.QtWidgets import QDoubleSpinBox
from PyQt5.QtWidgets import QGroupBox
from PyQt5.QtWidgets import QHBoxLayout
from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QSpinBox
from PyQt5.QtWidgets import QVBoxLayout

from pql.evaluator.evaluator import Evaluator
from pql.gui.Wizard import Wizard, Page
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.TypeVisitor import TypeVisitor


class Gui(FormVisitor, TypeVisitor):
    def __init__(self, environment):
        self.wizard = Wizard()
        self.evaluator = Evaluator(environment)
        self.ast = None
        self.conditional_if_list = list(tuple())
        self.conditional_if_else_list = list(tuple())

    def show(self):
        self.wizard.show()

    def visit(self, ql_ast):
        self.ast = ql_ast
        for form in self.ast:
            self.wizard.add_page(form.apply(self))
        self.render_conditionals()
        return self.wizard

    def render_conditionals(self):
        self.trigger_conditional_if()
        self.trigger_conditional_if_else()

    def form(self, node):
        page = Page(node.name, self.wizard)
        layout = QVBoxLayout()
        for statement in node.statements:
            statement.parent = page
            layout.addWidget(statement.apply(self))
        page.set_layout(layout)
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
        widget = self.numeric(float, QDoubleSpinBox)
        widget.setDecimals(2)
        return widget

    def integer(self, node):
        widget = self.numeric(int, QSpinBox)
        widget.setMaxLength(8)
        return widget

    def boolean(self, node):
        widget = QCheckBox()
        widget.setChecked(False)
        widget.stateChanged.connect(lambda value: self.update_trigger_boolean(widget, bool(value)))
        self.connect_conditionals(widget.stateChanged)
        return widget

    def numeric(self, value_type, widget_type):
        widget = widget_type()
        widget.valueChanged[value_type].connect(lambda value: self.update_trigger_numeric(widget, value, widget_type))
        self.connect_conditionals(widget.valueChanged)
        self.set_min_max(widget)
        return widget

    def connect_conditionals(self, signal):
        signal.connect(self.trigger_conditional_if)
        signal.connect(self.trigger_conditional_if_else)

    def set_min_max(self, widget, minimum=(-(10 ** 10)), maximum=(10**10)):
        widget.setMinimum(minimum)
        widget.setMaximum(maximum)

    def update_trigger_numeric(self, widget, value, type):
        self.evaluator.update_value(widget.objectName(), value)
        self.update_visible_values(type, self.update_value_numeric)

    def update_trigger_boolean(self, widget, value):
        self.evaluator.update_value(widget.objectName(), value)
        self.update_visible_values(QCheckBox, self.update_value_boolean)

    def update_visible_values(self, widget_type, function):
        environment = self.evaluator.visit(self.ast)
        for key, value in environment.items():
            widget = self.wizard.findChild(widget_type, key)
            if widget is not None:
                function(widget, value)

    def update_value_boolean(self, widget, value):
        if value is not None:
            widget.setChecked(value)

    def update_value_numeric(self, widget, value):
        if value is not None:
            widget.setValue(value)
