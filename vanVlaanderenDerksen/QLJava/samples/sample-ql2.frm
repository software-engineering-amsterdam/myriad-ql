form
	"dit is een test" buyingPrice MONEY
	"dit is een test2" buyingPrice2 MONEY
	
	"dit is een boolean" booleanId BOOLEAN
	
	"What was the \\"selling price\\"?" sellingPrice MONEY = (5 - 3) / 2 + 5
	
	if (true)
		"asdsa" vraag2 BOOLEAN
		"asdsasdsada" vraag3 BOOLEAN
	else if (booleanId)
		"nog een vraag" vraag4 BOOLEAN
	else if (booleanId && true)
		"nog een vraag 4" vraag5 BOOLEAN
	endif
endform