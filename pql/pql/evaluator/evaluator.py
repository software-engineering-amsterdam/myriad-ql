# coding=utf-8
from pql.traversal.ExpressionVisitor import ExpressionVisitor
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.IdentifierVisitor import IdentifierVisitor


class Evaluator(FormVisitor, ExpressionVisitor, IdentifierVisitor):
    def __init__(self, environment):
        self.environment = environment

    def visit(self, pql_ast):
        [form.apply(self) for form in pql_ast]
        return self.environment

    def form(self, node):
        [statement.apply(self) for statement in node.children]

    def conditional_if_else(self, node):
        self.conditional_if(node)
        [statement.apply(self) for statement in node.else_statement_list]

    def conditional_if(self, node):
        [statement.apply(self) for statement in node.statements]

    def or_(self, node):
        return node.lhs.apply(self) or node.rhs.apply(self)

    def value(self, node):
        return node.value

    def greater_inclusive(self, node):
        return node.lhs.apply(self) >= node.rhs.apply(self)

    def lower_exclusive(self, node):
        return node.lhs.apply(self) < node.rhs.apply(self)

    def and_(self, node):
        return node.lhs.apply(self) and node.rhs.apply(self)

    def greater_exclusive(self, node):
        return node.lhs.apply(self) > node.rhs.apply(self)

    def division(self, node):
        return node.lhs.apply(self) / node.rhs.apply(self)

    def subtraction(self, node):
        return node.lhs.apply(self) - node.rhs.apply(self)

    def lower_inclusive(self, node):
        return node.lhs.apply(self) <= node.rhs.apply(self)

    def field(self, node):
        if node.expression is not None:
            self.environment[node.name.name] = node.expression.apply(self)

    def identifier(self, node):
        if node.name in self.environment:
            return self.environment[node.name]

    def equality(self, node):
        return node.lhs.apply(self) == node.rhs.apply(self)

    def addition(self, node):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def multiplication(self, node):
        return node.lhs.apply(self) * node.rhs.apply(self)

    def inequality(self, node):
        return node.lhs.apply(self) != node.rhs.apply(self)

