from enum import Enum


class Datatype:
    def __eq__(self, other):
        return type(self) == type(other)


class IntegerDatatype(Datatype):
    def accept(self, visitor):
        return visitor.visit_integer_datatype(self)


class DecimalDatatype(Datatype):
    def accept(self, visitor):
        return visitor.visit_decimal_datatype(self)


class BooleanDatatype(Datatype):
    def accept(self, visitor):
        return visitor.visit_boolean_datatype(self)


class StringDatatype(Datatype):
    def accept(self, visitor):
        return visitor.visit_string_datatype(self)


class Datatypes(Enum):
    integer = IntegerDatatype()
    decimal = DecimalDatatype()
    boolean = BooleanDatatype()
    string = StringDatatype()
