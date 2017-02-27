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

        # Find all (indirect) dependencies of this questions dependencies using
        # breadth first search on the dependency table.
        for dependency in dependencies:
            dependencies += [implicit_dependency
                             for implicit_dependency in
                             self.dependencies.get(dependency, [])
                             if implicit_dependency not in dependencies]

        for dependency in dependencies:
            if node.name in self.dependencies.get(dependency, []):
                self.error("computed question \"{}\" has circular dependency "
                           " on computed question \"{}\"".format(node.name,
                                                                 dependency))

        self.dependencies[node.name] = dependencies
        return [node.name]

    def visit_if_conditional(self, node):
        requires = node.condition.accept(self)
        scope = sum([element.accept(self) for element in node.ifbody], [])

        for variable in requires:
            if variable in scope:
                self.error("condition depends on question \"{}\" within "
                           "own scope".format(variable))
            if variable in self.constants:
                self.warn("condition depends on constant computed "
                          "question \"{}\"".format(variable))

        return scope

    def visit_ifelse_conditional(self, node):
        requires = node.condition.accept(self)
        scope = sum([element.accept(self) for element in node.ifbody +
                     node.elsebody], [])

        for variable in requires:
            if variable in scope:
                self.error("condition depends on question \"{}\" within "
                           "own scope".format(variable))
            if variable in self.constants:
                self.warn("condition depends on constant computed "
                          "question \"{}\"".format(variable))

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
