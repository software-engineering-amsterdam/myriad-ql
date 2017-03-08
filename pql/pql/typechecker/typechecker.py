# coding=utf-8
from pql.traversal.ExpressionVisitor import ExpressionVisitor
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.IdentifierVisitor import IdentifierVisitor
from pql.typechecker.types import DataTypes


class TypeChecker(FormVisitor, ExpressionVisitor, IdentifierVisitor):
    def __init__(self, ql_identifier_check_result):
        self.identifier_dict = ql_identifier_check_result
        self.errors = list()

    def visit(self, pql_ast):
        self.errors.clear()
        [form.apply(self) for form in pql_ast]
        return self.errors

    def form(self, node):
        [statement.apply(self) for statement in node.statements]

    def field(self, node):
        if node.expression is not None:
            node.expression.apply(self)

    def subtraction(self, node):
        return self.type_detection(node, self.arithmetic_type_detection)

    def division(self, node):
        return self.type_detection(node, self.arithmetic_type_detection)

    def multiplication(self, node):
        return self.type_detection(node, self.arithmetic_type_detection)

    def addition(self, node):
        return self.type_detection(node, self.arithmetic_type_detection)

    def conditional_if(self, node):
        condition = node.condition.apply(self)
        if condition is not DataTypes.boolean:
            self.errors.append("Condition does not contain a boolean expression: %s" % condition)
        [statement.apply(self) for statement in node.statements]

    def conditional_if_else(self, node):
        condition = node.condition.apply(self)
        if condition is not DataTypes.boolean:
            self.errors.append("Condition does not contain a boolean expression: %s" % condition)
        [statement.apply(self) for statement in node.statements]
        [statement.apply(self) for statement in node.else_statement_list]

    def greater_exclusive(self, node):
        return self.type_detection(node, self.boolean_type_detection)

    def greater_inclusive(self, node):
        return self.type_detection(node, self.boolean_type_detection)

    def lower_inclusive(self, node):
        return self.type_detection(node, self.boolean_type_detection)

    def lower_exclusive(self, node):
        return self.type_detection(node, self.boolean_type_detection)

    def equality(self, node):
        return self.type_detection(node, self.boolean_type_detection)

    def inequality(self, node):
        return self.type_detection(node, self.boolean_type_detection)

    def and_(self, node):
        return self.type_detection(node, self.boolean_type_detection, allowed_arithmetic_types=set())

    def or_(self, node):
        return self.type_detection(node, self.boolean_type_detection, allowed_arithmetic_types=set())

    def negation(self, node):
        allowed_types = DataTypes.boolean
        rhs = node.rhs.apply(self)
        if rhs is allowed_types:
            return DataTypes.boolean
        return None


    def arithmetic_type_detection(self, allowed_arithmetic_types, _, type_set):
        dominant_type = None
        if type_set.issubset(allowed_arithmetic_types):
            if DataTypes.money in type_set:
                dominant_type = DataTypes.money
            else:
                dominant_type = DataTypes.integer
        else:
            self.errors.append("TypeMismatch: The given leaves are of type %s, and only %s types are allowed" % (
                type_set, allowed_arithmetic_types))
        return dominant_type

    def boolean_type_detection(self, allowed_arithmetic_types, allowed_boolean_types, type_set):
        dominant_type = None
        allowed_types = allowed_arithmetic_types.union(allowed_boolean_types)
        if type_set.issubset(allowed_types):
            if type_set.issubset(allowed_arithmetic_types):
                dominant_type = DataTypes.boolean
            elif type_set.issubset(allowed_boolean_types):
                dominant_type = DataTypes.boolean
            else:
                self.errors.append("TypeMismatch: The given leaves are of type %s, and only %s types are allowed" % (
                    type_set, allowed_types))
        else:
            self.errors.append("TypeMismatch: The given leaves are of type %s, and only %s types are allowed" % (
                type_set, allowed_types))

        return dominant_type

    def type_detection(self, node, func, allowed_arithmetic_types={DataTypes.integer, DataTypes.money},
                       allowed_boolean_types={DataTypes.boolean}):
        type_set = {node.lhs.apply(self), node.rhs.apply(self)}
        return func(allowed_arithmetic_types, allowed_boolean_types, type_set)

    def identifier(self, node):
        return self.identifier_dict[node.name].data_type

    def value(self, node):
        return node.data_type
