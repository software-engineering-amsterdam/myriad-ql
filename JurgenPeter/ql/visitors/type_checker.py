from misc.visitor import CheckerVisitor
from ql.datatypes import *


class TypeChecker(CheckerVisitor):

    computable_datatypes = [IntegerDatatype(), DecimalDatatype()]

    def __init__(self, symboltable, errors=[]):
        self.symboltable = symboltable
        self.errors = errors

    def visit_form(self, node):
        for element in node.body:
            self.visit(element)

    def visit_question(self, node):
        pass

    def visit_computed_question(self, node):
        computation_type = self.visit(node.computation)
        if (computation_type is not None and
                computation_type != self.symboltable[node.name]):
            self.error("computed question \"{}\" "
                       "has incompatable datatype".format(node.name))

    def visit_if_conditional(self, node):
        condition_type = self.visit(node.condition)
        if condition_type is not None and condition_type != BooleanDatatype():
            self.error("condition does not evaluate to boolean value")

        for element in node.ifbody:
            self.visit(element)

    def visit_ifelse_conditional(self, node):
        condition_type = self.visit(node.condition)
        if condition_type is not None and condition_type != BooleanDatatype():
            self.error("condition does not evaluate to boolean value")

        for element in node.ifbody:
            self.visit(element)
        for element in node.elsebody:
            self.visit(element)

    def visit_plusop(self, node):
        right_type = self.visit(node.right)
        if right_type is None:
            return None
        if right_type in self.computable_datatypes:
            return right_type
        self.error("unary + operator has incompatible datatype")
        return None

    def visit_minop(self, node):
        right_type = self.visit(node.right)
        if right_type is None:
            return None
        if right_type in self.computable_datatypes:
            return right_type
        self.error("unary - operator has incompatible datatype")
        return None

    def visit_notop(self, node):
        right_type = self.visit(node.right)
        if right_type is None:
            return None
        if right_type == BooleanDatatype():
            return BooleanDatatype()
        self.error("! operator has incompatible datatype")
        return None

    def visit_computation_binop(self, node, op):
        left_type = self.visit(node.left)
        right_type = self.visit(node.right)
        if left_type is None or right_type is None:
            return None
        if (left_type in self.computable_datatypes and
                right_type in self.computable_datatypes):
            return self.dominant_datatype(left_type, right_type)
        self.error("{} operator has incompatible datatypes".format(op))
        return None

    def visit_comparison_binop(self, node, op):
        left_type = self.visit(node.left)
        right_type = self.visit(node.right)
        if left_type is None or right_type is None:
            return None
        if (left_type in self.computable_datatypes and
                right_type in self.computable_datatypes):
            return BooleanDatatype()
        self.error("{} operator has incompatible datatypes".format(op))
        return None

    def visit_equality_binop(self, node, op):
        left_type = self.visit(node.left)
        right_type = self.visit(node.right)
        if left_type is None or right_type is None:
            return None
        if left_type == right_type:
            return left_type
        self.error("{} operator has incompatible datatypes".format(op))
        return None

    def visit_logical_binop(self, node, op):
        left_type = self.visit(node.left)
        right_type = self.visit(node.right)
        if left_type is None or right_type is None:
            return None
        if left_type == right_type == BooleanDatatype():
            return BooleanDatatype()
        self.error("{} operator has incompatible datatypes".format(op))
        return None

    def visit_mulop(self, node):
        return self.visit_computation_binop(node, "*")

    def visit_divop(self, node):
        left_type = self.visit(node.left)
        right_type = self.visit(node.right)
        if left_type is None or right_type is None:
            return None
        if (left_type in self.computable_datatypes and
                right_type in self.computable_datatypes):
            return DecimalDatatype()
        self.error("/ operator has incompatible datatypes")
        return None

    def visit_addop(self, node):
        return self.visit_computation_binop(node, "+")

    def visit_subop(self, node):
        return self.visit_computation_binop(node, "-")

    def visit_ltop(self, node):
        return self.visit_comparison_binop(node, "<")

    def visit_leop(self, node):
        return self.visit_comparison_binop(node, "<=")

    def visit_gtop(self, node):
        return self.visit_comparison_binop(node, ">")

    def visit_geop(self, node):
        return self.visit_comparison_binop(node, ">=")

    def visit_eqop(self, node):
        return self.visit_equality_binop(node, "==")

    def visit_neop(self, node):
        return self.visit_equality_binop(node, "!=")

    def visit_andop(self, node):
        return self.visit_logical_binop(node, "&&")

    def visit_orop(self, node):
        return self.visit_logical_binop(node, "||")

    def visit_variable(self, node):
        if node.name in self.symboltable:
            return self.symboltable[node.name]
        self.error("varable name \"{}\" is not a "
                   "question name".format(node.name))
        return None

    def visit_constant(self, node):
        return node.datatype

    @staticmethod
    def dominant_datatype(left_type, right_type):
        if left_type == DecimalDatatype() or right_type == DecimalDatatype():
            return DecimalDatatype()
        return IntegerDatatype()
