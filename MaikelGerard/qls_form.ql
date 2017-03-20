form taxOfficeExample {
    "Did you sell a house in 2010?"
        hasSoldHouse: boolean
    "Did you buy a house in 2010?"
        hasBoughtHouse: boolean
    "Did you enter a loan?"
        hasMaintLoan: boolean

    if (hasSoldHouse) {
        "What was the selling price?"
            sellingPrice: decimal
        "Private debts for the sold house:"
            privateDebt: integer
        "Value residue:"
            valueResidue: decimal = (sellingPrice - privateDebt)
    }
}