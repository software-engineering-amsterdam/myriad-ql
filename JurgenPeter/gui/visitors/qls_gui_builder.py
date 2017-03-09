from ql.visitors.question_finder import QuestionFinder
from gui.visitors.widget_creator import WidgetCreator
from gui.visitors.gui_builder import GuiBuilder


class QlsGuiBuilder(GuiBuilder):

    def __init__(self, app, listener, exit, widgets, form):
        super().__init__(app, listener, exit, widgets)
        self.form = form

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
        self.create_exit_button()

    def visit_styled_layout(self, node, stylings):
        self.visit_layout(node, stylings + node.stylings)

    def visit_page(self, node, stylings):
        self.app.startTab(node.name)
        for element in node.body:
            self.visit(element, stylings)
        self.app.stopTab()

    def visit_styled_page(self, node, stylings):
        self.visit_page(node, stylings + node.stylings)

    def visit_section(self, node, stylings):
        self.app.startLabelFrame(node.name)
        for element in node.body:
            self.visit(element, stylings)
        self.app.stopLabelFrame()

    def visit_styled_section(self, node, stylings):
        self.visit_section(node, stylings + node.stylings)

    def visit_question_anchor(self, node, stylings):
        question = QuestionFinder(node.name).find(self.form)
        self.visit(question, stylings)

    def visit_styled_question_anchor(self, node, stylings):
        self.visit_question_anchor(node, stylings + [node.styling])

    def visit_question(self, node, stylings):
        widget_constructor = WidgetCreator().create(node.datatype)
        widget_arguments = []

        for styling in stylings:
            widget_constructor, widget_arguments = \
                styling.modify_widget_constructor(node.datatype,
                                                  widget_constructor,
                                                  widget_arguments)

        widget = widget_constructor(self.app, node, *widget_arguments)
        widget.set_listener(self.listener)

        for styling in stylings:
            widget.apply(styling)

        self.widgets[node.name] = widget

    def visit_computed_question(self, node, stylings):
        self.visit_question(node, stylings)
        self.widgets[node.name].disable()
