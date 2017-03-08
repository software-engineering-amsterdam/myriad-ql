from QL.stages.parser import Parser as QLParser
from QLS import AST

import pyparsing as pp


class Parser(QLParser):
    # Define the tokens used for parsing the QLS input.
    STYLESHEET = pp.Keyword("stylesheet").suppress()
    PAGE = pp.Keyword("page").suppress()
    SECTION = pp.Keyword("section").suppress()
    DEFAULT = pp.Keyword("default").suppress()
    QUESTION = pp.Keyword("question").suppress()
    WIDGET = pp.Keyword("widget").suppress()

    # Add parsing logic for the color value literal.
    COLOR_VALUE = pp.Regex("#[a-z\d]{6}")

    # Define the style property names and widget type names.
    WIDTH = pp.Keyword("width").suppress()
    HEIGHT = pp.Keyword("height").suppress()
    FONT = pp.Keyword("font").suppress()
    FONTSIZE = pp.Keyword("fontsize").suppress()
    COLOR = pp.Keyword("color").suppress()

    SLIDER = pp.Keyword("slider").suppress()
    SPINBOX = pp.Keyword("spinbox").suppress()
    TEXT = pp.Keyword("text").suppress()
    RADIO = pp.Keyword("radio").suppress()
    CHECKBOX = pp.Keyword("checkbox").suppress()
    DROPDOWN = pp.Keyword("dropdown").suppress()

    def __init__(self):
        # Enable caching of parsing logic.
        pp.ParserElement.enablePackrat()

        # Add parse actions to create AST nodes from parse results.
        self.parse_literal_type_nodes()
        self.parse_literal_nodes()
        self.parse_property_type_nodes()
        self.parse_widget_type_nodes()

        self.TYPE_NAMES = (self.BOOLEAN_TYPE ^ self.INTEGER_TYPE ^
                           self.MONEY_TYPE ^ self.DECIMAL_TYPE ^
                           self.STRING_TYPE ^ self.DATE_TYPE)
        self.TYPES = (self.BOOLEAN ^ self.INTEGER ^ self.DECIMAL ^ self.DATE ^
                      self.STRING ^ self.VARIABLE ^ self.COLOR_VALUE)
        self.PROPERTY_TYPES = (self.WIDTH ^ self.HEIGHT ^ self.FONT ^
                               self.FONTSIZE ^ self.COLOR)
        self.WIDGET_TYPES = (self.SLIDER ^ self.SPINBOX ^ self.TEXT ^
                             self.RADIO ^ self.CHECKBOX ^ self.DROPDOWN)

        # Create the grammar incrementally to simplify unit test creation.
        self.widget_type = self.define_widget_type()
        self.property_type = self.define_property_type()
        self.default = self.define_default()
        self.question = self.define_question()
        self.section = self.define_section()
        self.page = self.define_page()
        self.grammar = self.define_grammar()

    @staticmethod
    def create_rgb_tuple(src, loc, tokens):
        hex_string = tokens[0][1:]
        r = int(hex_string[0:2], 16)
        g = int(hex_string[2:4], 16)
        b = int(hex_string[4:6], 16)
        return r, g, b

    def parse_literal_nodes(self):
        # Add parse action to all the 'base' literal nodes.
        super(Parser, self).parse_literal_nodes()

        self.COLOR_VALUE.setParseAction(self.create_node(AST.ColorNode))

    def parse_property_type_nodes(self):
        self.WIDTH.setParseAction(
            self.create_int, self.create_node(AST.WidthNode))
        self.HEIGHT.setParseAction(
            self.create_int, self.create_node(AST.HeightNode))
        self.FONT.setParseAction(self.create_node(AST.FontNode))
        self.FONTSIZE.setParseAction(
            self.create_int, self.create_node(AST.FontSizeNode))
        self.COLOR.setParseAction(
            self.create_rgb_tuple, self.create_node(AST.ColorNode))

    def parse_widget_type_nodes(self):
        self.SLIDER.setParseAction(self.create_node(AST.SliderNode))
        self.SPINBOX.setParseAction(self.create_node(AST.SpinboxNode))
        self.TEXT.setParseAction(self.create_node(AST.TextNode))
        self.RADIO.setParseAction(self.create_node(AST.RadioNode))
        self.CHECKBOX.setParseAction(self.create_node(AST.CheckboxNode))
        self.DROPDOWN.setParseAction(self.create_node(AST.DropdownNode))

    def define_widget_type(self):
        return self.WIDGET + self.WIDGET_TYPES

    def define_property_type(self):
        return self.PROPERTY_TYPES + self.COLON + self.TYPES

    def define_default(self):
        header = self.DEFAULT + self.TYPE_NAMES
        body = pp.Group(
            pp.OneOrMore(self.property_type) + self.widget_type
        ).setParseAction(AST.BlockNode)

        with_props = header + self.curly_embrace(body)
        without_props = header + self.widget_type

        default = with_props | without_props
        return default.setParseAction(self.create_node(AST.DefaultNode))

    def define_question(self):
        question = self.QUESTION + self.VARIABLE + pp.Optional(self.widget_type)
        return question.setParseAction(self.create_node(AST.QuestionNode))

    def define_section(self):
        section = pp.Forward()
        header = self.SECTION + self.STRING
        body = pp.Group(
            pp.OneOrMore(self.question | self.default | section)
        ).setParseAction(AST.BlockNode)

        section << header + self.curly_embrace(body)
        return section.setParseAction(self.create_node(AST.SectionNode))

    def define_page(self):
        header = self.PAGE + self.VARIABLE
        body = pp.Group(
            pp.OneOrMore(self.section | self.default)
        ).setParseAction(AST.BlockNode)

        page = header + self.curly_embrace(body)
        return page.setParseAction(self.create_node(AST.PageNode))

    def define_grammar(self):
        header = self.STYLESHEET + self.VARIABLE
        body = pp.Group(pp.OneOrMore(self.page)).setParseAction(AST.BlockNode)

        stylesheet = header + self.curly_embrace(body)
        return stylesheet.setParseAction(self.create_node(AST.StylesheetNode))

    def parse(self, input_str):
        return self.grammar.parseString(input_str, parseAll=True)[0].__str__()
