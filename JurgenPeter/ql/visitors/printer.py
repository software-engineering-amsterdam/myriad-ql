class Printer:

    def visit(self, node):
        print(node.accept(self))

    def visit_form(self, node):
        return "form {} [\n{}\n]".format(
            node.identifier,
            "\n".join([s.accept(self) for s in node.statements]))

    def visit_question(self, node):
        return "{}: \"{}\" {}".format(
            node.identifier, node.label, node.datatype.name)

    def visit_computed_question(self, node):
        return "{}: \"{}\" {} = {}".format(
            node.identifier, node.label, node.datatype.name,
            node.computation.accept(self))

    def visit_if_conditional(self, node):
        return "if {} [\n{}\n]".format(
            node.condition.accept(self),
            "\n".join([s.accept(self) for s in node.ifstatements]))

    def visit_ifelse_conditional(self, node):
        return "if {} [\n{}\n]\nelse [\n{}\n]".format(
            node.condition.accept(self),
            "\n".join([s.accept(self) for s in node.ifstatements]),
            "\n".join([s.accept(self) for s in node.elsestatements]))

    def visit_plusop(self, node):
        return "+{}".format(node.right.accept(self))

    def visit_minop(self, node):
        return "-{}".format(node.right.accept(self))

    def visit_notop(self, node):
        return "!{}".format(node.right.accept(self))

    def visit_mulop(self, node):
        return "({} * {})".format(node.left.accept(self),
                                  node.right.accept(self))

    def visit_divop(self, node):
        return "({} / {})".format(node.left.accept(self),
                                  node.right.accept(self))

    def visit_addop(self, node):
        return "({} + {})".format(node.left.accept(self),
                                  node.right.accept(self))

    def visit_subop(self, node):
        return "({} - {})".format(node.left.accept(self),
                                  node.right.accept(self))

    def visit_ltop(self, node):
        return "({} < {})".format(node.left.accept(self),
                                  node.right.accept(self))

    def visit_leop(self, node):
        return "({} <= {})".format(node.left.accept(self),
                                   node.right.accept(self))

    def visit_gtop(self, node):
        return "({} > {})".format(node.left.accept(self),
                                  node.right.accept(self))

    def visit_geop(self, node):
        return "({} >= {})".format(node.left.accept(self),
                                   node.right.accept(self))

    def visit_eqop(self, node):
        return "({} == {})".format(node.left.accept(self),
                                   node.right.accept(self))

    def visit_neop(self, node):
        return "({} != {})".format(node.left.accept(self),
                                   node.right.accept(self))

    def visit_andop(self, node):
        return "({} && {})".format(node.left.accept(self),
                                   node.right.accept(self))

    def visit_orop(self, node):
        return "({} || {})".format(node.left.accept(self),
                                   node.right.accept(self))

    def visit_variable(self, node):
        return node.identifier

    def visit_constant(self, node):
        return str(node.value)
