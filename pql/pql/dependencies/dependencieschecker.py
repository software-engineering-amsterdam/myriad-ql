# coding=utf-8
from pql.messages.error import Error
from pql.traversal.BinaryExpressionVisitor import BinaryExpressionVisitor
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.IdentifierVisitor import IdentifierVisitor
from pql.traversal.TypeVisitor import TypeVisitor
from pql.traversal.UnaryExpressionVisitor import UnaryExpressionVisitor


class DependenciesChecker(FormVisitor, BinaryExpressionVisitor, UnaryExpressionVisitor, IdentifierVisitor, TypeVisitor):
    def __init__(self, ast):
        self.ast = ast
        self.errors = list()

    def visit(self):
        properties = self.ast.apply(self, dict())
        self.errors.clear()
        self.ast.apply(self, properties)
        return self.errors

    def form(self, node, properties=None):
        for statement in node.statements:
            result = statement.apply(self, properties)
            if result is not None:
                key, value = result
                properties[key] = value
        return properties

    def conditional_if_else(self, node, local_properties=None):
        self.conditional_if(node, local_properties.copy())
        self.conditional(node, local_properties.copy(), node.else_statement_list)

    def conditional_if(self, node, local_properties=None):
        self.conditional(node, local_properties.copy(), node.statements)

    def conditional(self, node, local_properties, statements):
        self.errors.extend(self.bad_references_in_expression(node.condition,  local_properties))
        for statement in statements:
            result = statement.apply(self, local_properties)
            if result is not None:
                key, value = result
                local_properties[key] = value

    def field(self, node, scope_properties=None):
        return node.name.name, node.name

    def assignment(self, node, scope_properties=None):
        self.errors.extend(self.bad_references_in_expression(node.expression, scope_properties))
        return node.name.name, node.name

    def bad_references_in_expression(self, expression, scope_properties):
        errors = list()
        children = expression.apply(self)
        for child in children:
            if child.name not in scope_properties:
                errors.append(Error("Expression at {} had a reference [{}] that was not resolvable at location {} "
                                         .format(expression.location, child.name, child.location), child.location))
        return errors

    def identifier(self, node, args=None):
        return [node]

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

    def boolean(self, node, args=None):
        return []

    def integer(self, node, args=None):
        return []

    def money(self, node, args=None):
        return []

    def string(self, node, args=None):
        return []
