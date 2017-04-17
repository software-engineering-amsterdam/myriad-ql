form errorForm {
    // Parser error: Illegal character.
    @

    // Warning: Duplicated label.
    "Duplicated label" question1: boolean
    "Duplicated label" question2: string

    // Error: reference to undefined questions.
    "Reference to undefined questions" assignation1: 
        decimal = (question1 && undefined)

    // Error: duplicate question declarations with different types.
    "Duplicate question declarations with different types" question1: string
    
    // Error: conditions that are not of the type boolean
    if (question1) {
        "Conditions that are not of the type boolean" question3: boolean   
    }

    // Error: wrong type assigned.
    "Wrong assignation type" assignation2: string = 3.0
    
    // Error: operands of invalid type to operators
    "operands of invalid type to operators" condition1: decimal = true + question1

    // Error: cyclic dependencies between questions
    "Cyclic dependency 1" assignation4: decimal = 2.0 + assignation5
    "Cyclic dependency 2" assignation5: decimal = 3.0 + assignation6
    "Cyclic dependency 3" assignation6: decimal = 4.0 + assignation4
}
