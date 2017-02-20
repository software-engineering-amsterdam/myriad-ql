class DependencyChecker:

    def __init__(self):
        self.stack = []
        self.depends_on = {}
        self.success = True

    def visit(self, node):
        node.accept(self)
        return self.success

    def visit_form(self, node):
        for statement in node.statements:
            statement.accept(self)

    def visit_question(self, node):
        pass

    def visit_computedquestion(self, node):
        pass

    def visit_ifconditional(self, node):
        # TODO: check hierarchy scoping
        for statement in node.ifstatements:
            statement.accept(self)

    def visit_ifelseconditional(self, node):
        # TODO: check hierarchy scoping
        for statement in node.ifstatements:
            statement.accept(self)
        for statement in node.ifelsestatements:
            statement.accept(self)

    def visit_plusop(self, node):
        return node.right.accept(self)

    def visit_minop(self, node):
        return node.right.accept(self)

    def visit_notop(self, node):
        return node.right.accept(self)

    def visit_mulop(self, node):
        return node.left.accept(self) + node.right.accept(self)

    def visit_divop(self, node):
        return node.left.accept(self) + node.right.accept(self)

    def visit_addop(self, node):
        return node.left.accept(self) + node.right.accept(self)

    def visit_subop(self, node):
        return node.left.accept(self) + node.right.accept(self)

    def visit_ltop(self, node):
        return node.left.accept(self) + node.right.accept(self)

    def visit_leop(self, node):
        return node.left.accept(self) + node.right.accept(self)

    def visit_gtop(self, node):
        return node.left.accept(self) + node.right.accept(self)

    def visit_geop(self, node):
        return node.left.accept(self) + node.right.accept(self)

    def visit_eqop(self, node):
        return node.left.accept(self) + node.right.accept(self)

    def visit_neop(self, node):
        return node.left.accept(self) + node.right.accept(self)

    def visit_andop(self, node):
        return node.left.accept(self) + node.right.accept(self)

    def visit_orop(self, node):
        return node.left.accept(self) + node.right.accept(self)

    def visit_variable(self, node):
        return [node.identifier]

    def visit_constant(self, node):
        return []