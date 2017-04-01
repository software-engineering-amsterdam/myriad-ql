import decimal
import datetime
import pyparsing as pp

from QL import AST


class Parser(object):
    IS = pp.Literal("=").suppress()
    COLON = pp.Literal(":").suppress()
    L_CURLY = pp.Literal("{").suppress()
    R_CURLY = pp.Literal("}").suppress()
    L_BRACE = pp.Literal("(").suppress()
    R_BRACE = pp.Literal(")").suppress()

    FORM = pp.Keyword("form").suppress()
    IF = pp.Keyword("if").suppress()
    ELSE = pp.Keyword("else").suppress()

    BOOLEAN_TYPE = pp.Keyword("boolean").suppress()
    INTEGER_TYPE = pp.Keyword("integer").suppress()
    DECIMAL_TYPE = pp.Keyword("decimal").suppress()
    STRING_TYPE = pp.Keyword("string").suppress()
    DATE_TYPE = pp.Keyword("date").suppress()

    BOOLEAN = (pp.Keyword("true").setParseAction(lambda _: True) ^
               pp.Keyword("false").setParseAction(lambda _: False))
    INTEGER = pp.Word(pp.nums)
    VARIABLE = pp.Word(pp.alphas, pp.alphanums + "_")
    DECIMAL = pp.Regex("(\d+\.\d*)|(\d*\.\d+)")
    STRING = pp.QuotedString('"')
    DATE = pp.Regex("([0][1-9])|([1-3][0-9])-[0-9]{2}-[0-9]{4}")

    # Special literal types that are not parsed into AST nodes.
    NAME = pp.Word(pp.alphas, pp.alphanums + "_")
    QUOTED_STRING = pp.QuotedString('"')

    def __init__(self):
        # Enable caching of parsing logic.
        pp.ParserElement.enablePackrat()

        # Add parse actions to create AST nodes from parse results.
        self.parse_literal_type_nodes()
        self.parse_literal_nodes()
        self.TYPE_NAMES = (self.BOOLEAN_TYPE ^ self.INTEGER_TYPE ^
                           self.DECIMAL_TYPE ^ self.STRING_TYPE ^
                           self.DATE_TYPE)
        self.TYPES = (self.BOOLEAN ^ self.INTEGER ^ self.DECIMAL ^
                      self.STRING ^ self.DATE ^ self.VARIABLE)

        # Create the grammar incrementally to simplify unit test creation.
        self.expression = self.define_expression()
        self.question = self.define_question()
        self.block = pp.Forward()
        self.conditional = self.define_conditional()

        self.grammar = self.define_grammar()

    def parse_literal_type_nodes(self):
        self.BOOLEAN_TYPE.setParseAction(self.create_node(AST.BoolTypeNode))
        self.INTEGER_TYPE.setParseAction(self.create_node(AST.IntTypeNode))
        self.DECIMAL_TYPE.setParseAction(self.create_node(AST.DecimalTypeNode))
        self.STRING_TYPE.setParseAction(self.create_node(AST.StringTypeNode))
        self.DATE_TYPE.setParseAction(self.create_node(AST.DateTypeNode))

    def parse_literal_nodes(self):
        self.BOOLEAN.setParseAction(self.create_node(AST.BoolNode))
        self.INTEGER.setParseAction(
            self.create_int, self.create_node(AST.IntNode))
        self.VARIABLE.setParseAction(self.create_node(AST.VarNode))
        self.DECIMAL.setParseAction(
            self.create_decimal, self.create_node(AST.DecimalNode))
        self.STRING.setParseAction(self.create_node(AST.StringNode))
        self.DATE.setParseAction(
            self.create_date, self.create_node(AST.DateNode))

    @staticmethod
    def create_int(src, loc, tokens):
        return int(tokens[0])

    @staticmethod
    def create_decimal(src, loc, tokens):
        return decimal.Decimal(tokens[0])

    @staticmethod
    def create_date(src, loc, tokens):
        return datetime.datetime.strptime(tokens[0], "%d-%m-%Y").date()

    def create_node(self, ast_class):
        # Create a certain AST node. The tokens that are passed by the
        # PyParsing library and the location information are used as arguments.
        def create_args(src, loc, tokens):
            line, col = self.get_line_loc_info(src, loc)
            args = tokens.asList()
            kwargs = {"line": line, "col": col}
            return ast_class(*args, **kwargs)
        return create_args

    @staticmethod
    def get_line_loc_info(src, loc):
        col = pp.col(loc, src)
        line = pp.lineno(loc, src)
        return line, col

    def define_expression(self):
        def create_operators(opp_list):
            opp, ast_class = opp_list[0]
            operator = create_operator(opp, ast_class)
            for (opp, ast_class) in opp_list[1:]:
                operator ^= create_operator(opp, ast_class)
            return operator

        def create_operator(opp, ast_class):
            return pp.Literal(opp).setParseAction(lambda _: ast_class)

        # Define all expression operators and their corresponding AST node.
        unary_ops = create_operators([
            ('!', AST.NegNode), ('-', AST.MinNode), ('+', AST.PlusNode)])
        arithmetic_level1 = create_operators([
            ('*', AST.MulNode), ('/', AST.DivNode)])
        arithmetic_level2 = create_operators([
            ('+', AST.AddNode), ('-', AST.SubNode)])
        logical_level1 = create_operators([
            ('<=', AST.LTENode), ('<', AST.LTNode),
            ('>=', AST.GTENode), ('>', AST.GTNode)
        ])
        logical_level2 = create_operators([
            ('==', AST.EqNode), ('!=', AST.NeqNode)])
        infix_and = create_operator('&&', AST.AndNode)
        infix_or = create_operator('||', AST.OrNode)

        # Define the expression parser, including precedence.
        return pp.infixNotation(self.TYPES, [
            (unary_ops, 1, pp.opAssoc.RIGHT, self.create_monop_node),
            (arithmetic_level1, 2, pp.opAssoc.LEFT, self.create_binops),
            (arithmetic_level2, 2, pp.opAssoc.LEFT, self.create_binops),
            (logical_level1, 2, pp.opAssoc.LEFT, self.create_binops),
            (logical_level2, 2, pp.opAssoc.LEFT, self.create_binops),
            (infix_and, 2, pp.opAssoc.LEFT, self.create_binops),
            (infix_or, 2, pp.opAssoc.LEFT, self.create_binops),
        ])

    def define_question(self):
        def create_question_grammar():
            # Use this function to prevent double parseActions, as the question
            # grammar is re-used in the comp_question grammar definition.
            return self.QUOTED_STRING + self.NAME + \
                   self.COLON + self.TYPE_NAMES

        question = create_question_grammar()
        question.setParseAction(self.create_node(AST.QuestionNode))

        comp_question = create_question_grammar() + self.IS + \
            self.round_embrace(self.expression)
        comp_question.setParseAction(self.create_node(AST.ComputedQuestionNode))

        return comp_question ^ question

    def define_conditional(self):
        def create_if_grammar():
            # Again used to prevent double parseActions.
            return self.IF + self.round_embrace(self.expression) + \
                self.curly_embrace(self.block)

        if_cond = create_if_grammar()
        if_cond.setParseAction(self.create_node(AST.IfNode))

        if_else_cond = create_if_grammar() + self.ELSE + \
            self.curly_embrace(self.block)
        if_else_cond.setParseAction(self.create_node(AST.IfElseNode))

        return if_else_cond ^ if_cond

    def define_grammar(self):
        self.block << pp.Group(
            pp.OneOrMore(self.question ^ self.conditional)
        ).setParseAction(self.create_node(AST.BlockNode))

        form = self.FORM + self.NAME + self.curly_embrace(self.block)
        return form.addParseAction(self.create_node(AST.FormNode))

    def round_embrace(self, arg):
        return self.L_BRACE + arg + self.R_BRACE

    def curly_embrace(self, arg):
        return self.L_CURLY + arg + self.R_CURLY

    def create_monop_node(self, src, loc, token):
        monop = token[0]
        ast_class = monop[0]
        monop_expr = monop[1]
        line, col = self.get_line_loc_info(src, loc)
        return ast_class(monop_expr, line, col)

    def create_binops(self, src, loc, token):
        token = token[0]
        line, col = self.get_line_loc_info(src, loc)

        # As PyParsing returns all terms on the same precedence level at once,
        # it is required to split the resulting tokens into binary operations.
        while len(token) > 1:
            [left, binop_class, right] = token[:3]
            token = [binop_class(left, right, line, col)] + token[3:]
        return token

    def parse(self, input_str):
        return self.grammar.parseString(input_str, parseAll=True)[0]
