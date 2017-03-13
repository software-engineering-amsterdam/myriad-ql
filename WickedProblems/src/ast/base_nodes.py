class Node(object):
    indent = 0
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
    def __init__(self, identifier, children):
        Node.__init__(self, "form")
        self._identifier = identifier
        self._children = children

    def __str__(self):
        __ret = "{}{} \"{}\"\n".format("\t" * Node.indent,
                                       self.__class__.__name__,
                                       self._identifier)
        Node.indent += 1
        for __child in self._children:
            __ret += "{}{}".format("\t" * Node.indent,__child)
        Node.indent -= 1
        return __ret

    __repr__ = __str__

class Question(Node):
    def __init__(self, text, identifier, field_type):
        Node.__init__(self, "question")
        text._identifier = identifier
        self._text = text
        self._identifier = identifier
        self._field_type = field_type(identifier)

    def __str__(self):
        return "{} \"{}\", {}, \"{}\"\n".format(
            self.__class__,
            self._identifier,
            self._field_type,
            self._text)
    __repr__ = __str__

class Conditional(Node):
    def __init__(self, condition, children):
        Node.__init__(self, "conditional")
        self._condition = condition
        self._children = children

    def __str__(self):
        __ret = "{} \"{}\"\n".format(
            self.__class__,
            self._condition)
        Node.indent += 1
        for __child in self._children:
            __ret += "{}{}\n".format("\t" * Node.indent, __child)
        Node.indent -= 1
        return __ret

    __repr__ = __str__

    def evaluate(self):
        __condition = self._condition
        print(__condition)

class Statement(Node):
    def __init__(self, text, identifier, field_type, children):
        Node.__init__(self, "statement")
        text._identifier = identifier
        self._identifier = identifier
        self._field_type = field_type(identifier)
        self._children = children

    def __str__(self):
        __ret = "{} \"{}\", {}\n".format(
            self.__class__,
            self._identifier,
            self._field_type)
        Node.indent += 1
        for __child in self._children:
            __ret += "{}{}\n".format("\t" * Node.indent, __child)
        Node.indent -= 1
        return __ret

    __repr__ = __str__
