/**
 * Created by Manuel on 12/02/2017.
 */


export let test1 =
    'form taxOfficeExample{\n' +
    'question `Did you sell a house in 2010?`\n' +
    'hasSoldHouse: boolean\n' +
    'question `Did you buy a house in 2010?`\n' +
    'hasBoughtHouse: boolean\n' +
    'question `Did you enter a loan?`\n' +
    'hasMaintLoan: boolean\n' +
    'if (hasSoldHouse) {\n' +
    'question `What was the selling price?`\n' +
    'sellingPrice: money\n' +
    'question `What was the private debts for the sold house?`\n' +
    'privateDebt: money\n' +
    'answer `Value residue:`\n' +
    'valueResidue: money = (sellingPrice-privateDebt)\n' +
    '\n}' +
    '\n}';

export let test2 = 'form y{\n \n}}';

export let test3 = `form taxOfficeExample {question 'Q1' sellingPrice: money\n
question 'Q2'\n soldPrice: money
answer 'A2'\n
A2: money = (Q1-Q2)\n
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