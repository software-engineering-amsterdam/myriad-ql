form Box1HouseOwning {
    enterMoney: "Please enter some money value" money
    hasSoldHouse: "Did you sell a house in 2010?" boolean
    hasBoughtHouse: "Did you by a house in 2010?" boolean
    hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean
    if (hasSoldHouse) {
        sellingPrice: "Price the house was sold for:" money
        privateDebt: "Private debts for the sold house:" money
        if (sellingPrice > privateDebt) {
            valueResidue: "Value residue:" money(sellingPrice - privateDebt)
        }
    } else {
        eQuestion: "No house sold, boring, please explain yourself:" string
    }
}