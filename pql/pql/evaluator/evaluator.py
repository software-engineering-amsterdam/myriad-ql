# coding=utf-8
import operator

from pql.traversal.ExpressionVisitor import ExpressionVisitor
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.IdentifierVisitor import IdentifierVisitor


class Evaluator(FormVisitor, ExpressionVisitor, IdentifierVisitor):
    def __init__(self, environment_type, ast):
        self.__environment = environment_type(ast).visit()
        self.ast = ast

    def visit(self):
        #TODO make environment receive and return environment instead of having it as instance variable
        self.ast.apply(self)
        return self.__environment

    def form(self, node):
        for statement in node.statements:
            statement.apply(self)

    def conditional_if_else(self, node):
        if node.condition.apply(self):
            for statement in node.statements:
                statement.apply(self)
        else:
            for statement in node.else_statement_list:
                statement.apply(self)

    def conditional_if(self, node):
        if node.condition.apply(self):
            for statement in node.statements:
                statement.apply(self)

    def expression(self, node):
        return node.apply(self)

    def field(self, node):
        if node.expression is not None:
            self.__environment[node.name.name] = node.expression.apply(self)

    def identifier(self, node):
        return self.__environment[node.name]

    def integer(self, node):
        return node.value

    def boolean(self, node):
        return node.value

    def money(self, node):
        return node.value

    def or_(self, node):
        lhs_result = node.lhs.apply(self)
        rhs_result = node.rhs.apply(self)
        if lhs_result is True or rhs_result is True:
            return True
        else:
            return self.apply_operator(node, (lambda lhs, rhs: lhs or rhs), False)

    def greater_inclusive(self, node):
        return self.apply_operator(node, operator.ge)

    def lower_exclusive(self, node):
        return self.apply_operator(node, operator.lt)

    def and_(self, node):
        return self.apply_operator(node, (lambda lhs, rhs: lhs and rhs), default_value=False)

    def greater_exclusive(self, node):
        return self.apply_operator(node, operator.gt)

    def division(self, node):
        return self.apply_operator(node, operator.truediv)

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

    def apply_operator(self, node, operator_function, default_value=None):
        lhs_result = node.lhs.apply(self)
        rhs_result = node.rhs.apply(self)
        if lhs_result is None or rhs_result is None:
            return default_value
        else:
            return operator_function(lhs_result, rhs_result)

    def positive(self, node):
        return +node.rhs.apply(self)

    def negative(self, node):
        return -node.rhs.apply(self)

    def negation(self, node):
        return not node.rhs.apply(self)

    def update_value(self, key, value):
        #TODO If environment is passed, this can be removed
        self.__environment[key] = value
        print(self.__environment)
