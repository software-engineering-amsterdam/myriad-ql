from ql.ast import Datatype
from ql.messages import *


class TypeChecker:

    arithmetic_datatypes = [Datatype.integer, Datatype.decimal]

    def __init__(self, symboltable, errors=[]):
        self.symboltable = symboltable
        self.errors = errors

    def error(self, message):
        self.errors.append(ErrorMessage(message))

    def warn(self, message):
        self.errors.append(WarningMessage(message))

    # TODO: rename
    def visit(self, node):
        node.accept(self)

    def visit_form(self, node):
        for element in node.body:
            element.accept(self)

    def visit_question(self, node):
        pass

    def visit_computed_question(self, node):
        node.computation.accept(self)

    def visit_if_conditional(self, node):
        conditiontype = node.condition.accept(self)
        if conditiontype is not None and conditiontype != Datatype.boolean:
            self.error("condition does not evaluate to boolean value")

        for element in node.ifbody:
            element.accept(self)

    def visit_ifelse_conditional(self, node):
        conditiontype = node.condition.accept(self)
        if conditiontype is not None and conditiontype != Datatype.boolean:
            self.error("condition does not evaluate to boolean value")

        for element in node.ifbody:
            element.accept(self)
        for element in node.elsebody:
            element.accept(self)

    def visit_plusop(self, node):
        right_type = node.right.accept(self)
        if right_type is None:
            return None
        if right_type in self.arithmetic_datatypes:
            return right_type
        self.error("unary + operator has incompatible datatype")
        return None

    def visit_minop(self, node):
        right_type = node.right.accept(self)
        if right_type is None:
            return None
        if right_type in self.arithmetic_datatypes:
            return right_type
        self.error("unary - operator has incompatible datatype")
        return None

    def visit_notop(self, node):
        right_type = node.right.accept(self)
        if right_type is None:
            return None
        if right_type == Datatype.boolean:
            return Datatype.boolean
        self.error("! operator has incompatible datatype")
        return None

    def visit_arithmetic_binop(self, node, op):
        left_type = node.left.accept(self)
        right_type = node.right.accept(self)
        if left_type is None or right_type is None:
            return None
        if (left_type in self.arithmetic_datatypes and
                right_type in self.arithmetic_datatypes):
            return self.dominant_datatype(left_type, right_type)
        self.error("{} operator has incompatible datatypes".format(op))
        return None

    def visit_comparison_binop(self, node, op):
        left_type = node.left.accept(self)
        right_type = node.right.accept(self)
        if left_type is None or right_type is None:
            return None
        if (left_type in self.arithmetic_datatypes and
                right_type in self.arithmetic_datatypes):
            return Datatype.boolean
        self.error("{} operator has incompatible datatypes".format(op))
        return None

    def visit_equality_binop(self, node, op):
        left_type = node.left.accept(self)
        right_type = node.right.accept(self)
        if left_type is None or right_type is None:
            return None
        if left_type == right_type:
            return left_type
        self.error("{} operator has incompatible datatypes".format(op))
        return None

    def visit_logical_binop(self, node, op):
        left_type = node.left.accept(self)
        right_type = node.right.accept(self)
        if left_type is None or right_type is None:
            return None
        if left_type == right_type == Datatype.boolean:
            return Datatype.boolean
        self.error("{} operator has incompatible datatypes".format(op))
        return None

    def visit_mulop(self, node):
        return self.visit_arithmetic_binop(node, "*")

    def visit_divop(self, node):
        return self.visit_arithmetic_binop(node, "/")

    def visit_addop(self, node):
        return self.visit_arithmetic_binop(node, "+")

    def visit_subop(self, node):
        return self.visit_arithmetic_binop(node, "-")

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
        if left_type == Datatype.decimal or right_type == Datatype.decimal:
            return Datatype.decimal
        return Datatype.integer
