form taxOfficeExample {
	"Did you sell a house in 2010?"
		hasSoldHouse: boolean
	"Did you buy a house in 2010?"
		hasBoughtHouse: boolean 
    "Did you buy or sell a house in 2010?"
		hasDoneEither: boolean = hasSoldHouse || hasBoughtHouse

	if (hasSoldHouse) {
		"If block statement price" sellingPrice: money
        if (hasBoughtHouse) {
            "Value residue:" valueResidue: money = (sellingPrice * 3)
        }
	} else {
        "Else block statement price?"	blockState: money
	}		
}