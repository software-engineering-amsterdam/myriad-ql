form taxOfficeExample {
    boolean hasSoldHouse("Did you sell a house in 2010");
    boolean hasBoughtHouse("Did you buy a house in 2010?");
    boolean hasMaintLoan("Did you enter a loan?");

    if (hasSoldHouse) {
        int sellingPrice("What was the selling price?");
        int privateDebt("Private debts for the sold house:");
        int hasMaintLoan("Value residue:") : (sellingPrice - privateDebt);
    }
}