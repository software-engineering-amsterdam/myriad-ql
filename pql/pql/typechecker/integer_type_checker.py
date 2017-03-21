# coding=utf-8
from pql.traversal.BinaryExpressionVisitor import BinaryExpressionVisitor
from pql.traversal.UnaryExpressionVisitor import UnaryExpressionVisitor
from pql.typechecker.types import is_number_type


class IntegerTypeChecker(BinaryExpressionVisitor, UnaryExpressionVisitor):
    def subtraction(self, node, args=None):
        if is_number_type(node.data_type):
            return node
        return None

    def division(self, node, args=None):
        if is_number_type(node.data_type):
            return node
        return None

    def multiplication(self, node, args=None):
        if is_number_type(node.data_type):
            return node
        return None

    def addition(self, node, args=None):
        if is_number_type(node.data_type):
            return node
        return None

    def greater_exclusive(self, node, args=None):
        if is_number_type(node.data_type):
            return True
        return None

    def greater_inclusive(self, node, args=None):
        if is_number_type(node.data_type):
            return True
        return None

    def lower_inclusive(self, node, args=None):
        if is_number_type(node.data_type):
            return True
        return None

    def lower_exclusive(self, node):
        if is_number_type(node.data_type):
            return True
        return None

    def equality(self, node, args=None):
        if is_number_type(node.data_type):
            return True
        return None

    def inequality(self, node, args=None):
        if is_number_type(node.data_type):
            return True
        return None

    def and_(self, node, args=None):
        return None

    def or_(self, node, args=None):
        return None

    def negation(self, node, args=None):
        return None

    def positive(self, node, args=None):
        return node.operand

    def negative(self, node, args=None):
        return node.operand

    def assignment(self, node, args=None):
        if node is not None and is_number_type(node.data_type):
            return node
        return None
