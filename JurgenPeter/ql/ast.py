from enum import Enum

Datatype = Enum("Datatype", "boolean string integer decimal")


class Node:
    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__


class Form(Node):

    def __init__(self, name, body):
        self.name = name
        self.body = body

    def accept(self, visitor):
        return visitor.visit_form(self)


class Question(Node):

    def __init__(self, name, label, datatype):
        self.name = name
        self.label = label
        self.datatype = datatype

    def accept(self, visitor):
        return visitor.visit_question(self)


class ComputedQuestion(Question):

    def __init__(self, name, label, datatype, computation):
        super().__init__(name, label, datatype)
        self.computation = computation

    def accept(self, visitor):
        return visitor.visit_computed_question(self)


class IfConditional(Node):

    def __init__(self, condition, ifbody):
        self.condition = condition
        self.ifbody = ifbody

    def accept(self, visitor):
        return visitor.visit_if_conditional(self)


class IfElseConditional(IfConditional):

    def __init__(self, condition, ifbody, elsebody):
        super().__init__(condition, ifbody)
        self.elsebody = elsebody

    def accept(self, visitor):
        return visitor.visit_ifelse_conditional(self)


class Expression(Node):
    pass


class UnOp(Expression):
    def __init__(self, right):
        self.right = right


class PlusOp(UnOp):
    def accept(self, visitor):
        return visitor.visit_plusop(self)


class MinOp(UnOp):
    def accept(self, visitor):
        return visitor.visit_minop(self)


class NotOp(UnOp):
    def accept(self, visitor):
        return visitor.visit_notop(self)


class BinOp(Expression):
    def __init__(self, left, right):
        self.left = left
        self.right = right


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


class Variable(Expression):

    def __init__(self, name):
        self.name = name

    def accept(self, visitor):
        return visitor.visit_variable(self)


class Constant(Expression):

    def __init__(self, value, datatype):
        self.value = value
        self.datatype = datatype

    def accept(self, visitor):
        return visitor.visit_constant(self)
