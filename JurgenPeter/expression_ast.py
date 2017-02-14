class UnOp:

    def __init__(self, right):
        self.right = right

    def evaluate(self, environment):
        return None

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class PlusOp(UnOp):

    def evaluate(self, environment):
        return self.right.evaluate(environment)

    def __str__(self):
        return "+{}".format(self.right)


class MinOp(UnOp):

    def evaluate(self, environment):
        right_value = self.right.evaluate(environment)
        if right_value is not None:
            return -right_value
        return None

    def __str__(self):
        return "-{}".format(self.right)


class NotOp(UnOp):

    def evaluate(self, environment):
        right_value = self.right.evaluate(environment)
        if right_value is not None:
            return not right_value
        return None

    def __str__(self):
        return "!{}".format(self.right)


class BinOp:

    def __init__(self, left, right):
        self.left = left
        self.right = right

    def evaluate(self, environment):
        return None

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__


class MulOp(BinOp):

    def evaluate(self, environment):
        left_value = self.left.evaluate(environment)
        right_value = self.right.evaluate(environment)
        if left_value is not None and right_value is not None:
            return left_value * right_value
        return None

    def __str__(self):
        return "({} * {})".format(self.left, self.right)


class DivOp(BinOp):

    def evaluate(self, environment):
        left_value = self.left.evaluate(environment)
        right_value = self.right.evaluate(environment)
        if left_value is not None and right_value is not None:
            return left_value / right_value
        return None

    def __str__(self):
        return "({} / {})".format(self.left, self.right)


class AddOp(BinOp):

    def evaluate(self, environment):
        left_value = self.left.evaluate(environment)
        right_value = self.right.evaluate(environment)
        if left_value is not None and right_value is not None:
            return left_value + right_value
        return None

    def __str__(self):
        return "({} + {})".format(self.left, self.right)


class SubOp(BinOp):

    def evaluate(self, environment):
        left_value = self.left.evaluate(environment)
        right_value = self.right.evaluate(environment)
        if left_value is not None and right_value is not None:
            return left_value - right_value
        return None

    def __str__(self):
        return "({} - {})".format(self.left, self.right)


class LtOp(BinOp):

    def evaluate(self, environment):
        left_value = self.left.evaluate(environment)
        right_value = self.right.evaluate(environment)
        if left_value is not None and right_value is not None:
            return left_value < right_value
        return None

    def __str__(self):
        return "({} < {})".format(self.left, self.right)


class LeOp(BinOp):

    def evaluate(self, environment):
        left_value = self.left.evaluate(environment)
        right_value = self.right.evaluate(environment)
        if left_value is not None and right_value is not None:
            return left_value <= right_value
        return None

    def __str__(self):
        return "({} <= {})".format(self.left, self.right)


class GtOp(BinOp):

    def evaluate(self, environment):
        left_value = self.left.evaluate(environment)
        right_value = self.right.evaluate(environment)
        if left_value is not None and right_value is not None:
            return left_value > right_value
        return None

    def __str__(self):
        return "({} > {})".format(self.left, self.right)


class GeOp(BinOp):

    def evaluate(self, environment):
        left_value = self.left.evaluate(environment)
        right_value = self.right.evaluate(environment)
        if left_value is not None and right_value is not None:
            return left_value >= right_value
        return None

    def __str__(self):
        return "({} >= {})".format(self.left, self.right)


class EqOp(BinOp):

    def evaluate(self, environment):
        left_value = self.left.evaluate(environment)
        right_value = self.right.evaluate(environment)
        if left_value is not None and right_value is not None:
            return left_value == right_value
        return None

    def __str__(self):
        return "({} == {})".format(self.left, self.right)


class NeOp(BinOp):

    def evaluate(self, environment):
        left_value = self.left.evaluate(environment)
        right_value = self.right.evaluate(environment)
        if left_value is not None and right_value is not None:
            return left_value != right_value
        return None

    def __str__(self):
        return "({} != {})".format(self.left, self.right)


class AndOp(BinOp):

    def evaluate(self, environment):
        left_value = self.left.evaluate(environment)
        right_value = self.right.evaluate(environment)
        if left_value is not None and right_value is not None:
            return left_value and right_value
        return None

    def __str__(self):
        return "({} && {})".format(self.left, self.right)


class OrOp(BinOp):

    def evaluate(self, environment):
        left_value = self.left.evaluate(environment)
        right_value = self.right.evaluate(environment)
        if left_value is not None and right_value is not None:
            return left_value or right_value
        return None

    def __str__(self):
        return "({} || {})".format(self.left, self.right)


class Variable:

    def __init__(self, identifier):
        self.identifier = identifier

    def evaluate(self, environment):
        if self.indentifier in environment:
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
