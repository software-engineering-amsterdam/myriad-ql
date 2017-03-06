from enum import Enum

Datatype = Enum("Datatype", "boolean string integer decimal")


class Form:

    def __init__(self, name, body):
        self.name = name
        self.body = body

    def accept(self, visitor):
        return visitor.visit_form(self)

    def __eq__(self, other):
        return (type(self) == type(other) and self.name == other.name and
                self.body == other.body)


class Question:

    def __init__(self, name, label, datatype):
        self.name = name
        self.label = label
        self.datatype = datatype

    def accept(self, visitor):
        return visitor.visit_question(self)

    def __eq__(self, other):
        return (type(self) == type(other) and self.name == other.name and
                self.label == other.label and self.datatype == other.datatype)


class ComputedQuestion(Question):

    def __init__(self, name, label, datatype, computation):
        super().__init__(name, label, datatype)
        self.computation = computation

    def accept(self, visitor):
        return visitor.visit_computed_question(self)

    def __eq__(self, other):
        return (type(self) == type(other) and
                self.name == other.name and
                self.label == other.label and
                self.datatype == other.datatype and
                self.computation == other.computation)


class IfConditional:

    def __init__(self, condition, ifbody):
        self.condition = condition
        self.ifbody = ifbody

    def accept(self, visitor):
        return visitor.visit_if_conditional(self)

    def __eq__(self, other):
        return (type(self) == type(other) and
                self.condition == other.condition and
                self.ifbody == other.ifbody)


class IfElseConditional(IfConditional):

    def __init__(self, condition, ifbody, elsebody):
        super().__init__(condition, ifbody)
        self.elsebody = elsebody

    def accept(self, visitor):
        return visitor.visit_ifelse_conditional(self)

    def __eq__(self, other):
        return (type(self) == type(other) and
                self.condition == other.condition and
                self.ifbody == other.ifbody and
                self.elsebody == other.elsebody)


class UnOp:
    def __init__(self, right):
        self.right = right

    def __eq__(self, other):
        return type(self) == type(other) and self.right == other.right


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
        return (type(self) == type(other) and self.left == other.left and
                self.right == other.right)


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

    def __init__(self, name):
        self.name = name

    def accept(self, visitor):
        return visitor.visit_variable(self)

    def __eq__(self, other):
        return type(self) == type(other) and self.name == other.name


class Constant:

    def __init__(self, value, datatype):
        self.value = value
        self.datatype = datatype

    def accept(self, visitor):
        return visitor.visit_constant(self)

    def __eq__(self, other):
        return (type(self) == type(other) and self.value == other.value and
                self.datatype == other.datatype)
