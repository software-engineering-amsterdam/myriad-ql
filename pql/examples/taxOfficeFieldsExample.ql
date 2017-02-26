form taxOfficeExample {
	"q1?"
		hasSoldHouse: boolean
	"q2?"
		hasBoughtHouse: boolean

	if (hasSoldHouse) {
		"What was the selling price?"
			sellingPrice: money
		"Value residue:"
			valueResidue: money =
				(sellingPrice - privateDebt)
	}

	"q3?"
		hasSoldHouse: boolean
}