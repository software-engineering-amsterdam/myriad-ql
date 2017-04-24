# -*- coding: utf-8 -*-
"""
This module contains the definition of a Parser checker. This module is passed
to the parser which will report the errors to it when they are detected.
"""


class ParserChecker(object):
    def __init__(self, checker):
        """Initialises the parser checker"""
        self.checker = checker

    def register_error(self, line, message):
        """Registers an error in the checker"""
        self.checker.add_error('line {}'.format(line), message)

    def register_warning(self, line, message):
        """Registers a warning in the checker"""
        self.checker.add_warning('line {}'.format(line), message)
