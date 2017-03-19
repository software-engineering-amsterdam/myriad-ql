form errorForm {

    "First declaration" question: boolean

    // Error: redefined operand.
    "Second declaration" question: string
    
    // Warning: duplicated label.
    "Second declaration" question2: string
    
    // Error: undefined variable.
    "Undefined variable" assignation1: decimal = undefined + assignation3
    
    // Error: wrong type assigned.
    "Wrong assignation type" assignation2: string = 3.0
    
    // Error: cyclic dependency.
    "Cyclic dependency" assignation3: decimal = 2.0 + assignation1

    // Error: invalid operands.
    // Error: invalid condition.
    if ("string" + "string") {
        "Invalid condition and invalid operands" condition1: decimal
    }
}
