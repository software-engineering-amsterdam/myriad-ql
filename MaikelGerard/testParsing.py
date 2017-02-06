import pyparsing as pp

example = """
form Box1HouseOwning {
    hasSoldHouse: "Did you sell a house in 2010?" boolean
    hasBoughtHouse: "Did you by a house in 2010?" boolean
    hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean

    if (hasSoldHouse) {
        sellingPrice: "Price the house was sold for:" money
        privateDebt: "Private debts for the sold house:" money
        valueResidue: "Value residue:" money(sellingPrice - privateDebt)
    }
}
"""

# Literal definitions.
colon = pp.Literal(":").suppress()
quote = pp.Literal('"')
curly_left = pp.Literal("{").suppress()
curly_right = pp.Literal("}").suppress()
brace_left = pp.Literal("(").suppress()
brace_right = pp.Literal(")").suppress()

# Keywords definitions.
form_kw = pp.Keyword("form")
if_kw = pp.Keyword("if")
else_kw = pp.Keyword("else")

# Type definitions
types = pp.oneOf("boolean int string date decimal money")

# Operator definitions
operator = ""#pp.("< <= =< ")

var = pp.Word(pp.alphas)
expression = pp.Group(var)
question = pp.Group(var + colon + pp.quotedString + types + pp.Optional(brace_left + expression + brace_right))

block = pp.Forward()
if_cond = if_kw + brace_left + expression + brace_right + curly_left + block + curly_right
conditional = if_cond + pp.Optional(else_kw + curly_left + block + curly_right)
block << pp.Group(pp.OneOrMore(question | conditional))

form = form_kw + pp.Word(pp.alphanums) + curly_left + block + curly_right



# Examples
ex1 = 'hasSoldHouse: "Did you sell a house in 2010?" boolean'
ex2 = """
form Box1HouseOwning {
    hasSoldHouse: "Did you sell a house in 2010?" boolean
    hasBoughtHouse: "Did you by a house in 2010?" boolean
    hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean

    if (hasSoldHouse) {
        sellingPrice: "Price the house was sold for:" money
        privateDebt: "Private debts for the sold house:" money
        valueResidue: "Value residue:" money
    }
}
"""
print question.parseString(ex1)
print form.parseString(ex2)