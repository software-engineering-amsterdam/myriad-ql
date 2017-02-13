import pyparsing as pp


class Token(object):
    def __init__(self, s, loc, token, var_type):
        if var_type == "int":
            self.val = int(token)
        elif var_type == "dec":
            self.val = float(token)
        elif var_type == "bool":
            self.val = bool(token)
        else:
            self.val = token

        self.var_type = var_type
        print self.val
        print self.var_type

        self.line = pp.lineno(loc, s)
        self.col = pp.col(loc, s)

    #def __str__(self):
    #    return str(self.val)


class QuestionnaireParser(object):
    LIT_IS = pp.Literal("=").suppress()
    LIT_COLON = pp.Literal(":").suppress()
    LIT_L_CURLY = pp.Literal("{").suppress()
    LIT_R_CURLY = pp.Literal("}").suppress()
    LIT_L_BRACE = pp.Literal("(").suppress()
    LIT_R_BRACE = pp.Literal(")").suppress()

    KW_FORM = pp.Keyword("form").setParseAction(lambda s, l, t: "@" + t[0])
    KW_IF = pp.Keyword("if").setParseAction(lambda s, l, t: "@" + t[0])
    KW_ELSE = pp.Keyword("else").setParseAction(lambda s, l, t: "@" + t[0])

    TYPE_NAME = pp.oneOf("boolean int string date decimal money")
    TYPE_VAR = pp.Word(pp.alphas, pp.alphanums + "_")
    TYPE_DECIMAL = pp.Regex("([0-9]+\.[0-9]*)|([0-9]*\.[0-9]+)")
    TYPE_BOOL = pp.Regex("true|false")
    TYPE_INT = pp.Word(pp.nums)

    def __init__(self):
        # Enable caching of parsing logic.
        pp.ParserElement.enablePackrat()
        pp.quotedString.setParseAction(pp.removeQuotes)
        pp.quotedString.setParseAction(self.create_token)

        # Set to parse action obtain token location
        self.TYPE_NAME.setParseAction(self.create_token)
        self.TYPE_VAR.setParseAction(
            lambda s, l, t: self.create_token(s, l, t, var_type="var"))
        self.TYPE_DECIMAL.setParseAction(
            lambda s, l, t: self.create_token(s, l, t, var_type="dec"))
        self.TYPE_INT.setParseAction(
            lambda s, l, t: self.create_token(s, l, t, var_type="int"))
        self.TYPE_BOOL.setParseAction(
            lambda s, l, t: self.create_token(s, l, t, var_type="bool"))

        self.grammar = self.define_grammar()

    def embrace(self, arg, brace_type="round"):
        if brace_type == "round":
            return self.LIT_L_BRACE + arg + self.LIT_R_BRACE
        elif brace_type == "curly":
            return self.LIT_L_CURLY + arg + self.LIT_R_CURLY

    def define_grammar(self):
        expression = self.define_expression()

        question = pp.Group(
            pp.quotedString + self.TYPE_VAR + self.LIT_COLON +
            self.TYPE_NAME + pp.Optional(self.LIT_IS + self.embrace(expression))
        )

        block = pp.Forward()
        if_cond = self.KW_IF + self.embrace(expression) +\
            self.embrace(block, "curly")

        conditional = pp.Group(if_cond + pp.Optional(
            self.KW_ELSE + self.embrace(block, "curly")))

        block << pp.Group(pp.OneOrMore(question | conditional))

        form = self.KW_FORM + self.TYPE_VAR + self.embrace(block, "curly")
        form_block = pp.Group(form)
        return pp.OneOrMore(form_block)

    def define_expression(self):
        # Define expressions including operator precedence. Based on:
        # http://pythonhosted.org/pyparsing/pyparsing-module.html#infixNotation
        expression_types = pp.Combine(
            self.TYPE_VAR | self.TYPE_DECIMAL | self.TYPE_INT | self.TYPE_BOOL
        )

        return pp.infixNotation(expression_types, [
            (pp.oneOf('- !'), 1, pp.opAssoc.RIGHT),
            (pp.oneOf('* /'), 2, pp.opAssoc.LEFT, self.convert_expr),
            (pp.oneOf('+ -'), 2, pp.opAssoc.LEFT, self.convert_expr),
            (pp.oneOf('< <= > >='), 2, pp.opAssoc.LEFT, self.convert_expr),
            (pp.oneOf('== !='), 2, pp.opAssoc.LEFT, self.convert_expr),
            (pp.Literal('&&'), 2, pp.opAssoc.LEFT, self.convert_expr),
            (pp.Literal('||'), 2, pp.opAssoc.LEFT, self.convert_expr),
        ])

    def convert_expr(self, s, l, t):
        # Converting ParseResult to List for easier manipulation.
        expr = t[0].asList()
        binary_expr = self.to_binary_expr(expr)

        return pp.ParseResults(binary_expr)

    def to_binary_expr(self, expr):
        # Group together every pair of sub-expressions.
        if len(expr) <= 2:
            return expr
        return [expr[:2] + self.to_binary_expr(expr[2:])]

    @staticmethod
    def create_token(s, l, t, var_type="str"):
        return Token(s, l, t[0], var_type)

    def parse(self, input_str):
        return self.grammar.parseString(input_str)
