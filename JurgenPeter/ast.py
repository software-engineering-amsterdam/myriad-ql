from enum import Enum

Datatype = Enum("Datatype", "boolean string integer date decimal money")
Operator = Enum("Operator", "plus minus times divided not and or lt gt leq geq eq neq")


class Form:

    def __init__(self, name, questions):
        self.name = name
        self.statements = statements


class Conditional:

    def __init__(self, condition, statements):
        self.condition = condition
        self.statements = statements


class Question:

    def __init__(self, name, label, datatype, expression=None):
        self.name = name
        self.label = label
        self.datatype = datatype
        self.expression = expression


class UnaryOperator:

    def __init__(self, operator, right):
        self.operator = operator
        self.right = right


class BinaryOperator:

    def __init__(self, operator, left, right):
        self.operator = operator
        self.left = left
        self.right = right


class Indentifier:

    def __init__(self, name):
        self.name = name


class Constant:

    def __init__(self, value, datatype):
        self.value = value
        self.datatype = datatype
