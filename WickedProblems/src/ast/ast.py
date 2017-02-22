class Node(object):
    # Base class of all nodes
    def __init__(self, identifier, parent):
        self.__identifier = identifier
        self.__parent = parent
        self.__children = []

        if parent is not None:
            parent.add_child(self)

    def add_child(self, child):
        if child and not isinstance(child, Node):
            raise TypeError("Child is not an instance of Node")
        self.__children.append(child)

    def get_children(self):
        return self.__children

    def get_parent(self):
        return self.__parent

    def get_identifier(self):
        return self.__identifier

    def get_text(self):
        return self._text

    def get_tree_representation(self):
        __ret = self.__class__.__name__ + "\n"
        for child in self.__children:
            __ret += "\t" + child.__class__.__name__ + "\n"
            for __sub in child.get_children():
                __ret += "\t\t" + __sub.get_tree_representation()
        return __ret

class Root(Node):
    # Node that only has children
    def __init__(self, identifier):
        Node.__init__(self, identifier, None)

class Child(Node):
    _variable = None
    # Node that can have both a parent and children
    def __init__(self, identifier, parent):
        Node.__init__(self, identifier, parent)

class ConditionalNode(Child):
    _eval_type = None
    _evaluation = None
    def __init__(self, parent, eval_type, evaluation):
        Child.__init__(self, "ConditionalNode", parent)
        self._eval_type = eval_type
        self._evaluation = evaluation

class QuestionNode(Child):
    _field_type = None
    _text = None

    def __init__(self, identifier, parent, field_type, text):
        Child.__init__(self, identifier, parent)
        self._field_type = field_type
        self._text = text

class StatementNode(Child):
    _field_type = None
    _text = None
    _evaluation = None

    def __init__(self, identifier, parent, field_type, text, evaluation):
        Child.__init__(self, identifier, parent)
        self._field_type = field_type
        self._evaluation = evaluation
