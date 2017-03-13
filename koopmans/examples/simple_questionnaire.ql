form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean
    "Did you enter a loan?"
        hasMaintLoan: integer = 55 + sellingPrice

  if (hasBoughtHouse && aap) {
    "What was the selling price?"
      sellingPrice: money = (aap + privateDebt)
      if ((hasMaintLoan > 5) * true) {
    "Private debts for the sold house:"
      privateDebt: money = valueResidue
    "Value residue:"
      valueResidue: money =
        (-5 / hasMaintLoan)
        }
  }

}