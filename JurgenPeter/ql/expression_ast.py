import operator as op


class UnOp:
    op_function = None
    as_string = ""

    def __init__(self, right):
        self.right = right

    def evaluate(self, environment):
        right_value = self.right.evaluate(environment)
        if right_value is not None:
            return self.op_function(right_value)
        return None

    def __eq__(self, other):
        return self.__dict__ == other.__dict__

    def __str__(self):
        return self.as_string + str(self.right)


class PlusOp(UnOp):
    op_function = op.pos
    as_string = "+"


class MinOp(UnOp):
    op_function = op.neg
    as_string = "-"


class NotOp(UnOp):
    op_function = op.not_
    as_string = "!"


class BinOp:
    op_function = None
    as_string = ""

    def __init__(self, left, right):
        self.left = left
        self.right = right

    def evaluate(self, environment):
        left_value = self.left.evaluate(environment)
        right_value = self.right.evaluate(environment)
        if left_value is not None and right_value is not None:
            return self.op_function(left_value, right_value)
        return None

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__

    def __str__(self):
        return "({} {} {})".format(self.left, self.as_string, self.right)


class MulOp(BinOp):
    op_function = op.mul
    as_string = "*"


class DivOp(BinOp):
    op_function = op.truediv
    as_string = "/"


class AddOp(BinOp):
    op_function = op.add
    as_string = "+"


class SubOp(BinOp):
    op_function = op.sub
    as_string = "-"


class LtOp(BinOp):
    op_function = op.lt
    as_string = "<"


class LeOp(BinOp):
    op_function = op.le
    as_string = "<="


class GtOp(BinOp):
    op_function = op.gt
    as_string = ">"


class GeOp(BinOp):
    op_function = op.ge
    as_string = ">="


class EqOp(BinOp):
    op_function = op.eq
    as_string = "=="


class NeOp(BinOp):
    op_function = op.ne
    as_string = "!="


class AndOp(BinOp):
    op_function = op.and_
    as_string = "&&"


class OrOp(BinOp):
    op_function = op.or_
    as_string = "||"


class Variable:

    def __init__(self, identifier):
        self.identifier = identifier

    def evaluate(self, environment):
        if self.identifier in environment:
            return environment[self.identifier]
        return None

    def __str__(self):
        return self.identifier

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class Constant:

    def __init__(self, value, datatype):
        self.value = value
        self.datatype = datatype

    def evaluate(self, _):
        return self.value

    def __str__(self):
        return str(self.value)

    def __eq__(self, other):
        return self.__dict__ == other.__dict__
