from collections import defaultdict

from misc.visitor import CheckerVisitor
from ql.traversals.dependency_finder import DependencyFinder


class DependencyChecker(CheckerVisitor):

    def __init__(self, errors=[]):
        self.known_dependencies = defaultdict(list)
        self.errors = errors

    def check(self, node):
        self.visit(node)

    def visit_form(self, node):
        for element in node.body:
            self.visit(element)

    def visit_question(self, node):
        return [node.name]

    def visit_computed_question(self, node):
        dependencies = DependencyFinder().find(node)

        if node.name in dependencies:
            self.error("computed question \"{}\" has dependency on "
                       "itself".format(node.name))

        self.find_indirect_dependencies(dependencies)

        for dependency in dependencies:
            if node.name in self.known_dependencies[dependency]:
                self.error("computed question \"{}\" has circular dependency "
                           "on computed question \"{}\"".format(node.name,
                                                                dependency))

        self.known_dependencies[node.name] = dependencies
        return [node.name]

    def visit_if_conditional(self, node):
        scope = sum([self.visit(element) for element in node.ifbody], [])
        return self.visit_conditional(node, scope)

    def visit_ifelse_conditional(self, node):
        scope = sum([self.visit(element) for element in node.ifbody +
                     node.elsebody], [])
        return self.visit_conditional(node, scope)

    def visit_conditional(self, node, scope):
        dependencies = DependencyFinder().find(node)
        for dependency in dependencies:
            if dependency in scope:
                self.error("condition depends on question \"{}\" within "
                           "own scope".format(dependency))
        return scope

    def find_indirect_dependencies(self, dependencies):
        for dependency in dependencies:
            for indirect_dependency in self.known_dependencies[dependency]:
                if indirect_dependency not in dependencies:
                    dependencies.append(indirect_dependency)
