from gui.widgets import *


class WidgetCreator:

    def create(self, node):
        return self.visit(node)

    def visit(self, node):
        return node.accept(self)

    def visit_integer_datatype(self, node):
        return IntegerEntryWidget

    def visit_decimal_datatype(self, node):
        return DecimalEntryWidget

    def visit_boolean_datatype(self, node):
        return CheckBoxWidget

    def visit_string_datatype(self, node):
        return EntryWidget
