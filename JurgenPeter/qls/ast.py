class Layout:
    def __init__(self, name, body):
        self.name = name
        self.body = body

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__


class StyledLayout(Layout):
    def __init__(self, name, body, styling):
        super().__init__(name, body)
        self.styling = styling


class Page:
    def __init__(self, name, body):
        self.name = name
        self.body = body

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__


class StyledPage(Page):
    def __init__(self, name, body, styling):
        super().__init__(name, body)
        self.styling = styling


class Section:
    def __init__(self, name, body):
        self.name = name
        self.body = body

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__


class StyledSection(Section):
    def __init__(self, name, body, styling):
        super().__init__(name, body)
        self.styling = styling


class QuestionAnchor:
    def __init__(self, name):
        self.name = name

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__


class StyledQuestionAnchor(QuestionAnchor):
    def __init__(self, name, styling):
        super().__init__(name)
        self.styling = styling


class DefaultStyling:
    def __init__(self, datatype, styling):
        self.datatype = datatype
        self.styling = styling

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__


class ColorAttribute:
    def __init__(self, color):
        self.color = color

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__

    def apply(self, widget):
        widget.set_color(self.color)


class FontSizeAttribute:
    def __init__(self, size):
        self.size = size

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__

    def apply(self, widget):
        widget.set_font_size(self.size)


class FontWeightAttribute:
    def __init__(self, weight):
        self.weight = weight

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__

    def apply(self, widget):
        widget.set_font_weight(self.weight)


class FontFamilyAttribute:
    def __init__(self, family):
        self.family = family

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__

    def apply(self, widget):
        widget.set_font_family(self.family)


class WidthAttribute:
    def __init__(self, width):
        self.width = width

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__

    def apply(self, widget):
        widget.set_width(self.width)


class WidgetTypeAttribute:
    def __init__(self, widget_constructor):
        self.widget_constructor = widget_constructor

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__
