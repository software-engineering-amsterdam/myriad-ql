class Node:
    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__


class Form(Node):

    def __init__(self, name, body):
        self.name = name
        self.body = body

    def accept(self, visitor, *args):
        return visitor.visit_form(self, *args)


class Question(Node):

    def __init__(self, name, label, datatype):
        self.name = name
        self.label = label
        self.datatype = datatype

    def accept(self, visitor, *args):
        return visitor.visit_question(self, *args)


class ComputedQuestion(Question):

    def __init__(self, name, label, datatype, computation):
        super().__init__(name, label, datatype)
        self.computation = computation

    def accept(self, visitor, *args):
        return visitor.visit_computed_question(self, *args)


class IfConditional(Node):

    def __init__(self, condition, ifbody):
        self.condition = condition
        self.ifbody = ifbody

    def accept(self, visitor, *args):
        return visitor.visit_if_conditional(self, *args)


class IfElseConditional(IfConditional):

    def __init__(self, condition, ifbody, elsebody):
        super().__init__(condition, ifbody)
        self.elsebody = elsebody

    def accept(self, visitor, *args):
        return visitor.visit_ifelse_conditional(self, *args)


class Expression(Node):
    pass


class UnOp(Expression):
    def __init__(self, right):
        self.right = right


class PlusOp(UnOp):
    def accept(self, visitor, *args):
        return visitor.visit_plusop(self, *args)


class MinOp(UnOp):
    def accept(self, visitor, *args):
        return visitor.visit_minop(self, *args)


class NotOp(UnOp):
    def accept(self, visitor, *args):
        return visitor.visit_notop(self, *args)


class BinOp(Expression):
    def __init__(self, left, right):
        self.left = left
        self.right = right


class MulOp(BinOp):
    def accept(self, visitor, *args):
        return visitor.visit_mulop(self, *args)


class DivOp(BinOp):
    def accept(self, visitor, *args):
        return visitor.visit_divop(self, *args)


class AddOp(BinOp):
    def accept(self, visitor, *args):
        return visitor.visit_addop(self, *args)


class SubOp(BinOp):
    def accept(self, visitor, *args):
        return visitor.visit_subop(self, *args)


class LtOp(BinOp):
    def accept(self, visitor, *args):
        return visitor.visit_ltop(self, *args)


class LeOp(BinOp):
    def accept(self, visitor, *args):
        return visitor.visit_leop(self, *args)


class GtOp(BinOp):
    def accept(self, visitor, *args):
        return visitor.visit_gtop(self, *args)


class GeOp(BinOp):
    def accept(self, visitor, *args):
        return visitor.visit_geop(self, *args)


class EqOp(BinOp):
    def accept(self, visitor, *args):
        return visitor.visit_eqop(self, *args)


class NeOp(BinOp):
    def accept(self, visitor, *args):
        return visitor.visit_neop(self, *args)


class AndOp(BinOp):
    def accept(self, visitor, *args):
        return visitor.visit_andop(self, *args)


class OrOp(BinOp):
    def accept(self, visitor, *args):
        return visitor.visit_orop(self, *args)


class Variable(Expression):

    def __init__(self, name):
        self.name = name

    def accept(self, visitor, *args):
        return visitor.visit_variable(self, *args)


class Constant(Expression):

    def __init__(self, value, datatype):
        self.value = value
        self.datatype = datatype

    def accept(self, visitor, *args):
        return visitor.visit_constant(self, *args)
