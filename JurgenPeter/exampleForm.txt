form BoxHouseOwning {
    hasSoldHouse: "Did you sell a house in 2010?" boolean
    hasBoughtHouse: "Did you by a house in 2010?" boolean = hasSoldHouse
    sellingPrice: "How much did you sell the house for?" integer

    if sellingPriceB >= 10 {
        privateDebt: "Private debts for the sold house:" decimal
        valueResidue: "Value residue:" decimal
    }
    else {
        x: "in de else?" boolean
        if x {
            y: "geneste if?" boolean
        }
    }

    sellingPriceB: "How much did you sell the house for? (B)" integer = 2 * sellingPrice
}
