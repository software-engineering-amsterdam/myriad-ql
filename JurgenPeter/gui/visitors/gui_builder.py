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
            Datatype.boolean: CheckBoxWidget,
            Datatype.string:  EntryWidget}

        self.stylings = []

    def push_styling(self, styling):
        self.stylings.append(styling)

    def push_stylings(self, stylings):
        self.stylings += stylings

    def pop_styling(self):
        self.stylings.pop()

    def pop_stylings(self, elements):
        for _ in range(elements):
            self.stylings.pop()

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
        self.push_stylings(node.stylings)
        self.visit_layout(node)
        self.pop_stylings(len(node.stylings))

    def visit_page(self, node):
        self.app.startTab(node.name)
        for element in node.body:
            element.accept(self)
        self.app.stopTab()

    def visit_styled_page(self, node):
        self.push_stylings(node.stylings)
        self.visit_page(node)
        self.pop_stylings(len(node.stylings))

    def visit_section(self, node):
        self.app.startLabelFrame(node.name)
        for element in node.body:
            element.accept(self)
        self.app.stopLabelFrame()

    def visit_styled_section(self, node):
        self.push_stylings(node.stylings)
        self.visit_section(node)
        self.pop_stylings(len(node.stylings))

    def visit_question_anchor(self, node):
        question = QuestionFinder(node.name).visit(self.form)
        question.accept(self)

    def visit_styled_question_anchor(self, node):
        self.push_styling(node.styling)
        self.visit_question_anchor(node)
        self.pop_styling()

    def visit_form(self, node):
        for element in node.body:
            element.accept(self)

    def visit_question(self, node):

        # TODO get widget from styling stack
        widget_constructor = self.default_widgets[node.datatype]

        for styling in self.stylings:
            widget_constructor = styling.modify_widget_constructor(node, widget_constructor)

        widget = widget_constructor(self.app, node)
        widget.set_listener(self.listener)

        for styling in self.stylings:
            widget.apply(styling)

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
