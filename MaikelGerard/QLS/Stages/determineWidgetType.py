# TODO: check form name?
from collections import OrderedDict


class DetermineWidgetType(object):
    def __init__(self, qls_ast, qls_env, ql_env):
        self.ast = qls_ast
        self.env = qls_env
        self.ql_env = ql_env
        self.default_stack = OrderedDict()

    def start_traversal(self):
        self.default_stack = OrderedDict()
        self.ast.accept(self)

    def style_sheet_node(self, style_sheet_node):
        style_sheet_node.body.accept(self)

    def page_node(self, page_node):
        page_node.body.accept(self)

    def page_with_defaults_node(self, page_node):
        num_defaults = page_node.defaults.num_children()
        assert num_defaults > 0, "Invalid number of defaults!"

        # Add defaults to the stack, visit body, pop them afterwards.
        page_node.defaults.accept(self)
        page_node.body.accept(self)
        for _ in range(0, num_defaults):
            self.default_stack.popitem()

    def section_node(self, section_node):
        section_node.body.accept(self)

    def section_with_defaults_node(self, section_node):
        num_defaults = section_node.defaults.num_children()
        assert num_defaults > 0, "Invalid number of defaults!"

        # Add defaults to the stack, visit body, pop them afterwards.
        section_node.defaults.accept(self)
        section_node.body.accept(self)
        for _ in range(0, num_defaults):
            self.default_stack.popitem()

    def question_node(self, question_node):
        # Retrieve QL question type.
        question_type = self.ql_env.get_var_type(question_node.name)

        # Set the QLS default styling, if specified.
        if question_type in self.default_stack:
            default = self.default_stack[question_type]
            self.env.set_styling(question_node.name, default)

    def widget_question_node(self, question_node):
        self.question_node(question_node)

    def default_node(self, default_node):
        self.default_stack[default_node.type] = default_node

    def default_with_props_node(self, default_node):
        self.default_node(default_node)
