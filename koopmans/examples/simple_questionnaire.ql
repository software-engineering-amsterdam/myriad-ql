form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: money = 2
    if (!hasSoldHouse){
     "Did you buy a house in 2010?"
        xx: money = hasBoughtHouse * 2
    }
"Did you buy222 a house in 2010?"
    ssss: money
}