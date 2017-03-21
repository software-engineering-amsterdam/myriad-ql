from misc.visitor import CheckerVisitor
from qls.visitors.styling_widget_type_getter import StylingWidgetTypeGetter


class TypeChecker(CheckerVisitor):
    def __init__(self, symbol_table, errors=[]):
        self.symboltable = symbol_table
        self.errors = errors

    def check(self, node):
        self.visit(node, [])

    def visit_layout(self, node, stylings):
        for element in node.body:
            self.visit(element, stylings)

    def visit_styled_layout(self, node, stylings):
        self.visit_layout(node, stylings + node.stylings)

    def visit_page(self, node, stylings):
        for element in node.body:
            self.visit(element, stylings)

    def visit_styled_page(self, node, stylings):
        self.visit_page(node, stylings + node.stylings)

    def visit_section(self, node, stylings):
        for element in node.body:
            self.visit(element, stylings)

    def visit_styled_section(self, node, stylings):
        self.visit_section(node, stylings + node.stylings)

    def visit_question_anchor(self, node, stylings):
        question_type = self.symboltable[node.name]
        widget_type = self.get_widget_type(question_type, stylings)

        if widget_type != question_type:
            self.error("widget datatype {} for question anchor "
                       "\"{}\" does not match question "
                       "datatype {}".format(widget_type, node.name,
                                            question_type))

    def visit_styled_question_anchor(self, node, stylings):
        self.visit_question_anchor(node, stylings + [node.styling])

    def get_widget_type(self, datatype, stylings):
        widget_type = datatype
        styling_getter = StylingWidgetTypeGetter(datatype)

        for styling in stylings:
            if styling_getter.get(styling):
                widget_type = styling_getter.get(styling)

        return widget_type
