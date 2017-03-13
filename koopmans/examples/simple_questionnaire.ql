form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: money
    if (hasSoldHouse) {
  "Did you enter a loan?"
    hasMaintLoan: boolean
    "Did you enter a loan?"
        hasMaintLoan: money = hasBoughtHouse
}

}