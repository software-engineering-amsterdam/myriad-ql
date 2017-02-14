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
    def __init__(self, parsed_output):
        super(Form, self).__init__('form')
        self.name = parsed_output.form_identifier
        if parsed_output.form_statement_list:
            field_expressions = parsed_output.form_statement_list['field_expression']
            # TODO Check how to handle this in case key is not there
            # if_blocks = parsed_output.form_statement_list['if_statement']
            # There should always be at least one field
            for field_expression in field_expressions:
                self.add_child(Field(field_expression))
            # TODO Check how to only do this in case list is not empty pythonically
            # for if_block in if_blocks:
            #     self.add_child(if_block)


class Field(Node):
    def __init__(self, parsed_output):
        super(Field, self).__init__('field')
        self.name = parsed_output.identifier
        self.title = parsed_output.title
