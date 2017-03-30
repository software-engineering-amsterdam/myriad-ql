# coding=utf-8
from pql.traversal.BinaryExpressionVisitor import BinaryExpressionVisitor
from pql.traversal.UnaryExpressionVisitor import UnaryExpressionVisitor
from pql.typechecker.types import is_number_type
from pql.typechecker.none_type import NoneType
from pql.ast.ast import Boolean


class IntegerTypeChecker(BinaryExpressionVisitor, UnaryExpressionVisitor):
    def subtraction(self, node, args=None):
        if is_number_type(node.data_type):
            return node
        return NoneType()

    def division(self, node, args=None):
        if is_number_type(node.data_type):
            return node
        return NoneType()

    def multiplication(self, node, args=None):
        if is_number_type(node.data_type):
            return node
        return NoneType()

    def addition(self, node, args=None):
        if is_number_type(node.data_type):
            return node
        return NoneType()

    def greater_exclusive(self, node, args=None):
        if is_number_type(node.data_type):
            return Boolean()
        return NoneType()

    def greater_inclusive(self, node, args=None):
        if is_number_type(node.data_type):
            return Boolean()
        return NoneType()

    def lower_inclusive(self, node, args=None):
        if is_number_type(node.data_type):
            return Boolean()
        return NoneType()

    def lower_exclusive(self, node, args=None):
        if is_number_type(node.data_type):
            return Boolean()
        return NoneType()

    def equality(self, node, args=None):
        if is_number_type(node.data_type):
            return Boolean()
        return NoneType()

    def inequality(self, node, args=None):
        if is_number_type(node.data_type):
            return Boolean()
        return NoneType()

    def and_(self, node, args=None):
        return NoneType()

    def or_(self, node, args=None):
        return NoneType()

    def negation(self, node, args=None):
        return NoneType()

    def positive(self, node, args=None):
        return node.operand

    def negative(self, node, args=None):
        return node.operand

    def assignment(self, node, args=None):
        if is_number_type(node.data_type):
            return node
        return NoneType()
