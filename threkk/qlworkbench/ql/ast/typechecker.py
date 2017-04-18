# -*- coding: utf-8 -*-
from .type import Boolean
from .type import Undefined


class TypeCheckerMessage(object):
    def __init__(self, node, message):
        self.node = node
        self.message = message


class Error(TypeCheckerMessage):
    def __str__(self):
        return 'ERROR: {} at: {}'.format(self.message, self.node)


class Warning(TypeCheckerMessage):
    def __str__(self):
        return 'WARNING: {} at: {}'.format(self.message, self.node)


class TypeChecker(object):
    def __init__(self, ast):
        self.ast = ast
        self.dependencies = {}
        self.types = {}
        self.errors = []
        self.warns = []

    def add_error(self, node, message):
        error = Error(node, message)
        self.errors.append(error)

    def add_warning(self, node, message):
        warn = Warning(node, message)
        self.warns.append(warn)

    def update(self, node):
        self.types[node.variable.name] = node.variable.type

        dependencies = []
        try:
            expr = node.expression
            dependencies += expr.depends_on()
        except AttributeError:
            # Do nothing, it is a declaration. We could have avoided the
            # exception but it seems that the pythonic way of doing it is to
            # let it crash http://stackoverflow.com/a/610923
            pass

        for condition in node.conditions:
            dependencies += condition.depends_on()

        self.dependencies[node.variable.name] = dependencies

    def get_type(self, key):
        try:
            return self.types[key]
        except KeyError:
            # If this exception is triggered, it means that the variable itself
            # that we are querying is an undefined variable. This error has
            # been already reported with the function __check_undefined_id
            return Undefined()

    def check(self):
        for key in self.ast.build_order:
            self.__check_dependencies(key)
            self.__check_expressions(key)

    def __check_expressions(self, key):
        node = self.ast.register[key]

        # Looks for invalid conditionals.
        conditions = node.conditions
        for condition in node.conditions:
            if condition.get_type(self) != Boolean():
                msg = ('The expression {} in the condition does not return a '
                       'boolean')
                self.add_error(node, msg.format(condition))

        # Looks for assignations with missmatecing types.
        expressions = []
        try:
            expr_type = node.expression.get_type(key)
            node_type = self.get_type(key)

            if node_type != expr_type:
                msg = 'The assignation expected a {} but got a {}'
                self.add_error(node, msg.format(node_type, expr_type))

            expressions.append(node.expression)
        except AttributeError:
            pass

        # Looks for operations with invalid operands.
        # Order does not matter as all the expressions will be evaluated.
        queue = conditions + expressions
        while queue:
            expr = queue.pop()
            children = expr.get_children()

            try:
                operation = expr.operation
                for child in children:
                    if (operation not in
                            child.get_type(self).allowed_operations()):
                        msg = ('The child {} does not allow to perform the '
                               '{} operation')
                        self.add_error(node, msg.format(child, operation))
                    queue.append(child)
            except AttributeError:
                # It is a Leaf node, nothing to worry about. Even if this looks
                # wrong, this is the pythonic way of doing it. It was this or
                # giving the LeafNodes a operation attribute, which does not
                # make sense.
                pass

    def __check_dependencies(self, key):
        """
        Detects the errors in dependencies:
            - Undefined dependencies.
            - Cyclic dependencies. I
        """
        for dependency in self.dependencies[key]:
            if dependency not in self.dependencies:
                node = self.ast.register[key]
                msg = 'The node depends on a undefined variable "{}"'
                self.add_error(node, msg.format(dependency))
            else:
                # For every dependency of a key, we need to check the
                # dependency and the ones that it has.
                all_dependencies = self.__get_extended_dependencies(dependency)

                if key in all_dependencies:
                    node = self.ast.register[key]
                    dependency_node = self.ast.register[dependency]
                    msg = 'The node {} has a cyclic dependency with {}'
                    self.add_error(node, msg.format(node, dependency_node))

    def __get_extended_dependencies(self, key):
        """
        Extracts the unique depencies of a key and the dependencies of the
        depencies.
        """
        all_dependencies = []
        queue = []

        # I don't even want to think about the complexity of this.
        all_dependencies += self.dependencies[key]
        queue += self.dependencies[key]
        while queue:
            dependency = queue.pop()
            if dependency in self.dependencies:
                dependency_dependencies = self.dependencies[dependency]
                all_dependencies += dependency_dependencies
                for dependency_dependency in dependency_dependencies:
                    if dependency_dependency not in all_dependencies:
                        queue.append(dependency_dependency)
        return all_dependencies
