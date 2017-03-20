# coding=utf-8
from pql.traversal.BinaryExpressionVisitor import BinaryExpressionVisitor
from pql.traversal.UnaryExpressionVisitor import UnaryExpressionVisitor
from pql.typechecker.types import DataTypes


class StringTypeChecker(BinaryExpressionVisitor, UnaryExpressionVisitor):
    def subtraction(self, node):
        return None

    def division(self, node):
        return None

    def multiplication(self, node):
        if node.data_type is DataTypes.integer:
            return node

    def addition(self, node):
        if node.data_type is DataTypes.string:
            return node

    def greater_exclusive(self, node):
        if node.data_type is DataTypes.string:
            return True
        return None

    def greater_inclusive(self, node):
        if node.data_type is DataTypes.string:
            return True
        return None

    def lower_inclusive(self, node):
        if node.data_type is DataTypes.string:
            return True
        return None

    def lower_exclusive(self, node):
        if node.data_type is DataTypes.string:
            return True
        return None

    def equality(self, node):
        return True

    def inequality(self, node):
        return True

    def and_(self, node):
        return None

    def or_(self, node):
        return None

    def negation(self, node):
        return None

    def positive(self, node):
        return None

    def negative(self, node):
        return None

    def assignment(self, node):
        if node is not None and ((node.data_type is DataTypes.string) or (node.data_type is DataTypes.integer)):
            return node
        return None
