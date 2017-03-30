# coding=utf-8
from pql.messages.error import Error
from pql.traversal.BinaryExpressionVisitor import BinaryExpressionVisitor
from pql.traversal.FormVisitor import FormVisitor
from pql.traversal.IdentifierVisitor import IdentifierVisitor
from pql.traversal.TypeVisitor import TypeVisitor
from pql.traversal.UnaryExpressionVisitor import UnaryExpressionVisitor
from pql.typechecker.types import DataTypes, is_number_type
from pql.typechecker.typecheckers import checker_of_data_type


class TypeChecker(FormVisitor, BinaryExpressionVisitor, IdentifierVisitor, TypeVisitor, UnaryExpressionVisitor):
    def __init__(self, ast, environment_type):
        self.symbol_table = environment_type(ast).visit()
        self.ast = ast
        self.errors = list()

    def visit(self):
        self.errors.clear()
        self.ast.apply(self)
        return self.errors

    def form(self, node, args=None):
        for statement in node.statements:
            statement.apply(self)

    def conditional_if(self, node, args=None):
        condition_result = node.condition.apply(self)
        if condition_result.data_type is not DataTypes.boolean:
            self.errors.append(
                Error("Invalid expression in a conditional statement, it expected a [DataTypes.boolean] "
                      "expression but received [{}], at location {}"
                      .format(condition_result, node.condition.location), node.condition.location))
        [statement.apply(self) for statement in node.statements]

    def conditional_if_else(self, node, args=None):
        self.conditional_if(node, args)
        [statement.apply(self) for statement in node.else_statement_list]

    def field(self, node, args=None):
        return node.data_type

    def assignment(self, node, args=None):
        lhs = node.data_type.apply(self)
        lhs_checker = checker_of_data_type[lhs.data_type]
        expression_type = node.expression.apply(self)
        result = lhs_checker.assignment(expression_type)

        if not (is_number_type(result.data_type) and is_number_type(node.data_type.data_type))\
                and result.data_type is not node.data_type.data_type:
                self.errors.append(
                    Error("Expression result [{}] did not match declared type [{}], at location: {}"
                             .format(result, node.data_type.data_type, node.expression.location), node.expression.location))

    def subtraction(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        lhs_checker = checker_of_data_type[lhs_type.data_type]
        rhs = node.rhs.apply(self)
        return lhs_checker.subtraction(rhs)

    def division(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        lhs_checker = checker_of_data_type[lhs_type.data_type]
        rhs = node.rhs.apply(self)
        return lhs_checker.division(rhs)

    def multiplication(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        lhs_checker = checker_of_data_type[lhs_type.data_type]
        rhs = node.rhs.apply(self)
        return lhs_checker.multiplication(rhs)

    def addition(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        lhs_checker = checker_of_data_type[lhs_type.data_type]
        rhs = node.rhs.apply(self)
        return lhs_checker.addition(rhs)

    def greater_exclusive(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        lhs_checker = checker_of_data_type[lhs_type.data_type]
        rhs = node.rhs.apply(self)
        return lhs_checker.greater_exclusive(rhs)

    def greater_inclusive(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        lhs_checker = checker_of_data_type[lhs_type.data_type]
        rhs = node.rhs.apply(self)
        return lhs_checker.greater_inclusive(rhs)

    def lower_inclusive(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        lhs_checker = checker_of_data_type[lhs_type.data_type]
        rhs = node.rhs.apply(self)
        return lhs_checker.lower_inclusive(rhs)

    def lower_exclusive(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        lhs_checker = checker_of_data_type[lhs_type.data_type]
        rhs = node.rhs.apply(self)
        return lhs_checker.lower_exclusive(rhs)

    def equality(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        lhs_checker = checker_of_data_type[lhs_type.data_type]
        rhs = node.rhs.apply(self)
        return lhs_checker.equality(rhs)

    def inequality(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        lhs_checker = checker_of_data_type[lhs_type.data_type]
        rhs = node.rhs.apply(self)
        return lhs_checker.inequality(rhs)

    def and_(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        lhs_checker = checker_of_data_type[lhs_type.data_type]
        rhs = node.rhs.apply(self)
        return lhs_checker.and_(rhs)

    def or_(self, node, args=None):
        lhs_type = node.lhs.apply(self)
        lhs_checker = checker_of_data_type[lhs_type.data_type]
        rhs = node.rhs.apply(self)
        return lhs_checker.or_(rhs)

    def negation(self, node, args=None):
        operand_result = node.operand.apply(self)
        lhs_checker = checker_of_data_type[operand_result.data_type]
        return lhs_checker.negation(node)

    def positive(self, node, args=None):
        operand_result = node.operand.apply(self)
        lhs_checker = checker_of_data_type[operand_result.data_type]
        return lhs_checker.positive(node)

    def negative(self, node, args=None):
        operand_result = node.operand.apply(self)
        lhs_checker = checker_of_data_type[operand_result.data_type]
        return lhs_checker.negative(node)

    def identifier(self, node, args=None):
        return self.symbol_table[node.name]

    def integer(self, node, args=None):
        return node

    def money(self, node, args=None):
        return node

    def boolean(self, node, args=None):
        return node

    def string(self, node, args=None):
        return node
