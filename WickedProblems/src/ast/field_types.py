from .base_nodes import Node

class FieldType(Node):
    def __init__(self):
        Node.__init__(self, "field_type")

class Boolean(FieldType):
    def __init__(self, identifier, value = [False]):
        FieldType.__init__(self)
        self._value = bool(value[0])

class String(FieldType):
    def __init__(self, identifier, value = [""]):
        FieldType.__init__(self)
        self._value = str(value[0])

class Integer(FieldType):
    def __init__(self, identifier, value = [0]):
        FieldType.__init__(self)
        self._value = int(value[0])

class Date(FieldType):
    def __init__(self, identifier, value):
        FieldType.__init__(self)
        # TODO
        self._value = value[0]

class Decimal(FieldType):
    def __init__(self, identifier, value = [float(0)]):
        FieldType.__init__(self)
        self._value = float(value[0])

# TODO
class Money(FieldType):
    def __init__(self, identifier, value = [0]):
        FieldType.__init__(self)
        self._value = float(value[0])

# TODO
class Currency(FieldType):
    def __init__(self, identifier, value = [0]):
        FieldType.__init__(self)
        self._value = float(value[0])

class Undefined(FieldType):
    def __init__(self):
        FieldType.__init__(self)
        self._value = False
