# coding=utf-8
class RootNode(object):
    def __init__(self, parser_output):
        self.root = Node("root_node")
        self.root.add_child(Form(parser_output))

    def __str__(self):
        return self.root.__str__()


class Node(object):
    def __init__(self, var_type):
        self.var_type = var_type
        self.children = []

    def add_child(self, node):
        self.children.append(node)

    def __str__(self, level=0):
        ret = "\t" * level + repr(self.var_type) + "\n"
        for child in self.children:
            ret += child.__str__(level + 1)
        return ret

    def __repr__(self):
        return '<%s>', self.var_type


class Form(Node):
    def __init__(self, content):
        print(content)
        assert content[0] == 'form', 'First encountered type should be a form'
        super(Form, self).__init__('form')
        self.name = content[1]
