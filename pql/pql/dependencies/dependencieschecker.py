# coding=utf-8
from collections import defaultdict

from pql.traversal.ExpressionVisitor import ExpressionVisitor
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.IdentifierVisitor import IdentifierVisitor


class DependenciesChecker(FormVisitor, ExpressionVisitor, IdentifierVisitor):
    def __init__(self, ast):
        self.ast = ast
        self.ast_stack = list()
        self.properties = defaultdict(list)

    def identifier(self, node):
        return node.name

    def greater_inclusive(self, node):
        pass

    def positive(self, node):
        pass

    def addition(self, node):
        pass

    def and_(self, node):
        pass

    def subtraction(self, node):
        pass

    def lower_inclusive(self, node):
        pass

    def inequality(self, node):
        pass

    def lower_exclusive(self, node):
        pass

    def negation(self, node):
        pass

    def or_(self, node):
        pass

    def multiplication(self, node):
        pass

    def greater_exclusive(self, node):
        pass

    def division(self, node):
        pass

    def negative(self, node):
        pass

    def equality(self, node):
        pass
    
    def visit(self):
        self.ast.apply(self)
        b = True

    def form(self, node):
        return [statement.apply(self) for statement in node.statements]

    def conditional_if_else(self, node):
        return self.conditional_if(node) + [statement.apply(self) for statement in node.else_statement_list]

    def conditional_if(self, node):
        return [statement.apply(self) for statement in node.statements]

    def field(self, node):
        self.properties[node.name.apply(self)].append(node.name)
        if node.expression is not None:
            children = node.expression.apply(self)
            stop = True