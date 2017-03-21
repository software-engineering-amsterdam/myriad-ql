form taxOfficeExample {
    "What is your first name?"
    firstName: string
  "What is your last name?"
    lastName: string
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean
  "What was the bought price?"
    boughtPrice: integer
  "What was the selling price?"
    sellingPrice: integer
  if (hasSoldHouse) {
    "Private debts for the sold house:"
      privateDebt: integer
    "Value residue:"
      valueResidue: money =
        (sellingPrice * boughtPrice - privateDebt)

    "Profit on sale:"
      valueOver: money =
        (privateDebt + boughtPrice)
  }
  "Is this questions being printed?"
    isPrinted: boolean
}
