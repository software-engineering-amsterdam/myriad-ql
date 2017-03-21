# coding=utf-8
from collections import defaultdict

from pql.messages.error import Error
from pql.traversal.BinaryExpressionVisitor import BinaryExpressionVisitor
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.IdentifierVisitor import IdentifierVisitor
from pql.traversal.TypeVisitor import TypeVisitor
from pql.traversal.UnaryExpressionVisitor import UnaryExpressionVisitor


class IdentifierChecker(FormVisitor, BinaryExpressionVisitor, UnaryExpressionVisitor, TypeVisitor, IdentifierVisitor):
    def __init__(self, ast):
        self.__symbol_table = defaultdict(list)
        self.__errors = list()
        self.ast = ast

    def visit(self):
        def build_error_list(identifiers):
            for key, value in identifiers.items():
                if len(value) > 1:
                    self.__errors.append(Error("Key: {} contained multiple entries, at the following locations: {}"
                                  .format(key, [v.location for v in value]), value[0].location))
        self.__errors.clear()
        self.__symbol_table.clear()
        self.ast.apply(self)
        build_error_list(self.__symbol_table)
        return self.__errors

    def form(self, node, args=None):
        for statement in node.statements:
            statement.apply(self)

    def conditional_if_else(self, node, args=None):
        self.conditional_if(node)
        for statement in node.else_statement_list:
            statement.apply(self)

    def conditional_if(self, node, args=None):
        result = node.condition.apply(self)
        self.find_unknown_identifiers(node, result)
        for statement in node.statements:
            statement.apply(self)

    def field(self, node, args=None):
        self.__symbol_table[node.name.name].append(node.name)

    def assignment(self, node, args=None):
        self.field(node, args)
        result = node.expression.apply(self)
        self.find_unknown_identifiers(node, result)

    def find_unknown_identifiers(self, node, result):
        for key in result:
            if key not in self.__symbol_table:
                self.__errors.append(Error("Identifier {} was not found in assignment on location {}"
                                           .format(key, node.location)))

    def greater_inclusive(self, node, args=None):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def addition(self, node, args=None):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def and_(self, node, args=None):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def subtraction(self, node, args=None):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def lower_inclusive(self, node, args=None):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def inequality(self, node, args=None):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def lower_exclusive(self, node, args=None):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def or_(self, node, args=None):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def multiplication(self, node, args=None):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def greater_exclusive(self, node, args=None):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def division(self, node, args=None):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def equality(self, node, args=None):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def positive(self, node, args=None):
        return node.operand.apply(self)

    def negation(self, node, args=None):
        return node.operand.apply(self)

    def negative(self, node, args=None):
        return node.operand.apply(self)

    def money(self, node, args=None):
        return []

    def integer(self, node, args=None):
        return []

    def string(self, node, args=None):
        return []

    def boolean(self, node, args=None):
        return []

    def identifier(self, node, args=None):
        return [node.name]
