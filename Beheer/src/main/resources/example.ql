form Box1HouseOwning {
    enterMoney: "Please enter some money value" money
    hasBoughtHouse: "Did you by a house in 2010?" boolean
    hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean
    hasSoldHouse: "Did you sell a house in 2010?" boolean
    if (hasSoldHouse) {
        dateSelling: "What was the exact date you sold de house" date
        sellingPrice: "Price the house was sold for:" money
        privateDebt: "Private debts for the sold house:" money
        if (sellingPrice > privateDebt) {
            valueResidue: "Value residue:" money(sellingPrice - privateDebt)
            valueResidue2: "Value residue2:" money(valueResidue)
        }
    } else {
        eQuestion: "No house sold, boring, please explain yourself:" string
    }
}