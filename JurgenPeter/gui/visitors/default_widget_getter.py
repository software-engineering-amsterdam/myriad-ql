from gui.widgets import *
from misc.visitor import Visitor


class DefaultWidgetGetter(Visitor):

    def get(self, node):
        return self.visit(node)

    def visit_integer_datatype(self, node):
        return IntegerEntryWidget

    def visit_decimal_datatype(self, node):
        return DecimalEntryWidget

    def visit_boolean_datatype(self, node):
        return CheckBoxWidget

    def visit_string_datatype(self, node):
        return EntryWidget
