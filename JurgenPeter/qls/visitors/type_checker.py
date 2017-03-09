from gui.visitors.widget_creator import WidgetCreator
from misc.messages import *
from ql.visitors.question_finder import QuestionFinder


class QlsTypeChecker:
    def __init__(self, symbol_table, errors=[]):
        self.symbol_table = symbol_table
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
        stylings += node.stylings
        self.visit_layout(node, stylings)
        for _ in node.stylings:
            stylings.pop()

    def visit_page(self, node, stylings):
        for element in node.body:
            self.visit(element, stylings)

    def visit_styled_page(self, node, stylings):
        stylings += node.stylings
        self.visit_page(node, stylings)
        for _ in node.stylings:
            stylings.pop()

    def visit_section(self, node, stylings):
        for element in node.body:
            self.visit(element, stylings)

    def visit_styled_section(self, node, stylings):
        stylings += node.stylings
        self.visit_section(node, stylings)
        for _ in node.stylings:
            stylings.pop()

    def visit_question_anchor(self, node, stylings):
        widget_constructor = WidgetCreator().create(self.symbol_table[node.name].value)

        for styling in stylings:
            widget_constructor = styling.modify_widget_constructor(
                self.symbol_table[node.name].value, widget_constructor)

        if widget_constructor.get_datatype() != self.symbol_table[node.name]:
            self.error("Widget \"{}\" does not match "
                       "question datatype".format(node.name))

        # question = QuestionFinder(node.name).find(self.form)
        # self.visit(question, stylings)

    def visit_styled_question_anchor(self, node, stylings):
        stylings.append(node.styling)
        self.visit_question_anchor(node, stylings)
        stylings.pop()

    def visit_question(self, node, stylings):
        widget_constructor = WidgetCreator().create(node.datatype.value)

        for styling in stylings:
            widget_constructor = styling.modify_widget_constructor(
                node, widget_constructor)

        if widget_constructor.get_datatype() != self.symbol_table[node.name]:
            self.error("Widget \"{}\" does not match "
                       "question datatype".format(node.name))
