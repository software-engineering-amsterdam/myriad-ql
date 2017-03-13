from misc.messages import *


class TypeChecker:
    def __init__(self, symbol_table, errors=[]):
        self.symboltable = symbol_table
        self.errors = errors

    def error(self, message):
        self.errors.append(ErrorMessage(message))

    def check(self, node):
        self.visit(node, [])

    def visit(self, node, stylings):
        return node.accept(self, stylings)

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
        widget_type = self.symboltable[node.name]

        for styling in stylings:
            if styling.widget_type(self.symboltable[node.name]):
                widget_type = styling.widget_type(self.symboltable[node.name])

        if widget_type != self.symboltable[node.name]:
            self.error("widget datatype {} for question anchor \"{}\" does not "
                       "match question datatype {}".format(widget_type,
                                                           node.name,
                                                           self.symboltable[node.name]))

    def visit_styled_question_anchor(self, node, stylings):
        self.visit_question_anchor(node, stylings + [node.styling])
