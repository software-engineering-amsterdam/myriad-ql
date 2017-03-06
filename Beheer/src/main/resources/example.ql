form Box1HouseOwning {
    hasSoldHouse: "Did you sell a house in 2010?" boolean
    hasBoughtHouse: "Did you by a house in 2010?" boolean
    hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean
    if (hasSoldHouse) {
        sellingPrice: "Price the house was sold for:" money
        privateDebt: "Private debts for the sold house:" money
        if (sellingPrice > privateDebt) {
            valueResidue: "Value residue:" money(sellingPrice - privateDebt)
        }
    }
    text1: "Text1" string
    text2: "Text2" string(text1)
    date1: "Date1" date
    date2: "Date2" date(date1)
}