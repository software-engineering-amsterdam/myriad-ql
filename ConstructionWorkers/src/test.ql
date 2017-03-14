form taxOfficeExample {

    if (hasSoldHouse) {

    }

    boolean hasBoughtHouse("Did you buy a house in 2010?");
    boolean hasSoldHouse("Did you sell a house in 2010");
    boolean hasMaintLoan("Did you enter a loan?");

    if (hasSoldHouse) {
        money sellingPrice("What was the selling price?");
        if (hasBoughtHouse) {
            money buyingPrice("Buying price:");
        }
        money privateDebt("Private debts for the sold house:");
        money valueResidue("Value residue:") : (sellingPrice - privateDebt);
    }
}