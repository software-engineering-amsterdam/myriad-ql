form taxOfficeExample {
    "In order to start this form, please write START in the box."
        start: string

    if (start == "START") {
        "Did you sell a house last year"
            hasSoldHouse: boolean

        if (hasSoldHouse) {
            "What was the selling price?"
                sellingPrice: decimal
            
            "Private debts for the sold house:"
                privateDebt: decimal

            "Value residue:"
                valueResidue: decimal = (sellingPrice - privateDebt) -
                (sellingPrice - privateDebt) * 0.25
            
            "Did you buy a house in 2010?"
                hasBoughtHouse: boolean

            if (hasBoughtHouse) {
                "What was the buying price?"
                    buyingPrice: decimal
            }

            "House change:"
                houseTrading: boolean = (hasSoldHouse && hasBoughtHouse)

            if (houseTrading) {
                "Benefit obtained"
                    benefit: decimal = valueResidue - (buyingPrice - buyingPrice * 0.16)
            }
        } else {
             "No need to fill this form" finished: boolean = true
        }
    }
}
