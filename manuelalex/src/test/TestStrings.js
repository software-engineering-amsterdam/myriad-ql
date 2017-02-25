/**
 * Created by Manuel on 12/02/2017.
 */


export let test1 =
`form taxOfficeExample{
    if (hasSoldHouse && myHouse) {
        question 'What was the selling price?'
        sellingPrice: money
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