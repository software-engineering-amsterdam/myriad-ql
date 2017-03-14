# coding=utf-8
from unittest import TestCase

from pql.dependencies.dependencieschecker import DependenciesChecker
from pql.environment.environmentcreator import EnvironmentCreator
from pql.evaluator.evaluator import Evaluator
from pql.identifierchecker.identifierchecker import IdentifierChecker
from pql.parser.parser import parse
from pql.typechecker.type_environment import TypeEnvironment
from pql.typechecker.typechecker import TypeChecker


class Shared(TestCase):
    def acquire_identifiers(self, ast):
        return IdentifierChecker(ast).visit()

    def check_type(self, ast):
        return TypeChecker(ast, TypeEnvironment).visit()

    def evaluate(self, ast):
        return Evaluator(EnvironmentCreator, ast).visit()

    def acquire_types(self, ast):
        return TypeEnvironment(ast).visit()

    def acquire_environment(self, ast):
        return EnvironmentCreator(ast).visit()

    def acquire_circular_references(self, ast):
        return DependenciesChecker(ast).visit()

    def apply_type_checking(self, input_string):
        form_node = parse(input_string).asList()
        errors = self.acquire_identifiers(form_node)
        self.assertEqual(len(errors), 0, "There are multiple declarations of a field.")
        return self.check_type(form_node)

    def apply_evaluate(self, input_string):
        form_node = parse(input_string).asList()
        errors = self.acquire_identifiers(form_node)
        self.assertEqual(len(errors), 0, "There are multiple declarations of a field.")
        type_errors = self.check_type(form_node)
        self.assertEqual(len(errors), 0, "There were type errors {}".format(type_errors))
        return self.evaluate(form_node)
