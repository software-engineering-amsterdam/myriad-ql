form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean
    "Did you enter a loan?"
        hasMaintLoan: boolean

  if (5 + 100) {
    "What was the selling price?"
      sellingPrice: money
      if (!hasMaintLoan) {
    "Private debts for the sold house:"
      privateDebt: boolean = hasSoldHouse
    "Value residue:"
      valueResidue: money =
        (-sellingPrice + 5)
        }
  }

}