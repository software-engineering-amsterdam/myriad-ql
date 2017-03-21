# coding=utf-8
from pyparsing import ParseFatalException

from pql.ast.ast import Location


class FatalError(ParseFatalException):
    def __init__(self, source, text, position):
        self.text = text
        self.location = Location(position, source)
        raise self

    def __str__(self):
        return "{} : {}".format(self.text, self.location)

    def __repr__(self):
        return str(self)
