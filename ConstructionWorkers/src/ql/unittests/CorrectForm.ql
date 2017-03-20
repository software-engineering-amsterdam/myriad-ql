form taxOfficeExample {
    boolean hasSoldHouse("Did you sell a house in 2010");
    boolean hasBoughtHouse("Did you buy a house in 2010?");
    boolean hasMaintLoan("Did you enter a loan?");

    if (hasSoldHouse) {
        integer sellingPrice("What was the selling price?");
        integer privateDebt("Private debts for the sold house:");
        integer valueResidue("Value residue:") : (sellingPrice - privateDebt);
    }
}