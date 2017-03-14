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
    COLOR_VALUE = pp.Regex("#[a-fA-F\d]{6}")

    # Define the style property names and widget type names.
    WIDTH = pp.Keyword("width").suppress()
    HEIGHT = pp.Keyword("height").suppress()
    FONT = pp.Keyword("font").suppress()
    FONTSIZE = pp.Keyword("fontsize").suppress()
    COLOR = pp.Keyword("color").suppress()

    SLIDER = pp.Keyword("slider").suppress()
    SPINBOX = pp.Keyword("spinbox").suppress()
    TEXT = pp.Keyword("text").suppress()
    NUMERIC = pp.Keyword("numeric").suppress()
    RADIO = pp.Keyword("radio").suppress()
    CHECKBOX = pp.Keyword("checkbox").suppress()
    DROPDOWN = pp.Keyword("dropdown").suppress()

    def __init__(self):
        # Enable caching of parsing logic.
        pp.ParserElement.enablePackrat()

        # Add parse actions to create AST nodes from parse results.
        self.parse_literal_type_nodes()
        self.parse_literal_nodes()
        self.parse_widget_type_nodes()

        self.TYPE_NAMES = (self.BOOLEAN_TYPE ^ self.INTEGER_TYPE ^
                           self.MONEY_TYPE ^ self.DECIMAL_TYPE ^
                           self.STRING_TYPE ^ self.DATE_TYPE)
        self.TYPES = (self.BOOLEAN ^ self.INTEGER ^ self.DECIMAL ^ self.DATE ^
                      self.STRING ^ self.VARIABLE ^ self.COLOR_VALUE)
        self.WIDGET_TYPES = (self.SLIDER ^ self.SPINBOX ^ self.TEXT ^
                             self.NUMERIC ^ self.RADIO ^ self.CHECKBOX ^
                             self.DROPDOWN)

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
        self.INTEGER.setParseAction(self.create_int)
        self.COLOR_VALUE.setParseAction(self.create_rgb_tuple)
        self.DECIMAL.setParseAction(self.create_decimal)
        self.STRING.setParseAction(pp.removeQuotes)
        self.DATE.setParseAction(self.create_date)

    def parse_widget_type_nodes(self):
        self.SLIDER.setParseAction(self.create_node(AST.SliderNode))
        self.SPINBOX.setParseAction(self.create_node(AST.SpinboxNode))
        self.TEXT.setParseAction(self.create_node(AST.TextNode))
        self.NUMERIC.setParseAction(self.create_node(AST.NumericNode))
        self.RADIO.setParseAction(self.create_node(AST.RadioNode))
        self.CHECKBOX.setParseAction(self.create_node(AST.CheckboxNode))
        self.DROPDOWN.setParseAction(self.create_node(AST.DropdownNode))

    def define_widget_type(self):
        return self.WIDGET + self.WIDGET_TYPES

    def define_property_type(self):
        PROP_WIDTH = self.WIDTH + self.COLON + self.INTEGER
        PROP_WIDTH.setParseAction(self.create_node(AST.WidthNode))
        PROP_HEIGHT = self.HEIGHT + self.COLON + self.INTEGER
        PROP_HEIGHT.setParseAction(self.create_node(AST.HeightNode))
        PROP_FONT = self.FONT + self.COLON + self.STRING
        PROP_FONT.setParseAction(self.create_node(AST.FontNode))
        PROP_FONTSIZE = self.FONTSIZE + self.COLON + self.INTEGER
        PROP_FONTSIZE.setParseAction(self.create_node(AST.FontSizeNode))
        PROP_COLOR = self.COLOR + self.COLON + self.COLOR_VALUE
        PROP_COLOR.setParseAction(self.create_node(AST.ColorNode))

        return PROP_WIDTH ^ PROP_HEIGHT ^ PROP_FONT ^ PROP_FONTSIZE ^ PROP_COLOR

    def define_default(self):
        header = self.DEFAULT + self.TYPE_NAMES
        props = pp.Group(
            pp.OneOrMore(self.property_type)
        ).setParseAction(self.create_node(AST.BlockNode))

        with_props = (header + self.curly_embrace(props + self.widget_type))\
            .setParseAction(self.create_node(AST.DefaultWithPropsNode))
        without_props = (header + self.widget_type)\
            .setParseAction(self.create_node(AST.DefaultNode))

        return with_props ^ without_props

    def define_question(self):
        def create_question():
            return self.QUESTION + self.VARIABLE
        widget_question = (create_question() + self.widget_type).setParseAction(
            self.create_node(AST.WidgetQuestionNode)
        )
        question = create_question().setParseAction(
            self.create_node(AST.QuestionNode)
        )
        return question ^ widget_question

    def define_section(self):
        with_defaults = pp.Forward()
        without_defaults = pp.Forward()

        header = self.SECTION + self.STRING
        body = pp.Group(
            pp.OneOrMore(self.question ^ with_defaults ^ without_defaults)
        ).setParseAction(self.create_node(AST.BlockNode))
        defaults = pp.Group(
            pp.OneOrMore(self.default)
        ).setParseAction(self.create_node(AST.BlockNode))

        with_defaults << (header + self.curly_embrace(body + defaults))\
            .addParseAction(self.create_node(AST.SectionWithDefaultsNode))
        without_defaults << (header + self.curly_embrace(body))\
            .addParseAction(self.create_node(AST.SectionNode))

        return with_defaults ^ without_defaults

    def define_page(self):
        header = self.PAGE + self.VARIABLE
        sections = pp.Group(
            pp.OneOrMore(self.section)
        ).setParseAction(self.create_node(AST.BlockNode))
        defaults = pp.Group(
            pp.OneOrMore(self.default)
        ).setParseAction(self.create_node(AST.BlockNode))

        with_defaults = (header + self.curly_embrace(sections + defaults))\
            .setParseAction(self.create_node(AST.PageWithDefaultsNode))
        without_defaults = (header + self.curly_embrace(sections))\
            .setParseAction(self.create_node(AST.PageNode))

        return with_defaults ^ without_defaults

    def define_grammar(self):
        header = self.STYLESHEET + self.VARIABLE
        body = pp.Group(
            pp.OneOrMore(self.page)
        ).setParseAction(self.create_node(AST.BlockNode))

        stylesheet = header + self.curly_embrace(body)
        return stylesheet.setParseAction(self.create_node(AST.StylesheetNode))

    def parse(self, input_str):
        return self.grammar.parseString(input_str, parseAll=True)[0]
