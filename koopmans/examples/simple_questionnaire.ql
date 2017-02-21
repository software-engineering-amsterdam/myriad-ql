form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
    "Did you buy a house in 2010?"
        hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean
    "Dij oiasjd asd "
        aap: string

  if (hasSoldHouse ) {
    "What was the selling price?"
      sellingPrice: money
      if (hasBoughtHouse || hasMaintLoan){
        "Private debts for the sold house:"
        privateDebt: money
        }
    "Value residue:"
      valueResidue: money =
        (sellingPrice + aap)
  }

}