form
	"Did you sell a house in 2010?" hasSoldHouse STRING
	"Did you buy a house in 2010?" hasBoughtHouse INTEGER
	"Did you enter a loan?" hasMaintLoan BOOLEAN
	
	"Value residue:" valueResidue MONEY = (sellingPrice - privateDebt)
endform