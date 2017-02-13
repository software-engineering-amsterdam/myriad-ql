def test_parser(parser):
    # Define full grammar and expression grammar.

    print "=== Testing form parsing ==="
    parse_form(parser)
    print "=== Testing expression parsing ==="
    parse_expr(parser.define_expression())


def parse_form(parser):
    # Forms to test
    form1 = """
    form Box1HouseOwning {
        hasSoldHouse: "Did you sell a house in 2010?" boolean
        hasBoughtHouse: "Did you buy a house in 2010?" boolean
        hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean

        if (hasSoldHouse + newPrice + 4 + 23) {
            sellingPrice: "Price the house was sold for:" money
            privateDebt: "Private debts for the sold house:" money
            valueResidue: "Value residue:" money(300 * 100 - 20 * 10 * (25 - 3))
            if (newPrice > 20) {
                privateDebt: "Private debts for the sold house:" money
            }
            else {
                privateDebt: "Private debts for the sold house:" money
            }
        }
    }
    """
    hoi = parser.parse(form1)
    print hoi


def parse_expr(expr):
    # Testing expression parsing
    ex1 = '30 + 239.0 - 239 * 239'
    ex2 = '30'
    ex3 = 'newPrice * 1000'
    ex4 = '(40 * 30)'
    ex5 = '((40 + 30))'
    ex6 = '5 + 10 / 234 / 1 - 20 && (19 * 12) || 2'
    ex7 = '10 && 20 || 2'
    ex8 = '!5 + !10'
    ex9 = '!(5 + 10 / 234 / 1 - !20 && (19 * 12) || 2)'
    ex10 = "!!8"
    ex11 = "300 * 100 - 20 * 10 * (25 - 3)"

    # Test examples. NOTE: Example 1 cannot be done for now.
    print expr.parseString(ex1, parseAll=True)[0]
    print expr.parseString(ex2, parseAll=True)
    print expr.parseString(ex3, parseAll=True)[0]
    print expr.parseString(ex4, parseAll=True)[0]
    print expr.parseString(ex5, parseAll=True)[0]
    print expr.parseString(ex6, parseAll=True)[0]
    print expr.parseString(ex7, parseAll=True)[0]
    print expr.parseString(ex8, parseAll=True)[0]
    print expr.parseString(ex9, parseAll=True)[0]
    print expr.parseString(ex10, parseAll=True)[0]
    print expr.parseString(ex11, parseAll=True)[0]
