import datetime
import decimal
import pyparsing as pp

from QL import AST


class QuestionnaireParser(object):
    # Define the tokens used for parsing the input.
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
    MONEY_TYPE = pp.Keyword("money").suppress()
    DECIMAL_TYPE = pp.Keyword("decimal").suppress()
    STRING_TYPE = pp.Keyword("string").suppress()
    DATE_TYPE = pp.Keyword("date").suppress()

    BOOLEAN = (pp.Keyword("true").setParseAction(lambda _: True) |
               pp.Keyword("false").setParseAction(lambda _: False))
    INTEGER = pp.Word(pp.nums)
    VARIABLE = pp.Word(pp.alphas, pp.alphanums + "_")
    DECIMAL = pp.Regex("(\d+\.\d*)|(\d*\.\d+)")
    STRING = pp.quotedString
    DATE = pp.Regex("([0][1-9])|([1-3][0-9])-[0-9]{2}-[0-9]{4}")

    def __init__(self):
        # Enable caching of parsing logic.
        pp.ParserElement.enablePackrat()

        # Add parse actions to create AST nodes from parse results.
        self.add_parse_actions()
        self.TYPE_NAMES = (
            self.BOOLEAN_TYPE ^ self.INTEGER_TYPE ^ self.MONEY_TYPE ^
            self.DECIMAL_TYPE ^ self.STRING_TYPE ^ self.DATE_TYPE
        )
        self.TYPES = (
            self.BOOLEAN ^ self.INTEGER ^ self.DECIMAL ^
            self.STRING ^ self.DATE ^ self.VARIABLE
        )

        # Create the grammar incrementally to simplify unit test creation.
        self.expression = self.define_expression()
        self.question = self.define_question()
        self.block = pp.Forward()
        self.conditional = self.define_conditional()

        self.grammar = self.define_grammar()

    def add_parse_actions(self):
        def create_int(src, loc, tokens):
            del src, loc
            return int(tokens[0])

        def create_decimal(src, loc, tokens):
            del src, loc
            return decimal.Decimal(tokens[0])

        def create_date(src, loc, tokens):
            del src, loc
            return datetime.datetime.strptime(tokens[0], "%d-%m-%Y").date()

        self.BOOLEAN_TYPE.setParseAction(self.create_node(AST.BoolTypeNode))
        self.INTEGER_TYPE.setParseAction(self.create_node(AST.IntTypeNode))
        self.MONEY_TYPE.setParseAction(self.create_node(AST.MoneyTypeNode))
        self.DECIMAL_TYPE.setParseAction(self.create_node(AST.DecimalTypeNode))
        self.STRING_TYPE.setParseAction(self.create_node(AST.StringTypeNode))
        self.DATE_TYPE.setParseAction(self.create_node(AST.DateTypeNode))

        self.BOOLEAN.setParseAction(self.create_node(AST.BoolNode))
        self.INTEGER.setParseAction(create_int, self.create_node(AST.IntNode))
        self.VARIABLE.setParseAction(self.create_node(AST.VarNode))
        self.DECIMAL.setParseAction(
            create_decimal, self.create_node(AST.DecimalNode))
        self.STRING.setParseAction(
            pp.removeQuotes, self.create_node(AST.StringNode))
        self.DATE.setParseAction(create_date, self.create_node(AST.DateNode))

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
        return self.L_BRACE + arg + self.R_BRACE

    def curly_embrace(self, arg):
        return self.L_CURLY + arg + self.R_CURLY

    def define_question(self):
        def create_question_grammar():
            """
            Use this function to prevent double parseActions, as the question
            grammar is re-used in the comp_question grammar definition.
            """
            return self.STRING + self.VARIABLE + self.COLON + self.TYPE_NAMES

        def create_question_node(question_class):
            def parse_question(src, loc, token):
                line, col = self.get_line_loc_info(src, loc)
                args = token.asList() + [line, col]
                # Make from the question and identifier nodes strings as
                # expected elsewhere in the program.
                args[0] = args[0].val
                args[1] = args[1].val
                return question_class(*args)
            return parse_question

        question = create_question_grammar()
        question.setParseAction(create_question_node(AST.QuestionNode))

        comp_question = create_question_grammar() + self.IS + \
            self.round_embrace(self.expression)
        comp_question.setParseAction(
            create_question_node(AST.ComputedQuestionNode))

        return comp_question | question

    def define_conditional(self):
        def create_if_grammar():
            """ Again used to prevent double parseActions. """
            return self.IF + self.round_embrace(self.expression) + \
                self.curly_embrace(self.block)

        if_cond = create_if_grammar()
        if_cond.setParseAction(self.create_node(AST.IfNode))

        if_else_cond = create_if_grammar() + self.ELSE + \
            self.curly_embrace(self.block)
        if_else_cond.setParseAction(self.create_node(AST.IfElseNode))

        return if_else_cond | if_cond

    def define_grammar(self):
        self.block << pp.Group(
            pp.OneOrMore(self.question | self.conditional)
        ).setParseAction(self.create_node(AST.BlockNode))

        normal_var = pp.Word(pp.alphas, pp.alphanums + "_")
        form = self.FORM + normal_var + \
            self.curly_embrace(self.block)
        return form.addParseAction(self.create_node(AST.FormNode))

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
