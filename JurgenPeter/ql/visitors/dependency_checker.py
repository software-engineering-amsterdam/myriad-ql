class DependencyChecker:

    def __init__(self):
        self.dependencies = {}
        self.constants = []
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
        for element in node.body:
            element.accept(self)

    def visit_question(self, node):
        return [node.name]

    def visit_computed_question(self, node):
        dependencies = node.computation.accept(self)

        if not dependencies:
            self.constants.append(node.name)

        if node.name in dependencies:
            self.error("computed question \"{}\" has dependency on "
                       "itself".format(node.name))

        """ Find all indirect dependencies of this questions by sequentially
            expanding the found dependencies. """
        for dependency in dependencies:
            dependencies += [indirect_dependency
                             for indirect_dependency in
                             self.dependencies.get(dependency, [])
                             if indirect_dependency not in dependencies]

        for dependency in dependencies:
            if node.name in self.dependencies.get(dependency, []):
                self.error("computed question \"{}\" has circular dependency "
                           " on computed question \"{}\"".format(node.name,
                                                                 dependency))

        self.dependencies[node.name] = dependencies
        return [node.name]

    def visit_if_conditional(self, node):
        dependencies = node.condition.accept(self)
        scope = sum([element.accept(self) for element in node.ifbody], [])

        for dependency in dependencies:
            if dependency in scope:
                self.error("condition depends on question \"{}\" within "
                           "own scope".format(dependency))
            if dependency in self.constants:
                self.warn("condition depends on constant computed "
                          "question \"{}\"".format(dependency))

        return scope

    def visit_ifelse_conditional(self, node):
        dependencies = node.condition.accept(self)
        scope = sum([element.accept(self) for element in node.ifbody +
                     node.elsebody], [])

        for dependency in dependencies:
            if dependency in scope:
                self.error("condition depends on question \"{}\" within "
                           "own scope".format(dependency))
            if dependency in self.constants:
                self.warn("condition depends on constant computed "
                          "question \"{}\"".format(dependency))

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
