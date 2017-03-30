# coding=utf-8
from pql.traversal.BinaryExpressionVisitor import BinaryExpressionVisitor
from pql.traversal.UnaryExpressionVisitor import UnaryExpressionVisitor
from pql.typechecker.types import DataTypes
from pql.typechecker.none_type import NoneType
from pql.ast.ast import Boolean


class StringTypeChecker(BinaryExpressionVisitor, UnaryExpressionVisitor):
    def subtraction(self, node, args=None):
        return NoneType()

    def division(self, node, args=None):
        return NoneType()

    def multiplication(self, node, args=None):
        return NoneType()

    def addition(self, node, args=None):
        if node.data_type is DataTypes.string:
            return node
        return NoneType()

    def greater_exclusive(self, node, args=None):
        if node.data_type is DataTypes.string:
            return Boolean()
        return NoneType()

    def greater_inclusive(self, node, args=None):
        if node.data_type is DataTypes.string:
            return Boolean()
        return NoneType()

    def lower_inclusive(self, node, args=None):
        if node.data_type is DataTypes.string:
            return Boolean()
        return NoneType()

    def lower_exclusive(self, node, args=None):
        if node.data_type is DataTypes.string:
            return Boolean()
        return NoneType()

    def equality(self, node, args=None):
        return Boolean()

    def inequality(self, node, args=None):
        return Boolean()

    def and_(self, node, args=None):
        return NoneType()

    def or_(self, node, args=None):
        return NoneType()

    def negation(self, node, args=None):
        return NoneType()

    def positive(self, node, args=None):
        return NoneType()

    def negative(self, node, args=None):
        return NoneType()

    def assignment(self, node, args=None):
        if node.data_type is DataTypes.string:
            return node
        return NoneType()
