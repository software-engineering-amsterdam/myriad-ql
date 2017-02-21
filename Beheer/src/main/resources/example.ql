form Box1HouseOwning {
    hasSoldHouse: "Did you sell a house in 2010?" boolean(10)
    hasSoldHouse: "Did you sell a house in 2010?" boolean("Foo")
    hasBoughtHouse: "Did you by a house in 2010?" boolean(FOO)
    hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean(True)
    if (hasSoldHouse && sellingPrice > 100) {
        if (hasBoughtHouse && valueResidue) {
            sellingPrice: "Price the house was sold for:" money(sdkfjgasjkdf)
        }
        privateDebt: "Private debts for the sold house:" money(ARG + 10)
        valueResidue: "Value residue:" money(sellingPrice - privateDebt+True)
    }
}
