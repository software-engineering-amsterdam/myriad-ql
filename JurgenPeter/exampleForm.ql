form BoxHouseOwning {
    dropdown: "Dropdown Boolean Question" boolean
    compBool: "Copies Dropdown at the bottom" boolean = dropdown
    genericInt: "Make this integer >= 5?" integer

    if compInt >= 10 {
        condBool: "Bool Question in conditional:" boolean
        condIntB: "This integer question Should be slider:" integer
    }
    else {
        x: "Conditional Boolean" boolean
        if x {
            y: "Last Nesting Level?" boolean
            if !y {
                z: "Then this is the last boolean" boolean
            }
        }
    }

    integerspinbox: "This input should be spinbox (5, 10)" integer

    compInt: "This integer doubles the other integer" integer = 2 * genericInt
    decimalA: "This is a decimal input field" decimal
    decimalB: "This is another decimal input field" decimal
    decimalQ: "This divides one decimal by another" decimal = decimalA / decimalB

    defaultText: "The Default is blue" integer
    greenText: "Here the default is overwritten" integer
    otherFont: "Times New Roman Font" integer
    bigBoldFont: "This text is big and bold" integer

    if !dropdown {
        hideWhenTrue: "Hidden when dropdown is true (boolean page)" boolean
    }

}
