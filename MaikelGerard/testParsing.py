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
var = pp.Word(pp.alphas)
decimal = pp.Regex("([0-9]+\.[0-9]*)|([0-9]*\.[0-9]+)")
integer = pp.Word(pp.nums)

# Based on: http://pythonhosted.org/pyparsing/pyparsing-module.html#infixNotation
expr_types = pp.Combine(var | decimal | integer)
operator = pp.infixNotation(expr_types,
       [
       (pp.oneOf('- !'), 1, pp.opAssoc.RIGHT),
       (pp.oneOf('* /'), 2, pp.opAssoc.LEFT),
       (pp.oneOf('+ -'), 2, pp.opAssoc.LEFT),
       (pp.oneOf('< <= > >='), 2, pp.opAssoc.LEFT),
       (pp.oneOf('== !='), 2, pp.opAssoc.LEFT),
       (pp.oneOf('&&'), 2, pp.opAssoc.LEFT),
       (pp.oneOf('||'), 2, pp.opAssoc.LEFT),
       ])

expression = operator

pp.quotedString.setParseAction(pp.removeQuotes)
question = pp.Group(var + colon + pp.quotedString + types + \
    pp.Optional(brace_left + expression + brace_right))

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
ex6 = '(40 + 30)'    # Very slow...
ex7 = '((40 + 30))'  # Super slow...
print question.parseString(ex1)
print form.parseString(ex2)

print operator.parseString(ex3)
print operator.parseString(ex4)
print operator.parseString(ex5)
print operator.parseString(ex6)
print operator.parseString(ex7)
