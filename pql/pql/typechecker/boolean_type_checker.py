# coding=utf-8
from pql.traversal.BinaryExpressionVisitor import BinaryExpressionVisitor
from pql.traversal.UnaryExpressionVisitor import UnaryExpressionVisitor
from pql.typechecker.types import DataTypes


class BooleanTypeChecker(BinaryExpressionVisitor, UnaryExpressionVisitor):
    def subtraction(self, node):
        return None

    def division(self, node):
        return None

    def multiplication(self, node):
        return None

    def addition(self, node):
        return None

    def greater_exclusive(self, node):
        return None

    def greater_inclusive(self, node):
        return None

    def lower_inclusive(self, node):
        return None

    def lower_exclusive(self, node):
        return None

    def equality(self, node):
        return True

    def inequality(self, node):
        return True

    def and_(self, node):
        if node.data_type is DataTypes.boolean:
            return node
        return None

    def or_(self, node):
        if node.data_type is DataTypes.boolean:
            return node
        return None

    def negation(self, node):
        return node.operand

    def positive(self, node):
        return None

    def negative(self, node):
        return None

    def assignment(self, node):
        if node is not None and node.data_type is DataTypes.boolean:
            return node
        return None
