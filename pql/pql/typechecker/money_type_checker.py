# coding=utf-8
from pql.traversal.BinaryExpressionVisitor import BinaryExpressionVisitor
from pql.traversal.UnaryExpressionVisitor import UnaryExpressionVisitor
from pql.typechecker.types import DataTypes


class MoneyTypeChecker(BinaryExpressionVisitor, UnaryExpressionVisitor):
    def __init__(self, node):
        self.node = node

    def subtraction(self, node):
        if self.is_number_type(node.data_type):
            return self.node
        return None

    def division(self, node):
        if self.is_number_type(node.data_type):
            return self.node
        return None

    def multiplication(self, node):
        if self.is_number_type(node.data_type):
            return self.node
        return None

    @staticmethod
    def is_number_type(node_data_type):
        return node_data_type is DataTypes.money or node_data_type is DataTypes.integer

    def addition(self, node):
        if self.is_number_type(node.data_type):
            return self.node
        return None

    def greater_exclusive(self, node):
        if self.is_number_type(node.data_type):
            return True
        return None

    def greater_inclusive(self, node):
        if self.is_number_type(node.data_type):
            return True
        return None

    def lower_inclusive(self, node):
        if self.is_number_type(node.data_type):
            return True
        return None

    def lower_exclusive(self, node):
        if self.is_number_type(node.data_type):
            return True
        return None

    def equality(self, node):
        if self.is_number_type(node.data_type):
            return True
        return None

    def inequality(self, node):
        if self.is_number_type(node.data_type):
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
        if node is not None and self.is_number_type(node.data_type):
            return self.node
        return None
