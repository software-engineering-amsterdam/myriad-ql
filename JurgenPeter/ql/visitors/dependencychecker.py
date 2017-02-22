class DependencyChecker:

    def __init__(self):
        self.dependencies = {}
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
        return [node.name]

    def visit_computed_question(self, node):
        dependencies = node.computation.accept(self)

        # Find all dependencies of this questions dependencies using breadth
        # first search.
        for dependency in dependencies:
            dependencies += [d for d in self.dependencies.get(dependency, []) if d not in dependencies]

        for dependency in dependencies:
            if dependency in self.dependencies and node.name in self.dependencies[dependency]:
                self.error("circular dependency between \"\" and \"\"".format(node.name, dependency))

        self.dependencies[node.name] = dependencies
        return [node.name]

    def visit_if_conditional(self, node):
        requires = node.condition.accept(self)
        scope = sum([statement.accept(self) for statement in node.ifbody], [])

        if any(variable in scope for variable in requires):
            self.error("condition depends on question in scope")

        return scope

    def visit_ifelse_conditional(self, node):
        requires = node.condition.accept(self)
        scope = sum([statement.accept(self) for statement in node.ifbody + node.elsebody], [])

        if any(variable in scope for variable in requires):
            self.error("condition depends on question in scope")

        return scope

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
