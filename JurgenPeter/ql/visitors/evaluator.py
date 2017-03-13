class UndefinedVariableException(Exception):
    pass


class Evaluator:

    def __init__(self, environment):
        self.environment = environment

    def evaluate(self, node):
        try:
            return self.visit(node)
        except (UndefinedVariableException, ZeroDivisionError):
            return None

    def visit(self, node):
        return node.accept(self)

    def visit_plusop(self, node):
        return + self.visit(node.right)

    def visit_minop(self, node):
        return - self.visit(node.right)

    def visit_notop(self, node):
        return not self.visit(node.right)

    def visit_mulop(self, node):
        return self.visit(node.left) * self.visit(node.right)

    def visit_divop(self, node):
        return self.visit(node.left) / self.visit(node.right)

    def visit_addop(self, node):
        return self.visit(node.left) + self.visit(node.right)

    def visit_subop(self, node):
        return self.visit(node.left) - self.visit(node.right)

    def visit_ltop(self, node):
        return self.visit(node.left) < self.visit(node.right)

    def visit_leop(self, node):
        return self.visit(node.left) <= self.visit(node.right)

    def visit_gtop(self, node):
        return self.visit(node.left) > self.visit(node.right)

    def visit_geop(self, node):
        return self.visit(node.left) >= self.visit(node.right)

    def visit_eqop(self, node):
        return self.visit(node.left) == self.visit(node.right)

    def visit_neop(self, node):
        return self.visit(node.left) != self.visit(node.right)

    def visit_andop(self, node):
        return self.visit(node.left) and self.visit(node.right)

    def visit_orop(self, node):
        return self.visit(node.left) or self.visit(node.right)

    def visit_variable(self, node):
        value = self.environment.get(node.name, None)
        if value is None:
            raise UndefinedVariableException
        return value

    def visit_constant(self, node):
        return node.value
