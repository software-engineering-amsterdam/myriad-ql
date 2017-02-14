from enum import Enum
from expression_ast import *

Datatype = Enum("Datatype", "boolean string integer decimal money")
Operator = Enum("Operator", "+ - * / ! && || < > <= >= == !=")


class Form:

    def __init__(self, identifier, statements):
        self.identifier = identifier
        self.statements = statements

    def __str__(self):
        return "form {} [\n{}\n]".format(
            self.identifier,
            "\n".join([s.__str__(4) for s in self.statements]))

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class Question:

    def __init__(self, identifier, label, datatype, expression=None):
        self.identifier = identifier
        self.label = label
        self.datatype = datatype
        self.expression = expression

    def __str__(self, indent=0):
        if self.expression is not None:
            return "{}{}: \"{}\" {} = {}".format(
                " " * indent, self.identifier, self.label, self.datatype.name,
                self.expression)
        return "{}{}: \"{}\" {}".format(
            " " * indent, self.identifier, self.label, self.datatype.name)

    def __eq__(self, other):
        return self.__dict__ == other.__dict__


class Conditional:

    def __init__(self, condition, statements, alternatives=None):
        self.condition = condition
        self.statements = statements
        self.alternatives = alternatives

    def __str__(self, indent=0):
        if self.alternatives is not None:
            return "{}if {} [\n{}\n{}]\n{}else [\n{}\n]".format(
                " " * indent, self.condition,
                "\n".join([s.__str__(indent + 4) for s in self.statements]),
                " " * indent, " " * indent,
                "\n".join([s.__str__(indent + 4) for s in self.alternatives]))
        return "{}if {} [\n{}\n{}]".format(
            " " * indent, self.condition,
            "\n".join([s.__str__(indent + 4) for s in self.statements]),
            " " * indent)

    def __eq__(self, other):
        return self.__dict__ == other.__dict__
