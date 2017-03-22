# coding=utf-8
from pql.ast.ast import Boolean
from pql.messages.error import Error
from pql.traversal.BinaryExpressionVisitor import BinaryExpressionVisitor
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.IdentifierVisitor import IdentifierVisitor
from pql.traversal.TypeVisitor import TypeVisitor
from pql.traversal.UnaryExpressionVisitor import UnaryExpressionVisitor
from pql.typechecker.types import DataTypes


class TypeChecker(FormVisitor, BinaryExpressionVisitor, IdentifierVisitor, TypeVisitor, UnaryExpressionVisitor):
    def __init__(self, ast, environment_type):
        self.symbol_table = environment_type(ast).visit()
        self.ast = ast
        self.errors = list()

    def visit(self):
        self.errors.clear()
        self.ast.apply(self)
        return self.errors

    def form(self, node, args=None):
        for statement in node.statements:
            statement.apply(self)

    def conditional_if(self, node, args=None):
        condition_result = node.condition.apply(self)
        if condition_result is None or condition_result.data_type is not DataTypes.boolean:
            self.errors.append(
                Error("Invalid expression in a conditional statement, it expected a [DataTypes.boolean] "
                      "expression but received [{}], at location {}"
                      .format(condition_result, node.condition.location), node.condition.location))
        [statement.apply(self) for statement in node.statements]

    def conditional_if_else(self, node, args=None):
        self.conditional_if(node, args)
        [statement.apply(self) for statement in node.else_statement_list]

    def field(self, node, args=None):
        return node.data_type

    def assignment(self, node, args=None):
        lhs = node.data_type.apply(self).checker()
        expression_type = node.expression.apply(self)
        result = lhs.assignment(expression_type)

        if result is None:
            self.errors.append(
                Error("Expression result [{}] did not match declared type [{}], at location: {}"
                         .format(result, node.data_type.data_type, node.expression.location), node.expression.location))

    def subtraction(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        if lhs_type is not None:
            lhs_checker = lhs_type.checker()
            rhs = node.rhs.apply(self)
            if rhs is not None:
                return lhs_checker.subtraction(rhs)
        return None

    def division(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        if lhs_type is not None:
            lhs_checker = lhs_type.checker()
            rhs = node.rhs.apply(self)
            if rhs is not None:
                return lhs_checker.division(rhs)
        return None

    def multiplication(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        if lhs_type is not None:
            lhs_checker = lhs_type.checker()
            rhs = node.rhs.apply(self)
            if rhs is not None:
                return lhs_checker.multiplication(rhs)
        return None

    def addition(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        if lhs_type is not None:
            lhs_checker = lhs_type.checker()
            rhs = node.rhs.apply(self)
            if rhs is not None:
                return lhs_checker.addition(rhs)
        return None

    def greater_exclusive(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        if lhs_type is not None:
            lhs_checker = lhs_type.checker()
            rhs = node.rhs.apply(self)
            if rhs is not None:
                result = lhs_checker.greater_exclusive(rhs)
                if result is not None:
                    return Boolean(0, '')
        return None

    def greater_inclusive(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        if lhs_type is not None:
            lhs_checker = lhs_type.checker()
            rhs = node.rhs.apply(self)
            if rhs is not None:
                result = lhs_checker.greater_inclusive(rhs)
                if result is not None:
                    return Boolean(0, '')
        return None

    def lower_inclusive(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        if lhs_type is not None:
            lhs_checker = lhs_type.checker()
            rhs = node.rhs.apply(self)
            if rhs is not None:
                result = lhs_checker.lower_inclusive(rhs)
                if result is not None:
                    return Boolean(0, '')
        return None

    def lower_exclusive(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        if lhs_type is not None:
            lhs_checker = lhs_type.checker()
            rhs = node.rhs.apply(self)
            if rhs is not None:
                result = lhs_checker.lower_exclusive(rhs)
                if result is not None:
                    return Boolean(0, '')
        return None

    def equality(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        if lhs_type is not None:
            lhs_checker = lhs_type.checker()
            rhs = node.rhs.apply(self)
            if rhs is not None:
                result = lhs_checker.equality(rhs)
                if result is not None:
                    return Boolean(0, '')
        return None

    def inequality(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        if lhs_type is not None:
            lhs_checker = lhs_type.checker()
            rhs = node.rhs.apply(self)
            if rhs is not None:
                result = lhs_checker.inequality(rhs)
                if result is not None:
                    return Boolean(0, '')
        return None

    def and_(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        if lhs_type is not None:
            lhs_checker = lhs_type.checker()
            rhs = node.rhs.apply(self)
            if rhs is not None:
                result = lhs_checker.and_(rhs)
                if result is not None:
                    return Boolean(0, '')
        return None

    def or_(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        if lhs_type is not None:
            lhs_checker = lhs_type.checker()
            rhs = node.rhs.apply(self)
            if rhs is not None:
                result = lhs_checker.or_(rhs)
                if result is not None:
                    return Boolean(0, '')
        return None

    def negation(self, node, args=None):
        operand_result = node.operand.apply(self)
        if operand_result is not None:
            lhs_checker = operand_result.checker()
            return lhs_checker.negation(node)
        return None

    def positive(self, node, args=None):
        operand_result = node.operand.apply(self)
        if operand_result is not None:
            lhs_checker = operand_result.checker()
            return lhs_checker.positive(node)
        return None

    def negative(self, node, args=None):
        operand_result = node.operand.apply(self)
        if operand_result is not None:
            lhs_checker = operand_result.checker()
            return lhs_checker.negative(node)
        return None

    def identifier(self, node, args=None):
        return self.symbol_table[node.name]

    def integer(self, node, args=None):
        return node

    def money(self, node, args=None):
        return node

    def boolean(self, node, args=None):
        return node

    def string(self, node, args=None):
        return node
