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
        for element in node.body:
            element.accept(self)

    def visit_question(self, node):
        widget = self.default_widgets[node.datatype](self.app, node)
        widget.set_listener(self.listener)
        self.widgets[node.name] = widget
        return widget

    def visit_computed_question(self, node):
        widget = self.visit_question(node)
        widget.disable()

    def visit_if_conditional(self, node):
        for element in node.ifbody:
            element.accept(self)

    def visit_ifelse_conditional(self, node):
        for element in node.ifbody:
            element.accept(self)
        for element in node.elsebody:
            element.accept(self)
