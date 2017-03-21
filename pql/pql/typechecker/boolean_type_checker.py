# coding=utf-8
from pql.traversal.BinaryExpressionVisitor import BinaryExpressionVisitor
from pql.traversal.UnaryExpressionVisitor import UnaryExpressionVisitor
from pql.typechecker.types import DataTypes


class BooleanTypeChecker(BinaryExpressionVisitor, UnaryExpressionVisitor):
    def subtraction(self, node, args=None):
        return None

    def division(self, node, args=None):
        return None

    def multiplication(self, node, args=None):
        return None

    def addition(self, node, args=None):
        return None

    def greater_exclusive(self, node, args=None):
        return None

    def greater_inclusive(self, node, args=None):
        return None

    def lower_inclusive(self, node, args=None):
        return None

    def lower_exclusive(self, node, args=None):
        return None

    def equality(self, node, args=None):
        return True

    def inequality(self, node, args=None):
        return True

    def and_(self, node, args=None):
        if node.data_type is DataTypes.boolean:
            return node
        return None

    def or_(self, node, args=None):
        if node.data_type is DataTypes.boolean:
            return node
        return None

    def negation(self, node, args=None):
        return node.operand

    def positive(self, node, args=None):
        return None

    def negative(self, node, args=None):
        return None

    def assignment(self, node, args=None):
        if node is not None and node.data_type is DataTypes.boolean:
            return node
        return None
