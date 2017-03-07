from ql.visitors.question_finder import QuestionFinder
from gui.visitors.widget_creator import WidgetCreator


class QlsGuiBuilder:
    
    def __init__(self, app, listener, widgets, form):
        self.app = app
        self.listener = listener
        self.form = form
        self.widgets = widgets

    def build(self, node):
        self.visit(node, [])

    def visit(self, node, stylings):
        node.accept(self, stylings)

    def visit_layout(self, node, stylings):
        self.app.startTabbedFrame(node.name)
        self.app.setTabbedFrameTabExpand(node.name, True)
        for element in node.body:
            self.visit(element, stylings)
        self.app.stopTabbedFrame()

    def visit_styled_layout(self, node, stylings):
        stylings += node.stylings
        self.visit_layout(node, stylings)
        for _ in node.stylings:
            stylings.pop()

    def visit_page(self, node, stylings):
        self.app.startTab(node.name)
        for element in node.body:
            self.visit(element, stylings)
        self.app.stopTab()

    def visit_styled_page(self, node, stylings):
        stylings += node.stylings
        self.visit_page(node, stylings)
        for _ in node.stylings:
            stylings.pop()

    def visit_section(self, node, stylings):
        self.app.startLabelFrame(node.name)
        for element in node.body:
            self.visit(element, stylings)
        self.app.stopLabelFrame()

    def visit_styled_section(self, node, stylings):
        stylings += node.stylings
        self.visit_section(node, stylings)
        for _ in node.stylings:
            stylings.pop()

    def visit_question_anchor(self, node, stylings):
        question = QuestionFinder(node.name).find(self.form)
        self.visit(question, stylings)

    def visit_styled_question_anchor(self, node, stylings):
        stylings.append(node.styling)
        self.visit_question_anchor(node, stylings)
        stylings.pop()

    def visit_question(self, node, stylings):
        widget_constructor = WidgetCreator().create(node.datatype.value)

        for styling in stylings:
            widget_constructor = styling.modify_widget_constructor(
                node, widget_constructor)

        widget = widget_constructor(self.app, node)
        widget.set_listener(self.listener)

        for styling in stylings:
            widget.apply(styling)

        self.widgets[node.name] = widget

    def visit_computed_question(self, node, stylings):
        self.visit_question(node, stylings)
        self.widgets[node.name].disable()
