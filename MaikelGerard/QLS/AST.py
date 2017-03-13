# TODO: Decide to just import all nodes?
# from QL.AST import *
from QL.AST import Node, BlockNode, ExpressionNode, TypeNode
from QL.AST import BoolTypeNode, IntTypeNode, MoneyTypeNode, DecimalTypeNode
from QL.AST import StringTypeNode, DateTypeNode
from QL.AST import VarNode, LiteralNode
from QL.AST import BoolNode, IntNode, MoneyNode, DecimalNode, StringNode
from QL.AST import DateNode

# TODO: Make PageNode, SectionNode and DefaultNode decendants of StylesheetNode?


class StylesheetNode(Node):
    def __init__(self, name, body, line=0, col=0):
        super(StylesheetNode, self).__init__(line, col)
        self.name = name
        self.body = body

    def accept(self, visitor, *args):
        visitor.style_sheet_node(self, *args)


class PageNode(Node):
    def __init__(self, name, body, line=0, col=0):
        super(PageNode, self).__init__(line, col)
        self.name = name
        self.body = body

    def accept(self, visitor, *args):
        visitor.page_node(self, *args)


class PageWithDefaultsNode(PageNode):
    def __init__(self, name, body, defaults, line=0, col=0):
        super(PageWithDefaultsNode, self).__init__(name, body, line, col)
        self.defaults = defaults

    def accept(self, visitor, *args):
        visitor.page_with_defaults_node(self, *args)


class SectionNode(Node):
    def __init__(self, name, body, line=0, col=0):
        super(SectionNode, self).__init__(line, col)
        self.name = name
        self.body = body

    def accept(self, visitor, *args):
        visitor.section_node(self, *args)


class SectionWithDefaultsNode(PageNode):
    def __init__(self, name, body, defaults, line=0, col=0):
        super(SectionWithDefaultsNode, self).__init__(name, body, line, col)
        self.defaults = defaults

    def accept(self, visitor, *args):
        visitor.section_with_defaults_node(self, *args)


class QuestionNode(Node):
    def __init__(self, name, line=0, col=0):
        super(QuestionNode, self).__init__(line, col)
        self.name = name

    def accept(self, visitor, *args):
        visitor.question_node(self, *args)


class WidgetQuestionNode(QuestionNode):
    def __init__(self, name, widget_type, line=0, col=0):
        super(WidgetQuestionNode, self).__init__(name, line, col)
        self.type = widget_type

    def accept(self, visitor, *args):
        visitor.widget_question_node(self, *args)


class WidgetNode(Node):
    def __init__(self, line=0, col=0):
        super(WidgetNode, self).__init__(line, col)


class SliderNode(WidgetNode):
    def __init__(self, line=0, col=0):
        super(SliderNode, self).__init__(line, col)

    @staticmethod
    def is_compatible_type(literal_type):
        return literal_type == IntTypeNode()

    def accept(self, visitor, *args):
        visitor.slider_node(self, *args)


class SpinboxNode(WidgetNode):
    def __init__(self, line=0, col=0):
        super(SpinboxNode, self).__init__(line, col)

    @staticmethod
    def is_compatible_type(literal_type):
        return literal_type == IntTypeNode()

    def accept(self, visitor, *args):
        return visitor.spinbox_node(self, *args)


class TextNode(WidgetNode):
    def __init__(self, line=0, col=0):
        super(TextNode, self).__init__(line, col)

    @staticmethod
    def is_compatible_type(literal_type):
        return literal_type == StringTypeNode()

    def accept(self, visitor, *args):
        return visitor.text_node(self, *args)


class NumericNode(WidgetNode):
    def __init__(self, line=0, col=0):
        super(NumericNode, self).__init__(line, col)

    @staticmethod
    def is_compatible_type(literal_type):
        return literal_type in [IntTypeNode(), MoneyTypeNode(),
                                DecimalTypeNode()]

    def accept(self, visitor, *args):
        return visitor.text_node(self, *args)


class RadioNode(WidgetNode):
    def __init__(self, line=0, col=0):
        super(RadioNode, self).__init__(line, col)

    @staticmethod
    def is_compatible_type(literal_type):
        return literal_type == BoolTypeNode()

    def accept(self, visitor, *args):
        return visitor.radio_node(self, *args)


class CheckboxNode(WidgetNode):
    def __init__(self, line=0, col=0):
        super(CheckboxNode, self).__init__(line, col)

    @staticmethod
    def is_compatible_type(literal_type):
        return literal_type == BoolTypeNode()

    def accept(self, visitor, *args):
        return visitor.checkbox_node(self, *args)


class DropdownNode(WidgetNode):
    def __init__(self, line=0, col=0):
        super(DropdownNode, self).__init__(line, col)

    @staticmethod
    def is_compatible_type(literal_type):
        # TODO: Not sure if we want date in here?
        return literal_type in [BoolTypeNode(), StringTypeNode(), IntTypeNode(),
                                DecimalTypeNode()]

    def accept(self, visitor, *args):
        return visitor.dropdown_node(self, *args)


class DefaultNode(Node):
    def __init__(self, var_type, line=0, col=0):
        super(DefaultNode, self).__init__(line, col)
        self.type = var_type

    def accept(self, visitor, *args):
        return visitor.default_node(self, *args)


class DefaultWithPropsNode(DefaultNode):
    def __init__(self, var_type, props, line, col):
        super(DefaultWithPropsNode, self).__init__(var_type, line, col)
        self.props = props

    def accept(self, visitor, *args):
        return visitor.default_with_props_node(self, *args)


class PropertyNode(Node):
    def __init__(self, value, line=0, col=0):
        super(PropertyNode, self).__init__(line, col)
        self.val = value


class WidthNode(PropertyNode):
    def __init__(self, value, line=0, col=0):
        super(WidthNode, self).__init__(value, line, col)

    def accept(self, visitor, *args):
        return visitor.width_node(self, *args)


class HeightNode(PropertyNode):
    def __init__(self, value, line=0, col=0):
        super(HeightNode, self).__init__(value, line, col)

    def accept(self, visitor, *args):
        return visitor.height_node(self, *args)


class FontNode(PropertyNode):
    def __init__(self, value, line=0, col=0):
        super(FontNode, self).__init__(value, line, col)

    def accept(self, visitor, *args):
        return visitor.font_node(self, *args)


class FontSizeNode(PropertyNode):
    def __init__(self, value, line=0, col=0):
        super(FontSizeNode, self).__init__(value, line, col)

    def accept(self, visitor, *args):
        return visitor.fontsize_node(self, *args)


class ColorNode(PropertyNode):
    def __init__(self, value, line=0, col=0):
        super(ColorNode, self).__init__(value, line, col)

    def accept(self, visitor, *args):
        return visitor.color_node(self, *args)
