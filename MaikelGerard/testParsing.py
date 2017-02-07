import pyparsing as pp


class QuestionnaireParser(object):
    LIT_COLON = pp.Literal(":").suppress()
    LIT_QUOTE = pp.Literal('"')
    LIT_L_CURLY = pp.Literal("{").suppress()
    LIT_R_CURLY = pp.Literal("}").suppress()
    LIT_L_BRACE = pp.Literal("(").suppress()
    LIT_R_BRACE = pp.Literal(")").suppress()

    KW_FORM = pp.Keyword("form")
    KW_IF = pp.Keyword("if")
    KW_ELSE = pp.Keyword("else")

    TYPE_NAME = pp.oneOf("boolean int string date decimal money")
    TYPE_VAR = pp.Word(pp.alphas, pp.alphanums + "_")
    TYPE_DECIMAL = pp.Regex("([0-9]+\.[0-9]*)|([0-9]*\.[0-9]+)")
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
            self.TYPE_VAR + self.LIT_COLON + pp.quotedString +
            self.TYPE_NAME + pp.Optional(self.embrace(expression))
        )

        block = pp.Forward()
        if_cond = self.KW_IF + self.embrace(expression) +\
            self.embrace(block, "curly")
        conditional = if_cond + pp.Optional(
            self.KW_ELSE + self.embrace(block, "curly")
        )
        block << pp.Group(pp.OneOrMore(question | conditional))

        return self.KW_FORM + self.TYPE_VAR + self.embrace(block, "curly")

    def define_expression(self):
        # Define expressions including operator precedence. Based on:
        # http://pythonhosted.org/pyparsing/pyparsing-module.html#infixNotation
        expression_types = pp.Combine(
            self.TYPE_VAR | self.TYPE_DECIMAL | self.TYPE_INT
        )

        return pp.infixNotation(expression_types, [
            (pp.oneOf('- !'), 1, pp.opAssoc.RIGHT),
            (pp.oneOf('* /'), 2, pp.opAssoc.LEFT),
            (pp.oneOf('+ -'), 2, pp.opAssoc.LEFT),
            (pp.oneOf('< <= > >='), 2, pp.opAssoc.LEFT),
            (pp.oneOf('== !='), 2, pp.opAssoc.LEFT),
            (pp.oneOf('&&'), 2, pp.opAssoc.LEFT),
            (pp.oneOf('||'), 2, pp.opAssoc.LEFT),
        ])

    def parse(self, input_str):
        return self.grammar.parseString(input_str)

if __name__ == '__main__':
    # Define full grammar and expression grammar.
    parser = QuestionnaireParser()
    expr = parser.define_expression()

    # Examples to test.
    ex1 = 'hasSoldHouse: "Did you sell a house in 2010?" boolean'
    ex2 = """
    form Box1HouseOwning {
        hasSoldHouse: "Did you sell a house in 2010?" boolean
        hasBoughtHouse: "Did you buy a house in 2010?" boolean
        hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean

        if (hasSoldHouse + newPrice + 4 + 23) {
            sellingPrice: "Price the house was sold for:" money
            privateDebt: "Private debts for the sold house:" money
            valueResidue: "Value residue:" money(300 * 100)
            if (newPrice > 20) {
                privateDebt: "Private debts for the sold house:" money
            }
            else {
                privateDebt: "Private debts for the sold house:" money
            }
        }
    }
    """
    ex3 = '30 + 239.0 - 239 * 239'
    ex4 = '30'
    ex5 = 'newPrice * 1000'
    ex6 = '(40 + 30)'
    ex7 = '((40 + 30))'

    # Test examples. NOTE: Example 1 cannot be done for now.
    print parser.parse(ex2)
    print expr.parseString(ex3)
    print expr.parseString(ex4)
    print expr.parseString(ex5)
    print expr.parseString(ex6)
    print expr.parseString(ex7)
