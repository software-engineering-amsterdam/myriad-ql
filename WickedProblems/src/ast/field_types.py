from .base_nodes import Node

class Value(Node):
    value = None

class FieldType(Node):
    pass

class Boolean(Value):
    def __init__(self, value=[False]):
        self.value = bool(value[0])

    def alg(self, _alg):
        return _alg.Boolean(self.value)

class String(Value):
    def __init__(self, value=""):
        self.value = str(value)

    def alg(self, _alg):
        return _alg.String(self.value)

class StringLiteral(Value):
    def __init__(self, value=""):
        self.value = str(value)

    def alg(self, _alg):
        return _alg.StringLiteral(self.value)

class Integer(Value):
    def __init__(self, value=0):
        self.value = int(value)

    def alg(self, _alg):
        return _alg.Integer(self.value)


class Date(Value):
    def __init__(self, value):
        self._value = value[0]


class Decimal(Value):
    def __init__(self, value=[float(0)]):
        self._value = float(value[0])

class Money(Value):
    def __init__(self, value=[0]):
        self._value = float(value[0])

    def alg(self, _alg):
        return _alg.Money(self.value)

class Currency(Value):
    def __init__(self, value=[0]):
        self.value = float(value[0])

class Undefined(Value):
    def __init__(self):
        self._value = False
