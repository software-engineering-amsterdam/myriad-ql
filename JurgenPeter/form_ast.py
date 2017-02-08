from enum import Enum

Datatype = Enum("Datatype", "boolean string integer decimal money")
Operator = Enum("Operator", "+ - * / ! && || < > <= >= == !=")


class Form:

    def __init__(self, name, statements):
        self.name = name
        self.statements = statements

    def __str__(self):
        return "form {} [\n{}\n]".format(self.name, "\n".join(
            [str(s) for s in self.statements]))


class Conditional:

    def __init__(self, condition, statements, alternative=None):
        self.condition = condition
        self.statements = statements
        self.alternative = alternative

    def __str__(self):
        if self.alternative is not None:
            return "if {} [\n{}\n]\nelse [\n{}\n]".format(
                self.condition, "\n".join([str(s) for s in self.statements]),
                "\n".join([str(s) for s in self.alternative]))
        return "if {} [\n{}\n]".format(self.condition, "\n".join(
            [str(s) for s in self.statements]))


class Question:

    def __init__(self, name, label, datatype, expression=None):
        self.name = name
        self.label = label
        self.datatype = datatype
        self.expression = expression

    def __str__(self):
        if self.expression is not None:
            return "{}: \"{}\" {} = {}".format(
                self.name, self.label, self.datatype.name, self.expression)
        return "{}: \"{}\" {}".format(
            self.name, self.label, self.datatype.name)


class UnaryOperator:

    def __init__(self, operator, right):
        self.operator = operator
        self.right = right

    def __str__(self):
        return "{}{}".format(self.operator.name, self.right)


class BinaryOperator:

    def __init__(self, left, operator, right):
        self.operator = operator
        self.left = left
        self.right = right

    def __str__(self):
        return "({} {} {})".format(
            self.left, self.operator.name, self.right)


class Identifier:

    def __init__(self, name):
        self.name = name

    def __str__(self):
        return self.name


class Constant:

    def __init__(self, value, datatype):
        self.value = value
        self.datatype = datatype

    def __str__(self):
        return str(self.value)
