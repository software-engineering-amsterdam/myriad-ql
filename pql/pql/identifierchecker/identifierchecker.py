class IdentifierChecker(object):
    def __init__(self):
        self.identifier_map = {}
        self.messages = []

    def visit(self, node):
        self.messages = []
        self.identifier_map = {}
        node.apply(self)
        return self.identifier_map, self.messages

