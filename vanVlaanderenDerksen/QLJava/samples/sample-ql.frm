form
	"Did you sell a house in 2010?" hasSoldHouse BOOLEAN
	"Did you buy a house in 2010?" hasBoughtHouse BOOLEAN
	"Did you enter a loan?" hasMaintLoan BOOLEAN

	// Comment hier
	if (hasSoldHouse)
		"What was the \\"selling price\\"?" sellingPrice MONEY
		"Private debts for the sold house:" privateDebt MONEY
		/*
		 * Nog een comment
		 */
		"Value residue:" valueResidue MONEY = (sellingPrice - privateDebt)
	else if (anotherTest)
		"Adsafdsfsda?" sellingGasd MONEY
	else
		"Did you enter a loan?" hasMaintLoan BOOLEAN
		
		if (hasMaintLoan)
			"What was the \\"selling price\\"?" sellingPrice MONEY
			"Private debts for the sold house:" privateDebt MONEY
		endif
	endif
endform