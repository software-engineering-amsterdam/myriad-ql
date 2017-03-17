form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean

  if (hasSoldHouse) {
    "What was the selling price?"
      sellingPrice: money
    "What was the selling price?"
      sellingPrice: money
    "What was the selling price?"
      sellingPrice: integer
    "Private debts for the sold house:"
      privateDebt: money
    "Value residue:"
      valueResidue: money =
        (sellingPrice - privateDebt)

    "Value my selling price:"
      valueOver: money =
        (sellingPrice - overMyValue)
  }
  "Is this questions being printed?"
    isPrinted: boolean
}