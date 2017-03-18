/**
 * Created by Manuel on 12/02/2017.
 */


export let test1 =
`form taxOfficeExample{
    question 'Did you sell a house in 2010?'
    hasSoldHouse: boolean
    question 'Did you buy a house in 2010?'
    hasBoughtHouse: boolean
    question 'Did you enter a loan?'
    hasMaintLoan: string
    if (hasSoldHouse >= hasBoughtHouse) {
        question 'What was the selling price?'
        sellingPrice: money
        question 'What was the private debts for the sold house?'
        privateDebt: money
        answer 'Value residue:'
        valueResidue: money = (sellingPrice-privateDebt)
    }
     }`;

export let test2 =
'form y{'
'}';

export let test3 =
`form taxOfficeExample {
    question 'Q1'
    sellingPrice: money
    question 'Q2'
    soldPrice: money
    answer 'A2'
    A2: money = (Q1-Q2)
}`;

// TODO sentences should be alphanumeric

export let test4 =
`form taxOfficeExample{\n
question 'Q1'\n
sellingPrice: money\n
question 'Q2'\n
privateDebt: money\n
answer 'A2'\n
A2: money = (Q1-Q)\n
\n}`;

export let test5 = 'put it on the floor';


export let test6 =
    `form taxOfficeExample{
    question 'Did you sell a house in 2010?'
    hasSoldHouse: boolean
    question 'Did you buy a house in 2010?'
    hasBoughtHouse: boolean
    question 'Did you enter a loan?'
    hasMaintLoan: string
    if (hasSoldHouse) {
        question 'What was the selling price?'
        sellingPrice: money
        question 'What was the private debts for the sold house?'
        privateDebt: date
        answer 'Value residue:'
        valueResidue: money = (hasSoldHouse-hasMaintLoan)
    } else {question 'Secondlabel' sellingPrice: money}
     }`;


export let test7 =
    `form taxOfficeExample{
    question 'Did you sell a house in 2010?'
    hasSoldHouse: boolean
    question 'Did you buy a house in 2010?'
    hasBoughtHouse: boolean
    question 'Did you enter a loan?'
    hasMaintLoan: string
    if (!(hasSoldHouse && hasBoughtHouse) && (hasSoldHouse && hasBoughtHouse)) {
        question 'What was the selling price?'
        sellingPrice: money
        question 'What was the private debts for the sold house?'
        privateDebt: date
        answer 'Value residue:'
        valueResidue: money = (hasSoldHouse-hasMaintLoan)
    } else {question 'Secondlabel' sellingPrice: money}
     }`;