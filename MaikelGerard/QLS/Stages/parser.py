import datetime
import decimal

import pyparsing as pp
from QLS import AST


class Parser(object):
    # Define the tokens used for parsing the input.
    LIT = {
        "IS": pp.Literal("=").suppress(),
        "COLON": pp.Literal(":").suppress(),
        "L_CURLY": pp.Literal("{").suppress(),
        "R_CURLY": pp.Literal("}").suppress(),
        "L_BRACE": pp.Literal("(").suppress(),
        "R_BRACE": pp.Literal(")").suppress()
    }
    KW = {
        "STYLESHEET": pp.Keyword("stylesheet").suppress(),
        "PAGE": pp.Keyword("page").suppress(),
        "SECTION": pp.Keyword("section").suppress(),
        "DEFAULT": pp.Keyword("default").suppress(),
        "QUESTION": pp.Keyword("question").suppress(),
        "WIDGET": pp.Keyword("widget").suppress()
    }
    PROP_TYPES = pp.oneOf("width height font fontsize color")
    WIDGET_TYPES = pp.oneOf("slider spinbox text radio checkbox dropdown")

    def __init__(self):
        # Enable caching of parsing logic.
        pp.ParserElement.enablePackrat()
        self.type_names = self.get_type_names()
        self.var_types = self.get_var_types()

        # Create the grammar incrementally to simplify unit test creation.
        self.widget = self.define_widget()
        self.property = self.define_property()
        self.default = self.define_default()
        self.question = self.define_question()
        self.section = self.define_section()
        self.page = self.define_page()
        self.grammar = self.define_grammar()

    def get_type_names(self):
        def create_type_node(keyword, ast_class):
            """
            Use the 'create_node' function to create a certain AST type node.
            The type name token is not required, and therefore suppressed.
            """
            return pp.Keyword(keyword).suppress().addParseAction(
                self.create_node(ast_class))

        return (
            create_type_node("boolean", AST.BoolTypeNode) |
            create_type_node("integer", AST.IntTypeNode) |
            create_type_node("money", AST.MoneyTypeNode) |
            create_type_node("decimal", AST.DecimalTypeNode) |
            create_type_node("string", AST.StringTypeNode) |
            create_type_node("date", AST.DateTypeNode)
        )

    def get_var_types(self):
        def create_decimal(src, loc, tokens):
            del src, loc
            return decimal.Decimal(tokens[0])

        def create_date(src, loc, tokens):
            del src, loc
            return datetime.datetime.strptime(tokens[0], "%d.%m.%Y").date()

        types = {
            "BOOLEAN": (pp.Keyword("true").setParseAction(lambda _: True) |
                        pp.Keyword("false").setParseAction(lambda _: False)),
            "INTEGER": pp.Word(pp.nums),
            "VARIABLE": pp.Word(pp.alphas, pp.alphanums + "_"),
            "DECIMAL": pp.Regex("(\d+\.\d*)|(\d*\.\d+)"),
            "DATE": pp.Regex("([0][1-9])|([1-3][0-9])\.[0-9]{2}\.[0-9]{4}"),
            "STRING": pp.quotedString.setParseAction(pp.removeQuotes),
            "COLOR": pp.Regex("#\d{6}")
        }

        # Extend parse logic to create AST nodes. TODO: Add color node.
        types["BOOLEAN"].setParseAction(self.create_node(AST.BoolNode))
        types["INTEGER"].setParseAction(
            create_decimal, self.create_node(AST.IntNode))
        types["VARIABLE"].setParseAction(self.create_node(AST.VarNode))
        types["DECIMAL"].setParseAction(
            create_decimal, self.create_node(AST.DecimalNode))
        types["DATE"].setParseAction(
            create_date, self.create_node(AST.DateNode))
        types["STRING"].setParseAction(self.create_node(AST.StringNode))

        return types

    def create_node(self, ast_class):
        """
        Create a certain AST node. The tokens that are passed by the
        PyParsing library and the location information are used as arguments.
        """
        def create_args(src, loc, token):
            line, col = self.get_line_loc_info(src, loc)
            args = token.asList() + [line, col]
            return ast_class(*args)
        return create_args

    @staticmethod
    def get_line_loc_info(src, loc):
        col = pp.col(loc, src)
        line = pp.lineno(loc, src)
        return line, col

    def round_embrace(self, arg):
        return self.LIT["L_BRACE"] + arg + self.LIT["R_BRACE"]

    def curly_embrace(self, arg):
        return self.LIT["L_CURLY"] + arg + self.LIT["R_CURLY"]

    def define_widget(self):
        return self.KW["WIDGET"] + self.WIDGET_TYPES

    def define_property(self):
        # Extend the parsing logic to convert the variables to AST nodes.
        var_types = (
            self.var_types["BOOLEAN"] | self.var_types["VARIABLE"] |
            self.var_types["DATE"] | self.var_types["DECIMAL"] |
            self.var_types["INTEGER"] | self.var_types["STRING"] |
            self.var_types["COLOR"]
        )
        return self.PROP_TYPES + self.LIT["COLON"] + var_types

    def define_default(self):
        with_props = self.KW["DEFAULT"] + self.type_names + self.curly_embrace(
            pp.ZeroOrMore(self.property) + self.widget
        )
        without_props = self.KW["DEFAULT"] + self.type_names + self.widget

        return with_props | without_props

    def define_question(self):
        return self.KW["QUESTION"] + self.var_types["VARIABLE"] +\
               pp.Optional(self.widget)

    def define_section(self):
        section = pp.Forward()
        header = self.KW["SECTION"] + self.var_types["STRING"]
        body = pp.OneOrMore(self.question | self.default | section)

        section << header + self.curly_embrace(body)
        return section

    def define_page(self):
        header = self.KW["PAGE"] + self.var_types["VARIABLE"]
        body = pp.OneOrMore(self.section | self.default)

        return header + self.curly_embrace(body)

    def define_grammar(self):
        header = self.KW["STYLESHEET"] + self.var_types["VARIABLE"]
        body = pp.OneOrMore(self.page)

        return header + self.curly_embrace(body)

    def parse(self, input_str):
        #for _ in self.grammar.parseString(input_str, parseAll=True):
        #    print(_)
        return self.grammar.parseString(input_str, parseAll=True)[0]
