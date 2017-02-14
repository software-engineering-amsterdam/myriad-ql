form
	"Did you sell a house in 2010?" hasSoldHouse boolean
	"Did you buy a house in 2010?" hasBoughtHouse boolean
	"Did you enter a loan?" hasMaintLoan boolean

	// Comment hier
	if (hasSoldHouse)
		"What was the \\"selling price\\"?" sellingPrice money
		"Private debts for the sold house:" privateDebt money
		/*
		 * Nog een comment
		 */
		"Value residue:" valueResidue money = (sellingPrice - privateDebt)
	else if (anotherTest)
		"Adsafdsfsda?" sellingGasd money
	else
		"Did you enter a loan?" hasMaintLoan boolean
		
		if (hasMaintLoan)
			"What was the \\"selling price\\"?" sellingPrice money
			"Private debts for the sold house:" privateDebt money
		endif
	endif
endform