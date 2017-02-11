const grammar = require('./ql.js');
const nearley = require('nearley');


//TO RUN EXECUTE THE FOLLOWING COMMANDS
//$npm install -g nearley
//$nearleyc ql.ne -o ql.js
//$node test.js


// Create a Parser object from our grammar.
const p = new nearley.Parser(grammar.ParserRules, grammar.ParserStart);

// Parse something
//p.feed("#00ff00");
const result = p.feed('form taxOfficeExample{\n' +
    'Did you sell a house in 2010?\n' +
    'hasSoldHouse: boolean\n' +
    'Did you buy a house in 2010?\n' +
    'hasBoughtHouse: boolean\n' +
    'Did you enter a loan?\n' +
    'hasMaintLoan: boolean\n' +
    'if (hasSoldHouse) {\n' +
    'What was the selling price?\n' +
    'sellingPrice: money\n' +
    'What was the private debts for the sold house?\n' +
    'privateDebt: money\n'+
    'Value residue:\n' +
    'valueResidue: money = (sellingPrice - privateDebt)\n' +
    '\n}' +
    '\n}').results;



var form = new Form(toString(result[0][1]));
var firstQuestion   = toString(result[0][4][0][0][0][0][0]);
var firstPropName   = toString(result[0][4][0][0][0][3][0][0]);
var firstPropValue  = toString(result[0][4][0][0][0][6]);

var firstQuestion = new Question(firstQuestion, firstPropName, firstPropValue);
form.addQuestion(firstQuestion);
console.log(form.name);
console.log(firstQuestion.question);

console.log(form.getQuestions());


//TODO: Functions below need to be put in other files

function Form (name) {
    this.name = name;
    this.questions;
    this.addQuestion = function(item) {
        questions = [item];

    }
    this.getQuestions = function(){
        return questions;
    }

    // this.name = function() {
    //     return this.name;
    // };
}

function Question (question, propertyName, propertyValue) {
    this.question = question;
    this.propertyName = propertyName;
    this.propertyValue = propertyValue;
}

function toString(charArray){
    return charArray.join().split(",").join("")
}
