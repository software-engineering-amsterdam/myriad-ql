from grammar.ql import QL


def start():
    parser = QL()
    print("Hello Start")
    parseTree = parser.parse('''
    form taxOfficeExample {
        "Did you sell a house in 2010?"
            hasSoldHouse: boolean
        "Did you buy a house in 2010?"
            hasBoughtHouse: boolean
        "Did you enter a loan?"
            hasMaintLoan: boolean

        if (hasSoldHouse) {
            "What was the selling price?"
            sellingPrice: money
            "Private debts for the sold house:"
            privateDebt: money
            "Value residue:"
            valueResidue: money =
                (sellingPrice + privateDebt)
        }
        if (hasSoldHouse + apples) {
            "What was the selling price?"
            sellingPrice: money
            "Private debts for the sold house:"
            privateDebt: money
            "Value residue:"
            valueResidue: money =
                (sellingPrice + privateDebt)
        }
        "Is this questions being printed?"
            isPrinted: boolean
    }
    ''')
    print(parseTree.asXML())

if __name__ == '__main__':
    start()
