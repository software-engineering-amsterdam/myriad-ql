class Printer:

    def print(self, node):
        print(self.visit(node))

    def visit(self, node):
        return node.accept(self)

    def visit_form(self, node):
        return "form {} [\n{}\n]".format(
            node.name,
            "\n".join([self.visit(element) for element in node.body]))

    def visit_question(self, node):
        return "{}: \"{}\" {}".format(
            node.name, node.label, node.datatype)

    def visit_computed_question(self, node):
        return "{}: \"{}\" {} = {}".format(
            node.name, node.label, node.datatype,
            self.visit(node.computation))

    def visit_if_conditional(self, node):
        return "if {} [\n{}\n]".format(
            self.visit(node.condition),
            "\n".join([self.visit(element) for element in node.ifbody]))

    def visit_ifelse_conditional(self, node):
        return "if {} [\n{}\n]\nelse [\n{}\n]".format(
            self.visit(node.condition),
            "\n".join([self.visit(element) for element in node.ifbody]),
            "\n".join([self.visit(element) for element in node.elsebody]))

    def visit_plusop(self, node):
        return "+{}".format(self.visit(node.right))

    def visit_minop(self, node):
        return "-{}".format(self.visit(node.right))

    def visit_notop(self, node):
        return "!{}".format(self.visit(node.right))

    def visit_mulop(self, node):
        return "({} * {})".format(self.visit(node.left),
                                  self.visit(node.right))

    def visit_divop(self, node):
        return "({} / {})".format(self.visit(node.left),
                                  self.visit(node.right))

    def visit_addop(self, node):
        return "({} + {})".format(self.visit(node.left),
                                  self.visit(node.right))

    def visit_subop(self, node):
        return "({} - {})".format(self.visit(node.left),
                                  self.visit(node.right))

    def visit_ltop(self, node):
        return "({} < {})".format(self.visit(node.left),
                                  self.visit(node.right))

    def visit_leop(self, node):
        return "({} <= {})".format(self.visit(node.left),
                                   self.visit(node.right))

    def visit_gtop(self, node):
        return "({} > {})".format(self.visit(node.left),
                                  self.visit(node.right))

    def visit_geop(self, node):
        return "({} >= {})".format(self.visit(node.left),
                                   self.visit(node.right))

    def visit_eqop(self, node):
        return "({} == {})".format(self.visit(node.left),
                                   self.visit(node.right))

    def visit_neop(self, node):
        return "({} != {})".format(self.visit(node.left),
                                   self.visit(node.right))

    def visit_andop(self, node):
        return "({} && {})".format(self.visit(node.left),
                                   self.visit(node.right))

    def visit_orop(self, node):
        return "({} || {})".format(self.visit(node.left),
                                   self.visit(node.right))

    def visit_variable(self, node):
        return node.name

    def visit_constant(self, node):
        return str(node.value)
