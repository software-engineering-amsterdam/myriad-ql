class Node(object):
    # Base class of all nodes
    def __init__(self, identifier):
        # variables are now internal (rather than private)
        self._children = []
        self._identifier = identifier

    def add_child(self, child):
        if child and not isinstance(child, Node):
            raise TypeError("Child is not an instance of Node")
        self._children.append(child)

class Root(Node):
    def __init__(self, identifier, content):
        Node.__init__(self, "form")

class Question(Node):
    def __init__(self, identifier, content):
        Node.__init__(self, "question")

class Conditional(Node):
    def __init__(self, identifier, evaluation):
        Node.__init__(self, "conditional")

class Statement(Node):
    def __init__(self, identifier, content):
        Node.__init__(self, "statement")
