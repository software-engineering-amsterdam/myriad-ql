form taxOfficeExample {
	"Did you sell a house in 2010?"
		hasSoldHouse: boolean
	"Did you buy a house in 2010?"
		hasBoughtHouse: boolean
    "Did you buy or sell a house in 2010?"
		hasDoneEither: boolean = hasSoldHouse || hasBoughtHouse
	"How big of a loan did you request??"
		hasMaintLoan: integer
}