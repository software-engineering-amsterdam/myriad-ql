form taxOfficeExample {

    if (hasSoldHouse) {

    }

    boolean hasBoughtHouse("Did you buy a house in 2010?");
    boolean hasMaintLoan("Did you enter a loan?");

    if (hasSoldHouse) {
        boolean hasSoldHouse("Did you sell a house in 2010");
        money sellingPrice("What was the selling price?");
        if (hasBoughtHouse) {
            money valueResid("Value resid:");
        }
        money privateDebt("Private debts for the sold house:");
        money valueResidue("Value residue:") : (aj - privateDebt);
    }
}