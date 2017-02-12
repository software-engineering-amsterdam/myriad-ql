form taxOfficeExample {
  boolean hasSoldHouse("Did you sell a house in 2010?") : BOOL;
  boolean hasBoughtHouse("Did you buy a house in 2010?") : BOOL;
  boolean hasMaintLoan("Did you enter a loan?") : BOOL;

  if (hasSoldHouse) {
  int sellingPrice("What was the selling price?") : INT;
  int privateDebt("Private debts for the sold house:") : INT;
  int hasMaintLoan("Value residue:") : (sellingPrice - privateDebt);
  }
}