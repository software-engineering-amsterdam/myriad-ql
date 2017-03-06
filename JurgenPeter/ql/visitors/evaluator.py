class Evaluator:

    def __init__(self, environment):
        self.environment = environment

    def visit(self, node):
        try:
            return node.accept(self)
        except TypeError: # TODO: custom None exception
            return None

    def visit_plusop(self, node):
        return + node.right.accept(self)

    def visit_minop(self, node):
        return - node.right.accept(self)

    def visit_notop(self, node):
        return not node.right.accept(self)

    def visit_mulop(self, node):
        return node.left.accept(self) * node.right.accept(self)

    def visit_divop(self, node):
        return node.left.accept(self) / node.right.accept(self)

    def visit_addop(self, node):
        return node.left.accept(self) + node.right.accept(self)

    def visit_subop(self, node):
        return node.left.accept(self) - node.right.accept(self)

    def visit_ltop(self, node):
        return node.left.accept(self) < node.right.accept(self)

    def visit_leop(self, node):
        return node.left.accept(self) <= node.right.accept(self)

    def visit_gtop(self, node):
        return node.left.accept(self) > node.right.accept(self)

    def visit_geop(self, node):
        return node.left.accept(self) >= node.right.accept(self)

    def visit_eqop(self, node):
        return node.left.accept(self) == node.right.accept(self)

    def visit_neop(self, node):
        return node.left.accept(self) != node.right.accept(self)

    def visit_andop(self, node):
        return node.left.accept(self) and node.right.accept(self)

    def visit_orop(self, node):
        return node.left.accept(self) or node.right.accept(self)

    def visit_variable(self, node):
        return self.environment.get(node.name, None)

    def visit_constant(self, node):
        return node.value
