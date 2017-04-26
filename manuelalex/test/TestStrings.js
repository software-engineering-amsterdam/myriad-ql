/**
 * Created by Manuel on 03/04/2017.
 */


export const validStrings = {
    1: 'form y{}',
    2: `form taxOfficeExample {
            question 'Did you sell a house in 2010?'
            hasSoldHouse: boolean
           `,
    '2.1': `form taxOfficeExample {
            question 'Did you sell a house in 2010?'
            hasSoldHouse: money
           `,
    '2.2': `form taxOfficeExample {
            question 'Did you sell a house in 2010?'
            hasSoldHouse: date
           `,
    '2.3': `form taxOfficeExample {
            question 'Did you sell a house in 2010?'
            hasSoldHouse: integer
           `,
    '2.4': `form taxOfficeExample {
            question 'Did you sell a house in 2010?'
            hasSoldHouse: string
           `,
    '2.5': `form taxOfficeExample {
            question 'Did you sell a house in 2010?'
            hasSoldHouse: decimal
           `,
    3: `form taxOfficeExample {
            question 'Did you sell a house in 2010?'
            hasSoldHouse: money
            answer 'Value residue:'
            valueResidue: money = (1000-privateDebt)
           `,
    '3.1': `form taxOfficeExample {
            question 'Did you sell a house in 2010?'
            hasSoldHouse: money
            answer 'Value residue:'
            valueResidue: boolean = (privateDebt == 1000)
           `,
    '3.2': `form taxOfficeExample {
            question 'Did you sell a house in 2010?'
            hasSoldHouse: money
            answer 'Value residue:'
            valueResidue: integer = (1000-privateDebt)
           `,
    4: `form taxOfficeExample {
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
        }`,
    5: `form taxOfficeExample {
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
            } else {
                question 'What was the selling price?'
                sellingPrice: money 
            }
        }`,
    6: `form taxOfficeExample {
            question 'Did you sell a house in 2010?'
            hasSoldHouse: boolean
            question 'Did you buy a house in 2010?'
            hasBoughtHouse: boolean
            question 'Did you enter a loan?'
            hasMaintLoan: string
            if ((hasSoldHouse >= hasBoughtHouse) && (hasSoldHouse == hasBougthtHouse)) {
                question 'What was the selling price?'
                sellingPrice: money
                question 'What was the private debts for the sold house?'
                privateDebt: money
                answer 'Value residue:'
                valueResidue: money = (sellingPrice-privateDebt)
            } else {
                question 'What was the selling price?'
                sellingPrice: money 
            }
        }`,
    7: `form taxOfficeExample {
            question 'Did you sell a house in 2010?'
            hasSoldHouse: boolean
            question 'Did you buy a house in 2010?'
            hasBoughtHouse: boolean
            question 'Did you enter a loan?'
            hasMaintLoan: string
            if ((hasSoldHouse >= hasBoughtHouse) && (hasSoldHouse == hasBoughtHouse)) {
                 question 'Did you enter a loan?'
                hasMaintLoan: string
            } else {
                question 'What was the selling price?'
                sellingPrice: money 
            }
            if(hasBoughtHouse){
                answer 'Value residue:'
                valueResidue: money = (sellingPrice-privateDebt)
            }
        }`,
    8: `form taxOfficeExample {
            if (true) {
                question 'What was the selling price?'
                sellingPrice: money
                }
        }`,
    9: `form taxOfficeExample {
            if (false) {
                question 'What was the selling price?'
                sellingPrice: money
                }
        }`,
    10: `form taxOfficeExample {
            if (1 < 2) {
                question 'What was the selling price?'
                sellingPrice: money
                }
        }`,
    11: `form taxOfficeExample {
            question 'What is your name?'
            name: string
            question 'Did you sell a house?'
            hasSoldHouse: boolean
            if ((2 > 1) && hasSoldHouse) {
                question 'What was the selling price?'
                sellingPrice: money
                question 'For what price did you buy the house?'
                buyPrice: money
                answer 'Value residue:'
                valueResidue: money = (sellingPrice-buyPrice)
                }
        }`,
    12: `form QLExample {
        question 'What is your name?'
        name: string
        question 'Did you sell a house?'
        hasSoldHouse: boolean
        question 'Did you bought a house?'
        hasBoughtHouse: boolean
        if(hasSoldHouse){
            question 'For what price did you sell the house?'
            sellPrice: money
            question 'When did you sell the house?'
            sellDate: date
        }
        
     }     
    `
};

export const invalidStrings = {
    1: 'forn y{}',
    2: 'form y{{',
    3:  `form y{
            question When did you sell the house?
            hasSoldHouse: date
    }`
};