module QL.Samples.Form exposing (goodSamples)


goodSamples : List String
goodSamples =
    [ goodExample1
    , goodExample2
    , goodExample3
    , goodExample4
    , goodExample5
    ]


goodExample1 : String
goodExample1 =
    """form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
}
"""


goodExample2 : String
goodExample2 =
    """form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean

  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
}
"""


goodExample3 : String
goodExample3 =
    """form taxOfficeExample {
    if (hasSoldHouse) {
      "What was the selling price?"
        sellingPrice: money
    }
  }
  """


goodExample4 : String
goodExample4 =
    """form taxOfficeExample {
  "Value residue:"
    valueResidue: money =
      (1 - 2)
}"""


goodExample5 : String
goodExample5 =
    """form taxOfficeExample {
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
    "Value residue:"
      valueResidue: money =
        (sellingPrice - privateDebt)
  }
}"""
