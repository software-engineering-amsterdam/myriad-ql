form taxOfficeExample {

    if (hasSoldHouse) {

    }

    boolean hasSoldHouse("Did you sell a house in 2010");
    boolean hasBoughtHouse("Did you buy a house in 2010?");
    boolean hasMaintLoan("Did you enter a loan?");

    if (hasSoldHouse) {
        money sellingPrice("What was the selling price?");
        money privateDebt("Private debts for the sold house:");
        money valueResidue("Value residue:") : (sellingPrice - privateDebt);
    }
}