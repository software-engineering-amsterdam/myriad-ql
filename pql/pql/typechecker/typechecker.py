# coding=utf-8
from pql.ast.ast import Money, Integer, Boolean
from pql.traversal.ExpressionVisitor import ExpressionVisitor
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.IdentifierVisitor import IdentifierVisitor
from pql.typechecker.types import DataTypes


class TypeChecker(FormVisitor, ExpressionVisitor, IdentifierVisitor):
    def __init__(self, ast, environment_type):
        self.symbol_table = environment_type(ast).visit()
        self.ast = ast
        self.errors = list()

    def visit(self):
        self.errors.clear()
        self.ast.apply(self)
        return self.errors

    def form(self, node):
        for statement in node.statements:
            statement.apply(self)

    def field(self, node):
        return node.data_type

    def assignment(self, node):
        result = node.expression.apply(self)
        if (result is not None) and (node.data_type.data_type is DataTypes.boolean) and (
                    result.data_type is not node.data_type.data_type):
            self.errors.append(
                "Expression of field [{}] did not match declared type [{}], at location: {}"
                    .format(result, node.data_type.data_type, node.expression.location))

    def subtraction(self, node):
        return self.arithmetic_type_detection(node)

    def division(self, node):
        return self.arithmetic_type_detection(node)

    def multiplication(self, node):
        return self.arithmetic_type_detection(node)

    def addition(self, node):
        return self.arithmetic_type_detection(node)

    def conditional_if(self, node):
        condition_result = node.condition.apply(self)
        if condition_result.data_type is not DataTypes.boolean:
            self.errors.append(
                "Invalid expression in a conditional statement, it expected a [DataTypes.boolean] expression but "
                "received [{}], at location {}".format(condition_result, node.condition.location))
        [statement.apply(self) for statement in node.statements]

    def conditional_if_else(self, node):
        condition_result = node.condition.apply(self)
        if condition_result is None:
            self.errors.append(
                "Invalid expression in a conditional statement, it expected a [DataTypes.boolean] expression but "
                "received [{}], at location {}".format(condition_result, node.condition.location))
        elif condition_result.data_type is not DataTypes.boolean:
            self.errors.append(
                "Invalid expression in a conditional statement, it expected a [DataTypes.boolean] expression but "
                "received [{}], at location {}".format(condition_result, node.condition.location))
        [statement.apply(self) for statement in node.statements]
        [statement.apply(self) for statement in node.else_statement_list]

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
        return self.boolean_type_detection(node, allowed_arithmetic_types=set())

    def or_(self, node):
        return self.boolean_type_detection(node, allowed_arithmetic_types=set())

    def negation(self, node):
        if node.operand.apply(self) is DataTypes.boolean:
            return DataTypes.boolean
        self.errors.append("Negation was passed a non-boolean value on location {} ".format(node.location))
        return None

    def positive(self, node):
        result = node.operand.apply(self)
        if result is (DataTypes.integer or DataTypes.money):
            return result
        self.errors.append("Positive was passed a non-numeric value on location {} ".format(node.location))
        return None

    def negative(self, node):
        result = node.operand.apply(self)
        if result is (DataTypes.integer or DataTypes.money):
            return result
        self.errors.append("Negative was passed a non-numeric value on location {} ".format(node.location))
        return None

    def arithmetic_type_detection(self, node, allowed_types={DataTypes.integer, DataTypes.money}):
        dominant_type = None
        type_set = {node.lhs.apply(self), node.rhs.apply(self)}
        type_set_data_types = {d_type.data_type for d_type in type_set if d_type is not None}

        if type_set_data_types.issubset(allowed_types):
            if DataTypes.money in type_set_data_types:
                dominant_type = Money(0, '', 0)
            else:
                dominant_type = Integer(0, '', 0)
        else:
            self.add_leaf_error(allowed_types, type_set)
        return dominant_type

    def boolean_type_detection(self, node, allowed_arithmetic_types={DataTypes.integer, DataTypes.money},
                               allowed_boolean_types={DataTypes.boolean}):
        dominant_type = None
        allowed_types = allowed_arithmetic_types.union(allowed_boolean_types)
        type_set = [node.lhs.apply(self), node.rhs.apply(self)]
        type_set_data_types = {d_type.data_type for d_type in type_set if d_type is not None}

        if type_set_data_types.issubset(allowed_types):
            if type_set_data_types.issubset(allowed_arithmetic_types):
                dominant_type = Boolean(0, '', False)
            elif type_set_data_types.issubset(allowed_boolean_types):
                dominant_type = Boolean(0, '', False)
            else:
                self.add_leaf_error(allowed_types, type_set)
        else:
            self.add_leaf_error(allowed_types, type_set)

        return dominant_type

    def add_leaf_error(self, allowed_types, type_set):
        self.errors.append("Type mismatch: the following types were incompatible {}, only {} is allowed "
                           .format(['{}: {}'.format(t.location, t.data_type) for t in type_set],
                                   [str(a) for a in allowed_types]))

    def identifier(self, node):
        return self.symbol_table[node.name]

    def integer(self, node):
        return node

    def money(self, node):
        return node

    def boolean(self, node):
        return node
