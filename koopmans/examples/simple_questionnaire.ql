form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: money
    if (hasSoldHouse){
     "Did you buy a house in 2010?"
        xxx: money = hasBoughtHouse
        }
}