# coding=utf-8
from pql.visitor.visitor import Visitor
from pql.typechecker.types import DataTypes


class TypeChecker(Visitor):

    def __init__(self, ql_identifier_check_result):
        self.identifier_dict = ql_identifier_check_result
        self.errors = list()

    def visit(self, pql_ast):
        print("visit")
        [form.apply(self) for form in pql_ast]

    def form(self, node):
        print("form")
        [statement.apply(self) for statement in node.children]

    def field(self, node):
        print("field")
        [arithmetic_statement.apply(self) for arithmetic_statement in node.children]

    def expression(self, node):
        print("expression")
        return [expression.apply(self) for expression in node.children]

    def subtraction(self, node):
        return self.arithmetic_type_detection(node)

    def division(self, node):
        return self.arithmetic_type_detection(node)

    def multiplication(self, node):
        return self.arithmetic_type_detection(node)

    def addition(self, node):
        return self.arithmetic_type_detection(node)

    def conditional(self, node):
        return []

    def greater_exclusive(self, node):
        return self.boolean_type_detection(node)

    def greater_inclusive(self, node):
        return self.boolean_type_detection(node)

    def lower_inclusive(self, node):
        return self.boolean_type_detection(node)

    def lower_exclusive(self, node):
        return self.boolean_type_detection(node)

    def equality(self, node):
        return self.boolean_type_detection(node)

    def inequality(self, node):
        return self.boolean_type_detection(node)

    def and_(self, node):
        return self.boolean_type_detection(node, aat=set())

    def or_(self, node):
        return self.boolean_type_detection(node, aat=set())

    def arithmetic_type_detection(self, node):
        allowed_types = {DataTypes.integer, DataTypes.money}
        dominant_type = None
        type_left = node.lhs.apply(self)
        type_right = node.rhs.apply(self)
        type_set = {type_left, type_right}

        if type_set.issubset(allowed_types):
            if DataTypes.money in type_set:
                dominant_type = DataTypes.money
            else:
                dominant_type = DataTypes.integer
        else:
            self.errors.append("TypeMismatch: The given leaves are of type %s, and only %s types are allowed" % (
            type_set, allowed_types))

        return dominant_type

    def boolean_type_detection(self, node, aat={DataTypes.integer, DataTypes.money}, abt={DataTypes.boolean}):
        allowed_arithmetic_types = aat
        allowed_boolean_types = abt
        allowed_types = allowed_arithmetic_types.union(allowed_boolean_types)
        dominant_type = None
        type_left = node.lhs.apply(self)
        type_right = node.rhs.apply(self)
        type_set = {type_left, type_right}

        if type_set.issubset(allowed_types):
            if type_set.issubset(allowed_arithmetic_types):
               dominant_type = DataTypes.boolean
            elif type_set.issubset(allowed_boolean_types):
                dominant_type = DataTypes.boolean
        else:
            self.errors.append("TypeMismatch: The given leaves are of type %s, and only %s types are allowed" % (
                type_set, allowed_types))

        return dominant_type

    def identifier(self, node):
        return self.identifier_dict[node.name]

    def value(self, node):
        return node.data_type
