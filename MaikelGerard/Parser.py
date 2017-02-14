import pyparsing as pp
import AST


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
    TYPE_BOOL = pp.oneOf("true false")
    TYPE_INT = pp.Word(pp.nums)

    def __init__(self):
        # Enable caching of parsing logic.
        pp.ParserElement.enablePackrat()
        pp.quotedString.setParseAction(pp.removeQuotes)

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
        ).addParseAction(AST.QuestionNode)

        block = pp.Forward()
        if_cond = self.KW_IF + self.embrace(expression) +\
            self.embrace(block, "curly")

        conditional = pp.Group(if_cond + pp.Optional(
            self.KW_ELSE + self.embrace(block, "curly"))).addParseAction(AST.ConditionalNode)

        block << pp.Group(pp.OneOrMore(question | conditional)).addParseAction(AST.BlockNode)

        form = self.KW_FORM + self.TYPE_VAR + self.embrace(block, "curly")
        form_block = pp.Group(form).addParseAction(AST.FormNode)
        return pp.OneOrMore(form_block).addParseAction(AST.QuestionnaireAST)

    def define_expression(self):
        # Define expressions including operator precedence. Based on:
        # http://pythonhosted.org/pyparsing/pyparsing-module.html#infixNotation

        var_types = (
            self.TYPE_BOOL.addParseAction(AST.BoolNode) |
            self.TYPE_VAR.addParseAction(AST.VarNode) |
            self.TYPE_DECIMAL.addParseAction(AST.DecimalNode) |
            self.TYPE_INT.addParseAction(AST.IntNode))

        return pp.infixNotation(var_types, [
            (pp.oneOf('- + !'), 1, pp.opAssoc.RIGHT, AST.MonOpNode),
            (pp.oneOf('* /'), 2, pp.opAssoc.LEFT, self.create_binops),
            (pp.oneOf('+ -'), 2, pp.opAssoc.LEFT, self.create_binops),
            (pp.oneOf('< <= > >='), 2, pp.opAssoc.LEFT, self.create_binops),
            (pp.oneOf('== !='), 2, pp.opAssoc.LEFT, self.create_binops),
            (pp.Literal('&&'), 2, pp.opAssoc.LEFT, self.create_binops),
            (pp.Literal('||'), 2, pp.opAssoc.LEFT, self.create_binops),
        ])

    @staticmethod
    def create_binops(src, loc, token):
        token = token[0]
        while len(token) > 1:
            token = [AST.BinOpNode(src, loc, token[:3])] + token[3:]
        return token

    def parse(self, input_str):
        return self.grammar.parseString(input_str)[0]

if __name__ == '__main__':
    form1 = """
    form taxOfficeExample {
        "Did you sell a house in 2010?" hasSoldHouse: boolean
        "Did you buy a house in 2010?" hasBoughtHouse: boolean
        "Did you enter a loan?" hasMaintLoan: boolean

        if (true == false * 100 * 5 * ! 8.0) {
            "What was the selling price?" sellingPrice: money
            "Private debts for the sold house:" privateDebt: money
            "Value residue:" valueResidue: money = (sellingPrice -
            privateDebt)
        }
    }
    """
    parser = QuestionnaireParser()
    AST = parser.parse(form1)
    print AST.print_ast()
    #print QuestionnaireAST(parser.parse(form1)).root
