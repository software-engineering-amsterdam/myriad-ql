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
		"Private debts frot he sold shouse:"
			privateDebt: money
		"Value residue:"
			valueResidue: money =
				(sellingPrice - privateDebt)
	}
}