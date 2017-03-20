from QL.stages.print_handler import PrintHandler as qlPrintHandler


class PrintHandler(qlPrintHandler):
    def __init__(self):
        self.output = ""

    def print_ast(self, ast):
        self.output = ""
        ast.accept(self, 0)
        print(self.output)

    def add_indent(self, indent):
        self.output += indent * "    "

    def style_sheet_node(self, style_sheet_node, indent):
        self.output += "stylesheet {}:\n".format(style_sheet_node.name)
        style_sheet_node.body.accept(self, indent + 1)

    def page_node(self, page_node, indent):
        self.add_indent(indent)
        self.output += "page {}:\n".format(page_node.name)
        page_node.body.accept(self, indent + 1)

    def page_with_defaults_node(self, page_with_defaults_node, indent):
        self.page_node(page_with_defaults_node, indent)
        page_with_defaults_node.defaults.accept(self, indent + 1)

    def section_node(self, section_node, indent):
        self.add_indent(indent)
        self.output += "section '{}':\n".format(section_node.name)
        section_node.body.accept(self, indent + 1)

    def section_with_defaults_node(self, section_with_defaults_node, indent):
        self.section_node(section_with_defaults_node, indent)
        section_with_defaults_node.defaults.accept(self, indent + 1)

    def widget_question_node(self, widget_question_node, indent):
        self.question_node(widget_question_node, indent)
        widget_question_node.type.accept(self, indent + 1)

    def question_node(self, question_node, indent):
        self.add_indent(indent)
        self.output += "question {}\n".format(question_node.name)

    def slider_node(self, _, indent):
        self.add_indent(indent)
        self.output += "widget slider\n"

    def spinbox_node(self, _, indent):
        self.add_indent(indent)
        self.output += "widget spinbox\n"

    def text_node(self, _, indent):
        self.add_indent(indent)
        self.output += "widget text\n"

    def numeric_node(self, _, indent):
        self.add_indent(indent)
        self.output += "widget numeric\n"

    def radio_node(self, _, indent):
        self.add_indent(indent)
        self.output += "widget radio\n"

    def checkbox_node(self, _, indent):
        self.add_indent(indent)
        self.output += "widget checkbox\n"

    def dropdown_node(self, _, indent):
        self.add_indent(indent)
        self.output += "widget dropdown\n"

    def default_node(self, default_node, indent):
        self.add_indent(indent)
        self.output += "default {}:\n".format(default_node.type.accept(self))
        default_node.widget_type.accept(self, indent + 1)

    def default_with_props_node(self, default_with_props_node, indent):
        self.add_indent(indent)
        default_type = default_with_props_node.type.accept(self)
        self.output += "default {}:\n".format(default_type)
        default_with_props_node.props.accept(self, indent + 1)
        default_with_props_node.widget_type.accept(self, indent + 1)

    def width_node(self, width_node, indent):
        self.add_indent(indent)
        self.output += "width: {}\n".format(width_node.val)

    def height_node(self, height_node, indent):
        self.add_indent(indent)
        self.output += "height: {}\n".format(height_node.val)

    def font_node(self, font_node, indent):
        self.add_indent(indent)
        self.output += "font: '{}'\n".format(font_node.val)

    def fontsize_node(self, fontsize_node, indent):
        self.add_indent(indent)
        self.output += "fontsize: {}\n".format(fontsize_node.val)

    def color_node(self, color_node, indent):
        self.add_indent(indent)
        self.output += "color: {}\n".format(color_node.val)
