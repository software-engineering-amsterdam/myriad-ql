from gui.visitors.default_widget_getter import DefaultWidgetGetter
from gui.visitors.gui_builder import GuiBuilder


class QlGuiBuilder(GuiBuilder):

    def build(self, node):
        self.visit(node)

    def visit_form(self, node):
        for element in node.body:
            self.visit(element)
        self.create_exit_button()

    def visit_question(self, node):
        constructor = DefaultWidgetGetter().get(node.datatype)
        widget = constructor(self.app, node)
        widget.set_listener(self.listener)
        self.widgets[node.name] = widget

    def visit_computed_question(self, node):
        self.visit_question(node)
        self.widgets[node.name].disable()

    def visit_if_conditional(self, node):
        for element in node.ifbody:
            self.visit(element)

    def visit_ifelse_conditional(self, node):
        self.visit_if_conditional(node)
        for element in node.elsebody:
            self.visit(element)
