import sys


class ErrorHandler(object):
    def __init__(self):
        self.errors = []

    def add_error(self, context, node, message):
        self.errors.append("{}: {}, at line {}, column {}".format(
            context, message, node.line, node.col
        ))

    def print_errors(self):
        for error in self.errors:
            print error

        if len(self.errors):
            print "Found {} error(s), stopping exection!".format(
                len(self.errors)
            )
            sys.exit()

    def clear_errors(self):
        self.errors = []
