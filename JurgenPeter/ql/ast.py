from enum import Enum

Datatype = Enum("Datatype", "boolean string integer decimal")


class Form:

    def __init__(self, identifier, statements):
        self.identifier = identifier
        self.statements = statements

    def accept(self, visitor):
        return visitor.visit_form(self)

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class Question:

    def __init__(self, identifier, label, datatype):
        self.identifier = identifier
        self.label = label
        self.datatype = datatype

    def accept(self, visitor):
        return visitor.visit_question(self)

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class ComputedQuestion:

    def __init__(self, identifier, label, datatype, computation):
        self.identifier = identifier
        self.label = label
        self.datatype = datatype
        self.computation = computation

    def accept(self, visitor):
        return visitor.visit_computed_question(self)

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class IfConditional:

    def __init__(self, condition, ifstatements):
        self.condition = condition
        self.ifstatements = ifstatements

    def accept(self, visitor):
        return visitor.visit_if_conditional(self)

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class IfElseConditional:

    def __init__(self, condition, ifstatements, elsestatements):
        self.condition = condition
        self.ifstatements = ifstatements
        self.elsestatements = elsestatements

    def accept(self, visitor):
        return visitor.visit_ifelse_conditional(self)

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class UnOp:
    def __init__(self, right):
        self.right = right

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class PlusOp(UnOp):
    def accept(self, visitor):
        return visitor.visit_plusop(self)


class MinOp(UnOp):
    def accept(self, visitor):
        return visitor.visit_minop(self)


class NotOp(UnOp):
    def accept(self, visitor):
        return visitor.visit_notop(self)


class BinOp:
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__


class MulOp(BinOp):
    def accept(self, visitor):
        return visitor.visit_mulop(self)


class DivOp(BinOp):
    def accept(self, visitor):
        return visitor.visit_divop(self)


class AddOp(BinOp):
    def accept(self, visitor):
        return visitor.visit_addop(self)


class SubOp(BinOp):
    def accept(self, visitor):
        return visitor.visit_subop(self)


class LtOp(BinOp):
    def accept(self, visitor):
        return visitor.visit_ltop(self)


class LeOp(BinOp):
    def accept(self, visitor):
        return visitor.visit_leop(self)


class GtOp(BinOp):
    def accept(self, visitor):
        return visitor.visit_gtop(self)


class GeOp(BinOp):
    def accept(self, visitor):
        return visitor.visit_geop(self)


class EqOp(BinOp):
    def accept(self, visitor):
        return visitor.visit_eqop(self)


class NeOp(BinOp):
    def accept(self, visitor):
        return visitor.visit_neop(self)


class AndOp(BinOp):
    def accept(self, visitor):
        return visitor.visit_andop(self)


class OrOp(BinOp):
    def accept(self, visitor):
        return visitor.visit_orop(self)


class Variable:

    def __init__(self, identifier):
        self.identifier = identifier

    def accept(self, visitor):
        return visitor.visit_variable(self)

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class Constant:

    def __init__(self, value, datatype):
        self.value = value
        self.datatype = datatype

    def accept(self, visitor):
        return visitor.visit_constant(self)

    def __eq__(self, other):
        return self.__dict__ == other.__dict__
