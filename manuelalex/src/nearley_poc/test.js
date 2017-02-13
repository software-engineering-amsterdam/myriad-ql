let jspmImport = require('../utils/JspmImport.js');

const grammar = require('./ql.js');

let nearley;
let test1, test2;


//$npm install -g nearley
//$nearleyc ql.ne -o ql.js

module.exports = class test {

    constructor() {
        Promise.all([
            jspmImport('nearley'),
            jspmImport('./src/nearley_poc/test/TestStrings.js')
        ]).then((imports) => {
            [nearley, {test1, test2}] = imports;
        }).then(this._run.bind(this)).catch((error) => {
            // todo handle import errors correctly
            console.log(error);
        });
    }

    _run() {
        let result;
        // Create a Parser object from our grammar.
        try{
            const parser = new nearley.Parser(grammar.ParserRules, grammar.ParserStart);
            result = parser.feed(test1).results;

        } catch (parseError){
            console.log(`Error at character ${parseError.offset}`);
        } finally{
            console.log(`Result: ${JSON.stringify(result)}`);
        }
    }
};






