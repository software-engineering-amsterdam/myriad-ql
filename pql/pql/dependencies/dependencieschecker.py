# coding=utf-8
from pql.traversal.ExpressionVisitor import ExpressionVisitor
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.IdentifierVisitor import IdentifierVisitor


class DependenciesChecker(FormVisitor, ExpressionVisitor, IdentifierVisitor):
    def __init__(self, ast):
        self.ast = ast
        self.properties = dict()
        self.errors = list()

    def visit(self):
        self.ast.apply(self)
        return self.errors

    def form(self, node):
        for statement in node.statements:
            statement.apply(self)

    def conditional_if_else(self, node, local_properties=None):
        self.conditional_if(node)
        if local_properties is None:
            local_properties = dict()
        for statement in node.else_statement_list:
            result = statement.apply(self, local_properties)
            if result is not None:
                key, value = result
                local_properties[key] = value

    def conditional_if(self, node, local_properties=None):
        if local_properties is None:
            local_properties = dict()
        for statement in node.statements:
            result = statement.apply(self, local_properties)
            if result is not None:
                key, value = result
                local_properties[key] = value

    def field(self, node, scope_properties=None):
        if scope_properties is None:
            self.properties[node.name.name] = node.name
        return node.name.name, node.name

    def assignment(self, node):
        children = node.expression.apply(self)
        bad_reference = []
        for child in children:
            if child.name not in self.properties:
                bad_reference.append(child)
        if len(bad_reference) > 0:
            self.errors.append("Field at {} had the following references that were not resolvable: {} "
                               .format(node.location,
                                       ["{}: {}".format(ref.name, ref.location) for ref in bad_reference]))

    def identifier(self, node):
        return [node]

    def greater_inclusive(self, node):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def addition(self, node):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def and_(self, node):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def subtraction(self, node):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def lower_inclusive(self, node):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def inequality(self, node):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def lower_exclusive(self, node):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def or_(self, node):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def multiplication(self, node):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def greater_exclusive(self, node):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def division(self, node):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def equality(self, node):
        return node.lhs.apply(self) + node.rhs.apply(self)

    def negative(self, node):
        pass

    def negation(self, node):
        pass

    def positive(self, node):
        pass

    def integer(self, node):
        pass

    def boolean(self, node):
        pass

    def money(self, node):
        pass
