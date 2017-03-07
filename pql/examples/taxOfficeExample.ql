form taxOfficeExample {
	"Did you sell a house in 2010?"
		hasSoldHouse: boolean
	"Did you buy a house in 2010?"
		hasBoughtHouse: boolean
	"How big of a loan did you request?"
		hasMaintLoan: money

	if (hasSoldHouse) {
		"What was the selling price?"
			sellingPrice: money
		"Private debts for the sold house:"
			privateDebt: money
		"Value residue:"
			valueResidue: money =
				(sellingPrice - privateDebt)
	}
}