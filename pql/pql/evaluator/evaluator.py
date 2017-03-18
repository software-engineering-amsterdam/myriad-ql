# coding=utf-8
import operator

from pql.traversal.BinaryExpressionVisitor import BinaryExpressionVisitor
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.IdentifierVisitor import IdentifierVisitor
from pql.traversal.TypeVisitor import TypeVisitor
from pql.traversal.UnaryExpressionVisitor import UnaryExpressionVisitor


class Evaluator(FormVisitor, BinaryExpressionVisitor, IdentifierVisitor, TypeVisitor, UnaryExpressionVisitor):
    def __init__(self, environment_type, ast):
        self.__environment = environment_type(ast).visit()
        self.ast = ast

    def visit(self, environment=None):
        if environment is None:
            environment = self.__environment.copy()
        self.ast.apply(self)

        while set(self.__environment.items()) ^ set(environment.items()):
            environment = self.__environment
            self.ast.apply(self)

        return self.__environment

    def form(self, node, args=None):
        for statement in node.statements:
            statement.apply(self)

    def conditional_if_else(self, node, args=None):
        if node.condition.apply(self):
            for statement in node.statements:
                statement.apply(self)
        else:
            for statement in node.else_statement_list:
                statement.apply(self)

    def conditional_if(self, node, args=None):
        if node.condition.apply(self):
            for statement in node.statements:
                statement.apply(self)

    def field(self, node, args=None):
        return node.name.apply(self)

    def assignment(self, node, args=None):
        self.__environment[node.name.name] = node.expression.apply(self)

    def identifier(self, node):
        return self.__environment[node.name]

    def integer(self, node):
        return node.value

    def boolean(self, node):
        return node.value

    def string(self, node):
        return node.value

    def money(self, node):
        return node.value

    def or_(self, node):
        lhs_result = node.lhs.apply(self)
        rhs_result = node.rhs.apply(self)
        if lhs_result is True or rhs_result is True:
            return True

    def greater_inclusive(self, node):
        return self.apply_operator(node, operator.ge)

    def lower_exclusive(self, node):
        return self.apply_operator(node, operator.lt)

    def and_(self, node):
        return self.apply_operator(node, (lambda lhs, rhs: lhs and rhs))

    def greater_exclusive(self, node):
        return self.apply_operator(node, operator.gt)

    def division(self, node):
        return self.apply_operator(node, lambda lhs, rhs:  0.00 if (rhs == 0.00) else operator.truediv(lhs,rhs))

    def subtraction(self, node):
        return self.apply_operator(node, operator.sub)

    def lower_inclusive(self, node):
        return self.apply_operator(node, operator.le)

    def equality(self, node):
        return self.apply_operator(node, operator.eq)

    def addition(self, node):
        return self.apply_operator(node, operator.add)

    def multiplication(self, node):
        return self.apply_operator(node, operator.mul)

    def inequality(self, node):
        return self.apply_operator(node, operator.ne)

    def apply_operator(self, node, operator_function):
        lhs_result = node.lhs.apply(self)
        rhs_result = node.rhs.apply(self)
        return operator_function(lhs_result, rhs_result)

    def positive(self, node):
        return +node.operand.apply(self)

    def negative(self, node):
        return -node.operand.apply(self)

    def negation(self, node):
        return not node.operand.apply(self)

    def update_value(self, key, value):
        self.__environment[key] = value
        return self.visit()
