from ql.ast import Datatype


class TypeChecker:

    arithmic_datatypes = [Datatype.money, Datatype.integer, Datatype.decimal]

    def __init__(self, symboltable):
        self.symboltable = symboltable
        self.success = True

    def execute(self, node):
        node.accept(self)
        return self.success

    def dominant_datatype(self, lefttype, righttype):
        if lefttype == Datatype.decimal or righttype == Datatype.decimal:
            return Datatype.decimal
        if lefttype == Datatype.money or righttype == Datatype.money:
            return Datatype.money
        return Datatype.integer

    def visit_form(self, node):
        for statement in node.statements:
            statement.accept(self)

    def visit_question(self, node):
        if node.expression is not None:
            node.expression.accept(self)

    def visit_conditional(self, node):
        conditiontype = node.condition.accept(self)
        if conditiontype is not None and conditiontype != Datatype.boolean:
            print("Error: condition does not evaluate to boolean value")
            self.success = False
        for statement in node.statements:
            statement.accept(self)
        if node.alternatives is not None:
            for statement in node.alternatives(self):
                statement.accept(self)

    def visit_plusop(self, node):
        righttype = node.right.accept(self)
        if righttype is None:
            return None
        if righttype in self.arithmic_datatypes:
            return righttype
        print("Error: unary + operator has incompatible datatype")
        self.success = False
        return None

    def visit_minop(self, node):
        righttype = node.right.accept(self)
        if righttype is None:
            return None
        if righttype in self.arithmic_datatypes:
            return righttype
        print("Error: unary - operator has incompatible datatype")
        self.success = False
        return None

    def visit_notop(self, node):
        righttype = node.right.accept(self)
        if righttype is None:
            return None
        if righttype == Datatype.boolean:
            return Datatype.boolean
        print("Error: ! operator has incompatible datatype")
        self.success = False
        return None

    def visit_arithmic_binop(self, node, op):
        lefttype = node.left.accept(self)
        righttype = node.right.accept(self)
        if lefttype is None or righttype is None:
            return None
        if (lefttype in self.arithmic_datatypes and
                righttype in self.arithmic_datatypes):
            return self.dominant_datatype(lefttype, righttype)
        print("Error: {} operator has incompatible datatypes".format(op))
        self.success = False
        return None

    def visit_comparison_binop(self, node, op):
        lefttype = node.left.accept(self)
        righttype = node.right.accept(self)
        if lefttype is None or righttype is None:
            return None
        if (lefttype in self.arithmic_datatypes and
                righttype in self.arithmic_datatypes):
            return Datatype.boolean
        print("Error: {} operator has incompatible datatypes".format(op))
        self.success = False
        return None

    def visit_equality_binop(self, node, op):
        lefttype = node.left.accept(self)
        righttype = node.right.accept(self)
        if lefttype is None or righttype is None:
            return None
        if lefttype == righttype:
            return lefttype
        print("Error: {} operator has incompatible datatypes".format(op))
        self.success = False
        return None

    def visit_logical_binop(self, node, op):
        lefttype = node.left.accept(self)
        righttype = node.right.accept(self)
        if lefttype is None or righttype is None:
            return None
        if lefttype == righttype == Datatype.boolean:
            return Datatype.boolean
        print("Error: {} operator has incompatible datatypes".format(op))
        self.success = False
        return None

    def visit_mulop(self, node):
        return self.visit_arithmic_binop(node, "*")

    def visit_divop(self, node):
        return self.visit_arithmic_binop(node, "/")

    def visit_addop(self, node):
        return self.visit_arithmic_binop(node, "+")

    def visit_subop(self, node):
        return self.visit_arithmic_binop(node, "-")

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
        if node.identifier in self.symboltable:
            return self.symboltable[node.identifier]
        print("Error: varable identifier \"{}\" does not correspond with a"
              "question".format(node.identifier))
        self.success = False
        return None

    def visit_constant(self, node):
        return node.datatype