class Datatype:
    def __eq__(self, other):
        return type(self) == type(other)


class IntegerDatatype(Datatype):
    def __str__(self):
        return "integer"

    def accept(self, visitor, *args):
        return visitor.visit_integer_datatype(self, *args)


class DecimalDatatype(Datatype):
    def __str__(self):
        return "decimal"

    def accept(self, visitor, *args):
        return visitor.visit_decimal_datatype(self, *args)


class BooleanDatatype(Datatype):
    def __str__(self):
        return "boolean"

    def accept(self, visitor, *args):
        return visitor.visit_boolean_datatype(self, *args)


class StringDatatype(Datatype):
    def __str__(self):
        return "string"

    def accept(self, visitor, *args):
        return visitor.visit_string_datatype(self, *args)
