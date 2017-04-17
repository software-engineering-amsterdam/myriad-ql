# -*- coding: utf-8 -*-
"""
This module contains the logic of the QL checker. The QL checker looks for and
reports errors in the AST tree. This module concreteley detects:

    - Duplicated declarations.
    - Duplicated labels.
    - Invalid assignations.
    - Invalid conditions.
    - Invalid operands.
    - Cyclic dependencies.
    - Undefined dependencies.
"""
from ql.ast.type import Boolean
from ql.ast.type import Undefined


class QLChecker(object):
    def __init__(self, ast, checker):
        """Initialises QL Checker."""
        super().__init__()
        self.ast = ast
        self.checker = checker
        self.dependencies = {}
        self.register = {}
        self.types = {}

        for node in ast.nodes:
            self.__register_node(node)

    def check(self):
        """
        This function iterates through all the declared nodes in the AST and
        checks for possible errors in them. In case any error is found, it is
        reported to the checker.
        """
        for node in self.ast.nodes:
            self.__check_cyclic_dependencies(node)
            self.__check_invalid_assignation(node)
            self.__check_invalid_conditions(node)
            self.__check_invalid_operands(node)
            self.__check_undefined_dependencies(node)

    def get_type(self, key):
        """
        Returns the type of the node.
        """
        try:
            return self.types[key]
        except KeyError:
            # If this exception is triggered, it means that the variable
            # itself that we are querying is an undefined variable. This
            # error has been already reported with the function
            # __check_undefined_id
            return Undefined()

    def register_error(self, node, message):
        """Registers an error in the typechcker"""
        self.checker.add_error(node, message)

    def register_warning(self, node, message):
        """Registers a warning in the typechecker"""
        self.checker.add_warning(node, message)

    def __register_node(self, node):
        """
        Adds a node to the QL checker. It will also check for certain errors
        detected on declaration time like duplicated questions and labels
        (warning) and will report them to the typechecker which will process
        them.
        """
        # Checks if the declaration of the node will produce an error. Our
        # register only allows to have one method declared so it is important
        # to detect the
        self.__check_duplicate_labels(node)
        self.__check_duplicate_declarations(node)

        # Add the type of the node to the dictionary.
        self.types[node.variable.name] = node.variable.type

        # Tracks the dependencies of the node.
        dependencies = []
        if node.expression:
            expr = node.expression
            dependencies += expr.depends_on()

        for condition in node.conditions:
            dependencies += condition.depends_on()

        self.dependencies[node.variable.name] = dependencies

        # Adds the node to a register with all the declared nodes.
        self.register[node.variable.name] = node

    ###
    # Checks on registration of the node.
    ###
    def __check_duplicate_labels(self, node):
        """
        Lookis for duplicated labels before adding it. If anyone is found,
        it will raise a warning in the typechecker.
        """
        labels = [item.text for key, item in self.register.items()]
        if (node.text in labels):
            msg = 'Duplicated field label {}'
            self.register_warning(node, msg.format(node.text))

    def __check_duplicate_declarations(self, node):
        """
        Duplicate question declarations with different types.

        The project description says that it should only detect duplicated
        questions if they have different types. However, it makes more sense
        detect any duplicate variable. Therefore, we do both.
        """
        variable_name = node.variable.name
        if variable_name in self.register:
            msg = 'Duplicated question detected. It was {}'

            existing_node = self.register[variable_name]
            if (existing_node.variable.type != node.variable.type):
                msg = 'Duplicated question with different type. It was {}'
            self.register_error(node, msg.format(existing_node))
        else:
            # Here we take the design decision of keeping the existing one and
            # ignoring the new one.
            self.register[variable_name] = node

    ####
    # Checks after registration of all the node.
    ####
    def __check_invalid_conditions(self, node):
        """
        This function checks for errors in the expressions. Concretely, it
        checks for expressions used in conditionals that do not return a
        boolean.
        """
        # Looks for invalid conditionals.
        for condition in node.conditions:
            if condition.get_type(self) != Boolean():
                msg = ('The expression {} in the condition does not return a '
                       'boolean')
                self.register_error(node, msg.format(condition))

    def __check_invalid_assignation(self, node):
        """
        This function checks for errors in the expressions. It checks for
        expressions used in assignations that return a diffrent type than the
        expected by the variable.
        """
        key = node.variable.name
        if node.expression:
            expr_type = node.expression.get_type(key)
            node_type = self.get_type(key)

            if node_type != expr_type:
                msg = 'The assignation expected a {} but got a {}'
                self.register_error(node, msg.format(node_type, expr_type))

    def __check_invalid_operands(self, node):
        """
        This function checks for errors in the expressions. It checks for
        operations with operands that are not supported, like adding strings.
        """
        queue = []
        if node.expression:
            queue += [node.expression]

        queue += node.conditions

        # Looks for operations with invalid operands.
        # Order does not matter as all the expressions will be evaluated.
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
                        self.register_error(node, msg.format(child, operation))
                    queue.append(child)
            except AttributeError:
                # It is a Leaf node, nothing to worry about. Even if this looks
                # wrong, this is the pythonic way of doing it. It was this or
                # giving the LeafNodes a operation attribute, which does not
                # make sense.
                pass

    def __check_undefined_dependencies(self, node):
        """
        Detects the errors in the dependencies of a node. It checks for
        Undefined dependencies: Variables referenced in a expressions which are
        not defined by any node.
        """
        for dependency in self.dependencies[node.variable.name]:
            if dependency not in self.dependencies:
                msg = 'The node depends on a undefined variable "{}"'
                self.register_error(node, msg.format(dependency))

    def __check_cyclic_dependencies(self, node):
        """
        Detects the errors in the dependencies of a node. It checks for nodes
        which dependencies depend on them, also known as cyclic dependencies.
        For this, we calcualte the extended dependencies of a node to not only
        check the dependencies of the node but also the dependencies of the
        dependencies.
        """
        key = node.variable.name
        for dependency in self.dependencies[key]:
                # For every dependency of a key, we need to check the
                # dependency and the ones that it has.
                all_dependencies = self.__get_extended_dependencies(dependency)
                if key in all_dependencies:
                    dependency_node = self.register[dependency]
                    msg = 'The node {} has a cyclic dependency with {}'
                    self.register_error(node, msg.format(node,
                                                         dependency_node))

    def __get_extended_dependencies(self, key):
        """
        Extracts the unique depencies of a key and the dependencies of the
        depencies.
        """
        all_dependencies = []
        queue = []

        if key in self.dependencies:
            all_dependencies += self.dependencies[key]
            queue += self.dependencies[key]

        # I don't even want to think about the complexity of this.
        while queue:
            dependency = queue.pop()
            if dependency in self.dependencies:
                dependency_dependencies = self.dependencies[dependency]
                all_dependencies += dependency_dependencies
                for dependency_dependency in dependency_dependencies:
                    if dependency_dependency not in all_dependencies:
                        queue.append(dependency_dependency)
        return all_dependencies
