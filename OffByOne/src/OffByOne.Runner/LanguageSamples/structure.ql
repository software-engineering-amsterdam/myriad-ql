form questionnaire { 
    ""Is this a question?""
        existentialism: boolean

    ""When will this be finished?""
        deadline: date

    ""What is your favourite decimal number?""
        favDecimal: decimal

    ""Answer the following equation: 5 + 2 * 3""
        equation: integer

    ""How much money do you have?"" 
        account: money

    ""String two:""
        stringtwo: string

    if(equation == 11){
        ""Do you like pie?""
            pie: boolean (existentialism)
    } else {
        ""Any comments?""
            comments: string
    }
}
