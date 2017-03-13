from ql.lexer import QLLexer
from ql.parser import QLParser
from ui.visitor import UIVisitor

if __name__ == '__main__':
    data = """form taxOfficeExample {
                "Did you sell a house in 2010?"
                    hasSoldHouse: boolean

                if (hasSoldHouse) {
                    "What was the selling price?"
                        sellingPrice: decimal
                    "Private debts for the sold house:"
                        privateDebt: decimal
                    "Value residue:"
                        valueResidue: decimal =
                            (sellingPrice - privateDebt)
                }

                "Did you buy a house in 2010?"
                    hasBoughtHouse: boolean

                if (hasBoughtHouse) {
                    "What was the buying price?"
                        buyingPrice: decimal
                }

                "House change:"
                    houseTrading: boolean =
                        (hasSoldHouse && hasBoughtHouse)

                if (houseTrading) {
                    "Total difference:"
                        priceDifference: decimal =
                            (valueResidue - buyingPrice)
                }
            }"""

    lex = QLLexer()
    par = QLParser(lex.tokens)
    ast = par.parser.parse(data, lexer=lex.lexer)
    ui_generator = UIVisitor(ast)
    ui = ui_generator.execute()
    ui.run()
