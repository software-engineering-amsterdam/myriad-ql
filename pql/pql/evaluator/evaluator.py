# coding=utf-8
import operator

from pql.traversal.ExpressionVisitor import ExpressionVisitor
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.IdentifierVisitor import IdentifierVisitor


class Evaluator(FormVisitor, ExpressionVisitor, IdentifierVisitor):
    def __init__(self, environment):
        self.__environment = environment

    def visit(self, pql_ast):
        # TODO Investigate whether environment can be passed as argument, this gets dirty very quickly because everything needs to pass the environment without actually needing it
        [form.apply(self) for form in pql_ast]
        return self.__environment

    def form(self, node):
        [statement.apply(self) for statement in node.statements]

    def conditional_if_else(self, node):
        if node.condition.apply(self):
            [statement.apply(self) for statement in node.statements]
        else:
            [statement.apply(self) for statement in node.else_statement_list]

    def conditional_if(self, node):
        if node.condition.apply(self):
            [statement.apply(self) for statement in node.statements]

    def expression(self, node):
        return node.apply(self)

    def field(self, node):
        if node.expression is not None:
            self.__environment[node.name.name] = node.expression.apply(self)

    def identifier(self, node):
        if node.name in self.__environment:
            return self.__environment[node.name]
        else:
            return None

    def value(self, node):
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

    def update_value(self, key, value):
        #TODO If environment is passed, this can be removed
        self.__environment[key] = value
        print(self.__environment)
