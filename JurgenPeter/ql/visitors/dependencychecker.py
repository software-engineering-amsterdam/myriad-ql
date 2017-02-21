class DependencyChecker:

    def __init__(self):
        self.stack = []
        self.errors = []
        self.warnings = []

    def error(self, message):
        self.errors.append(message)

    def warn(self, message):
        self.warnings.append(message)

    def visit(self, node):
        node.accept(self)
        return self.errors, self.warnings

    def visit_form(self, node):
        for statement in node.body:
            statement.accept(self)

    def visit_question(self, node):
        pass

    def visit_computed_question(self, node):
        pass

    def visit_if_conditional(self, node):
        # TODO: check if condition uses variable from question inside scope
        for statement in node.ifbody:
            statement.accept(self)

    def visit_ifelse_conditional(self, node):
        # TODO: check if condition uses variable from question inside scope
        for statement in node.ifbody:
            statement.accept(self)
        for statement in node.ifelsebody:
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
        return [node.name]

    def visit_constant(self, node):
        return []
