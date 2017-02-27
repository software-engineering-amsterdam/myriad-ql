# coding=utf-8
from pql.visitor.visitor import Visitor
from pql.typechecker.types import DataTypes


class TypeChecker(Visitor):

    def visit(self, pql_ast):
        return [form.apply(self) for form in pql_ast]

    def form(self, node):
        return [statement.apply(self) for statement in node.children]

    def field(self, node):
        return [arithmetic_statement.apply(self) for arithmetic_statement in node.children]

    def expression(self, node):
        return [expression.apply(self) for expression in node.children]

    def subtraction(self, node):
        return self.binary(node)

    def division(self, node):
        return self.binary(node)

    def multiplication(self, node):
        return self.binary(node)

    def addition(self, node):
        return self.binary(node)

    def conditional(self, node):
        return []

    def greater_exclusive(self, node):
        return []

    def greater_inclusive(self, node):
        return []

    def lower_inclusive(self, node):
        return []

    def lower_exclusive(self, node):
        return []

    def equality(self, node):
        return []

    def inequality(self, node):
        return []

    def and_(self, node):
        return []

    def or_(self, node):
        return []

    def binary(self, node):
        allowed_types = {DataTypes.integer, DataTypes.money}
        type = None
        errors = []
        type_left = node.lhs.apply(self)
        type_right = node.rhs.apply(self)
        type_set = {type_left, type_right}

        if type_set.issubset(allowed_types):
            if len(type_set) is 2:
                type = DataTypes.money
            else:
                type = DataTypes.integer
        else:
            errors.append("TypeMismatch: The given leaves are of type %s, and only %s types are allowed" % (
            type_set, allowed_types))

        # print(type, errors)

        return (type, errors)

    def identifier(self, node):
        return node.name

    def value(self, node):
        return node.data_type
