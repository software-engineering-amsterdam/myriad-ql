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
        self.body.accept(visitor, *args)


class PageNode(Node):
    def __init__(self, name, body, line=0, col=0):
        super(PageNode, self).__init__(line, col)
        self.name = name
        self.body = body

    def accept(self, visitor, *args):
        self.body.accept(visitor, *args)


class SectionNode(Node):
    def __init__(self, name, body, line=0, col=0):
        super(SectionNode, self).__init__(line, col)
        self.name = name
        self.body = body

    def accept(self, visitor, *args):
        self.body.accept(visitor, *args)


class QuestionNode(Node):
    def __init__(self, name, widget_type=None, line=0, col=0):
        super(QuestionNode, self).__init__(line, col)
        self.name = name
        self.type = widget_type

    def accept(self, visitor, *args):
        visitor.question_node(self, *args)


class WidgetNode(Node):
    def __init__(self, line=0, col=0):
        super(WidgetNode, self).__init__(line, col)


class SliderNode(WidgetNode):
    def __init__(self, line=0, col=0):
        super(SliderNode, self).__init__(line, col)


class SpinboxNode(WidgetNode):
    def __init__(self, line=0, col=0):
        super(SpinboxNode, self).__init__(line, col)


class TextNode(WidgetNode):
    def __init__(self, line=0, col=0):
        super(TextNode, self).__init__(line, col)


class RadioNode(WidgetNode):
    def __init__(self, line=0, col=0):
        super(RadioNode, self).__init__(line, col)


class CheckboxNode(WidgetNode):
    def __init__(self, line=0, col=0):
        super(CheckboxNode, self).__init__(line, col)


class DropdownNode(WidgetNode):
    def __init__(self, line=0, col=0):
        super(DropdownNode, self).__init__(line, col)


class DefaultNode(Node):
    def __init__(self, var_type, body, line=0, col=0):
        super(DefaultNode, self).__init__(line, col)
        self.type = var_type
        self.body = body

    def accept(self, visitor, *args):
        self.body.accept(visitor, *args)


class PropertyNode(Node):
    def __init__(self, value, line=0, col=0):
        super(PropertyNode, self).__init__(line, col)
        self.val = value


class WidthNode(PropertyNode):
    def __init__(self, value, line=0, col=0):
        super(WidthNode, self).__init__(value, line, col)

    def accept(self, visitor, *args):
        visitor.width_node(self, *args)


class HeightNode(PropertyNode):
    def __init__(self, value, line=0, col=0):
        super(HeightNode, self).__init__(value, line, col)

    def accept(self, visitor, *args):
        visitor.height_node(self, *args)


class FontNode(PropertyNode):
    def __init__(self, value, line=0, col=0):
        super(FontNode, self).__init__(value, line, col)

    def accept(self, visitor, *args):
        visitor.font_node(self, *args)


class FontSizeNode(PropertyNode):
    def __init__(self, value, line=0, col=0):
        super(FontSizeNode, self).__init__(value, line, col)

    def accept(self, visitor, *args):
        visitor.font_size_node(self, *args)


class ColorNode(PropertyNode):
    def __init__(self, value, line=0, col=0):
        super(ColorNode, self).__init__(value, line, col)

    def accept(self, visitor, *args):
        visitor.color_node(self, *args)
