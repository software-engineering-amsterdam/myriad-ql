class Node:
    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__


class Layout(Node):

    def __init__(self, name, body):
        self.name = name
        self.body = body

    def accept(self, visitor, *args):
        visitor.visit_layout(self, *args)


class StyledLayout(Layout):

    def __init__(self, name, body, stylings):
        super().__init__(name, body)
        self.stylings = stylings

    def accept(self, visitor, *args):
        visitor.visit_styled_layout(self, *args)


class Page(Node):

    def __init__(self, name, body):
        self.name = name
        self.body = body

    def accept(self, visitor, *args):
        visitor.visit_page(self, *args)


class StyledPage(Page):

    def __init__(self, name, body, stylings):
        super().__init__(name, body)
        self.stylings = stylings

    def accept(self, visitor, *args):
        visitor.visit_styled_page(self, *args)


class Section(Node):

    def __init__(self, name, body):
        self.name = name
        self.body = body

    def accept(self, visitor, *args):
        visitor.visit_section(self, *args)


class StyledSection(Section):

    def __init__(self, name, body, stylings):
        super().__init__(name, body)
        self.stylings = stylings

    def accept(self, visitor, *args):
        visitor.visit_styled_section(self, *args)


class QuestionAnchor(Node):

    def __init__(self, name):
        self.name = name

    def accept(self, visitor, *args):
        visitor.visit_question_anchor(self, *args)


class StyledQuestionAnchor(QuestionAnchor):

    def __init__(self, name, attributes):
        super().__init__(name)
        self.styling = Styling(attributes)

    def accept(self, visitor, *args):
        visitor.visit_styled_question_anchor(self, *args)


class Styling(Node):

    def __init__(self, attributes):
        self.attributes = attributes

    def applicable(self, datatype):
        return True

    def modify_widget_constructor(self, node, widget_constructor):
        if self.applicable(node.datatype):
            for attribute in self.attributes:
                widget_constructor = attribute.modify_widget_constructor(
                    widget_constructor)
        return widget_constructor


class DefaultStyling(Styling):

    def __init__(self, datatype, attributes):
        super().__init__(attributes)
        self.datatype = datatype

    def applicable(self, datatype):
        return self.datatype == datatype


class Attribute(Node):
    def modify_widget_constructor(self, widget):
        return widget


class ColorAttribute(Attribute):

    def __init__(self, color):
        self.color = color

    def apply_on(self, widget):
        widget.set_color(self.color)


class FontSizeAttribute(Attribute):

    def __init__(self, size):
        self.size = size

    def apply_on(self, widget):
        widget.set_font_size(self.size)


class FontWeightAttribute(Attribute):

    def __init__(self, weight):
        self.weight = weight

    def apply_on(self, widget):
        widget.set_font_weight(self.weight)


class FontFamilyAttribute(Attribute):

    def __init__(self, family):
        self.family = family

    def apply_on(self, widget):
        widget.set_font_family(self.family)


class WidthAttribute(Attribute):

    def __init__(self, width):
        self.width = width

    def apply_on(self, widget):
        widget.set_width(self.width)


class WidgetTypeAttribute(Attribute):

    def __init__(self, widget_constructor):
        self.widget_constructor = widget_constructor

    def apply_on(self, _):
        pass

    def modify_widget_constructor(self, widget):
        return self.widget_constructor
