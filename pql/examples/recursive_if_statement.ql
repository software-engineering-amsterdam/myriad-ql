form taxOfficeExample {
	"Did you sell a house in 2010?"
		hasSoldHouse: boolean

	if (hasSoldHouse) {
		if (hasBoughtHouse) {
		"What was the selling price?"
			sellingPrice: money
	    }
	}
}