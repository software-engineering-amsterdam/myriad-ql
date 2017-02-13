from enum import Enum

Datatype = Enum("Datatype", "boolean string integer decimal money")
Operator = Enum("Operator", "+ - * / ! && || < > <= >= == !=")


class Node:

    # TODO generic tree node comparison based on attrs dictionary
    def __eq__(self, other):
        return True


class Form:

    def __init__(self, name, statements):
        self.name = name
        self.statements = statements

    def __str__(self):
        return "form {} [\n{}\n]".format(self.name,
            "\n".join([s.__str__(4) for s in self.statements]))

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class Cond:

    def __init__(self, condition, statements, alternative=None):
        self.condition = condition
        self.statements = statements
        self.alternative = alternative

    def __str__(self, indentation=0):
        if self.alternative is not None:
            return "{}if {} [\n{}\n{}]\n{}else [\n{}\n]".format(
                " " * indentation, self.condition,
                "\n".join([s.__str__(indentation + 4) for s in self.statements]),
                " " * indentation, " " * indentation,
                "\n".join([s.__str__(indentation + 4) for s in self.alternative]))
        return "{}if {} [\n{}\n{}]".format(" " * indentation, self.condition,
            "\n".join([s.__str__(indentation + 4) for s in self.statements]),
            " " * indentation)

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class Quest:

    def __init__(self, name, label, datatype, expression=None):
        self.name = name
        self.label = label
        self.datatype = datatype
        self.expression = expression

    def __str__(self, indentation=0):
        if self.expression is not None:
            return "{}{}: \"{}\" {} = {}".format(
                " " * indentation, self.name, self.label, self.datatype.name,
                self.expression)
        return "{}{}: \"{}\" {}".format(
            " " * indentation, self.name, self.label, self.datatype.name)

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class UnOp:

    def __init__(self, operator, right):
        self.operator = operator
        self.right = right

    def __str__(self):
        return "{}{}".format(self.operator.name, self.right)

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class BinOp:

    def __init__(self, left, operator, right):
        self.operator = operator
        self.left = left
        self.right = right

    def __str__(self):
        return "({} {} {})".format(self.left, self.operator.name, self.right)

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class Iden:

    def __init__(self, name):
        self.name = name

    def __str__(self):
        return self.name

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class Const:

    def __init__(self, value, datatype):
        self.value = value
        self.datatype = datatype

    def __str__(self):
        return str(self.value)

    def __eq__(self, other):
        return self.__dict__ == other.__dict__
