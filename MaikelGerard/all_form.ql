form taxOfficeExample {
    "Did you sell a house in 2010?" hasSoldHouse: boolean
    "Did you buy a house in 2010?" hasBoughtHouse: boolean
    "Did you enter a loan?" hasMaintLoan: integer

    if (hasSoldHouse) {
        "What was the selling price?" sellingPrice: decimal
        "Private debts for the sold house:" privateDebt: integer
        "Value residue:" valueResidue: integer = (sellingPrice -
        privateDebt)
        "AnotherField:" testField: decimal = (100 * 100.0 + 20 / 60.00)
        "AllMath:" expr: boolean =
            ((((100 * 20 + (+20) - (-30)) >= 20)  && (10 / 2 < valueResidue)) ||
             (10 < 50) || (30 <= 20) || !(30 > 40) || true || false)
        "AlwaysFalse:" falseExpr : boolean = (false)
        if (hasBoughtHouse) {
            "HasBoughHouse" var1: integer
        }
        else {
            "HasNotBoughtHouse" var2: boolean
            if (var2) {
                "Test123" var3: boolean
                "Pick a date" var4: date
            }
        }
        "Entry1" str1: string
        "Entry2" str2: string
        "Computed Entry" str3 : string = ("Hallo" + " " + str2 + "!!")
    }
    "Did you enter a loan?" hasMain2tLoan: boolean
}