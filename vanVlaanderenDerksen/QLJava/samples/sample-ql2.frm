form taxOfficeExample
	"Did you sell a house in 2010?" hasSoldHouse BOOLEAN
	"Did you buy a house in 2010?" hasBoughtHouse BOOLEAN
	"Did you enter a loan?" hasMaintLoan BOOLEAN
    
	if(hasSoldHouse)
		"What was the selling price?" sellingPrice MONEY
		"Private debts for the sold house:" privateDebt MONEY
		"Value residue:" valueResidue MONEY = (sellingPrice - privateDebt)
	endif
endform