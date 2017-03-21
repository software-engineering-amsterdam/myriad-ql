# coding=utf-8
import copy
import operator

from pql.environment.environmentcreator import EnvironmentCreator
from pql.traversal.BinaryExpressionVisitor import BinaryExpressionVisitor
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.IdentifierVisitor import IdentifierVisitor
from pql.traversal.TypeVisitor import TypeVisitor
from pql.traversal.UnaryExpressionVisitor import UnaryExpressionVisitor


class Evaluator(FormVisitor, BinaryExpressionVisitor, IdentifierVisitor, TypeVisitor, UnaryExpressionVisitor):
    def __init__(self, ast):
        self.ast = ast

    def visit(self, original_environment=None):
        if original_environment is None:
            original_environment = EnvironmentCreator(self.ast).visit()
        new_environment = self.ast.apply(self, copy.deepcopy(original_environment))

        while set(new_environment.items()) ^ set(original_environment.items()):
            original_environment = new_environment
            new_environment = self.ast.apply(self, original_environment)

        return new_environment

    def form(self, node, environment=None):
        for statement in node.statements:
            statement.apply(self, environment)
        return environment

    def conditional_if_else(self, node, environment=None):
        if node.condition.apply(self, environment):
            for statement in node.statements:
                statement.apply(self, environment)
        else:
            for statement in node.else_statement_list:
                statement.apply(self, environment)

    def conditional_if(self, node, environment=None):
        if node.condition.apply(self, environment):
            for statement in node.statements:
                statement.apply(self, environment)

    def field(self, node, environment=None):
        return node.name.apply(self, environment)

    def assignment(self, node, environment=None):
        environment.update(node.name.name, node.expression.apply(self, environment))

    def identifier(self, node, environment=None):
        return environment.value(node.name)

    def integer(self, node, environment=None):
        return node.value

    def boolean(self, node, environment=None):
        return node.value

    def string(self, node, environment=None):
        return node.value

    def money(self, node, environment=None):
        return node.value

    def or_(self, node, environment=None):
        return self.apply_operator(node, (lambda lhs, rhs: lhs or rhs), environment)

    def greater_inclusive(self, node, environment=None):
        return self.apply_operator(node, operator.ge, environment)

    def lower_exclusive(self, node, environment=None):
        return self.apply_operator(node, operator.lt, environment)

    def and_(self, node, environment=None):
        return self.apply_operator(node, (lambda lhs, rhs: lhs and rhs), environment)

    def greater_exclusive(self, node, environment=None):
        return self.apply_operator(node, operator.gt, environment)

    def division(self, node, environment=None):
        return self.apply_operator(node, lambda lhs, rhs:  0.00 if (rhs == 0.00) else operator.truediv(lhs, rhs), environment)

    def subtraction(self, node, environment=None):
        return self.apply_operator(node, operator.sub, environment)

    def lower_inclusive(self, node, environment=None):
        return self.apply_operator(node, operator.le, environment)

    def equality(self, node, environment=None):
        return self.apply_operator(node, operator.eq, environment)

    def addition(self, node, environment=None):
        return self.apply_operator(node, operator.add, environment)

    def multiplication(self, node, environment=None):
        return self.apply_operator(node, operator.mul, environment)

    def inequality(self, node, environment=None):
        return self.apply_operator(node, operator.ne, environment)

    def apply_operator(self, node, operator_function, environment):
        lhs_result = node.lhs.apply(self, environment)
        rhs_result = node.rhs.apply(self, environment)
        return operator_function(lhs_result, rhs_result)

    def positive(self, node, environment=None):
        return +node.operand.apply(self, environment)

    def negative(self, node, environment=None):
        return -node.operand.apply(self, environment)

    def negation(self, node, environment=None):
        return not node.operand.apply(self, environment)
