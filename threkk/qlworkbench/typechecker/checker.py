# -*- coding: utf-8 -*-
"""
This module contains the error checker. The error checker is in charge of
making sure that there are no errors in the QL program that would make the
program to crash. The error checker accepts different types of checker engines
at the same time to catch all the different types of errors.
"""


class CheckerMessage(object):
    """
    Parent abstract class which holds the information necessary by the
    checker to display a message.
    """
    def __init__(self, node, message):
        self.node = node
        self.message = message


class Error(CheckerMessage):
    """Class which holds a checker error."""
    def __str__(self):
        return 'ERROR: {} at: {}'.format(self.message, self.node)


class Warning(CheckerMessage):
    """Class which holds a checker warning."""
    def __str__(self):
        return 'WARNING: {} at: {}'.format(self.message, self.node)


class Checker(object):
    """
    This class contains the list of errors and warnings of the checkers. It is
    used by the different checkers to register their errors.
    """
    def __init__(self):
        self.errors = []
        self.warns = []

    def add_error(self, node, message):
        """Adds a new error to the error list."""
        error = Error(node, message)
        self.errors.append(error)

    def add_warning(self, node, message):
        """Adds a new warning to the warning list."""
        warn = Warning(node, message)
        self.warns.append(warn)

    def get_errors(self):
        """Retrieves a list of reported errors."""
        return self.errors

    def get_warnings(self):
        """Retrieves a list of reported warning."""
        return self.warns
