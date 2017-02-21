class AbstractNode(object):
    # Base class of all nodes

    def __init__(self, identifier, parent):
        self.__identifier = identifier
        self.__parent = parent
        self.__children = []
        self.__interface_item = None

        if parent is not None:
            parent.add_child(self)

    def __str__(self):
        return "{name}".format(name=self.__class__.__name__)

    def _get_children_string(self, level):
        ''' String representation of children nodes '''
        return "".join([node.get_tree_representation(level + 1) for node in self.__children])

    def add_child(self, child):
        if child and not isinstance(child, AbstractNode):
            raise TypeError("Child is not an instance of AbstractNode")
        self.__children.append(child)

    def set_interface_item(self, item):
        self.__interface_item = item

    def get_interface_item(self):
        return self.__interface_item

    def get_children(self):
        return self.__children

    def get_parent(self):
        return self.__parent

    def get_identifier(self):
        return self.__identifier

    def get_tree_representation(self, level=0):
        ''' Pretty print tree structure '''
        return "".center(level, "\t") + str(self) + "\n" + self._get_children_string(level)

class BaseNode(AbstractNode):
    # Node that only has children

    def __init__(self, identifier):
        AbstractNode.__init__(self, identifier, None)


class LeafNode(AbstractNode):
    _variable = None
    # Node that can have both a parent and children

    def __init__(self, identifier, parent):
        AbstractNode.__init__(self, identifier, parent)


class ConditionalNode(LeafNode):
    _eval_type = None
    _evaluation = None

    def __init__(self, parent, eval_type, evaluation):
        LeafNode.__init__(self, "ConditionalNode", parent)
        self._eval_type = eval_type
        self._evaluation = evaluation


class QuestionNode(LeafNode):
    _field_type = None
    _text = None

    def __init__(self, identifier, parent, field_type, text):
        LeafNode.__init__(self, identifier, parent)
        self._field_type = field_type
        self._text = text


class StatementNode(LeafNode):
    _field_type = None
    _text = None
    _evaluation = None

    def __init__(self, identifier, parent, field_type, text, evaluation):
        LeafNode.__init__(self, identifier, parent)
        self._field_type = field_type
        self._evaluation = evaluation

# Test Code
if __name__ == '__main__':
    root = BaseNode("TestRootNode")
    child = LeafNode("Child 1", root)
    child2 = LeafNode("Child 2", root)
    grandchild = LeafNode("Grandchild 1", child)
    grandchild2 = LeafNode("Grandchild 2", child)
    grandchild3 = LeafNode("Grandchild 3", child2)
    print("Root Node Parent")
    print(root.get_parent())
    print("Root Node Children: ")
    print(root.get_children())
    print("Child #1 Parent")
    print(child.get_parent())
    print("Child #2 Parent")
    print(child2.get_parent())
    print("Child #1 Children")
    print(child.get_children())
    print("Child #2 Children")
    print(child2.get_children())
    print("Grandchild #1 Parent")
    print(grandchild.get_parent())
    print("Grandchild #2 Parent")
    print(grandchild2.get_parent())
    print("Grandchild #3 Parent")
    print(grandchild3.get_parent())