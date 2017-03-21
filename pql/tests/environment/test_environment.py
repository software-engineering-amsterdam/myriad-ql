from unittest import TestCase

from pql.environment.environment import Environment
from tests.shared import Shared


class TestEnvironment(Shared):
    def teast_create(self):
        environment = Environment()
        self.assertIsNotNone(environment)

    def test_update(self):
        environment = Environment()
        self.assertEqual(len(environment.items()), 0, "Should be empty")
        environment.update('test', 1)
        self.assertEqual(len(environment.items()), 1, "Should not be empty")

    def test_clear(self):
        environment = Environment()
        self.assertEqual(len(environment.items()), 0, "Should be empty")
        environment.update('test', 1)
        self.assertEqual(len(environment.items()), 1, "Should not be empty")
        environment.clear()
        self.assertEqual(len(environment.items()), 0, "Should be empty")

    def test_value(self):
        environment = Environment()
        self.assertEqual(len(environment.items()), 0, "Should be empty")
        environment.update('test', 1)
        self.assertEqual(1, environment.value('test'), "Value should equal 1")

    def test_contains(self):
        environment = Environment()
        self.assertEqual(len(environment.items()), 0, "Should be empty")
        environment.update('test', 1)
        self.assertTrue(environment.contains('test'), "Should contain this value")
        self.assertFalse(environment.contains('test2'), "Should not contain this value")
