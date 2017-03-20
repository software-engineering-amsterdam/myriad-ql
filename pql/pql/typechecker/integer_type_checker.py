# coding=utf-8
from pql.traversal.BinaryExpressionVisitor import BinaryExpressionVisitor
from pql.traversal.UnaryExpressionVisitor import UnaryExpressionVisitor
from pql.typechecker.types import is_number_type, DataTypes


class IntegerTypeChecker(BinaryExpressionVisitor, UnaryExpressionVisitor):
    def subtraction(self, node):
        if is_number_type(node.data_type):
            return node
        return None

    def division(self, node):
        if is_number_type(node.data_type):
            return node
        return None

    def multiplication(self, node):
        if is_number_type(node.data_type) or node.data_type is DataTypes.string:
            return node
        return None

    def addition(self, node):
        if is_number_type(node.data_type):
            return node
        return None

    def greater_exclusive(self, node):
        if is_number_type(node.data_type):
            return True
        return None

    def greater_inclusive(self, node):
        if is_number_type(node.data_type):
            return True
        return None

    def lower_inclusive(self, node):
        if is_number_type(node.data_type):
            return True
        return None

    def lower_exclusive(self, node):
        if is_number_type(node.data_type):
            return True
        return None

    def equality(self, node):
        if is_number_type(node.data_type):
            return True
        return None

    def inequality(self, node):
        if is_number_type(node.data_type):
            return True
        return None

    def and_(self, node):
        return None

    def or_(self, node):
        return None

    def negation(self, node):
        return None

    def positive(self, node):
        return node.operand

    def negative(self, node):
        return node.operand

    def assignment(self, node):
        if node is not None and is_number_type(node.data_type):
            return node
        return None
