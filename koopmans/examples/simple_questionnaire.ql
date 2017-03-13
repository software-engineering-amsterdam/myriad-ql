form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: money
    if (!hasSoldHouse) {
  "Did you enter a l1oan?"
    hasMaintLoan: boolean
    "Did you enter a lo2an?"
        hasMaintLoan: money = hasBoughtHouse
            "Did you enter a loa3n?"
                xxx: money = hasMaintLoan
}

}