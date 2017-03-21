from QLS.stages.determine_is_computed import DetermineIsComputed


class TypeChecker(object):
    def __init__(self, qls_ast, qls_env, ql_env, error_handler):
        self.ast = qls_ast
        self.env = qls_env
        self.ql_env = ql_env
        self.handler = error_handler
        self.is_computed_visitor = DetermineIsComputed()

    def start_traversal(self):
        self.env.clear_env()
        self.ast.accept(self)

        # Check if all QL questions are defined in QLS
        for var in self.ql_env.get_vars():
            if var not in self.env.get_vars():
                self.handler.add_question_not_in_qls_error(var)

    def style_sheet_node(self, style_sheet_node):
        style_sheet_node.body.accept(self)

    def page_node(self, page_node):
        page_node.body.accept(self)

    def page_with_defaults_node(self, page_node):
        page_node.body.accept(self)
        page_node.defaults.accept(self)

    def section_node(self, section_node):
        section_node.body.accept(self)

    def section_with_defaults_node(self, section_node):
        section_node.body.accept(self)
        section_node.defaults.accept(self)

    def widget_question_node(self, widget_question_node):
        self.question_node(widget_question_node)

        question_name = widget_question_node.name
        if not self.ql_env.exists(question_name):
            return
        literal_type = self.ql_env.get_var_type(question_name)
        widget_question_node.type.accept(self, literal_type)

    def question_node(self, question_node):
        if not self.ql_env.exists(question_node.name):
            self.handler.add_question_not_in_ql_error(question_node)
            return

        ql_node = self.ql_env.get_question_node(question_node.name)
        is_computed = ql_node.accept(self.is_computed_visitor)
        self.env.add_var(question_node, is_computed)

    def check_widget_compatibility(self, widget_node,
                                   literal_type, widget_name):
        if not widget_node.is_compatible_type(literal_type):
            self.handler.add_incompatible_widget_type_error(
                widget_node, widget_name, literal_type
            )

    def slider_node(self, slider_node, literal_type):
        self.check_widget_compatibility(slider_node, literal_type, "slider")

    def spinbox_node(self, spinbox_node, literal_type):
        self.check_widget_compatibility(spinbox_node, literal_type, "spinbox")

    def text_node(self, text_node, literal_type):
        self.check_widget_compatibility(text_node, literal_type, "text")

    def numeric_node(self, numeric_node, literal_type):
        self.check_widget_compatibility(numeric_node, literal_type, "text")

    def radio_node(self, radio_node, literal_type):
        self.check_widget_compatibility(radio_node, literal_type, "radio")

    def checkbox_node(self, checkbox_node, literal_type):
        self.check_widget_compatibility(checkbox_node, literal_type, "checkbox")

    def dropdown_node(self, dropdown_node, literal_type):
        self.check_widget_compatibility(dropdown_node, literal_type, "dropdown")

    def default_node(self, default_node):
        default_node.widget_type.accept(self, default_node.type)

    def default_with_props_node(self, default_node):
        self.default_node(default_node)
