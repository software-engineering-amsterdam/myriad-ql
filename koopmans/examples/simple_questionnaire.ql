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
    "Private debts for the sold house:"
      privateDebt: money
          "JOEHOE"
            aap: string
    "Value residue:"
      valueResidue: money =
        (sellingPrice - privateDebt)
        "Value res2idue:"
              xx: string =
                (aap)
  }

}