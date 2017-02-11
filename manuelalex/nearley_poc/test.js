var grammar = require("./ql.js");
var nearley = require("nearley");

// Create a Parser object from our grammar.
var p = new nearley.Parser(grammar.ParserRules, grammar.ParserStart);

// Parse something
//p.feed("#00ff00");
var result = p.feed("form taxOfficeExample{\n" +
    "Did you sell a house in 2010?\n" +
    "hasSoldHouse: boolean\n" +
    "Did you buy a house in 2010?\n" +
    "hasBoughtHouse: boolean\n" +
    "Did you enter a loan?\n" +
    "hasMaintLoan: boolean\n" +
    "if (hasSoldHouse) {\n" +
    "What was the selling price?\n" +
    "sellingPrice: money\n" +
    "What was the private debts for the sold house?\n" +
    "privateDebt: money\n"+
    "Value residue:\n" +
    "valueResidue: money = (sellingPrice - privateDebt)\n" +
    "\n}" +
    "\n}").results;
console.log(result);

// p.results --> [ ["sum", "1", "1"] ]

//$npm install -g nearley
//$nearleyc ql.ne -o ql.js


