from misc.visitor import Visitor


class StylingWidgetGetter(Visitor):
    
    def __init__(self, datatype):
        self.datatype = datatype
    
    def get(self, node):
        return self.visit(node)

    def visit_styling(self, node):
        if not node.applicable_on(self.datatype):
            return None

        for attribute in node.attributes:
            if self.visit(attribute):
                return self.visit(attribute)
        return None

    def visit_color_attribute(self, node):
        return None

    def visit_font_size_attribute(self, node):
        return None

    def visit_font_weight_attribute(self, node):
        return None

    def visit_font_family_attribute(self, node):
        return None

    def visit_width_attribute(self, node):
        return None

    def visit_widget_type_attribute(self, node):
        return lambda gui, question: node.widget(gui, question,
                                                 *node.widget_arguments)
