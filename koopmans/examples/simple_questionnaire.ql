form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean
    "Did you enter a loan?"
        hasMaintLoan: boolean

  if (5 + true) {
    "What was the selling price?"
      sellingPrice: money
      if (!false) {
    "Private debts for the sold house:"
      privateDebt: money = 8
    "Value residue:"
      valueResidue: money =
        (privateDebt * 10)
        }
  }

}