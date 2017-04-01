from QL.stages.parser import Parser as QLParser
from QL import AST as QLAST
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

    # Define the style properties.
    WIDTH = pp.Keyword("width").suppress()
    HEIGHT = pp.Keyword("height").suppress()
    FONT = pp.Keyword("font").suppress()
    FONTSIZE = pp.Keyword("fontsize").suppress()
    COLOR = pp.Keyword("color").suppress()

    PROP_WIDTH = WIDTH + QLParser.COLON + QLParser.INTEGER
    PROP_HEIGHT = HEIGHT + QLParser.COLON + QLParser.INTEGER
    PROP_FONT = FONT + QLParser.COLON + QLParser.QUOTED_STRING
    PROP_FONTSIZE = FONTSIZE + QLParser.COLON + QLParser.INTEGER
    PROP_COLOR = COLOR + QLParser.COLON + COLOR_VALUE

    # Define the widget type names.
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
        self.parse_property_nodes()

        self.LITERAL_TYPES = (self.BOOLEAN_TYPE ^ self.INTEGER_TYPE ^
                              self.DECIMAL_TYPE ^ self.STRING_TYPE ^
                              self.DATE_TYPE)
        self.WIDGET_TYPES = (self.SLIDER ^ self.SPINBOX ^ self.TEXT ^
                             self.NUMERIC ^ self.RADIO ^ self.CHECKBOX ^
                             self.DROPDOWN)
        self.PROPERTIES = (self.PROP_WIDTH ^ self.PROP_HEIGHT ^ self.PROP_FONT ^
                           self.PROP_FONTSIZE ^ self.PROP_COLOR)

        # Create the grammar incrementally to simplify unit test creation.
        self.widget_type = self.WIDGET + self.WIDGET_TYPES
        self.property_type = self.PROPERTIES
        self.default = self.define_default()
        self.question = self.define_question()
        self.section = self.define_section()
        self.page = self.define_page()
        self.grammar = self.define_grammar()

    def parse_literal_nodes(self):
        self.INTEGER.setParseAction(self.create_int)
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

    def parse_property_nodes(self):
        self.PROP_WIDTH.setParseAction(self.create_node(AST.WidthNode))
        self.PROP_HEIGHT.setParseAction(self.create_node(AST.HeightNode))
        self.PROP_FONT.setParseAction(self.create_node(AST.FontNode))
        self.PROP_FONTSIZE.setParseAction(self.create_node(AST.FontSizeNode))
        self.PROP_COLOR.setParseAction(self.create_node(AST.ColorNode))

    def define_default(self):
        header = self.DEFAULT + self.LITERAL_TYPES
        props = pp.Group(
            pp.OneOrMore(self.property_type)
        ).setParseAction(self.create_node(QLAST.BlockNode))

        with_props = (header + self.curly_embrace(props + self.widget_type))\
            .setParseAction(self.create_node(AST.DefaultWithPropsNode))
        without_props = (header + self.widget_type)\
            .setParseAction(self.create_node(AST.DefaultNode))

        return with_props ^ without_props

    def define_question(self):
        def create_question():
            return self.QUESTION + self.NAME
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

        header = self.SECTION + self.QUOTED_STRING
        body = pp.Group(
            pp.OneOrMore(self.question ^ with_defaults ^ without_defaults)
        ).setParseAction(self.create_node(QLAST.BlockNode))
        defaults = pp.Group(
            pp.OneOrMore(self.default)
        ).setParseAction(self.create_node(QLAST.BlockNode))

        with_defaults << (header + self.curly_embrace(body + defaults))\
            .addParseAction(self.create_node(AST.SectionWithDefaultsNode))
        without_defaults << (header + self.curly_embrace(body))\
            .addParseAction(self.create_node(AST.SectionNode))

        return with_defaults ^ without_defaults

    def define_page(self):
        header = self.PAGE + self.NAME
        sections = pp.Group(
            pp.OneOrMore(self.section)
        ).setParseAction(self.create_node(QLAST.BlockNode))
        defaults = pp.Group(
            pp.OneOrMore(self.default)
        ).setParseAction(self.create_node(QLAST.BlockNode))

        with_defaults = (header + self.curly_embrace(sections + defaults))\
            .setParseAction(self.create_node(AST.PageWithDefaultsNode))
        without_defaults = (header + self.curly_embrace(sections))\
            .setParseAction(self.create_node(AST.PageNode))

        return with_defaults ^ without_defaults

    def define_grammar(self):
        header = self.STYLESHEET + self.NAME
        body = pp.Group(
            pp.OneOrMore(self.page)
        ).setParseAction(self.create_node(QLAST.BlockNode))

        stylesheet = header + self.curly_embrace(body)
        return stylesheet.setParseAction(self.create_node(AST.StylesheetNode))

    def parse(self, input_str):
        return self.grammar.parseString(input_str, parseAll=True)[0]
