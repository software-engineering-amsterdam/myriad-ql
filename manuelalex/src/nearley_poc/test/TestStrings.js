/**
 * Created by Manuel on 12/02/2017.
 */


export let test1 = 'form taxOfficeExample{\n' +
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
    'valueResidue: money = (sellingPrice - privateDebt)\n' +
    '\n}' +
    '\n}';

export let test2 = 'form y{\n \n}}';