from collections import OrderedDict


class DetermineWidgetType(object):
    def __init__(self, qls_ast, qls_env, ql_env):
        self.ast = qls_ast
        self.env = qls_env
        self.ql_env = ql_env

    def start_traversal(self):
        prev_defaults = OrderedDict()
        self.ast.accept(self, prev_defaults)

    def style_sheet_node(self, style_sheet_node, prev_defaults):
        style_sheet_node.body.accept(self, prev_defaults)

    def page_node(self, page_node, prev_defaults):
        page_node.body.accept(self, prev_defaults)

    def page_with_defaults_node(self, page_node, prev_defaults):
        num_defaults = page_node.defaults.num_children()
        assert num_defaults > 0, "Invalid number of defaults!"

        # Add defaults to the stack, visit body, pop them afterwards.
        current_defaults = prev_defaults.copy()
        page_node.defaults.accept(self, current_defaults)
        page_node.body.accept(self, current_defaults)

    def section_node(self, section_node, prev_defaults):
        section_node.body.accept(self, prev_defaults)

    def section_with_defaults_node(self, section_node, prev_defaults):
        num_defaults = section_node.defaults.num_children()
        assert num_defaults > 0, "Invalid number of defaults!"

        # Add defaults to the stack, visit body, pop them afterwards.
        current_defaults = prev_defaults.copy()
        section_node.defaults.accept(self, current_defaults)
        section_node.body.accept(self, current_defaults)

    def question_node(self, question_node, current_defaults):
        # Retrieve QL question type.
        question_type = self.ql_env.get_var_type(question_node.name)

        # Set the QLS default styling, if specified.
        if question_type in current_defaults:
            default = current_defaults[question_type]
            self.env.set_styling(question_node.name, default)

    def widget_question_node(self, question_node, current_defaults):
        self.question_node(question_node, current_defaults)

    @staticmethod
    def default_node(default_node, current_defaults):
        current_defaults[default_node.type] = default_node

    def default_with_props_node(self, default_node, current_defaults):
        self.default_node(default_node, current_defaults)
