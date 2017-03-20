form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: money

"Did you buy222 a house in 2010?"
    ssss: money = ((hasBoughtHouse * 2) * 2)
}