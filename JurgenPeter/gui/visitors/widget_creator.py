from ql.ast import Datatype
from gui.widgets import *


class WidgetCreator:

    def __init__(self, app, widgets, listener):
        self.app = app
        self.widgets = widgets
        self.listener = listener

        # TODO replace with QLS styling object
        self.default_widgets = {
            Datatype.integer: IntegerEntryWidget,
            Datatype.decimal: DecimalEntryWidget,
            Datatype.boolean: CheckBoxWidget,
            Datatype.string:  EntryWidget}

    def visit(self, node):
        node.accept(self)

    def visit_form(self, node):
        for statement in node.body:
            statement.accept(self)

    def visit_question(self, node):
        widget = self.default_widgets[node.datatype](self.app, node)
        widget.set_listener(self.listener)
        self.widgets[node.name] = widget
        return widget

    def visit_computed_question(self, node):
        widget = self.visit_question(node)
        widget.disable()

    def visit_if_conditional(self, node):
        for statement in node.ifbody:
            statement.accept(self)

    def visit_ifelse_conditional(self, node):
        for statement in node.ifbody:
            statement.accept(self)
        for statement in node.elsebody:
            statement.accept(self)
