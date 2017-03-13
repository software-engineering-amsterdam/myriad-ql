form taxOfficeExample {
	"Did you sell a house in 2010?"
		hasSoldHouse: boolean
	"Did you buy a house in 2010?"
		hasBoughtHouse: boolean
    "Did you buy or sell a house in 2010?"
		hasDoneEither: boolean = hasSoldHouse || hasBoughtHouse
	"How big of a loan did you request??"
		hasMaintLoan: integer

	if (hasSoldHouse) {
		"What was the selling price?"
			sellingPrice: money
		"Private debts for the sold house:"
			privateDebt: money
        if (hasBoughtHouse) {
            "What was the buying price?"
                buyingPrice: money
        }
		"Value residue:"
			valueResidue: money =
				(sellingPrice - privateDebt)
	} else {
        "Else block statement price?"	blockState: money
	}
		"This is a very nice placeholder" placeHolder1: money
		"This is a very nice placeholder too!" placeHolder2: money
		"This is a THE BEST placeholder" placeHolder3: money
		"This is the actual BEST placeholder" placeHolder3: money
}