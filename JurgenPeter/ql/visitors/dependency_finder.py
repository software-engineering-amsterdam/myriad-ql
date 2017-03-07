class DependencyFinder:

    def find(self, node):
        return self.visit(node)

    def visit(self, node):
        return node.accept(self)

    def visit_computed_question(self, node):
        return self.visit(node.computation)

    def visit_if_conditional(self, node):
        return self.visit(node.condition)

    def visit_ifelse_conditional(self, node):
        return self.visit(node.condition)

    def visit_plusop(self, node):
        return self.visit(node.right)

    def visit_minop(self, node):
        return self.visit(node.right)

    def visit_notop(self, node):
        return self.visit(node.right)

    def visit_mulop(self, node):
        return self.visit(node.left) + self.visit(node.right)

    def visit_divop(self, node):
        return self.visit(node.left) + self.visit(node.right)

    def visit_addop(self, node):
        return self.visit(node.left) + self.visit(node.right)

    def visit_subop(self, node):
        return self.visit(node.left) + self.visit(node.right)

    def visit_ltop(self, node):
        return self.visit(node.left) + self.visit(node.right)

    def visit_leop(self, node):
        return self.visit(node.left) + self.visit(node.right)

    def visit_gtop(self, node):
        return self.visit(node.left) + self.visit(node.right)

    def visit_geop(self, node):
        return self.visit(node.left) + self.visit(node.right)

    def visit_eqop(self, node):
        return self.visit(node.left) + self.visit(node.right)

    def visit_neop(self, node):
        return self.visit(node.left) + self.visit(node.right)

    def visit_andop(self, node):
        return self.visit(node.left) + self.visit(node.right)

    def visit_orop(self, node):
        return self.visit(node.left) + self.visit(node.right)

    def visit_variable(self, node):
        return [node.name]

    def visit_constant(self, node):
        return []
