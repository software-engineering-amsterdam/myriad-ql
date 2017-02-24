from parser.ql import *
from ast.ast import *

if __name__ == '__main__':
    ql_test = 'form taxOfficeExample { "Did you sell a house in 2010?" hasSoldHouse: boolean "Did you buy a house in 2010?" hasBoughtHouse: boolean "Did you enter a loan?" hasMaintLoan: boolean  if (hasSoldHouse || (!hasSoldHouse && hasBoughtHouse)) { "What was the selling price?" sellingPrice: money "Private debts for the sold house:" privateDebt: money "Value residue:" valueResidue: money = (sellingPrice - privateDebt) } "Is this questions being printed?" isPrinted: boolean }'
    __parsed = QL.form.parseString(ql_test)
    print(__parsed[0])
    # eval_test = 'if (hasSoldHouse || (!hasSoldHouse && hasBoughtHouse)) { "What was the selling price?" sellingPrice: money "Private debts for the sold house:" privateDebt: money "Value residue:" valueResidue: money = (sellingPrice - privateDebt) }'
    # __parsed = QL.conditional.parseString(eval_test)
    # print(__parsed)
