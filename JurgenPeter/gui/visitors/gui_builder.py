from ql.ast import Datatype
from ql.visitors.question_finder import QuestionFinder
from gui.widgets import *


class GuiBuilder:

    def __init__(self, app, listener, form):
        self.app = app
        self.listener = listener
        self.form = form
        self.widgets = {}

        # TODO replace with QLS styling object
        self.default_widgets = {
            Datatype.integer: IntegerEntryWidget,
            Datatype.decimal: DecimalEntryWidget,
            Datatype.boolean: RadioWidget,
            Datatype.string:  EntryWidget}

    def visit(self, node):
        node.accept(self)
        return self.widgets

    def visit_layout(self, node):
        self.app.startTabbedFrame(node.name)
        self.app.setTabbedFrameTabExpand(node.name, True)
        for element in node.body:
            element.accept(self)
        self.app.stopTabbedFrame()

    def visit_styled_layout(self, node):
        self.visit_layout(node)

    def visit_page(self, node):
        self.app.startTab(node.name)
        for element in node.body:
            element.accept(self)
        self.app.stopTab()

    def visit_styled_page(self, node):
        self.visit_page(node)

    def visit_section(self, node):
        self.app.startLabelFrame(node.name)
        for element in node.body:
            element.accept(self)
        self.app.stopLabelFrame()

    def visit_styled_section(self, node):
        self.visit_section(node)

    def visit_question_anchor(self, node):
        question = QuestionFinder(node.name).visit(self.form)
        question.accept(self)

    def visit_styled_question_anchor(self, node):
        self.visit_question_anchor(node)

    def visit_form(self, node):
        for element in node.body:
            element.accept(self)

    def visit_question(self, node):
        widget = self.default_widgets[node.datatype](self.app, node)
        widget.set_listener(self.listener)
        self.widgets[node.name] = widget
        return widget

    def visit_computed_question(self, node):
        widget = self.visit_question(node)
        widget.disable()

    def visit_if_conditional(self, node):
        for element in node.ifbody:
            element.accept(self)

    def visit_ifelse_conditional(self, node):
        for element in node.ifbody:
            element.accept(self)
        for element in node.elsebody:
            element.accept(self)
