form taxOfficeExample {
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
    } else {
        "How much would you pay for a house?"
            whishedPrice: decimal
    }

    "House change:"
        houseTrading: boolean =
            (hasSoldHouse && hasBoughtHouse)

    if (houseTrading) {
            "Total difference:"
            priceDifference: decimal =
                (valueResidue - buyingPrice)
    }
    // Trying other stuff.
    "Say something"
        something: string
    if (something == "something") {
        "Yay :D"
            yay: decimal = (2.0 + 3)
    }
}
